package com.besson.endfield.blockentity.custom.production1;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOBlockEntity;
import com.besson.endfield.fluid.ModFluids;
import com.besson.endfield.recipe.InputEntry;
import com.besson.endfield.recipe.custom.RefiningUnitRecipe;
import com.besson.endfield.screen.custom.screenHandler.RefiningUnitScreenHandler;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
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

import java.util.Iterator;
import java.util.Optional;

import static com.besson.endfield.block.ModBlockEntityWithFacing.FACING;

public class RefiningUnitBlockEntity extends BaseIOBlockEntity<RefiningUnitRecipe> implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final int POWER_PRE_TICK = 5;
    public static final long FLUID_CAPACITY = 50000;
    public static final long SEWAGE_CAPACITY = 50000;
    public boolean nowFluidMode = false;

    public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            return FLUID_CAPACITY;
        }

        @Override
        protected void onFinalCommit() {
            markDirty();
            if (world != null) {
                world.updateListeners(pos, getCachedState(), getCachedState(), 3);
            }
        }
    };

    public final SingleVariantStorage<FluidVariant> sewageStorage = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.of(ModFluids.STILL_SEWAGE);
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            return SEWAGE_CAPACITY;
        }

        @Override
        protected void onFinalCommit() {
            markDirty();
            if (world != null) {
                world.updateListeners(pos, getCachedState(), getCachedState(), 3);
            }
        }
    };

    public RefiningUnitBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.REFINING_UNIT, pos, state, 40);
    }
    
    public void changeFluidMode(World world) {
        nowFluidMode = !nowFluidMode;
        Direction facing = getFacing(getCachedState());
        BlockPos left = pos.offset(facing.rotateYCounterclockwise()).up(2);
        BlockPos right = pos.offset(facing.rotateYClockwise()).up(2);
        if (nowFluidMode) {
            world.setBlockState(left, ModBlocks.REFINING_UNIT_SIDE.getDefaultState().with(FACING, facing));
            world.setBlockState(right, ModBlocks.REFINING_UNIT_SIDE.getDefaultState().with(FACING, facing));
            BlockEntity be = world.getBlockEntity(left);
            if (be instanceof RefiningUnitSideBlockEntity adjunct) {
                adjunct.setParentPos(pos);
            }
            BlockEntity be2 = world.getBlockEntity(right);
            if (be2 instanceof RefiningUnitSideBlockEntity adjunct) {
                adjunct.setParentPos(pos);
            }
        }
        if (!nowFluidMode) {
            clearFluidTanks();
            BlockEntity be = world.getBlockEntity(left);
            if (be instanceof RefiningUnitSideBlockEntity adjunct) {
                adjunct.setParentPos(null);
            }
            BlockEntity be2 = world.getBlockEntity(right);
            if (be2 instanceof RefiningUnitSideBlockEntity adjunct) {
                adjunct.setParentPos(null);
            }
            world.removeBlock(left, false);
            world.removeBlock(right, false);
        }
        markDirty();
    }
    
    public void clearFluidTanks() {
        boolean changed = fluidStorage.amount > 0 || sewageStorage.amount > 0;
        fluidStorage.variant = FluidVariant.blank();
        fluidStorage.amount = 0;
        sewageStorage.variant = FluidVariant.blank();
        sewageStorage.amount = 0;
        if (changed) {
            markDirty();
            if (world != null) {
                world.updateListeners(pos, getCachedState(), getCachedState(), 3);
            }
        }
    }
    
    private final Storage<FluidVariant> sidedFluidInterface = new Storage<>() {
        @Override
        public long insert(FluidVariant variant, long maxAmount, TransactionContext tx) {
            if (!nowFluidMode || variant.isBlank()) return 0;
            return fluidStorage.insert(variant, maxAmount, tx);
        }

        @Override
        public long extract(FluidVariant variant, long maxAmount, TransactionContext tx) {
            return sewageStorage.extract(variant, maxAmount, tx);
        }

        @Override
        public Iterator<StorageView<FluidVariant>> iterator() {
            return sewageStorage.iterator();
        }
    };

    @Nullable
    public Storage<FluidVariant> getFluidStorage(Direction side) {
        if (side == null) return null;
        Direction facing = getFacing(getCachedState());
        Direction left = facing.rotateYCounterclockwise();
        Direction right = facing.rotateYClockwise();
        if (side == left || side == right) {
            return sidedFluidInterface;
        }
        return null;
    }

    @Override
    protected PropertyDelegate createPropertyDelegate() {
        return new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> RefiningUnitBlockEntity.this.progress;
                    case 1 -> RefiningUnitBlockEntity.this.maxProgress;
                    case 2 -> RefiningUnitBlockEntity.this.enable ? 1 : 0;
                    case 3 -> (int) RefiningUnitBlockEntity.this.fluidStorage.amount;
                    case 4 -> (int) FLUID_CAPACITY;
                    case 5 -> (int) RefiningUnitBlockEntity.this.sewageStorage.amount;
                    case 6 -> (int) SEWAGE_CAPACITY;
                    case 7 -> RefiningUnitBlockEntity.this.storedPower;
                    case 8 -> RefiningUnitBlockEntity.this.nowFluidMode ? 1 : 0;
                    case 9 -> fluidStorage.variant.isBlank()
                            ? Registries.FLUID.getRawId(Fluids.WATER)
                            : Registries.FLUID.getRawId(fluidStorage.variant.getFluid());
                    case 10 -> sewageStorage.variant.isBlank()
                            ? Registries.FLUID.getRawId(Fluids.WATER)
                            : Registries.FLUID.getRawId(sewageStorage.variant.getFluid());
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                
            }

            @Override
            public int size() {
                return 11;
            }
        };
    }

    @Override
    protected int getPowerCostPerTick() {
        return POWER_PRE_TICK;
    }

    @Override
    protected Direction getFacing(BlockState state) {
        return state.get(FACING);
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
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.refining_unit");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new RefiningUnitScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected Optional<RefiningUnitRecipe> getMatchRecipe(World world) {
        SimpleInventory inv = new SimpleInventory(inputInv.size());
        for (int i = 0; i < inputInv.size(); i++) {
            inv.setStack(i, inputInv.getStack(i));
        }
        return world.getRecipeManager()
                .getFirstMatch(RefiningUnitRecipe.Type.INSTANCE, inv, world);
    }

    @Override
    protected void craftItem(World world) {
        getMatchRecipe(world).ifPresent(r -> {
            ItemStack result = r.getOutput(world.getRegistryManager());
            ItemStack out = outputInv.getStack(0);
            outputInv.setStack(0, new ItemStack(result.getItem(), out.getCount() + result.getCount()));

            InputEntry recipeInput = r.getInput();
            ItemStack stack = inputInv.getStack(0);
            if (recipeInput.ingredient().test(stack) && stack.getCount() >= recipeInput.count()) {
                inputInv.removeStack(0, recipeInput.count());
            }

            if (r.requiresFluid()) {
                try (Transaction tx = Transaction.openOuter()) {
                    long extracted = fluidStorage.extract(r.getRequiredFluid(), r.getRequiredFluidAmount(), tx);
                    if (extracted == r.getRequiredFluidAmount()) {
                        tx.commit();
                    }
                }
            }

            if (r.producesFluid()) {
                try (Transaction tx = Transaction.openOuter()) {
                    sewageStorage.insert(r.getOutputFluid(), r.getOutputFluidAmount(), tx);
                    tx.commit();
                }
            }
        });
    }

    @Override
    protected boolean hasCorrectRecipe(World world) {
        Optional<RefiningUnitRecipe> match = getMatchRecipe(world);

        if (match.isPresent()) {
            RefiningUnitRecipe recipe = match.get();

            InputEntry recipeInput = recipe.getInput();
            boolean matched = false;
            ItemStack stack = inputInv.getStack(0);
            if (recipeInput.ingredient().test(stack) && stack.getCount() >= recipeInput.count()) {
                matched = true;
            }
            if (!matched) return false;

            if (recipe.requiresFluid()) {
                if (fluidStorage.getResource().isBlank() ||
                        !fluidStorage.getResource().equals(recipe.getRequiredFluid()) ||
                        fluidStorage.getAmount() < recipe.getRequiredFluidAmount()) {
                    return false;
                }
            }

            if (recipe.producesFluid()) {
                long spaceLeft = SEWAGE_CAPACITY - sewageStorage.getAmount();
                if (spaceLeft < recipe.getOutputFluidAmount()) {
                    return false;
                }
                if (!sewageStorage.getResource().isBlank() &&
                        !sewageStorage.getResource().equals(recipe.getOutputFluid())) {
                    return false;
                }
            }

            ItemStack result = recipe.getOutput(world.getRegistryManager());
            return canOutputAccept(result);
        }

        return false;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtCompound tankNbt = new NbtCompound();
        tankNbt.put("variant", fluidStorage.variant.toNbt());
        tankNbt.putLong("amount", fluidStorage.amount);
        nbt.put("FluidTank", tankNbt);

        NbtCompound sewageNbt = new NbtCompound();
        sewageNbt.put("variant", sewageStorage.variant.toNbt());
        sewageNbt.putLong("amount", sewageStorage.amount);
        nbt.put("SewageTank", sewageNbt);
        
        nbt.putBoolean("fluidMode", nowFluidMode);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("FluidTank")) {
            NbtCompound tankNbt = nbt.getCompound("FluidTank");
            FluidVariant variant = FluidVariant.fromNbt(tankNbt.getCompound("variant"));
            long amount = tankNbt.getLong("amount");
            fluidStorage.variant = amount == 0 ? FluidVariant.blank() : variant;
            fluidStorage.amount = amount;
        }
        if (nbt.contains("SewageTank")) {
            NbtCompound sewageNbt = nbt.getCompound("SewageTank");
            FluidVariant variant = FluidVariant.fromNbt(sewageNbt.getCompound("variant"));
            long amount = sewageNbt.getLong("amount");
            sewageStorage.variant = amount == 0 ? FluidVariant.blank() : variant;
            sewageStorage.amount = amount;
        }
        
        nowFluidMode = nbt.getBoolean("fluidMode");
    }

    public Text getFluidMode() {
        return Text.translatable(nowFluidMode ? "endfield.fluid_mode.open" : "endfield.fluid_mode.close");
    }
}
