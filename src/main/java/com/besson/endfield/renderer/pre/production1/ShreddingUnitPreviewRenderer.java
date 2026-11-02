package com.besson.endfield.renderer.pre.production1;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.production1.SeedPickingUnitBlock;
import com.besson.endfield.block.custom.production1.ShreddingUnitBlock;
import com.besson.endfield.blockentity.custom.production1.SeedPickingUnitBlockEntity;
import com.besson.endfield.blockentity.custom.production1.ShreddingUnitBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class ShreddingUnitPreviewRenderer extends BasePreviewRenderer<ShreddingUnitBlockEntity> {
    private static final ShreddingUnitPreviewRenderer INSTANCE = new ShreddingUnitPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.SHREDDING_UNIT.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.SHREDDING_UNIT.getDefaultState().with(ShreddingUnitBlock.FACING, facing).with(ShreddingUnitBlock.PREVIEW, true);
    }

    @Override
    protected ShreddingUnitBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new ShreddingUnitBlockEntity(pos, state);
    }
}
