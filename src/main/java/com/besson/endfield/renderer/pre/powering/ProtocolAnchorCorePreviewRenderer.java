package com.besson.endfield.renderer.pre.powering;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.powering.ProtocolAnchorCorePortBlock;
import com.besson.endfield.blockentity.custom.powering.ProtocolAnchorCoreBlockEntity;
import com.besson.endfield.blockentity.custom.powering.ProtocolAnchorCorePortBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class ProtocolAnchorCorePreviewRenderer extends BasePreviewRenderer<ProtocolAnchorCoreBlockEntity> {
    private static final ProtocolAnchorCorePreviewRenderer INSTANCE = new ProtocolAnchorCorePreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.PROTOCOL_ANCHOR_CORE.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.PROTOCOL_ANCHOR_CORE.getDefaultState().with(ProtocolAnchorCorePortBlock.FACING, facing).with(ProtocolAnchorCorePortBlock.PREVIEW, true);
    }

    @Override
    protected ProtocolAnchorCoreBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new ProtocolAnchorCoreBlockEntity(pos, state);
    }
}
