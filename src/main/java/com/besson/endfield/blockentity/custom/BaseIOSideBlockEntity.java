package com.besson.endfield.blockentity.custom;

import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static com.besson.endfield.block.ModBlockEntityWithFacing.FACING;

public abstract class BaseIOSideBlockEntity extends BlockEntity {
    protected BlockPos parentPos;
    public BaseIOSideBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
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

    public abstract @Nullable BaseIOBlockEntity<?> getParentBlock();
    
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    
    public static <T extends BaseIOSideBlockEntity> void tick(World world, BlockPos pos,  BlockState state, T be) {
        if (world.isClient()) return;

        BaseIOBlockEntity<?> parent = be.getParentBlock();
        if (parent != null) {
            Direction facing = state.get(FACING);
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

                    long extracted = view.extract(variant, 1, tx);

                    if (extracted > 0) {

                        ItemStack stack = variant.toStack((int) extracted);

                        // 提交事务（真正扣除机器物品）
                        tx.commit();

                        // 传给传送带
                        belt.acceptItem(stack, facing);

                        return;
                    }
                }
            }
        }
    }
}
