package com.besson.endfield.blockentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.ViewerCountManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class ModAbstractContainerBlockEntity extends LootableContainerBlockEntity {
    private DefaultedList<ItemStack> inv = createInventory();
    private final ViewerCountManager stateManager = new ViewerCountManager() {

        @Override
        protected void onContainerOpen(World world, BlockPos pos, BlockState state) {

        }

        @Override
        protected void onContainerClose(World world, BlockPos pos, BlockState state) {

        }

        @Override
        protected void onViewerCountUpdate(World world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount) {
            world.addSyncedBlockEvent(pos, state.getBlock(), 1, newViewerCount);
        }

        @Override
        protected boolean isPlayerViewing(PlayerEntity player) {
            if (!(player.currentScreenHandler instanceof GenericContainerScreenHandler)) {
                return false;
            } else {
                Inventory inventory = ((GenericContainerScreenHandler)player.currentScreenHandler).getInventory();
                return inventory == ModAbstractContainerBlockEntity.this;
            }
        }
    };

    protected ModAbstractContainerBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public DefaultedList<ItemStack> createInventory() {
        return DefaultedList.ofSize(27, ItemStack.EMPTY);
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inv;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inv = list;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, this);
    }

    @Override
    public int size() {
        return 27;
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.inv = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(nbt)) {
            Inventories.readNbt(nbt, this.inv);
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inv);
        }
    }

    @Override
    public void onOpen(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.openContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    @Override
    public void onClose(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.closeContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public void markDirty() {
        super.markDirty();
        if (this.world != null && !this.world.isClient()) {
            this.world.updateListeners(pos, getCachedState(), getCachedState(), Block.NOTIFY_ALL);
        }
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        super.setStack(slot, stack);
        markDirty();
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack result = super.removeStack(slot);
        markDirty();
        return result;
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = super.removeStack(slot, amount);
        markDirty();
        return result;
    }

    @Override
    public void clear() {
        super.clear();
        markDirty();
    }
}
