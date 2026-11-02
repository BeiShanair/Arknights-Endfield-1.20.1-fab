package com.besson.endfield.blockentity.custom.resourcing;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.recipe.custom.OreRigRecipe;
import com.besson.endfield.screen.custom.screenHandler.HydroMiningRigScreenHandler;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
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
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Optional;

public class HydroMiningRigBlockEntity extends BaseRigBlockEntity<OreRigRecipe> implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final long WATER_CAPACITY = 50000;
    public static final long WATER_COST_PER_CRAFT = 1000;
    private static final int MAX_PROGRESS = 60;

    public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            return WATER_CAPACITY;
        }

        @Override
        protected boolean canInsert(FluidVariant variant) {
            return variant.getFluid() == Fluids.WATER;
        }

        @Override
        protected void onFinalCommit() {
            markDirty();
            if (world != null) {
                world.updateListeners(pos, getCachedState(), getCachedState(), 3);
            }
        }
    };

    public HydroMiningRigBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HYDRO_MINING_RIG, pos, state, MAX_PROGRESS);
    }

    @Override
    protected PropertyDelegate createPropertyDelegate() {
        return new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> HydroMiningRigBlockEntity.this.progress;
                    case 1 -> HydroMiningRigBlockEntity.this.maxProgress;
                    case 2 -> HydroMiningRigBlockEntity.this.enable ? 1 : 0;
                    case 3 -> (int) HydroMiningRigBlockEntity.this.fluidStorage.amount;
                    case 4 -> (int) WATER_CAPACITY;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {

            }

            @Override
            public int size() {
                return 5;
            }
        };
    }

    @Override
    protected int getPowerCostPerTick() {
        return 0;
    }

    @Override
    protected int getTier() {
        return 4;
    }

    @Nullable
    public Storage<FluidVariant> getFluidStorage(Direction side) {
        return fluidStorage;
    }

    @Override
    protected Optional<OreRigRecipe> getMatchRecipe(World world) {
        SimpleInventory inv = new SimpleInventory(1);
        BlockState below = world.getBlockState(this.pos.down());
        ItemStack belowStack = below.getBlock().asItem().getDefaultStack();
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
                    // 无论开采何种矿石，驱动矿机都需要足够的水（固定消耗）
                    if (!hasEnoughWater()) return false;
                    return canOutputAccept(recipe.getOutput(world.getRegistryManager()));
                })
                .orElse(false);
    }

    protected boolean hasEnoughWater() {
        return !fluidStorage.getResource().isBlank()
                && fluidStorage.getResource().getFluid() == Fluids.WATER
                && fluidStorage.getAmount() >= WATER_COST_PER_CRAFT;
    }

    @Override
    protected void craftItem(World world) {
        getMatchRecipe(world).ifPresent(recipe -> {
            ItemStack result = recipe.getOutput(world.getRegistryManager());
            ItemStack out = outputInv.getStack(0);
            outputInv.setStack(0, new ItemStack(result.getItem(), out.getCount() + result.getCount()));

            if (fluidStorage.getResource().getFluid() == Fluids.WATER) {
                try (Transaction tx = Transaction.openOuter()) {
                    long extracted = fluidStorage.extract(FluidVariant.of(Fluids.WATER), WATER_COST_PER_CRAFT, tx);
                    if (extracted == WATER_COST_PER_CRAFT) {
                        tx.commit();
                    }
                }
            }
        });
    }

    public static void tick(World world, BlockPos pos, BlockState state, HydroMiningRigBlockEntity be) {
        if (world.isClient()) return;

        if (!be.getEnable()) {
            be.isWorking = false;
            be.resetProgress();
            world.updateListeners(pos, state, state, 3);
            be.markDirty();
            return;
        }

        boolean canRun = be.isOutputSlotAvailable() && be.hasCorrectRecipe(world);

        if (canRun) {
            be.incrementProgress();
            if (be.hasCraftingFinished()) {
                be.craftItem(world);
                be.resetProgress();
            }
        } else {
            be.resetProgress();
        }

        be.submitTimer++;
        if (be.submitTimer >= SUBMIT_INTERVAL) {
            be.submitTimer = 0;
            be.flushToGlobalStorage(world);
        }

        if (be.isWorking != canRun) {
            be.isWorking = canRun;
            world.updateListeners(pos, state, state, 3);
        }

        be.markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtCompound tankNbt = new NbtCompound();
        tankNbt.put("variant", fluidStorage.variant.toNbt());
        tankNbt.putLong("amount", fluidStorage.amount);
        nbt.put("WaterTank", tankNbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("WaterTank")) {
            NbtCompound tankNbt = nbt.getCompound("WaterTank");
            FluidVariant variant = FluidVariant.fromNbt(tankNbt.getCompound("variant"));
            long amount = tankNbt.getLong("amount");
            fluidStorage.variant = amount == 0 ? FluidVariant.blank() : variant;
            fluidStorage.amount = amount;
        }
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.hydro_mining_rig");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new HydroMiningRigScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
