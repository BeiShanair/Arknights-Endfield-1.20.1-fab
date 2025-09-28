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

public class RefiningUnitSideBlockEntity extends BlockEntity implements SidedInventory, ImplementedInventory {
    private BlockPos parentPos;

    public RefiningUnitSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.REFINING_UNIT_SIDE, pos, state);
    }

    public @Nullable RefiningUnitBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof RefiningUnitBlockEntity entity1) {
            return entity1;
        }
        return null;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        RefiningUnitBlockEntity parent  = getParentBlock();
        if (parent != null) {
            return parent.getItems();
        }
        return DefaultedList.ofSize(0, ItemStack.EMPTY);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        RefiningUnitBlockEntity entity = getParentBlock();
        return entity != null ? entity.getAvailableSlots(side) : new int[0];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        RefiningUnitBlockEntity entity = getParentBlock();
        return entity != null && entity.canInsert(slot, stack, dir);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        RefiningUnitBlockEntity entity = getParentBlock();
        return entity != null && entity.canExtract(slot, stack, dir);
    }

    public void setParentPos(BlockPos parentPos) {
        this.parentPos = parentPos;
        markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (parentPos != null) {
            nbt.putInt("parentX", parentPos.getX());
            nbt.putInt("parentY", parentPos.getY());
            nbt.putInt("parentZ", parentPos.getZ());
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("parentX") && nbt.contains("parentY") && nbt.contains("parentZ")) {
            int x = nbt.getInt("parentX");
            int y = nbt.getInt("parentY");
            int z = nbt.getInt("parentZ");
            this.parentPos = new BlockPos(x, y, z);
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
}
