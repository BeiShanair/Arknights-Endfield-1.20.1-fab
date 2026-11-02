package com.besson.endfield.renderer.pre.resourcing;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.resourcing.ElectricMiningRigBlock;
import com.besson.endfield.block.custom.resourcing.PortableOriginiumRigBlock;
import com.besson.endfield.blockentity.custom.resourcing.ElectricMiningRigBlockEntity;
import com.besson.endfield.blockentity.custom.resourcing.PortableOriginiumRigBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class PortableOriginiumRigPreviewRenderer extends BasePreviewRenderer<PortableOriginiumRigBlockEntity> {
    private static final PortableOriginiumRigPreviewRenderer INSTANCE = new PortableOriginiumRigPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.PORTABLE_ORIGINIUM_RIG.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.PORTABLE_ORIGINIUM_RIG.getDefaultState().with(PortableOriginiumRigBlock.FACING, facing).with(PortableOriginiumRigBlock.PREVIEW, true);
    }

    @Override
    protected PortableOriginiumRigBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new PortableOriginiumRigBlockEntity(pos, state);
    }
}
