package com.besson.endfield.renderer.pre.production2;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.production1.ShreddingUnitBlock;
import com.besson.endfield.block.custom.production2.FillingUnitBlock;
import com.besson.endfield.blockentity.custom.production1.ShreddingUnitBlockEntity;
import com.besson.endfield.blockentity.custom.production2.FillingUnitBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class FillingUnitPreviewRenderer extends BasePreviewRenderer<FillingUnitBlockEntity> {
    private static final FillingUnitPreviewRenderer INSTANCE = new FillingUnitPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.FILLING_UNIT.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.FILLING_UNIT.getDefaultState().with(FillingUnitBlock.FACING, facing).with(FillingUnitBlock.PREVIEW, true);
    }

    @Override
    protected FillingUnitBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new FillingUnitBlockEntity(pos, state);
    }
}
