package com.besson.endfield.renderer.pre.production1;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.powering.ThermalBankBlock;
import com.besson.endfield.block.custom.production1.FittingUnitBlock;
import com.besson.endfield.blockentity.custom.powering.ThermalBankBlockEntity;
import com.besson.endfield.blockentity.custom.production1.FittingUnitBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class FittingUnitPreviewRenderer extends BasePreviewRenderer<FittingUnitBlockEntity> {
    private static final FittingUnitPreviewRenderer INSTANCE = new FittingUnitPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.FITTING_UNIT.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.FITTING_UNIT.getDefaultState().with(FittingUnitBlock.FACING, facing).with(FittingUnitBlock.PREVIEW, true);
    }

    @Override
    protected FittingUnitBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new FittingUnitBlockEntity(pos, state);
    }
}
