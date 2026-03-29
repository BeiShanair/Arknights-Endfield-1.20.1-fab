package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.custom.PlantingUnitBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.recipe.custom.PlantingUnitRecipe;
import com.besson.endfield.screen.custom.PlantingUnitScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;

public class PlantingUnitBlockEntity extends BaseIOBlockEntity<PlantingUnitRecipe> implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final int POWER_PRE_TICK = 20;

    public PlantingUnitBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PLANTING_UNIT, pos, state, 40);
    }

    @Override
    protected PropertyDelegate createPropertyDelegate() {
        return new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> PlantingUnitBlockEntity.this.progress;
                    case 1 -> PlantingUnitBlockEntity.this.maxProgress;
                    case 2 -> PlantingUnitBlockEntity.this.enable ? 1 : 0;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> PlantingUnitBlockEntity.this.progress = value;
                    case 1 -> PlantingUnitBlockEntity.this.maxProgress = value;
                    case 2 -> PlantingUnitBlockEntity.this.enable = value == 1;
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
        return state.get(PlantingUnitBlock.FACING);
    }

    @Override
    protected int getInputSize() {
        return 1;
    }

    @Override
    protected int getOutputSize() {
        return 1;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {}

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
    
    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.planting_unit");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PlantingUnitScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected Optional<PlantingUnitRecipe> getMatchRecipe(World world) {
        SimpleInventory inv = new SimpleInventory(inputInv.size());
        for (int i = 0; i < inputInv.size(); i++) {
            inv.setStack(i, inputInv.getStack(i));
        }
        return world.getRecipeManager()
                .getFirstMatch(PlantingUnitRecipe.Type.INSTANCE, inv, world);
    }
    
    @Override
    protected void craftItem(World world) {
        getMatchRecipe(world).ifPresent(r -> {
            ItemStack result = r.getOutput(world.getRegistryManager());
            outputInv.setStack(0,
                    new ItemStack(result.getItem(), outputInv.getStack(0).getCount() + result.getCount()));
            inputInv.removeStack(0, 1);
        });
    }
    
    @Override
    protected boolean hasCorrectRecipe(World world) {
        Optional<PlantingUnitRecipe> match = getMatchRecipe(world);

        if (match.isPresent()) {
            ItemStack result = match.get().getOutput(world.getRegistryManager());
            return canOutputAccept(result);
        }

        return false;
    }
}
