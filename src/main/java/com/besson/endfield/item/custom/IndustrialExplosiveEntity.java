package com.besson.endfield.item.custom;

import com.besson.endfield.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class IndustrialExplosiveEntity extends ThrownItemEntity {
    public IndustrialExplosiveEntity(EntityType<? extends IndustrialExplosiveEntity> entityType, World world) {
        super(entityType, world);
    }

    public IndustrialExplosiveEntity(EntityType<? extends IndustrialExplosiveEntity> entityType, LivingEntity owner, World world) {
        super(entityType, owner, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.INDUSTRIAL_EXPLOSIVE;
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient()){
            double x = this.getX();
            double y = this.getY();
            double z = this.getZ();

            float power = 10.0f;

            this.getWorld().createExplosion(this, x, y, z, power, World.ExplosionSourceType.TNT);
            this.discard();
        }
    }

    @Override
    protected ItemStack getItem() {
        return new ItemStack(ModItems.INDUSTRIAL_EXPLOSIVE);
    }
}
