package com.besson.endfield.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class FluidBottleItem extends BucketItem {
    private final Fluid bottledFluid;
    private final Item emptyBottle;

    public FluidBottleItem(Fluid fluid, Item emptyBottle, Item.Settings settings) {
        super(fluid, settings);
        this.bottledFluid = fluid;
        this.emptyBottle = emptyBottle;
    }

    public Fluid getBottledFluid() {
        return bottledFluid;
    }

    public Item getEmptyBottle() {
        return emptyBottle;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack original = user.getStackInHand(hand);
        TypedActionResult<ItemStack> result = super.use(world, user, hand);
        ItemStack resultStack = result.getValue();
        if (result.getResult().isAccepted()
                && resultStack != original && resultStack.isOf(Items.BUCKET)) {
            if (!world.isClient()) {
                ItemStack bottle = new ItemStack(this.emptyBottle);
                if (!user.giveItemStack(bottle)) {
                    user.dropItem(bottle, false);
                }
                original.decrement(1);
            }
            return TypedActionResult.consume(original);
        }
        return result;
    }
}
