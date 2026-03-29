package com.besson.endfield.blockentity.custom.resourcing;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.recipe.custom.OreRigRecipe;
import com.besson.endfield.screen.custom.PortableOriginiumRigScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;

public class PortableOriginiumRigBlockEntity extends BaseRigBlockEntity<OreRigRecipe> implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private int maxProgress = 60;

    public PortableOriginiumRigBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PORTABLE_ORIGINIUM_RIG, pos, state, 60);
    }

    @Override
    protected PropertyDelegate createPropertyDelegate() {
        return new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> PortableOriginiumRigBlockEntity.this.progress;
                    case 1 -> PortableOriginiumRigBlockEntity.this.maxProgress;
                    case 2 -> PortableOriginiumRigBlockEntity.this.enable ? 1 : 0;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> PortableOriginiumRigBlockEntity.this.progress = value;
                    case 1 -> PortableOriginiumRigBlockEntity.this.maxProgress = value;
                    case 2 -> PortableOriginiumRigBlockEntity.this.enable = value == 1;
                }
            }

            @Override
            public int size() {
                return 3;
            }
        };
    }

    @Override
    protected int getTier() {
        return 1;
    }

    @Override
    protected int getPowerCostPerTick() {
        return 0;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0,
                state -> this.isWorking
                        ? state.setAndContinue(RawAnimation.begin().thenLoop("working"))
                        : state.setAndContinue(RawAnimation.begin().thenLoop("idle"))));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.portable_originium_rig");
    }
    
    public static void tick(World world, BlockPos pos, BlockState state, PortableOriginiumRigBlockEntity be) {
        if (world.isClient()) return;

        if (!be.getEnable()) {
            be.isWorking = false;
            world.updateListeners(pos, state, state, 3);
            be.markDirty();
            return;
        }

        boolean activeNow = be.hasCorrectRecipe(world);

        if (be.isOutputSlotAvailable()) {
            if (activeNow) {

                be.incrementProgress();
                markDirty(world, pos, state);

                if (be.hasCraftingFinished()) {
                    be.craftItem(world);
                    be.resetProgress();
                }
            } else {
                be.resetProgress();
            }
        } else {
            be.resetProgress();
            be.markDirty();
        }

        if (be.isWorking != activeNow) {
            be.isWorking = activeNow;
            be.markDirty();
            world.updateListeners(pos, state, state, 3);
        }
    }

    @Override
    protected void craftItem(World world) {
        getMatchRecipe(world).ifPresent(recipe -> {
            ItemStack result = recipe.getOutput(world.getRegistryManager());
            ItemStack out = outputInv.getStack(0);
            outputInv.setStack(0, new ItemStack(result.getItem(), out.getCount() + result.getCount()));
        });
    }

    @Override
    protected Optional<OreRigRecipe> getMatchRecipe(World world) {
        SimpleInventory inv = new SimpleInventory(1);
        BlockState belowState = world.getBlockState(this.pos.down());
        ItemStack belowStack = belowState.getBlock().asItem().getDefaultStack();
        inv.setStack(0, belowStack);

        return world.getRecipeManager()
                .getFirstMatch(OreRigRecipe.Type.INSTANCE, inv, world)
                .map(recipe -> (OreRigRecipe) recipe);
    }
    
    @Override
    protected boolean hasCorrectRecipe(World world) {
        return getMatchRecipe(world)
                .map(recipe -> {
                    if (recipe.getTier() > getTier()) return false;
                    return canOutputAccept(recipe.getOutput(world.getRegistryManager()));
                })
                .orElse(false);
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PortableOriginiumRigScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }
}
