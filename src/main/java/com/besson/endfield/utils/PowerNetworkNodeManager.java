package com.besson.endfield.utils;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentStateManager;

import java.util.Map;
import java.util.Optional;
import java.util.WeakHashMap;

public class PowerNetworkNodeManager {
    private static final Map<ServerWorld, PowerNetworkNodeManager> INSTANCE = new WeakHashMap<>();
    private final ServerWorld world;
    private final NodeState state;
    
    public PowerNetworkNodeManager(ServerWorld world) {
        this.world = world;
        PersistentStateManager manager = world.getPersistentStateManager();

        String dimKey = world.getRegistryKey().getValue().toString().replace(':', '_').replace('/', '_');
        String stateName = "power_network_state_node_" + dimKey;
        
        this.state = manager.getOrCreate(NodeState::fromNbt, NodeState::new, stateName);
    }
    
    public static PowerNetworkNodeManager get(ServerWorld world) {
        return INSTANCE.computeIfAbsent(world, PowerNetworkNodeManager::new);
    }

    public void register(NodeEntry entry) {
        if (state.nodeEntries.add(entry)) {
            state.markDirty();
        }
    }

    public void unregister(BlockPos pos) {
        if (state.nodeEntries.removeIf(e -> e.pos().equals(pos))) {
            state.markDirty();
        }
    }

    public Optional<NodeEntry> findNearest(BlockPos from, NodeType type, int range) {
        NodeEntry nearest = null;
        int best = Integer.MAX_VALUE;

        for (NodeEntry entry : state.nodeEntries) {
            if (entry.pos().equals(from)) continue;
            
            if (!type.canConnectTo(entry.type())) continue;

            double dist = Math.sqrt(entry.pos().getSquaredDistance(from));
            if (dist <= range && dist < best) {
                best = (int) dist;
                nearest = entry;
            }
        }
        return Optional.ofNullable(nearest);
    }

    public ServerWorld getWorld() {
        return world;
    }
}
