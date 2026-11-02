package com.besson.endfield.blockentity.custom.production2;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.production2.FillingUnitBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOBlockEntity;
import com.besson.endfield.recipe.InputEntry;
import com.besson.endfield.recipe.custom.FillingUnitRecipe;
import com.besson.endfield.screen.custom.screenHandler.FillingUnitScreenHandler;
import com.besson.endfield.util.MultiBlockPlacementUtil;
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
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
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
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.besson.endfield.block.ModBlockEntityWithFacing.FACING;

public class FillingUnitBlockEntity extends BaseIOBlockEntity<FillingUnitRecipe> implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final int POWER_PRE_TICK = 20;
    public static final long FLUID_CAPACITY = 50000;
    public static final int CONVERTIBLE_INPUT_SLOT = 1;

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

    public FillingUnitBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FILLING_UNIT, pos, state, 200);
    }

    public void changeFluidMode(World world, PlayerEntity player) {
        Direction facing = getFacing(getCachedState());
        BlockPos left = FillingUnitBlock.getFluidTankPositions(facing, pos)[0];
        BlockPos right = FillingUnitBlock.getFluidTankPositions(facing, pos)[1];

        if (!nowFluidMode) {
            if (!MultiBlockPlacementUtil.isReplaceable(world, left) || !MultiBlockPlacementUtil.isReplaceable(world, right)) {
                player.sendMessage(Text.translatable("endfield.fluid_mode.blocked"), true);
                return;
            }
            returnConvertibleSlotItems(player);
            nowFluidMode = true;

            world.setBlockState(left, ModBlocks.FILLING_UNIT_SIDE.getDefaultState().with(FACING, facing));
            world.setBlockState(right, ModBlocks.FILLING_UNIT_SIDE.getDefaultState().with(FACING, facing));
            if (world.getBlockEntity(left) instanceof FillingUnitSideBlockEntity adjunct) {
                adjunct.setParentPos(pos);
            }
            if (world.getBlockEntity(right) instanceof FillingUnitSideBlockEntity adjunct) {
                adjunct.setParentPos(pos);
            }
        } else {
            returnConvertibleSlotItems(player);
            clearFluidTank();
            nowFluidMode = false;

            if (world.getBlockEntity(left) instanceof FillingUnitSideBlockEntity adjunct) {
                adjunct.setParentPos(null);
            }
            if (world.getBlockEntity(right) instanceof FillingUnitSideBlockEntity adjunct) {
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

    private void returnConvertibleSlotItems(PlayerEntity player) {
        ItemStack stack = inputInv.removeStack(CONVERTIBLE_INPUT_SLOT);
        if (!stack.isEmpty()) {
            if (!player.giveItemStack(stack)) {
                player.dropItem(stack, false);
            }
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
                    case 0 -> FillingUnitBlockEntity.this.progress;
                    case 1 -> FillingUnitBlockEntity.this.maxProgress;
                    case 2 -> FillingUnitBlockEntity.this.enable ? 1 : 0;
                    case 3 -> FillingUnitBlockEntity.this.storedPower;
                    case 4 -> (int) FillingUnitBlockEntity.this.fluidStorage.amount;
                    case 5 -> (int) FLUID_CAPACITY;
                    case 6 -> FillingUnitBlockEntity.this.nowFluidMode ? 1 : 0;
                    case 7 -> FillingUnitBlockEntity.this.fluidStorage.variant.isBlank()
                            ? Registries.FLUID.getRawId(Fluids.WATER)
                            : Registries.FLUID.getRawId(FillingUnitBlockEntity.this.fluidStorage.variant.getFluid());
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
        return state.get(FillingUnitBlock.FACING);
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
        controllers.add(new AnimationController<>(this, "controller", 0,
                state -> state.setAndContinue(RawAnimation.begin().thenLoop("working"))));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.filling_unit");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FillingUnitScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected Optional<FillingUnitRecipe> getMatchRecipe(World world) {
        SimpleInventory inv = new SimpleInventory(inputInv.size());
        for (int i = 0; i < inputInv.size(); i++) {
            inv.setStack(i, inputInv.getStack(i));
        }
        List<FillingUnitRecipe> matches = world.getRecipeManager()
                .getAllMatches(FillingUnitRecipe.Type.INSTANCE, inv, world);
        for (FillingUnitRecipe recipe : matches) {
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

            DefaultedList<InputEntry> recipeInputs = r.getInput();
            boolean[] used = new boolean[inputInv.size()];
            for (InputEntry entry: recipeInputs) {
                for (int i = 0; i < used.length; i++) {
                    ItemStack stack = inputInv.getStack(i);
                    if (!used[i] && entry.ingredient().test(stack)) {
                        inputInv.removeStack(i, entry.count());
                        used[i] = true;
                        break;
                    }
                }
            }

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
        Optional<FillingUnitRecipe> match = getMatchRecipe(world);
        if (match.isPresent()) {
            FillingUnitRecipe recipe = match.get();
            this.maxProgress = recipe.getProcessingTime();
            DefaultedList<InputEntry> recipeInputs = recipe.getInput();
            boolean[] used = new boolean[recipeInputs.size()];
            for (InputEntry entry: recipeInputs) {
                boolean matched = false;
                for (int i = 0; i < used.length; i++) {
                    ItemStack stack = inputInv.getStack(i);
                    if (!used[i] && entry.ingredient().test(stack) && stack.getCount() >= entry.count()) {
                        matched = true;
                        used[i] = true;
                        break;
                    }
                }
                if (!matched) return false;
            }

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
        this.maxProgress = FillingUnitRecipe.DEFAULT_PROCESSING_TIME;
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
