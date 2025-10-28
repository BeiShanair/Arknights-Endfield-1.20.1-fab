package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.custom.ProtocolAnchorCorePortBlock;
import com.besson.endfield.blockentity.ImplementedInventory;
import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ProtocolAnchorCorePortBlockEntity extends BlockEntity implements SidedInventory, ImplementedInventory {

    private BlockPos parentPos;

    protected ProtocolAnchorCorePortBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ProtocolAnchorCorePortBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(ModBlockEntities.PROTOCOL_ANCHOR_CORE_PORT, blockPos, blockState);
    }

    public static void tick(World world, BlockPos pos, BlockState state, ProtocolAnchorCorePortBlockEntity entity) {
        if (world.isClient()) return;
        if (entity.parentPos != null) return;

        for (BlockPos p : BlockPos.iterate(pos.add(4, 0, 4), pos.add(-4, 0, -4))) {
            BlockEntity checkEntity = world.getBlockEntity(p);
            if (checkEntity instanceof ProtocolAnchorCoreBlockEntity) {
                entity.setParentPos(p);
                entity.markDirty();
                break;
            }
        }
    }
    public @Nullable ProtocolAnchorCoreBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof ProtocolAnchorCoreBlockEntity parentBlock) {
            return parentBlock;
        }
        return null;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        ProtocolAnchorCoreBlockEntity parent = this.getParentBlock();
        if (parent != null) {
            return parent.getItems();
        }
        return DefaultedList.ofSize(0, ItemStack.EMPTY);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        ProtocolAnchorCoreBlockEntity parent = this.getParentBlock();
        return parent != null ? parent.getAvailableSlots(side) : new int[0];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        Direction facing = this.getCachedState().get(ProtocolAnchorCorePortBlock.FACING);
        return facing == dir;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        Direction facing = this.getCachedState().get(ProtocolAnchorCorePortBlock.FACING);
        return facing != dir;
    }

    public void setParentPos(BlockPos parentPos) {
        this.parentPos = parentPos;
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
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}
