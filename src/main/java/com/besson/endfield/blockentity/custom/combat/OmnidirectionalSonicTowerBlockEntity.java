package com.besson.endfield.blockentity.custom.combat;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class OmnidirectionalSonicTowerBlockEntity extends BaseGunTower {
    public OmnidirectionalSonicTowerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OMNIDIRECTIONAL_SONIC_TOWER, pos, state);
    }

    @Override
    protected float getRange() {
        return 6f;
    }

    @Override
    protected int getFireInternal() {
        return 100;
    }

    @Override
    protected int getPowerCostPerTick() {
        return 20;
    }

    @Override
    protected void shoot(World world) {
        spawnCycleTrail(world, getPos().toCenterPos());
        Box box = new Box(getPos(), getPos()).expand(getRange());
        for (LivingEntity e : world.getEntitiesByClass(LivingEntity.class, box, e -> e.isAlive() && !(e instanceof PlayerEntity))) {
            e.setStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 60), null);
            e.setStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100), null);
        }

        world.playSound(null, this.pos, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f);

        this.fireCooldown = FIRE_INTERNAL;
    }

    protected static void spawnCycleTrail(World level, Vec3d start) {
        int steps = 120;

        for (int i = 0; i < steps; i++) {
            double angle = (double) i / steps * 2 * Math.PI;
            
            double vx = Math.cos(angle) * 4;
            double vz = Math.sin(angle) * 4;
            
            double speed = 0.2;

            ((ServerWorld) level).spawnParticles(ParticleTypes.END_ROD, start.x, start.y, start.z, 0, vx, 0, vz, speed);
        }
    }
}
