package com.besson.endfield.item.custom;

import com.besson.endfield.entity.ModItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class IndustrialExplosiveItem extends Item {
    public IndustrialExplosiveItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient()) {
            IndustrialExplosiveEntity entity = new IndustrialExplosiveEntity(ModItemEntity.INDUSTRIAL_EXPLOSIVE, user, world);
            entity.setItem(stack);
            float v = 1.5f;
            float i = 1.0f;
            entity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, v, i);

            world.spawnEntity(entity);
        }

        user.getItemCooldownManager().set(this, 20);

        if (!user.getAbilities().creativeMode) {
            stack.decrement(1);
        }

        user.swingHand(hand);

        return TypedActionResult.success(stack, world.isClient());
    }
}
