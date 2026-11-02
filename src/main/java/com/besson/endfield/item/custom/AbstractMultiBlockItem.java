package com.besson.endfield.item.custom;

import com.besson.endfield.util.MultiBlockPlacementUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AbstractMultiBlockItem extends BlockItem {
    protected AbstractMultiBlockItem(Block block, Settings settings) {
        super(block, settings);
    }
    protected abstract BlockPos[] getOccupiedPositions(ItemPlacementContext context);

    @Override
    public ActionResult place(ItemPlacementContext context) {
        World world = context.getWorld();
        BlockPos[] occupied = this.getOccupiedPositions(context);
        if (!world.isClient() && !MultiBlockPlacementUtil.isAllReplaceable(world, occupied)) {
            PlayerEntity player = context.getPlayer();
            if (player != null) {
                player.sendMessage(Text.translatable("message.no_enough_area"), false);
            }
            return ActionResult.FAIL;
        }

        ActionResult result = super.place(context);
        if (result.isAccepted() && world instanceof ServerWorld serverWorld) {
            MultiBlockPlacementUtil.pushEntitiesOut(serverWorld, context.getBlockPos(), occupied);
        }
        return result;
    }

    @Override
    protected boolean canPlace(ItemPlacementContext context, BlockState state) {
        return !this.checkStatePlacement() || state.canPlaceAt(context.getWorld(), context.getBlockPos());
    }
}
