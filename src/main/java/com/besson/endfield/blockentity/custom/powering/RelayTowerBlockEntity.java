package com.besson.endfield.blockentity.custom.powering;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.utils.power.NodeEntry;
import com.besson.endfield.utils.power.NodeType;
import com.besson.endfield.utils.power.PowerNetworkNodeManager;
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
import software.bernie.geckolib.util.GeckoLibUtil;

public class RelayTowerBlockEntity extends BlockEntity implements GeoBlockEntity {
    private BlockPos connectedNode;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private boolean needsInit = true;
    public boolean isPowered = false;

    protected int tickNum = 0;
    
    public RelayTowerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RELAY_TOWER, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, RelayTowerBlockEntity be) {
        if (world.isClient()) return;

        if (be.needsInit && world instanceof ServerWorld serverWorld) {
            be.needsInit = false;

            PowerNetworkNodeManager manager = PowerNetworkNodeManager.get(serverWorld);
            manager.register(new NodeEntry(pos, NodeType.RELAY));

            if (be.connectedNode == null) {
                manager.findNearest(pos, NodeType.RELAY, 80).ifPresent(target -> {
                    be.connectedNode = target.pos();
                    be.isPowered = true;
                    be.markDirty();
                    world.updateListeners(pos, state, state, 3);
                });
            }
        }

        if (be.tickNum % 20 == 0) {
            be.tickNum = 0;
            if (be.connectedNode == null) return;

            if (world.getBlockEntity(be.connectedNode) == null) {
                be.removeConnectedNode();
                if (world instanceof ServerWorld serverWorld) {
                    PowerNetworkNodeManager manager = PowerNetworkNodeManager.get(serverWorld);

                    manager.findNearest(pos, NodeType.RELAY, 80).ifPresent(target -> {
                        be.connectedNode = target.pos();
                        be.isPowered = true;
                        be.markDirty();
                        world.updateListeners(pos, state, state, 3);
                    });
                } else {
                    be.isPowered = false;
                    be.markDirty();
                    world.updateListeners(pos, state, state, 3);
                }
            }
        }
    }

    @Override
    public void setWorld(World world) {
        super.setWorld(world);
        if (world instanceof ServerWorld) {
            needsInit = true;
        }
    }

    @Override
    public void markRemoved() {
        if (world instanceof  ServerWorld serverWorld) {
            PowerNetworkNodeManager.get(serverWorld).unregister(this.getPos());
        }
        super.markRemoved();
    }

    public BlockPos getConnectedNode() {
        return connectedNode;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (connectedNode != null) {
            nbt.putLong("connected", connectedNode.asLong());
        }
        nbt.putBoolean("isPowered", isPowered);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("connected")) {
            connectedNode = BlockPos.fromLong(nbt.getLong("connected"));
        }
        isPowered = nbt.getBoolean("isPowered");
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public void setConnectedNode(BlockPos recorded) {
        this.connectedNode = recorded;
    }

    public void removeConnectedNode() {
        this.connectedNode = null;
    }
}
