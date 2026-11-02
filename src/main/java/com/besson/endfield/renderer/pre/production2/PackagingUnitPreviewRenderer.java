package com.besson.endfield.renderer.pre.production2;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.production2.FillingUnitBlock;
import com.besson.endfield.block.custom.production2.PackagingUnitBlock;
import com.besson.endfield.blockentity.custom.production2.FillingUnitBlockEntity;
import com.besson.endfield.blockentity.custom.production2.PackagingUnitBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class PackagingUnitPreviewRenderer extends BasePreviewRenderer<PackagingUnitBlockEntity> {
    private static final PackagingUnitPreviewRenderer INSTANCE = new PackagingUnitPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.PACKAGING_UNIT.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.PACKAGING_UNIT.getDefaultState().with(PackagingUnitBlock.FACING, facing).with(PackagingUnitBlock.PREVIEW, true);
    }

    @Override
    protected PackagingUnitBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new PackagingUnitBlockEntity(pos, state);
    }
}
