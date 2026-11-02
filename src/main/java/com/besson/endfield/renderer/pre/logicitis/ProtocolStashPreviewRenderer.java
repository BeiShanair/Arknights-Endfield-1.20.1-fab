package com.besson.endfield.renderer.pre.logicitis;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.depot.ProtocolStashBlock;
import com.besson.endfield.blockentity.custom.depot.ProtocolStashBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class ProtocolStashPreviewRenderer extends BasePreviewRenderer<ProtocolStashBlockEntity> {
    private static final ProtocolStashPreviewRenderer INSTANCE = new ProtocolStashPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.PROTOCOL_STASH.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.PROTOCOL_STASH.getDefaultState().with(ProtocolStashBlock.FACING, facing).with(ProtocolStashBlock.PREVIEW, true);
    }

    @Override
    protected ProtocolStashBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new ProtocolStashBlockEntity(pos, state);
    }
}
