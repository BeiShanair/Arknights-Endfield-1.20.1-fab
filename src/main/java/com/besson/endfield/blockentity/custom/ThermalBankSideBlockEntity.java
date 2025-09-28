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

public class ThermalBankSideBlockEntity extends BlockEntity implements ImplementedInventory, SidedInventory {
    private BlockPos parentPos;

    public ThermalBankSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.THERMAL_BANK_SIDE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        ThermalBankBlockEntity parent = getParentBlock();
        if (parent != null) {
            return parent.getItems();
        }
        return DefaultedList.ofSize(0, ItemStack.EMPTY);
    }

    public @Nullable ThermalBankBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof ThermalBankBlockEntity entity1) {
            return entity1;
        }
        return null;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        ThermalBankBlockEntity entity = getParentBlock();
        return entity != null ? entity.getAvailableSlots(side) : new int[0];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        ThermalBankBlockEntity entity = getParentBlock();
        return entity != null && entity.canInsert(slot, stack, dir);
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }

    public void setParentPos(BlockPos parentPos) {
        this.parentPos = parentPos;
        markDirty();
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("parentX") && nbt.contains("parentY") && nbt.contains("parentZ")) {
            int x = nbt.getInt("parentX");
            int y = nbt.getInt("parentY");
            int z = nbt.getInt("parentZ");
            parentPos = new BlockPos(x, y, z);
        }
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
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }
}
