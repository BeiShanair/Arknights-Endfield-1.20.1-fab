package com.besson.endfield.renderer.pre.powering;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.powering.RelayTowerBlock;
import com.besson.endfield.blockentity.custom.powering.RelayTowerBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

public class RelayTowerPreviewRenderer extends BasePreviewRenderer<RelayTowerBlockEntity> {
    private static final RelayTowerPreviewRenderer INSTANCE = new RelayTowerPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.RELAY_TOWER.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.RELAY_TOWER.getDefaultState().with(RelayTowerBlock.FACING, facing).with(RelayTowerBlock.PREVIEW, true);
    }

    @Override
    protected RelayTowerBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new RelayTowerBlockEntity(pos, state);
    }
}
