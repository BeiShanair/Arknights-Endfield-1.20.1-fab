package com.besson.endfield.renderer.pre.resourcing;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.resourcing.ElectricMiningRigBlock;
import com.besson.endfield.block.custom.resourcing.ElectricMiningRigMkIIBlock;
import com.besson.endfield.blockentity.custom.resourcing.ElectricMiningRigBlockEntity;
import com.besson.endfield.blockentity.custom.resourcing.ElectricMiningRigMkIIBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class ElectricMiningRigMkIIPreviewRenderer extends BasePreviewRenderer<ElectricMiningRigMkIIBlockEntity> {
    private static final ElectricMiningRigMkIIPreviewRenderer INSTANCE = new ElectricMiningRigMkIIPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.ELECTRIC_MINING_RIG_MK_II.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.ELECTRIC_MINING_RIG_MK_II.getDefaultState().with(ElectricMiningRigMkIIBlock.FACING, facing).with(ElectricMiningRigMkIIBlock.PREVIEW, true);
    }

    @Override
    protected ElectricMiningRigMkIIBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new ElectricMiningRigMkIIBlockEntity(pos, state);
    }
}
