package com.besson.endfield.blockentity.custom.combat;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
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

public class HeGrenadeTowerBlockEntity extends BaseGunTower {
    public HeGrenadeTowerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HE_GRENADE_TOWER, pos, state);
    }

    @Override
    protected float getRange() {
        return 12.5f;
    }

    @Override
    protected int getFireInternal() {
        return 60;
    }

    @Override
    protected int getPowerCostPerTick() {
        return 20;
    }

    @Override
    protected void shoot(World world) {
        if (targetUuid == null) return;
        Entity target = ((ServerWorld) world).getEntity(targetUuid);
        if (!(target instanceof LivingEntity t)) return;
        if (!t.isAlive()) return;

        Vec3d towerPos = Vec3d.ofCenter(pos).add(0, 2.5, 0);
        Vec3d targetPos = t.getPos().add(0, t.getHeight(), 0);

        spawnTrail(world, towerPos, targetPos);
        t.timeUntilRegen = 0;
        boolean died = t.damage(world.getDamageSources().generic(), 55.0f);
        if (died || t.isDead()) {
            onKilled(t);
        }
        world.createExplosion(null, targetPos.x, targetPos.y, targetPos.z, 0.0f, World.ExplosionSourceType.NONE);
        Box box = new Box(targetPos, targetPos).expand(3);
        for (LivingEntity e : world.getEntitiesByClass(LivingEntity.class, box, e -> e.isAlive() && !(e instanceof PlayerEntity))) {
            double distance = e.getPos().distanceTo(targetPos);
            if (distance <= 5) {
                double damage = 55.0 * (1 - distance / 5);
                e.timeUntilRegen = 0;
                boolean died2 = e.damage(world.getDamageSources().generic(), (float) damage);
                if (died2 || e.isDead()) {
                    onKilled(e);
                }
            }
        }
        for (int x = -5; x <= 5; x++) {
            for (int z = -5; z <= 5; z++) {
                double dist = x * x + z * z;

                if (dist > 5 * 5) continue;
                if (world.random.nextFloat() > 0.6f) continue;

                BlockPos firePos = t.getBlockPos();
                BlockPos groundPos = firePos.down();

                if (!world.getBlockState(groundPos).isSolid()) {
                    continue;
                }
                if (!world.getBlockState(firePos).isAir()) {
                    continue;
                }
                world.setBlockState(firePos, Blocks.FIRE.getDefaultState(), 3);
            }
        }
        world.playSound(null, this.pos, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f);
        this.fireCooldown = FIRE_INTERNAL;
    }

    protected static void spawnTrail(World level, Vec3d start, Vec3d end) {
        Vec3d direction = end.subtract(start);
        double length = direction.length();
        Vec3d step = direction.normalize().multiply(0.5);
        int steps = (int)(length / 0.5);
        Vec3d pos = start;
        for (int i = 0; i < steps; i++) {
            ((ServerWorld) level).spawnParticles(ParticleTypes.CLOUD, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0);
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
