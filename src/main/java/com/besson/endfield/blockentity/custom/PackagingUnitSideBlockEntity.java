package com.besson.endfield.blockentity.custom;

import com.besson.endfield.blockentity.ImplementedInventory;
import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

public class PackagingUnitSideBlockEntity extends BlockEntity implements SidedInventory, ImplementedInventory {
    private BlockPos parentPos;

    public PackagingUnitSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PACKAGING_UNIT_SIDE, pos, state);
    }

    public @Nullable PackagingUnitBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof PackagingUnitBlockEntity entity1) {
            return entity1;
        }
        return null;
    }

    public void setParentPos(BlockPos parentPos) {
        this.parentPos = parentPos;
        markDirty();
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        PackagingUnitBlockEntity parent  = getParentBlock();
        if (parent != null) {
            return parent.getItems();
        }
        return DefaultedList.ofSize(0, ItemStack.EMPTY);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (parentPos != null) {
            nbt.putLong("parentPos", parentPos.asLong());
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("parentPos")) {
            this.parentPos = BlockPos.fromLong(nbt.getLong("parentPos"));
        }
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
    public int[] getAvailableSlots(Direction side) {
        PackagingUnitBlockEntity entity = getParentBlock();
        return entity != null ? entity.getAvailableSlots(side) : new int[0];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        PackagingUnitBlockEntity entity = getParentBlock();
        return entity != null && entity.canInsert(slot, stack, dir);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        PackagingUnitBlockEntity entity = getParentBlock();
        return entity != null && entity.canExtract(slot, stack, dir);
    }
}
