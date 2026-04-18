package com.besson.endfield.blockentity.custom.combat;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.powering.ElectricPylonBlockEntity;
import com.besson.endfield.blockentity.custom.powering.RelayTowerBlockEntity;
import com.besson.endfield.utils.power.NodeType;
import com.besson.endfield.utils.power.PowerNetworkManager;
import com.besson.endfield.utils.power.PowerNetworkNodeManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MedicalTowerBlockEntity extends BaseGunTower {
    public MedicalTowerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MEDICAL_TOWER, pos, state);
    }

    @Override
    protected float getRange() {
        return 6;
    }

    @Override
    protected int getFireInternal() {
        return 100;
    }

    @Override
    protected int getPowerCostPerTick() {
        return 5;
    }

    @Override
    protected void shoot(World world) {
        if (targetUuid == null) return;
        Entity target = ((ServerWorld) world).getEntity(targetUuid);
        if (target == null) return;
        if (target instanceof PlayerEntity player) {
            Vec3d targetPos = player.getPos().add(0, target.getHeight() * 0.8, 0);

            player.heal(5.0f);
            ((ServerWorld) world).spawnParticles(ParticleTypes.HEART, targetPos.x, targetPos.y, targetPos.z, 5, 0.5, 0.5, 0.5, 0);

            this.fireCooldown = FIRE_INTERNAL;
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, MedicalTowerBlockEntity be) {
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

        if (!be.isPowered && be.storedPower < be.getRequiredPower()) return;

        be.updateTarget(world);

        if (be.fireCooldown > 0) {
            be.fireCooldown--;
        } else if (be.hasTarget()) {
            be.shoot(world);
            be.storedPower -= be.getPowerCostPerTick();

        }

        be.markDirty();
    }

    @Override
    protected void updateTarget(World world) {
        if (targetUuid != null) {
            Entity e = ((ServerWorld) world).getEntity(targetUuid);
            if (e instanceof PlayerEntity && e.isAlive() && e.squaredDistanceTo(Vec3d.ofCenter(pos)) < RANGE * RANGE) {
                return;
            }
            targetUuid = null;
        }

        List<LivingEntity> list = world.getEntitiesByClass(LivingEntity.class,
                new Box(pos).expand(RANGE), LivingEntity::isAlive);
        list.removeIf(e -> !(e instanceof PlayerEntity));
        if (!list.isEmpty()) {
            targetUuid = list.get(0).getUuid();
        }
    }
}
