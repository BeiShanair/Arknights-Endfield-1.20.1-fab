package com.besson.endfield.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class MaterialBottleItem extends Item {
    private final Map<Fluid, Item> bottledVariants = new HashMap<>();

    public MaterialBottleItem(Item.Settings settings) {
        super(settings);
    }

    public MaterialBottleItem addBottled(Fluid fluid, Item bottled) {
        bottledVariants.put(fluid, bottled);
        return this;
    }

    public @Nullable Item getBottledItem(Fluid fluid) {
        return bottledVariants.get(fluid);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        BlockHitResult hit = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);
        if (hit.getType() != HitResult.Type.BLOCK) {
            return TypedActionResult.pass(stack);
        }
        BlockPos pos = hit.getBlockPos();
        if (!world.canPlayerModifyAt(user, pos)) {
            return TypedActionResult.fail(stack);
        }
        FluidState fluidState = world.getFluidState(pos);
        Item bottled = bottledVariants.get(fluidState.getFluid());
        if (bottled == null || !fluidState.isStill()
                || !(world.getBlockState(pos).getBlock() instanceof FluidBlock)) {
            return TypedActionResult.fail(stack);
        }
        ItemStack bottledStack = new ItemStack(bottled);
        if (!world.isClient()) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState(),
                    Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
            world.emitGameEvent(user, GameEvent.FLUID_PICKUP, pos);
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!user.giveItemStack(bottledStack)) {
                user.dropItem(bottledStack, false);
            }
            if (!user.getAbilities().creativeMode) {
                stack.decrement(1);
            }
        }
        world.playSound(user, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
        return TypedActionResult.consume(stack);
    }
}
