package com.besson.endfield.blockentity.custom.combat;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LNTowerBlockEntity extends BaseGunTower {
    public LNTowerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LN_TOWER, pos, state);
    }

    @Override
    protected float getRange() {
        return 15f;
    }

    @Override
    protected int getFireInternal() {
        return 100;
    }

    @Override
    protected int getPowerCostPerTick() {
        return 10;
    }

    @Override
    protected void shoot(World world) {
        if (targetUuid == null) return;
        Entity target = ((ServerWorld) world).getEntity(targetUuid);
        if (target == null) return;

        Vec3d towerPos = Vec3d.ofCenter(pos).add(0, 2.5, 0);
        Vec3d targetPos = target.getPos().add(0, target.getHeight(), 0);

        spawnBulletTrail(world, towerPos, targetPos);
        Box box = new Box(targetPos, targetPos).expand(3);
        for (LivingEntity e : world.getEntitiesByClass(LivingEntity.class, box, e -> e.isAlive() && !(e instanceof PlayerEntity))) {
            e.setStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100), null);
        }
        world.playSound(null, this.pos, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f);

        this.fireCooldown = FIRE_INTERNAL;
    }

    protected static void spawnBulletTrail(World level, Vec3d start, Vec3d end) {
        Vec3d direction = end.subtract(start);
        double length = direction.length();
        Vec3d step = direction.normalize().multiply(0.5);
        int steps = (int)(length / 0.5);
        Vec3d pos = start;
        for (int i = 0; i < steps; i++) {
            ((ServerWorld) level).spawnParticles(ParticleTypes.SNOWFLAKE, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0);
            pos = pos.add(step);
        }
    }

    protected void updateRotation(World world) {
        if (targetUuid == null) return;
        Entity target = ((ServerWorld) world).getEntity(targetUuid);
        if (target == null) return;

        Vec3d towerPos = Vec3d.ofCenter(pos).add(0, 1.5, 0);
        Vec3d targetPos = target.getPos().add(0, target.getHeight() / 2, 0);
        Vec3d direction = targetPos.subtract(towerPos).normalize();

        float desiredYaw = (float) (MathHelper.atan2(direction.x, direction.z) * (180f / Math.PI)) + 180f;
        float desiredPitch = (float) (Math.asin(direction.y) * (180f / Math.PI));

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
}
