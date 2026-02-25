package com.besson.endfield.blockentity.custom;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ThermalBankSideBlockEntity extends BlockEntity {
    private BlockPos parentPos;

    public ThermalBankSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.THERMAL_BANK_SIDE, pos, state);
    }

    public @Nullable ThermalBankBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof ThermalBankBlockEntity entity1) {
            return entity1;
        }
        return null;
    }
    
    public void setParentPos(BlockPos parentPos) {
        this.parentPos = parentPos;
        markDirty();
    }

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
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }
}
