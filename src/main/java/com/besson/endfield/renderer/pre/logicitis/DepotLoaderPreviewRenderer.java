package com.besson.endfield.renderer.pre.logicitis;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.depot.DepotLoaderBlock;
import com.besson.endfield.blockentity.custom.depot.DepotLoaderBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class DepotLoaderPreviewRenderer extends BasePreviewRenderer<DepotLoaderBlockEntity> {
    private static final DepotLoaderPreviewRenderer INSTANCE = new DepotLoaderPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.DEPOT_LOADER.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.DEPOT_LOADER.getDefaultState().with(DepotLoaderBlock.FACING, facing).with(DepotLoaderBlock.PREVIEW, true);
    }

    @Override
    protected DepotLoaderBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new DepotLoaderBlockEntity(pos, state);
    }
}
