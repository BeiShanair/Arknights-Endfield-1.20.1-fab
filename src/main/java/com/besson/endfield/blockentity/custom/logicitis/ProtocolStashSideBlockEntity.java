package com.besson.endfield.blockentity.custom.logicitis;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.blockentity.ModBlockEntities;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ProtocolStashSideBlockEntity extends BlockEntity {
    private BlockPos parentPos;

    public ProtocolStashSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PROTOCOL_STASH_SIDE, pos, state);
    }

    public void setParentPos(BlockPos parentPos) {
        this.parentPos = parentPos;
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

    @Nullable
    public ProtocolStashBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = this.world.getBlockEntity(parentPos);
        if (entity instanceof ProtocolStashBlockEntity parent) {
            return parent;
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

    public static void tick(World world, BlockPos pos, BlockState state, ProtocolStashSideBlockEntity be) {
        if (world.isClient()) return;

        ProtocolStashBlockEntity parent = be.getParentBlock();
        if (parent != null) {
            Direction facing = state.get(ModBlockEntityWithFacing.FACING);
            Storage<ItemVariant> machine =
                    ItemStorage.SIDED.find(world, pos, facing.getOpposite());

            if (machine == null) return;

            BlockPos beltPos = pos.offset(facing.getOpposite());
            BlockEntity targetBe = world.getBlockEntity(beltPos);

            if (!(targetBe instanceof BeltBlockEntity belt)) return;

            if (!belt.canAcceptFrom(facing)) return;

            try (Transaction tx = Transaction.openOuter()) {
                for (StorageView<ItemVariant> view : machine) {
                    if (view.isResourceBlank()) continue;
                    ItemVariant variant = view.getResource();
                    long ex = view.extract(variant, 1, tx);
                    if (ex > 0) {
                        ItemStack stack = variant.toStack((int) ex);
                        tx.commit();

                        belt.acceptItem(stack, facing);
                        return;
                    }
                }
            }
        }
    }
}
