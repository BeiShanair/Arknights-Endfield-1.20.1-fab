package com.besson.endfield.blockentity.custom.production2;

import com.besson.endfield.block.custom.production2.GearingUnitBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOBlockEntity;
import com.besson.endfield.recipe.InputEntry;
import com.besson.endfield.recipe.custom.GearingUnitRecipe;
import com.besson.endfield.screen.custom.screenHandler.GearingUnitScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;

public class GearingUnitBlockEntity extends BaseIOBlockEntity<GearingUnitRecipe> implements GeoBlockEntity {
    private static final int POWER_PRE_TICK = 10;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public GearingUnitBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GEARING_UNIT, pos, state, 200);
    }

    @Override
    protected PropertyDelegate createPropertyDelegate() {
        return new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> GearingUnitBlockEntity.this.progress;
                    case 1 -> GearingUnitBlockEntity.this.maxProgress;
                    case 2 -> GearingUnitBlockEntity.this.enable ? 1 : 0;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GearingUnitBlockEntity.this.progress = value;
                    case 1 -> GearingUnitBlockEntity.this.maxProgress = value;
                    case 2 -> GearingUnitBlockEntity.this.enable = value == 1;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    @Override
    protected int getPowerCostPerTick() {
        return POWER_PRE_TICK;
    }

    @Override
    protected Direction getFacing(BlockState state) {
        return state.get(GearingUnitBlock.FACING);
    }

    @Override
    protected int getInputSize() {
        return 2;
    }

    @Override
    protected int getOutputSize() {
        return 1;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.gearing_unit");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new GearingUnitScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected Optional<GearingUnitRecipe> getMatchRecipe(World world) {
        SimpleInventory inv = new SimpleInventory(inputInv.size());
        for (int i = 0; i < inputInv.size(); i++) {
            inv.setStack(i, inputInv.getStack(i));
        }
        return world.getRecipeManager()
                .getFirstMatch(GearingUnitRecipe.Type.INSTANCE, inv, world);
    }

    @Override
    protected void craftItem(World world) {
        getMatchRecipe(world).ifPresent(r -> {
            ItemStack result = r.getOutput(world.getRegistryManager());
            outputInv.setStack(0,
                    new ItemStack(result.getItem(), outputInv.getStack(0).getCount() + result.getCount()));

            DefaultedList<InputEntry> recipeInputs = r.getInput();
            boolean[] used = new boolean[inputInv.size()];
            for (InputEntry entry : recipeInputs) {
                for (int i = 0; i < used.length; i++) {
                    ItemStack stack = inputInv.getStack(i);
                    if (!used[i] && entry.getIngredient().test(stack) && stack.getCount() >= entry.getCount()) {
                        inputInv.removeStack(i, entry.getCount());
                        used[i] = true;
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected boolean hasCorrectRecipe(World world) {
        Optional<GearingUnitRecipe> match = getMatchRecipe(world);
        if (match.isPresent()) {
            DefaultedList<InputEntry> recipeInputs = match.get().getInput();
            boolean[] used = new boolean[recipeInputs.size()];
            for (InputEntry entry : recipeInputs) {
                boolean matched = false;
                for (int i = 0; i < used.length; i++) {
                    ItemStack stack = inputInv.getStack(i);
                    if (!used[i] && entry.getIngredient().test(stack) && stack.getCount() >= entry.getCount()) {
                        used[i] = true;
                        matched = true;
                        break;
                    }
                }
                if (!matched) return false;
            }

            ItemStack result = match.get().getOutput(world.getRegistryManager());
            return canOutputAccept(result);
        }

        return false;
    }
}
