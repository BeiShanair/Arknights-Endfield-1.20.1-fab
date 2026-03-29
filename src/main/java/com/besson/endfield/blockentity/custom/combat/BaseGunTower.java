package com.besson.endfield.blockentity.custom.combat;

import com.besson.endfield.blockentity.custom.powering.ElectricPylonBlockEntity;
import com.besson.endfield.blockentity.custom.powering.RelayTowerBlockEntity;
import com.besson.endfield.utils.NodeType;
import com.besson.endfield.utils.PowerNetworkManager;
import com.besson.endfield.utils.PowerNetworkNodeManager;
import com.besson.endfield.utils.TurretFakePlayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Monster;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
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
import java.util.concurrent.atomic.AtomicReference;

public abstract class BaseGunTower extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    protected final double RANGE = getRange();
    protected final int FIRE_INTERNAL = getFireInternal();

    protected static final float MAX_YAW_SPEED = 6.0f;
    protected static final float MAX_PITCH_SPEED = 4.0f;

    protected float turretYaw;
    protected float turretPitch;

    protected int fireCooldown = 0;
    protected UUID targetUuid = null;
    protected static final float AIM_ANGLE_TOLERANCE = 3.0f;
    protected float targetDesiredYaw;
    protected float targetDesiredPitch;

    protected int tickNum = 0;
    protected boolean isPowered = false;
    protected boolean registeredToManager = false;
    protected int storedPower;
    protected static final int MAX_STORED_POWER = 10000;
    protected boolean isWorking;
    protected boolean enable = true;
    protected boolean needsInit = true;
    
    public BaseGunTower(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    protected abstract float getRange();
    protected abstract int getFireInternal();
    protected abstract int getPowerCostPerTick();

    public static <T extends BaseGunTower> void tick(World world, BlockPos pos, BlockState state, T be) {
        if (world.isClient()) return;

        if (be.needsInit && world instanceof ServerWorld serverWorld) {
            be.needsInit = false;
            PowerNetworkManager.get(serverWorld).registerConsumer(be.getPos(), be::getRequiredPower, be::receiveElectricCharge);
            be.registeredToManager = true;
        }
        
        if (!be.getEnable()) {
            be.isWorking = false;
            world.updateListeners(pos, state, state, 3);
            be.markDirty();
            return;
        }
        be.tickNum++;
        
        if (be.tickNum % 20 == 0 && world instanceof ServerWorld serverWorld) {
            AtomicReference<BlockPos> t = new AtomicReference<>();
            PowerNetworkNodeManager manager = PowerNetworkNodeManager.get(serverWorld);
            manager.findNearest(pos, NodeType.CONSUMER, 10).ifPresent(target -> t.set(target.pos()));
            if (t.get() != null) {
                BlockEntity b = world.getBlockEntity(t.get());
                if (b instanceof ElectricPylonBlockEntity || b instanceof RelayTowerBlockEntity) {
                    be.isPowered = true;
                } else {
                    be.isPowered = false;
                    be.isWorking = false;
                    be.markDirty();
                    world.updateListeners(pos, state, state, 3);
                }
            }
            be.tickNum = 0;
        }
        
        if (!be.isPowered && be.storedPower < be.getPowerCostPerTick()) return;
        
        be.updateTarget(world);
        be.updateRotation(world);

        if (be.fireCooldown > 0) {
            be.fireCooldown--;
        } else if (be.hasTarget()) {
            if (be.isAimed()) {
                be.shoot(world);
                be.storedPower -= be.getPowerCostPerTick();
            }
        }

        be.markDirty();
    }

    public boolean getEnable() {
        return enable;
    }

    public void receiveElectricCharge(int amount) {
        this.storedPower = Math.min(this.storedPower + amount * 20, MAX_STORED_POWER);
    }
    
    public int getRequiredPower() {
        if (isWorking || isPowered && storedPower < MAX_STORED_POWER) {
            return getPowerCostPerTick();
        }
        return 0;
    }
    
    protected void updateTarget(World world) {
        if (targetUuid != null) {
            Entity e = ((ServerWorld) world).getEntity(targetUuid);
            if (e instanceof Monster && e.isAlive() && e.squaredDistanceTo(Vec3d.ofCenter(pos)) < RANGE * RANGE) {
                return;
            }
            targetUuid = null;
        }
        
        List<LivingEntity> list = world.getEntitiesByClass(LivingEntity.class,
                new Box(pos).expand(RANGE), LivingEntity::isAlive);
        list.removeIf(e -> !(e instanceof Monster));
        if (!list.isEmpty()) {
            targetUuid = list.get(0).getUuid();
        }
    }
    
    protected boolean hasTarget() {
        return targetUuid != null;
    }
    
    protected void updateRotation(World world) {
        if (targetUuid == null) return;
        Entity target = ((ServerWorld) world).getEntity(targetUuid);
        if (target == null) return;

        Vec3d towerPos = Vec3d.ofCenter(pos).add(0, 1.5, 0);
        Vec3d targetPos = target.getPos().add(0, target.getHeight() / 2, 0);
        Vec3d direction = targetPos.subtract(towerPos).normalize();

        // 计算目标角度（度），对 yaw 加 180° 修正以匹配模型朝向
        float desiredYaw = (float) (MathHelper.atan2(direction.x, direction.z) * (180f / Math.PI)) + 180f;
        float desiredPitch = (float) -(Math.asin(direction.y) * (180f / Math.PI));

        float prevYaw = this.turretYaw;
        float prevPitch = this.turretPitch;

        this.targetDesiredYaw = desiredYaw;
        this.targetDesiredPitch = desiredPitch;

        this.turretYaw = approachAngle(this.turretYaw, desiredYaw, MAX_YAW_SPEED);
        this.turretPitch = approachAngle(this.turretPitch, desiredPitch, MAX_PITCH_SPEED);

        if (Math.abs(prevYaw - this.turretYaw) > 0.01f || Math.abs(prevPitch - this.turretPitch) > 0.01f) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    protected boolean isAimed() {
        if (targetUuid == null) return false;
        float yawDiff = Math.abs(wrapDegrees(this.turretYaw - this.targetDesiredYaw));
        float pitchDiff = Math.abs(wrapDegrees(this.turretPitch - this.targetDesiredPitch));
        return yawDiff <= AIM_ANGLE_TOLERANCE && pitchDiff <= AIM_ANGLE_TOLERANCE;
    }

    protected float approachAngle(float current, float target, float maxDelta) {
        float delta = wrapDegrees(target - current);
        if (delta > maxDelta) delta = maxDelta;
        if (delta < -maxDelta) delta = -maxDelta;
        return current + delta;
    }

    protected float wrapDegrees(float angle) {
        angle %= 360.0f;
        if (angle >= 180.0f) angle -= 360.0f;
        if (angle < -180.0f) angle += 360.0f;
        return angle;
    }

    protected abstract void shoot(World world);

    protected void onKilled(LivingEntity target) {
        if (!(world instanceof ServerWorld serverWorld)) return;

        int exp = getExperienceReward(target);

        if (exp > 0) {
            ExperienceOrbEntity.spawn(serverWorld, target.getPos(), exp);
        }
    }

    protected int getExperienceReward(LivingEntity target) {
        return target.getXpToDrop();
    }

    protected static void spawnBulletTrail(World level, Vec3d start, Vec3d end) {

        Vec3d direction = end.subtract(start);
        double length = direction.length();
        Vec3d step = direction.normalize().multiply(0.5);
        int steps = (int)(length / 0.5);
        Vec3d pos = start;
        for (int i = 0; i < steps; i++) {
            ((ServerWorld) level).spawnParticles(ParticleTypes.CRIT, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0);
            pos = pos.add(step);
        }
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
        nbt.putInt("storedPower", this.storedPower);
        nbt.putBoolean("isWorking", this.isWorking);
        nbt.putBoolean("enable", this.enable);
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
        this.storedPower = nbt.getInt("storedPower");
        this.isWorking = nbt.getBoolean("isWorking");
        this.enable = nbt.getBoolean("enable");
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
