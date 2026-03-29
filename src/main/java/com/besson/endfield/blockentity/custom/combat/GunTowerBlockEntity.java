package com.besson.endfield.blockentity.custom.combat;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class GunTowerBlockEntity extends BaseGunTower {
    public GunTowerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GUN_TOWER, pos, state);
    }

    @Override
    protected float getRange() {
        return 15.0f;
    }

    @Override
    protected int getFireInternal() {
        return 40;
    }

    @Override
    protected int getPowerCostPerTick() {
        return 5;
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
        boolean died = t.damage(world.getDamageSources().generic(), 6.0f);
        world.playSound(null, this.pos, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0f, 1.0f);
        if (died || t.isDead()) {
            onKilled(t);
        }
        this.fireCooldown = FIRE_INTERNAL;
    }
}
