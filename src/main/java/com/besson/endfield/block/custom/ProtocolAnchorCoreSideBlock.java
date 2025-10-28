package com.besson.endfield.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;

public class ProtocolAnchorCoreSideBlock extends Block {

    public ProtocolAnchorCoreSideBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }
}
