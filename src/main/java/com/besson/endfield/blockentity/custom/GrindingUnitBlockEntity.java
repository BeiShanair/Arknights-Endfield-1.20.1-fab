package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.ElectrifiableDevice;
import com.besson.endfield.block.custom.GrindingUnitBlock;
import com.besson.endfield.blockentity.ImplementedInventory;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.recipe.InputEntry;
import com.besson.endfield.recipe.custom.GrindingUnitRecipe;
import com.besson.endfield.screen.custom.GrindingUnitScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
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

public class GrindingUnitBlockEntity extends BlockEntity implements SidedInventory, GeoBlockEntity, ExtendedScreenHandlerFactory, ImplementedInventory, ElectrifiableDevice {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
    public static final int INPUT_SLOT1 = 0;
    public static final int INPUT_SLOT2 = 1;
    public static final int OUTPUT_SLOT = 2;

    private static final int[] INPUT_SLOTS = {0, 1};
    private static final int[] OUTPUT_SLOTS = {2};

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 40;

    private int storedPower = 0;
    private static final int POWER_PRE_TICK = 10;
    private boolean isWorking = false;

    public GrindingUnitBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GRINDING_UNIT, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> GrindingUnitBlockEntity.this.progress;
                    case 1 -> GrindingUnitBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GrindingUnitBlockEntity.this.progress = value;
                    case 1 -> GrindingUnitBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void receiveElectricCharge(int amount) {
        this.storedPower = Math.min(storedPower + amount, 100);
    }

    @Override
    public boolean needsPower() {
        return storedPower < POWER_PRE_TICK;
    }

    @Override
    public int getRequiredPower() {
        return POWER_PRE_TICK;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.grinding_unit");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new GrindingUnitScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("progress", progress);
        nbt.putBoolean("isWorking", isWorking);
        nbt.putInt("storedPower", storedPower);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        this.progress = nbt.getInt("progress");
        this.isWorking = nbt.getBoolean("isWorking");
        this.storedPower = nbt.getInt("storedPower");
    }

    public static void tick(World world, BlockPos pos, BlockState state, GrindingUnitBlockEntity entity) {
        if (world.isClient()) return;

        if (entity.isOutputSlotAvailable()) {
            boolean hasRecipe = entity.hasCorrectRecipe(world);
            if (entity.needsPower() || !hasRecipe) {
                entity.isWorking = false;
            } else if (!entity.needsPower() && !entity.isWorking) {
                entity.isWorking = true;
            }
            entity.markDirty();
            world.updateListeners(pos, state, state, 3);

            if (hasRecipe && entity.storedPower >= POWER_PRE_TICK) {
                entity.incrementProgress();
                entity.storedPower -= POWER_PRE_TICK;

                if (entity.hasCraftingFinished()) {
                    entity.craftItem(world);
                    entity.resetProgress();
                }
            } else {
                entity.resetProgress();
            }
        } else {
            entity.resetProgress();
        }
        entity.markDirty();
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private Optional<GrindingUnitRecipe> getMatchingRecipe(World world) {
        SimpleInventory inv = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++) {
            inv.setStack(i, this.getStack(i));
        }
        return world.getRecipeManager()
                .getFirstMatch(GrindingUnitRecipe.Type.INSTANCE, inv, world);
    }

    private void craftItem(World world) {
        Optional<GrindingUnitRecipe> match = getMatchingRecipe(world);
        if (match.isPresent()) {
            ItemStack result = match.get().getOutput(world.getRegistryManager());
            this.setStack(OUTPUT_SLOT,
                    new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));

            DefaultedList<InputEntry> recipeInputs = match.get().getInput();
            boolean[] used = new boolean[this.size() - 1];
            for (InputEntry entry: recipeInputs) {
                for (int i = 0; i < used.length; i++) {
                    ItemStack stack = this.getStack(i);
                    if (!used[i] && entry.getIngredient().test(stack) && stack.getCount() >= entry.getCount()) {
                        this.removeStack(i, entry.getCount());
                        used[i] = true;
                        break;

                    }
                }
            }
        }
    }

    private boolean hasCorrectRecipe(World world) {
        Optional<GrindingUnitRecipe> match = getMatchingRecipe(world);
        if (match.isPresent()) {
            DefaultedList<InputEntry> recipeInputs = match.get().getInput();
            boolean[] used = new boolean[this.size() -1];
            for (InputEntry entry : recipeInputs) {
                boolean matched = false;
                for (int i = 0; i < used.length; i++) {
                    ItemStack stack = this.getStack(i);
                    if (!used[i] && entry.getIngredient().test(stack) && stack.getCount() >= entry.getCount()) {
                        matched = true;
                        used[i] = true;
                        break;
                    }
                }
                if (!matched) return false;
            }
            ItemStack result = match.get().getOutput(world.getRegistryManager());
            return canInsertAmountIntoOutputSlot(result) &&
                    canInsertItemIntoOutputSlot(result.getItem());
        }
        return false;
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void incrementProgress() {
        this.progress++;
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean isOutputSlotAvailable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT).getMaxCount();
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        BlockState state = world != null ? world.getBlockState(pos) : null;
        if (state != null && state.contains(GrindingUnitBlock.FACING)) {
            Direction facing = state.get(GrindingUnitBlock.FACING);
            if (side == facing) {
                return INPUT_SLOTS;
            } else if (side == facing.getOpposite()) {
                return OUTPUT_SLOTS;
            }
        }
        return new int[0];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return slot == INPUT_SLOT1 || slot == INPUT_SLOT2;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return slot == OUTPUT_SLOT;
    }
}
