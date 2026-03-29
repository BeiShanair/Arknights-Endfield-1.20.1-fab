package com.besson.endfield.blockentity.custom.combat;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SurgeTowerBlockEntity extends BaseGunTower {
    public SurgeTowerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SURGE_TOWER, pos, state);
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

        Vec3d towerPos = Vec3d.ofCenter(pos).add(0, 3, 0);
        Vec3d targetPos = t.getPos().add(0, t.getHeight(), 0);

        spawnBulletTrail(world, towerPos, targetPos);
        t.timeUntilRegen = 0;
        boolean died = t.damage(world.getDamageSources().generic(), 48.0f);
        world.playSound(null, this.pos, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f);
        if (died || t.isDead()) {
            onKilled(t);
        }
        this.fireCooldown = FIRE_INTERNAL;
    }

    protected static void spawnBulletTrail(World level, Vec3d start, Vec3d end) {
        Vec3d direction = end.subtract(start);
        double length = direction.length();
        Vec3d step = direction.normalize().multiply(0.5);
        int steps = (int)(length / 0.5);
        Vec3d pos = start;
        for (int i = 0; i < steps; i++) {
            ((ServerWorld) level).spawnParticles(ParticleTypes.ELECTRIC_SPARK, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0);
            pos = pos.add(step);
        }
    }
}
