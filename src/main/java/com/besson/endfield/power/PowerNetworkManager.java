package com.besson.endfield.power;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.PersistentStateManager;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PowerNetworkManager {
    private static final Map<ServerWorld, PowerNetworkManager> INSTANCE = new WeakHashMap<>();

    private final ServerWorld world;
    private PowerState state;
    private final Map<BlockPos, GeneratorInfo> generators = new HashMap<>();
    private final Map<BlockPos, ConsumerInfo> consumers = new HashMap<>();

    private int lastTotalGenerated = 0;
    private int lastTotalDemand = 0;
    private double lastSupplyRatio = 0.0;
    private final int maxBatteryCapacity = 100000;
    private int currentStoredEnergy;

    public PowerNetworkManager(ServerWorld world) {
        this.world = world;
        PersistentStateManager manager = world.getPersistentStateManager();
        this.state = manager.getOrCreate(PowerState::fromNbt, PowerState::new, "power_network_state");
        this.currentStoredEnergy = state.storedEnergy;
    }

    public static PowerNetworkManager get(ServerWorld world) {
        return INSTANCE.computeIfAbsent(world, w -> {
            PowerNetworkManager manager = new PowerNetworkManager(w);
            return manager;
        });
    }

    public static void registerGlobalTick() {
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            for (ServerWorld world : server.getWorlds()) {
                PowerNetworkManager manager = get(world);
                manager.tick();
            }
        });
    }

    public void registerGenerator(BlockPos pos, Supplier<Integer> generatedSupplier) {
        generators.put(pos.toImmutable(), new GeneratorInfo(pos.toImmutable(), generatedSupplier));
    }

    public void unregisterGenerator(BlockPos pos) {
        generators.remove(pos.toImmutable());
    }

    public void registerConsumer(BlockPos pos, Supplier<Integer> demandSupplier, Consumer<Integer> receiveCallback) {
        consumers.put(pos.toImmutable(), new ConsumerInfo(pos.toImmutable(), demandSupplier, receiveCallback));
    }

    public void unregisterConsumer(BlockPos pos) {
        consumers.remove(pos.toImmutable());
    }

    public void tick() {
        // 聚合当前发电量 & 当前需求
        int totalGenerated = 0;
        for (GeneratorInfo g : generators.values()) {
            try {
                int v = Math.max(0, g.generatedSupplier.get());
                totalGenerated += v;
            } catch (Throwable t) {
                // 容错
            }
        }

        int totalDemand = 0;
        for (ConsumerInfo c : consumers.values()) {
            try {
                int d = Math.max(0, c.demandSupplier.get());
                totalDemand += d;
            } catch (Throwable t) {
                // 容错
            }
        }

        lastTotalGenerated = totalGenerated;
        lastTotalDemand = totalDemand;

        int availableEnergy = totalGenerated;

        if (totalGenerated >= totalDemand) {
            int surplus = totalGenerated - totalDemand;
            int charge = Math.min(surplus, maxBatteryCapacity - currentStoredEnergy);
            currentStoredEnergy += charge;
            availableEnergy -= charge;
        } else {
            int deficit = totalDemand - totalGenerated;
            int discharge = Math.min(deficit, currentStoredEnergy);
            currentStoredEnergy -= discharge;
            availableEnergy += discharge;
        }

        double supplyRatio = Math.min(1.0, (double) availableEnergy / (double) totalDemand);
        lastSupplyRatio = supplyRatio;

        for (ConsumerInfo c : consumers.values()) {
            try {
                int demand = Math.max(0, c.demandSupplier.get());
                int supply = (int) Math.floor(demand * supplyRatio);
                c.receivePower.accept(supply);
            } catch (Throwable t) {
                // 容错
            }
        }

        state.storedEnergy = currentStoredEnergy;
        state.markDirty();
    }

    public int getLastTotalGenerated() {
        return lastTotalGenerated;
    }

    public int getLastTotalDemand() {
        return lastTotalDemand;
    }

    public double getLastSupplyRatio() {
        return lastSupplyRatio;
    }

    public int getCurrentStoredEnergy() {
        return currentStoredEnergy;
    }

    public int getMaxBatteryCapacity() {
        return maxBatteryCapacity;
    }

    public static class GeneratorInfo {
        public final BlockPos pos;
        public final Supplier<Integer> generatedSupplier;

        public GeneratorInfo(BlockPos pos, Supplier<Integer> generatedSupplier) {
            this.pos = pos;
            this.generatedSupplier = generatedSupplier;
        }
    }

    public static class ConsumerInfo {
        public final BlockPos pos;
        public final Supplier<Integer> demandSupplier;
        public final Consumer<Integer> receivePower;

        public ConsumerInfo(BlockPos pos, Supplier<Integer> demandSupplier, Consumer<Integer> receivePower) {
            this.pos = pos;
            this.demandSupplier = demandSupplier;
            this.receivePower = receivePower;
        }
    }
}
