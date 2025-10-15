package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.ElectrifiableDevice;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.power.PowerNetworkManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.List;

public class ElectricPylonBlockEntity extends BlockEntity implements GeoBlockEntity {
    private BlockPos connectedNode;
    private boolean registeredToManager = false;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public ElectricPylonBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ELECTRIC_PYLON, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, ElectricPylonBlockEntity entity) {
        if (world.isClient()) return;

        if (entity.connectedNode == null || world.getBlockEntity(entity.connectedNode) == null) {
            BlockPos closest = null;
            double closestDist = Double.MAX_VALUE;

            for (BlockPos p: BlockPos.iterate(pos.add(-10, -10, -10), pos.add(10, 10, 10))) {
                if (p.equals(pos)) continue;

                BlockEntity candidate = world.getBlockEntity(p);

                if (candidate instanceof ProtocolAnchorCoreBlockEntity || candidate instanceof RelayTowerBlockEntity) {
                    double d = pos.getSquaredDistance(p);
                    if (d < closestDist) {
                        closest = p.toImmutable();
                        closestDist = d;
                    }
                }
            }
            entity.connectedNode = closest;
            markDirty(world, pos, state);
            world.updateListeners(pos, state, state, 3);
        }

//        if (entity.connectedNode != null) {
//            ProtocolAnchorCoreBlockEntity core = entity.findCore(world);
//            if (core != null && core.canSupplyPower()) {
//                entity.supplyPower(core);
//            }
//        }
    }

    @Override
    public void setWorld(World world) {
        super.setWorld(world);
        if (!registeredToManager && world instanceof ServerWorld serverWorld) {
            PowerNetworkManager.get(serverWorld).registerConsumer(this.getPos(), () -> {
                try {
                    return this.getSurroundingDemand();
                } catch (Throwable t) {
                    return 0;
                }
            }, (amount) -> {
                try {
                    this.distributeToSurroundings(amount);
                } catch (Throwable ignored) {

                }
            });
            registeredToManager = true;
        }
    }

    private void distributeToSurroundings(Integer amount) {
        if (world == null || amount <= 0) return;
        List<ElectrifiableDevice> devices = new ArrayList<>();
        for (BlockPos target: BlockPos.iterate(pos.add(-10, 0, -10), pos.add(10, 0, 10))) {
            BlockEntity be = world.getBlockEntity(target);
            if (be instanceof ElectrifiableDevice device) {
                if (device.needsPower()) {
                    devices.add(device);
                }
            }
        }
        if (devices.isEmpty()) return;
        int perDevice = amount / devices.size();
        for (ElectrifiableDevice device: devices) {
            int required = device.getRequiredPower();
            int toGive = Math.min(perDevice, required);
            device.receiveElectricCharge(toGive);
            amount -= toGive;
            if (amount <= 0) break;
        }
    }

    private Integer getSurroundingDemand() {
        if (world == null) return 0;
        int totalDemand = 0;
        for (BlockPos target: BlockPos.iterate(pos.add(-10, 0, -10), pos.add(10, 0, 10))) {
            BlockEntity be = world.getBlockEntity(target);
            if (be instanceof ElectrifiableDevice device) {
                if (device.needsPower()) {
                    totalDemand += device.getRequiredPower();
                }
            }
        }
        return totalDemand;
    }

    @Override
    public void markRemoved() {
        if (world instanceof  ServerWorld serverWorld) {
            PowerNetworkManager.get(serverWorld).unregisterConsumer(this.getPos());
        }
        super.markRemoved();
    }

    private ProtocolAnchorCoreBlockEntity findCore(World world) {
        if (connectedNode == null) return null;
        BlockEntity be = world.getBlockEntity(connectedNode);
        if (be instanceof ProtocolAnchorCoreBlockEntity core) {
            return core;
        } else if (be instanceof RelayTowerBlockEntity relay) {
            return relay.getConnectedCore(world);
        }
        return null;
    }

    private void supplyPower(ProtocolAnchorCoreBlockEntity core) {
        if (world == null) return;
        if (core.getStoredPower() < 20) return;

        for (BlockPos target: BlockPos.iterate(pos.add(-10, 0, -10), pos.add(10, 0, 10))) {
            BlockEntity be = null;
            if (world != null) {
                be = world.getBlockEntity(target);
            }
            if (be instanceof ElectrifiableDevice device) {
                if (device.needsPower()) {
                    int required = device.getRequiredPower();
                    device.receiveElectricCharge(required * 2);
                    core.consumePower(required);
                }
            }
        }
    }

    public BlockPos getConnectedNode() {
        return connectedNode;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (connectedNode != null) {
            nbt.putInt("connectedX", connectedNode.getX());
            nbt.putInt("connectedY", connectedNode.getY());
            nbt.putInt("connectedZ", connectedNode.getZ());
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("connectedX") && nbt.contains("connectedY") && nbt.contains("connectedZ")) {
            int x = nbt.getInt("connectedX");
            int y = nbt.getInt("connectedY");
            int z = nbt.getInt("connectedZ");
            connectedNode = new BlockPos(x, y, z);
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

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0,
                state -> state.setAndContinue(RawAnimation.begin().thenLoop("working"))));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
