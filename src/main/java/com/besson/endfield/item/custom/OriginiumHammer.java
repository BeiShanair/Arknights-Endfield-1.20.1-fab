package com.besson.endfield.item.custom;

import com.besson.endfield.blockentity.custom.production1.RefiningUnitBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class OriginiumHammer extends Item {
    public OriginiumHammer(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient()) return ActionResult.PASS;
        BlockEntity be = world.getBlockEntity(context.getBlockPos());
        if (be instanceof RefiningUnitBlockEntity b) {
            b.changeFluidMode(world);
            context.getPlayer().sendMessage(b.getFluidMode(), true);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
