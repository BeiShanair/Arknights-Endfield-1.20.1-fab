package com.besson.endfield.renderer.pre.logicitis;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.depot.DepotBusSectionBlock;
import com.besson.endfield.blockentity.custom.depot.DepotBusSectionBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class DepotBusSectionPreviewRenderer extends BasePreviewRenderer<DepotBusSectionBlockEntity> {
    private static final DepotBusSectionPreviewRenderer INSTANCE = new DepotBusSectionPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.DEPOT_BUS_SECTION.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.DEPOT_BUS_SECTION.getDefaultState().with(DepotBusSectionBlock.FACING, facing).with(DepotBusSectionBlock.PREVIEW, true);
    }

    @Override
    protected DepotBusSectionBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new DepotBusSectionBlockEntity(pos, state);
    }
}
