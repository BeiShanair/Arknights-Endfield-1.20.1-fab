package com.besson.endfield.pipe;

import com.besson.endfield.blockentity.custom.PipeBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtLong;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PipeNetwork {
    private final UUID id;
    private final Set<BlockPos> nodes = new HashSet<>();
    private final SingleVariantStorage<FluidVariant> storage;

    public PipeNetwork(UUID id) {
        this.id = id;
        this.storage = new SingleVariantStorage<>() {
            @Override
            protected FluidVariant getBlankVariant() {
                return FluidVariant.blank();
            }

            @Override
            protected long getCapacity(FluidVariant variant) {
                return nodes.size() * 10000L;
            }
        };
    }

    public PipeNetwork() {
        this(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    public void addNode(BlockPos pos) {
        nodes.add(pos);
    }

    public void removeNode(BlockPos pos) {
        nodes.remove(pos);
    }

    public Set<BlockPos> getNodes() {
        return nodes;
    }

    public Storage<FluidVariant> getStorage() {
        return storage;
    }

    public void tick(World world) {
        if (world.isClient) return; // 客户端不进行逻辑运算

        for (BlockPos pos : nodes) {
            BlockEntity pipeBe = world.getBlockEntity(pos);
            if (!(pipeBe instanceof PipeBlockEntity)) continue;

            for (Direction dir : Direction.values()) {
                BlockPos neighborPos = pos.offset(dir);

                // 目标储罐或其他能接收流体的方块接口
                Storage<FluidVariant> neighborStorage =
                        PipeAPI.getNeighborFluidStorage(world, neighborPos, dir.getOpposite());

                if (neighborStorage == null || neighborStorage == storage) continue; // 无接口或自环传输

                // -----------------------------
                // 第一阶段：输出流体（push outward）
                // -----------------------------
                if (!storage.isResourceBlank() && storage.getAmount() > 0) {
                    try (Transaction tx = Transaction.openOuter()) {
                        long moved = StorageUtil.move(storage, neighborStorage, v -> true, 1000, tx);
                        if (moved > 0) tx.commit();
                    }
                }

                // -----------------------------
                // 第二阶段：吸入流体（pull inward）
                // -----------------------------
                if (storage.getAmount() < storage.getCapacity()) {
                    try (Transaction tx = Transaction.openOuter()) {
                        long moved = StorageUtil.move(neighborStorage, storage, v -> true, 1000, tx);
                        if (moved > 0) tx.commit();
                    }
                }
            }
        }
    }

    public NbtCompound toNbt() {
        NbtCompound tag = new NbtCompound();
        NbtList list = new NbtList();
        for (BlockPos pos : nodes) {
            list.add(NbtLong.of(pos.asLong()));
        }
        tag.put("nodes", list);
        tag.putUuid("id", id);
        tag.put("storage", storage.variant.toNbt());
        tag.putLong("amount", storage.amount);
        return tag;
    }

    public static PipeNetwork fromNbt(NbtCompound tag) {
        PipeNetwork net = new PipeNetwork(tag.getUuid("id"));
        NbtList list = tag.getList("nodes", NbtElement.LONG_TYPE);
        for (int i = 0; i < list.size(); i++) {
            net.addNode(BlockPos.fromLong(((NbtLong) list.get(i)).longValue()));
        }
        FluidVariant variant = FluidVariant.fromNbt(tag.getCompound("storage"));
        long amount = tag.getLong("amount");
        if (!variant.isBlank() && amount > 0) {
            try (Transaction tx = Transaction.openOuter()) {
                net.getStorage().insert(variant, amount, tx);
                tx.commit();
            }
        }
        return net;
    }
}
