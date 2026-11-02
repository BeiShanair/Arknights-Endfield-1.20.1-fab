package com.besson.endfield.renderer.pre.production1;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.production1.MouldingUnitBlock;
import com.besson.endfield.block.custom.production1.PlantingUnitBlock;
import com.besson.endfield.blockentity.custom.production1.MouldingUnitBlockEntity;
import com.besson.endfield.blockentity.custom.production1.PlantingUnitBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class PlantingUnitPreviewRenderer extends BasePreviewRenderer<PlantingUnitBlockEntity> {
    private static final PlantingUnitPreviewRenderer INSTANCE = new PlantingUnitPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.PLANTING_UNIT.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.PLANTING_UNIT.getDefaultState().with(PlantingUnitBlock.FACING, facing).with(PlantingUnitBlock.PREVIEW, true);
    }

    @Override
    protected PlantingUnitBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new PlantingUnitBlockEntity(pos, state);
    }
}
