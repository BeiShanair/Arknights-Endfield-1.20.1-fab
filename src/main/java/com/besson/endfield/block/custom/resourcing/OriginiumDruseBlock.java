package com.besson.endfield.block.custom.resourcing;

import com.besson.endfield.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class OriginiumDruseBlock extends Block {
    public OriginiumDruseBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.5f) {
            BlockPos upPos = pos.up();
            if (world.getBlockState(upPos).isAir()) {
                world.setBlockState(upPos, ModBlocks.ORIGINIUM_DRUSE.getDefaultState());
            }
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }
}
