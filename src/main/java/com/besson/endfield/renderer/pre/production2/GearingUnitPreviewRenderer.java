package com.besson.endfield.renderer.pre.production2;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.production2.FillingUnitBlock;
import com.besson.endfield.block.custom.production2.GearingUnitBlock;
import com.besson.endfield.blockentity.custom.production2.FillingUnitBlockEntity;
import com.besson.endfield.blockentity.custom.production2.GearingUnitBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class GearingUnitPreviewRenderer extends BasePreviewRenderer<GearingUnitBlockEntity> {
    private static final GearingUnitPreviewRenderer INSTANCE = new GearingUnitPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.GEARING_UNIT.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.GEARING_UNIT.getDefaultState().with(GearingUnitBlock.FACING, facing).with(GearingUnitBlock.PREVIEW, true);
    }

    @Override
    protected GearingUnitBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new GearingUnitBlockEntity(pos, state);
    }
}
