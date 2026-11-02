package com.besson.endfield.fluid;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;

public abstract class SewageFluid extends ModBaseFluid {
    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_SEWAGE;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_SEWAGE;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.SEWAGE_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.SEWAGE_BLOCK.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    public static class Flowing extends SewageFluid {
        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }
    }

    public static class Source extends SewageFluid {
        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }
}
