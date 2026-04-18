package com.besson.endfield.blockentity.custom.logicitis;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class DepotUnloaderSideBlockEntity extends BlockEntity {
    private BlockPos parentPos;

    public DepotUnloaderSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DEPOT_UNLOADER_SIDE, pos, state);
    }

    public void setParentPos(BlockPos pos) {
        this.parentPos = pos;
        markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (parentPos != null) {
            nbt.putLong("parent", parentPos.asLong());
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("parent")) {
            parentPos = BlockPos.fromLong(nbt.getLong("parent"));
        }
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Nullable
    public DepotUnloaderBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity be = world.getBlockEntity(parentPos);
        if (be instanceof DepotUnloaderBlockEntity parent) {
            return parent;
        }
        return null;
    }
}
