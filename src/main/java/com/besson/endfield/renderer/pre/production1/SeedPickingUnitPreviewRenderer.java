package com.besson.endfield.renderer.pre.production1;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.production1.RefiningUnitBlock;
import com.besson.endfield.block.custom.production1.SeedPickingUnitBlock;
import com.besson.endfield.blockentity.custom.production1.RefiningUnitBlockEntity;
import com.besson.endfield.blockentity.custom.production1.SeedPickingUnitBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class SeedPickingUnitPreviewRenderer extends BasePreviewRenderer<SeedPickingUnitBlockEntity> {
    private static final SeedPickingUnitPreviewRenderer INSTANCE = new SeedPickingUnitPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.SEED_PICKING_UNIT.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.SEED_PICKING_UNIT.getDefaultState().with(SeedPickingUnitBlock.FACING, facing).with(SeedPickingUnitBlock.PREVIEW, true);
    }

    @Override
    protected SeedPickingUnitBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new SeedPickingUnitBlockEntity(pos, state);
    }
}
