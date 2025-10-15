package com.besson.endfield.power;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PowerNetworkManager {
    private static final Map<ServerWorld, PowerNetworkManager> INSTANCE = new WeakHashMap<>();

    private final ServerWorld world;
    private final Map<BlockPos, GeneratorInfo> generators = new HashMap<>();
    private final Map<BlockPos, ConsumerInfo> consumers = new HashMap<>();

    private int lastTotalGenerated = 0;
    private int lastTotalDemand = 0;
    private double lastSupplyRatio = 0.0;

    public PowerNetworkManager(ServerWorld world) {
        this.world = world;
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
                // 容错：若某个方块实体在卸载或抛异常，解绑它
                // (但不要在循环中直接修改 map，收集后移除)
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
        if (totalDemand <= 0 || totalGenerated <= 0) {
            lastSupplyRatio = totalDemand == 0 ? 1.0 : 0.0; // 无负载视为充足
            // 即便没有电力，仍需回调消费者 receive(0) 以保持状态一致（可选）
            for (ConsumerInfo c : consumers.values()) {
                try { c.receivePower.accept(0); } catch (Throwable ignored) {}
            }
            return;
        }

        // 分配策略：按比例（consumer.demand / totalDemand）分配 available energy
        double supplyRatio = Math.min(1.0, (double) totalGenerated / (double) totalDemand);
        lastSupplyRatio = supplyRatio;

        // 为每个 consumer 计算 supply 并回调
        for (ConsumerInfo c : consumers.values()) {
            try {
                int demand = Math.max(0, c.demandSupplier.get());
                int supply = (int) Math.floor(demand * supplyRatio);
                // 可能在末位造成小量未分配的电力 —— 可实现“剩余分配”优化，先忽略
                c.receivePower.accept(supply);
            } catch (Throwable t) {
                // 容错
            }
        }
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
