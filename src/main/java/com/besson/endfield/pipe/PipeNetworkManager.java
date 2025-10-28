package com.besson.endfield.pipe;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.PersistentState;
import net.minecraft.world.World;

import java.util.*;

public class PipeNetworkManager extends PersistentState {
    public static final String KEY = "pipe_network_manager";

    private final Map<BlockPos, UUID> nodeToNetwork = new HashMap<>();
    private final Map<UUID, PipeNetwork> networks = new HashMap<>();

    private final ServerWorld world;

    public PipeNetworkManager(ServerWorld world) {
        this.world = world;
    }

    public static PipeNetworkManager getInstance(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(
                nbt-> fromNbt(world, nbt),
                () -> new PipeNetworkManager(world),
                KEY
        );
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtList list = new NbtList();
        for (Map.Entry<BlockPos, UUID> d : nodeToNetwork.entrySet()) {
            NbtCompound entry = new NbtCompound();
            entry.putLong("pos", d.getKey().asLong());
            entry.putUuid("net", d.getValue());
            list.add(entry);
        }
        nbt.put("nodes", list);

        NbtList nets = new NbtList();
        for (PipeNetwork network : networks.values()) {
            nets.add(network.toNbt());
        }
        nbt.put("networks", nets);

        return nbt;
    }

    public static PipeNetworkManager fromNbt(ServerWorld world, NbtCompound nbt) {
        PipeNetworkManager manager = new PipeNetworkManager(world);
        NbtList list = nbt.getList("nodes", 10);
        for (int i = 0; i < list.size(); i++) {
            NbtCompound entry = list.getCompound(i);
            BlockPos pos = BlockPos.fromLong(entry.getLong("pos"));
            UUID id = entry.getUuid("net");
            manager.nodeToNetwork.put(pos, id);
            manager.networks.computeIfAbsent(id, map ->
                    new PipeNetwork()).addNode(pos);
        }

        NbtList nets = nbt.getList("networks", 10);
        for (int i = 0; i < nets.size(); i++) {
            PipeNetwork net = PipeNetwork.fromNbt(nets.getCompound(i));
            manager.networks.put(net.getId(), net);
            for (BlockPos pos : net.getNodes()) {
                manager.nodeToNetwork.put(pos, net.getId());
            }
        }

        return manager;
    }

    public void markDirtyAndSave() {
        this.markDirty();

        if (world != null) {
            world.getPersistentStateManager().save();
        }
    }

    public void onPipeAdded(World world, BlockPos pos) {
        if (world.isClient) return;

        // 先尝试找到周围已存在的网络
        Set<UUID> adjacentNetworks = new HashSet<>();

        for (Direction dir : Direction.values()) {
            BlockPos neighborPos = pos.offset(dir);
            UUID neighborId = nodeToNetwork.get(neighborPos);
            if (neighborId != null) {
                adjacentNetworks.add(neighborId);
            }
        }

        PipeNetwork targetNetwork;

        if (adjacentNetworks.isEmpty()) {
            // 若周围没有网络，则创建新网络
            targetNetwork = new PipeNetwork();
            networks.put(targetNetwork.getId(), targetNetwork);
        } else {
            // 若有一个或多个网络，则取第一个作为主网络
            UUID mainId = adjacentNetworks.iterator().next();
            targetNetwork = networks.get(mainId);

            // 若有多个不同网络，则合并
            for (UUID otherId : adjacentNetworks) {
                if (!otherId.equals(mainId)) {
                    mergeNetworks(otherId, mainId);
                }
            }
        }

        // 把当前管道加入目标网络
        targetNetwork.addNode(pos);
        nodeToNetwork.put(pos, targetNetwork.getId());

        markDirtyAndSave();
    }

    public void onPipeRemoved(World world, BlockPos pos) {
        if (world.isClient) return;

        UUID netId = nodeToNetwork.remove(pos);
        if (netId == null) return;

        PipeNetwork network = networks.get(netId);
        if (network == null) return;

        network.removeNode(pos);

        if (network.getNodes().isEmpty()) {
            networks.remove(netId);
            markDirtyAndSave();
            return;
        }

        // 检查网络是否被断裂成多个部分（BFS）
        Set<BlockPos> visited = new HashSet<>();
        List<Set<BlockPos>> subnets = new ArrayList<>();

        for (BlockPos start : network.getNodes()) {
            if (visited.contains(start)) continue;
            Set<BlockPos> subnet = new HashSet<>();
            dfs(start, network.getNodes(), subnet, visited);
            subnets.add(subnet);
        }

        if (subnets.size() > 1) {
            // 拆分成多个新网络
            networks.remove(netId);
            for (Set<BlockPos> subset : subnets) {
                PipeNetwork newNet = new PipeNetwork();
                for (BlockPos p : subset) {
                    newNet.addNode(p);
                    nodeToNetwork.put(p, newNet.getId());
                }
                networks.put(newNet.getId(), newNet);
            }
        }

        markDirtyAndSave();
    }

    private void dfs(BlockPos start, Set<BlockPos> all, Set<BlockPos> subnet, Set<BlockPos> visited) {
        if (!visited.add(start)) return;
        subnet.add(start);
        for (Direction dir : Direction.values()) {
            BlockPos next = start.offset(dir);
            if (all.contains(next)) dfs(next, all, subnet, visited);
        }
    }

    private void mergeNetworks(UUID fromId, UUID toId) {
        if (fromId.equals(toId)) return;

        PipeNetwork from = networks.remove(fromId);
        PipeNetwork to = networks.get(toId);
        if (from == null || to == null) return;

        // 1) 将 from 的所有节点加入到 to
        for (BlockPos node : from.getNodes()) {
            to.addNode(node);
            nodeToNetwork.put(node, toId);
        }

        // 2) 将流体从 from.storage 安全地迁移到 to.storage（事务式）
        Storage<FluidVariant> fromStorage = from.getStorage();
        Storage<FluidVariant> toStorage = to.getStorage();

        if (fromStorage != null && toStorage != null) {
            try (Transaction tx = Transaction.openOuter()) {
                long moved = StorageUtil.move(fromStorage, toStorage, v -> true, Long.MAX_VALUE, tx);
                if (moved > 0) {
                    tx.commit();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 3) 移除被合并网络后的清理（可选）
        //    如果你的 PipeNetwork 内部有 tick 缓存或引用，可以调用 clear()
    }

    public void tickAllNetworks(World world) {
        for (PipeNetwork network : networks.values()) {
            network.tick(world);
        }
    }
}
