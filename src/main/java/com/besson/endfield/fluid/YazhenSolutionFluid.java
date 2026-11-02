package com.besson.endfield.fluid;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;

public abstract class YazhenSolutionFluid extends ModBaseFluid {
    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_YAZHEN_SOLUTION;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_YAZHEN_SOLUTION;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.YAZHEN_SOLUTION_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.YAZHEN_SOLUTION_BLOCK.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    public static class Flowing extends YazhenSolutionFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }
    }

    public static class Source extends YazhenSolutionFluid {
        @Override
        public boolean isStill(FluidState state) {
            return true;
        }

        @Override
        public int getLevel(FluidState state) {
            return 8;
        }
    }
}
