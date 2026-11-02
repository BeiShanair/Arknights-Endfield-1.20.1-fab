package com.besson.endfield.renderer.pre.production1;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.production1.PlantingUnitBlock;
import com.besson.endfield.block.custom.production1.RefiningUnitBlock;
import com.besson.endfield.blockentity.custom.production1.PlantingUnitBlockEntity;
import com.besson.endfield.blockentity.custom.production1.RefiningUnitBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class RefiningUnitPreviewRenderer extends BasePreviewRenderer<RefiningUnitBlockEntity> {
    private static final RefiningUnitPreviewRenderer INSTANCE = new RefiningUnitPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.REFINING_UNIT.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.REFINING_UNIT.getDefaultState().with(RefiningUnitBlock.FACING, facing).with(RefiningUnitBlock.PREVIEW, true);
    }

    @Override
    protected RefiningUnitBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new RefiningUnitBlockEntity(pos, state);
    }
}
