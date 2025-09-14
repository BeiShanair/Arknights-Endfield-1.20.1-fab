package com.besson.endfield.blockentity.custom;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashSet;
import java.util.Set;

public class RelayTowerBlockEntity extends BlockEntity implements GeoBlockEntity {
    private BlockPos connectedNode;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public RelayTowerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RELAY_TOWER, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, RelayTowerBlockEntity entity) {
        if (world.isClient()) return;

        if (entity.connectedNode == null || world.getBlockEntity(entity.connectedNode) == null) {
            BlockPos closest = null;
            double closestDist = Double.MAX_VALUE;

            for (BlockPos p: BlockPos.iterate(pos.add(-30, 0, -30), pos.add(30, 0, 30))) {
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
    }

    public BlockPos getConnectedNode() {
        return connectedNode;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (connectedNode != null) {
            nbt.putInt("connectedNodeX", connectedNode.getX());
            nbt.putInt("connectedNodeY", connectedNode.getY());
            nbt.putInt("connectedNodeZ", connectedNode.getZ());
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("connectedNodeX") && nbt.contains("connectedNodeY") && nbt.contains("connectedNodeZ")) {
            int x = nbt.getInt("connectedNodeX");
            int y = nbt.getInt("connectedNodeY");
            int z = nbt.getInt("connectedNodeZ");
            connectedNode = new BlockPos(x, y, z);
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

    public ProtocolAnchorCoreBlockEntity getConnectedCore(World world) {
        return findConnectedCore(world, new HashSet<>());
    }

    private ProtocolAnchorCoreBlockEntity findConnectedCore(World world, Set<BlockPos> visited) {
        if (connectedNode == null) return null;
        if (visited.contains(pos)) return null;

        visited.add(pos);

        BlockEntity be = world.getBlockEntity(connectedNode);
        if (be instanceof ProtocolAnchorCoreBlockEntity core) {
            return core;
        }

        if (be instanceof RelayTowerBlockEntity relay) {
            return relay.findConnectedCore(world, visited);
        }
        return null;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
