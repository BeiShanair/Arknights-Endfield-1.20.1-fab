package com.besson.endfield.renderer.pre.production1;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.production1.FittingUnitBlock;
import com.besson.endfield.block.custom.production1.MouldingUnitBlock;
import com.besson.endfield.blockentity.custom.production1.FittingUnitBlockEntity;
import com.besson.endfield.blockentity.custom.production1.MouldingUnitBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class MouldingUnitPreviewRenderer extends BasePreviewRenderer<MouldingUnitBlockEntity> {
    private static final MouldingUnitPreviewRenderer INSTANCE = new MouldingUnitPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.MOULDING_UNIT.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.MOULDING_UNIT.getDefaultState().with(MouldingUnitBlock.FACING, facing).with(MouldingUnitBlock.PREVIEW, true);
    }

    @Override
    protected MouldingUnitBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new MouldingUnitBlockEntity(pos, state);
    }
}
