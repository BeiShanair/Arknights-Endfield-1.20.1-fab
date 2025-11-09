package com.besson.endfield.blockentity.custom;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.UUID;

public class GunTowerBlockEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final double RANGE = 16.0;
    private static final int FIRE_INTERNAL = 40;

    private float turretYaw;
    private float turretPitch;

    private int fireCooldown = 0;
    private UUID targetUuid = null;

    public GunTowerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GUN_TOWER, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, GunTowerBlockEntity blockEntity) {
        if (world.isClient()) return;

        blockEntity.updateTarget(world);
        blockEntity.updateRotation(world);

        if (blockEntity.fireCooldown > 0) blockEntity.fireCooldown--;
        else if (blockEntity.hasTarget()) blockEntity.shoot(world);

        blockEntity.markDirty();
    }

    private void updateTarget(World world) {
        if (targetUuid != null) {
            Entity e = ((ServerWorld) world).getEntity(targetUuid);
            if (e instanceof HostileEntity && e.isAlive() && e.squaredDistanceTo(Vec3d.ofCenter(pos)) < RANGE * RANGE) {
                return;
            }
            targetUuid = null;
        }

        List<HostileEntity> list = world.getEntitiesByClass(HostileEntity.class,
                new Box(pos).expand(RANGE), e -> e.isAlive());
        if (!list.isEmpty()) {
            targetUuid = list.get(0).getUuid();
        }
    }

    private boolean hasTarget() {
        return targetUuid != null;
    }

    private void updateRotation(World world) {
        if (targetUuid == null) return;
        Entity target = ((ServerWorld) world).getEntity(targetUuid);
        if (target == null) return;

        Vec3d towerPos = Vec3d.ofCenter(pos).add(0, 1.5, 0);
        Vec3d targetPos = target.getPos().add(0, target.getHeight() / 2, 0);
        Vec3d direction = targetPos.subtract(towerPos).normalize();

        this.turretYaw = (float) (MathHelper.atan2(direction.x, direction.z) * (180f / Math.PI));
        this.turretPitch = (float) (Math.asin(direction.y) * (180f / Math.PI));

        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
    }

    private void shoot(World world) {
        if (targetUuid == null) return;
        Entity target = ((ServerWorld) world).getEntity(targetUuid);
        if (target == null) return;

        Vec3d towerPos = Vec3d.ofCenter(pos).add(0, 1.5, 0);
        Vec3d targetPos = target.getPos().add(0, target.getHeight() / 2, 0);
        Vec3d direction = targetPos.subtract(towerPos).normalize();

        // Shoot Arrow
        ArrowEntity arrow = new ArrowEntity(world, towerPos.x, towerPos.y, towerPos.z);
        arrow.setVelocity(direction.x * 3, direction.y * 3, direction.z * 3);
        world.spawnEntity(arrow);

        this.fireCooldown = FIRE_INTERNAL;
    }

    public float getTurretPitch() {
        return turretPitch;
    }

    public float getTurretYaw() {
        return turretYaw;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("fireCooldown", fireCooldown);
        if (targetUuid != null) {
            nbt.putUuid("targetUuid", targetUuid);
        }
        nbt.putFloat("turretYaw", turretYaw);
        nbt.putFloat("turretPitch", turretPitch);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.fireCooldown = nbt.getInt("fireCooldown");
        if (nbt.contains("targetUuid")) {
            this.targetUuid = nbt.getUuid("targetUuid");
        }
        this.turretYaw = nbt.getFloat("turretYaw");
        this.turretPitch = nbt.getFloat("turretPitch");
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
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
