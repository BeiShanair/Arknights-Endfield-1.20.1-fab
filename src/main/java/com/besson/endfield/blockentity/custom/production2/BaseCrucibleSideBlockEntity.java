package com.besson.endfield.blockentity.custom.production2;

import com.besson.endfield.blockentity.custom.BaseIOSideBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

public abstract class BaseCrucibleSideBlockEntity extends BaseIOSideBlockEntity {
    protected int fluidPortIndex = -1;
    protected int particleTick = 0;

    public BaseCrucibleSideBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public abstract @Nullable BaseCrucibleBlockEntity getParentBlock();

    public int getFluidPortIndex() {
        return fluidPortIndex;
    }

    public void cycleFluidPort() {
        this.fluidPortIndex = this.fluidPortIndex >= 1 ? -1 : this.fluidPortIndex + 1;
        markDirty();
        if (world != null) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
            if (fluidPortIndex >= 0 && world instanceof ServerWorld serverWorld) {
                spawnPortMarker(serverWorld, pos, fluidPortIndex, 12);
            }
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, BaseCrucibleSideBlockEntity be) {
        BaseIOSideBlockEntity.tick(world, pos, state, be);
        if (world.isClient() || !(world instanceof ServerWorld serverWorld)) return;
        if (be.fluidPortIndex < 0) return;
        if (++be.particleTick < 20) return;
        be.particleTick = 0;
        spawnPortMarker(serverWorld, pos, be.fluidPortIndex, 3);
    }

    public static void spawnPortMarker(ServerWorld world, BlockPos pos, int portIndex, int count) {
        Vector3f color = portIndex == 0
                ? new Vector3f(0.2f, 0.85f, 1.0f)
                : new Vector3f(1.0f, 0.6f, 0.15f);
        world.spawnParticles(new DustParticleEffect(color, 1.2f),
                pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5,
                count, 0.35, 0.05, 0.35, 0.01);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("fluidPort", fluidPortIndex);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        fluidPortIndex = nbt.contains("fluidPort") ? nbt.getInt("fluidPort") : -1;
    }
}
