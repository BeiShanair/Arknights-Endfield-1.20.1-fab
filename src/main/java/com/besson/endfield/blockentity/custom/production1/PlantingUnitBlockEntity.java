package com.besson.endfield.blockentity.custom.production1;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.production1.PlantingUnitBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOBlockEntity;
import com.besson.endfield.recipe.custom.PlantingUnitRecipe;
import com.besson.endfield.screen.custom.screenHandler.PlantingUnitScreenHandler;
import com.besson.endfield.util.MultiBlockPlacementUtil;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.SimpleInventory;
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

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.besson.endfield.block.ModBlockEntityWithFacing.FACING;

public class PlantingUnitBlockEntity extends BaseIOBlockEntity<PlantingUnitRecipe> implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final int POWER_PRE_TICK = 20;

    public static final long FLUID_CAPACITY = 50000;

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

    public PlantingUnitBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PLANTING_UNIT, pos, state, 40);
    }

    public void changeFluidMode(World world, PlayerEntity player) {
        Direction facing = getFacing(getCachedState());
        BlockPos[] tankPositions = PlantingUnitBlock.getFluidTankPositions(facing, pos);
        BlockPos left = tankPositions[0];
        BlockPos right = tankPositions[1];

        if (!nowFluidMode) {
            if (!MultiBlockPlacementUtil.isReplaceable(world, left) || !MultiBlockPlacementUtil.isReplaceable(world, right)) {
                player.sendMessage(Text.translatable("endfield.fluid_mode.blocked"), true);
                return;
            }
            nowFluidMode = true;

            world.setBlockState(left, ModBlocks.PLANTING_UNIT_SIDE.getDefaultState().with(FACING, facing));
            world.setBlockState(right, ModBlocks.PLANTING_UNIT_SIDE.getDefaultState().with(FACING, facing));
            if (world.getBlockEntity(left) instanceof PlantingUnitSideBlockEntity adjunct) {
                adjunct.setParentPos(pos);
            }
            if (world.getBlockEntity(right) instanceof PlantingUnitSideBlockEntity adjunct) {
                adjunct.setParentPos(pos);
            }
        } else {
            clearFluidTank();
            nowFluidMode = false;

            if (world.getBlockEntity(left) instanceof PlantingUnitSideBlockEntity adjunct) {
                adjunct.setParentPos(null);
            }
            if (world.getBlockEntity(right) instanceof PlantingUnitSideBlockEntity adjunct) {
                adjunct.setParentPos(null);
            }
            world.removeBlock(left, false);
            world.removeBlock(right, false);
        }
        markDirty();
        if (world != null) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    public void clearFluidTank() {
        boolean changed = fluidStorage.amount > 0;
        fluidStorage.variant = FluidVariant.blank();
        fluidStorage.amount = 0;
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
            return 0;
        }

        @Override
        public Iterator<StorageView<FluidVariant>> iterator() {
            return Collections.emptyIterator();
        }
    };

    @Nullable
    public Storage<FluidVariant> getFluidStorage(Direction side) {
        if (side == null || !nowFluidMode) return null;
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
                    case 0 -> PlantingUnitBlockEntity.this.progress;
                    case 1 -> PlantingUnitBlockEntity.this.maxProgress;
                    case 2 -> PlantingUnitBlockEntity.this.enable ? 1 : 0;
                    case 3 -> PlantingUnitBlockEntity.this.storedPower;
                    case 4 -> (int) PlantingUnitBlockEntity.this.fluidStorage.amount;
                    case 5 -> (int) FLUID_CAPACITY;
                    case 6 -> PlantingUnitBlockEntity.this.nowFluidMode ? 1 : 0;
                    case 7 -> PlantingUnitBlockEntity.this.fluidStorage.variant.isBlank()
                            ? Registries.FLUID.getRawId(Fluids.WATER)
                            : Registries.FLUID.getRawId(PlantingUnitBlockEntity.this.fluidStorage.variant.getFluid());
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
            }

            @Override
            public int size() {
                return 8;
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
        List<PlantingUnitRecipe> matches = world.getRecipeManager()
                .getAllMatches(PlantingUnitRecipe.Type.INSTANCE, inv, world);
        for (PlantingUnitRecipe recipe : matches) {
            if (nowFluidMode != recipe.requiresFluid()) continue;
            if (nowFluidMode && (fluidStorage.variant.isBlank()
                    || !fluidStorage.variant.equals(recipe.getRequiredFluid())
                    || fluidStorage.amount < recipe.getRequiredFluidAmount())) {
                continue;
            }
            return Optional.of(recipe);
        }
        return Optional.empty();
    }

    @Override
    protected void craftItem(World world) {
        getMatchRecipe(world).ifPresent(r -> {
            ItemStack result = r.getOutput(world.getRegistryManager());
            outputInv.setStack(0,
                    new ItemStack(result.getItem(), outputInv.getStack(0).getCount() + result.getCount()));
            inputInv.removeStack(0, 1);

            if (r.requiresFluid()) {
                try (Transaction tx = Transaction.openOuter()) {
                    long extracted = fluidStorage.extract(r.getRequiredFluid(), r.getRequiredFluidAmount(), tx);
                    if (extracted == r.getRequiredFluidAmount()) {
                        tx.commit();
                    }
                }
            }
        });
    }

    @Override
    protected boolean hasCorrectRecipe(World world) {
        Optional<PlantingUnitRecipe> match = getMatchRecipe(world);

        if (match.isPresent()) {
            PlantingUnitRecipe recipe = match.get();

            if (recipe.requiresFluid()) {
                if (fluidStorage.getResource().isBlank() ||
                        !fluidStorage.getResource().equals(recipe.getRequiredFluid()) ||
                        fluidStorage.getAmount() < recipe.getRequiredFluidAmount()) {
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
        nowFluidMode = nbt.getBoolean("fluidMode");
    }

    public Text getFluidMode() {
        return Text.translatable(nowFluidMode ? "endfield.fluid_mode.open" : "endfield.fluid_mode.close");
    }
}
