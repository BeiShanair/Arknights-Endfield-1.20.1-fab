package com.besson.endfield.blockentity.custom.production2;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOBlockEntity;
import com.besson.endfield.recipe.InputEntry;
import com.besson.endfield.recipe.custom.ForgeOfTheSkyRecipe;
import com.besson.endfield.screen.custom.screenHandler.ForgeOfTheSkyScreenHandler;
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
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Iterator;
import java.util.Optional;

import static com.besson.endfield.block.ModBlockEntityWithFacing.FACING;

public class ForgeOfTheSkyBlockEntity extends BaseIOBlockEntity<ForgeOfTheSkyRecipe> implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final int POWER_PRE_TICK = 50;
    public static final int DEFAULT_CRAFT_TIME = 40;
    public static final long FLUID_CAPACITY = 50000;

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

    private final Storage<FluidVariant> insertOnlyFluidInterface = new Storage<>() {
        @Override
        public long insert(FluidVariant variant, long maxAmount, TransactionContext tx) {
            if (variant.isBlank()) return 0;
            return fluidStorage.insert(variant, maxAmount, tx);
        }

        @Override
        public long extract(FluidVariant variant, long maxAmount, TransactionContext tx) {
            return 0; // 罐内流体不可被外部抽取
        }

        @Override
        public Iterator<StorageView<FluidVariant>> iterator() {
            return fluidStorage.iterator();
        }
    };

    public ForgeOfTheSkyBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FORGE_OF_THE_SKY, pos, state, DEFAULT_CRAFT_TIME);
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
    protected PropertyDelegate createPropertyDelegate() {
        return new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ForgeOfTheSkyBlockEntity.this.progress;
                    case 1 -> ForgeOfTheSkyBlockEntity.this.maxProgress;
                    case 2 -> ForgeOfTheSkyBlockEntity.this.enable ? 1 : 0;
                    case 3 -> ForgeOfTheSkyBlockEntity.this.storedPower;
                    case 4 -> (int) ForgeOfTheSkyBlockEntity.this.fluidStorage.amount;
                    case 5 -> (int) FLUID_CAPACITY;
                    case 6 -> fluidStorage.variant.isBlank()
                            ? Registries.FLUID.getRawId(Fluids.WATER)
                            : Registries.FLUID.getRawId(fluidStorage.variant.getFluid());
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ForgeOfTheSkyBlockEntity.this.progress = value;
                    case 1 -> ForgeOfTheSkyBlockEntity.this.maxProgress = value;
                    case 2 -> ForgeOfTheSkyBlockEntity.this.enable = value == 1;
                }
            }

            @Override
            public int size() {
                return 7;
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
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.forge_of_the_sky");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ForgeOfTheSkyScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected Optional<ForgeOfTheSkyRecipe> getMatchRecipe(World world) {
        SimpleInventory inv = new SimpleInventory(inputInv.size());
        for (int i = 0; i < inputInv.size(); i++) {
            inv.setStack(i, inputInv.getStack(i));
        }
        Optional<ForgeOfTheSkyRecipe> match = world.getRecipeManager()
                .getFirstMatch(ForgeOfTheSkyRecipe.Type.INSTANCE, inv, world);

        int newMax = match.flatMap(ForgeOfTheSkyRecipe::getCraftTime).orElse(DEFAULT_CRAFT_TIME);
        if (newMax != this.maxProgress) {
            this.maxProgress = newMax;
            this.progress = 0;
        }
        return match;
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
        });
    }

    @Override
    protected boolean hasCorrectRecipe(World world) {
        Optional<ForgeOfTheSkyRecipe> match = getMatchRecipe(world);
        if (match.isEmpty()) return false;

        ForgeOfTheSkyRecipe recipe = match.get();

        InputEntry recipeInput = recipe.getInput();
        ItemStack stack = inputInv.getStack(0);
        if (!recipeInput.ingredient().test(stack) || stack.getCount() < recipeInput.count()) {
            return false;
        }

        if (recipe.requiresFluid()) {
            if (fluidStorage.getResource().isBlank() ||
                    !fluidStorage.getResource().equals(recipe.getRequiredFluid()) ||
                    fluidStorage.getAmount() < recipe.getRequiredFluidAmount()) {
                return false;
            }
        }

        return canOutputAccept(recipe.getOutput(world.getRegistryManager()));
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtCompound tankNbt = new NbtCompound();
        tankNbt.put("variant", fluidStorage.variant.toNbt());
        tankNbt.putLong("amount", fluidStorage.amount);
        nbt.put("FluidTank", tankNbt);
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
    }

    public Storage<FluidVariant> getFluidStorage(@Nullable Direction direction) {
        return insertOnlyFluidInterface;
    }
}
