package com.besson.endfield.block.custom.powering;

import com.besson.endfield.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class ProtocolAnchorCoreSideBlock extends Block {

    public ProtocolAnchorCoreSideBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.PROTOCOL_ANCHOR_CORE_ITEM);
    }
}
