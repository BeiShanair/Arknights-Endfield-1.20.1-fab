package com.besson.endfield.renderer.pre.logicitis;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.depot.DepotUnloaderBlock;
import com.besson.endfield.blockentity.custom.depot.DepotUnloaderBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class DepotUnloaderPreviewRenderer extends BasePreviewRenderer<DepotUnloaderBlockEntity> {
    private static final DepotUnloaderPreviewRenderer INSTANCE = new DepotUnloaderPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.DEPOT_UNLOADER.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.DEPOT_UNLOADER.getDefaultState().with(DepotUnloaderBlock.FACING, facing).with(DepotUnloaderBlock.PREVIEW, true);
    }

    @Override
    protected DepotUnloaderBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new DepotUnloaderBlockEntity(pos, state);
    }
}
