package com.besson.endfield.blockentity.custom.depot;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class FluidTankSideBlockEntity extends BlockEntity {
    private BlockPos parentPos;
    public FluidTankSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLUID_TANK_SIDE, pos, state);
    }
    
    public void setParentPos(BlockPos parentPos) {
        this.parentPos = parentPos;
        markDirty();
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
            parentPos = BlockPos.fromLong(nbt.getLong("parentPos"));
        }
    }
    
    public @Nullable FluidTankBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof FluidTankBlockEntity tank) {
            return tank;
        }
        return null;
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}
