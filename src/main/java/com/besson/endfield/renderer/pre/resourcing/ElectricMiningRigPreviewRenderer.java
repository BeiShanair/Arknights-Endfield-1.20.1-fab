package com.besson.endfield.renderer.pre.resourcing;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.production2.FillingUnitBlock;
import com.besson.endfield.block.custom.resourcing.ElectricMiningRigBlock;
import com.besson.endfield.blockentity.custom.production2.FillingUnitBlockEntity;
import com.besson.endfield.blockentity.custom.resourcing.ElectricMiningRigBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class ElectricMiningRigPreviewRenderer extends BasePreviewRenderer<ElectricMiningRigBlockEntity> {
    private static final ElectricMiningRigPreviewRenderer INSTANCE = new ElectricMiningRigPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.ELECTRIC_MINING_RIG.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.ELECTRIC_MINING_RIG.getDefaultState().with(ElectricMiningRigBlock.FACING, facing).with(ElectricMiningRigBlock.PREVIEW, true);
    }

    @Override
    protected ElectricMiningRigBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new ElectricMiningRigBlockEntity(pos, state);
    }
}
