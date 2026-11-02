package com.besson.endfield.fluid;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;

public abstract class PrecipitationAcidFluid extends ModBaseFluid {
    @Override
    public Fluid getFlowing() {
        return ModFluids.FLOWING_PRECIPITATION_ACID;
    }

    @Override
    public Fluid getStill() {
        return ModFluids.STILL_PRECIPITATION_ACID;
    }

    @Override
    public Item getBucketItem() {
        return ModItems.PRECIPITATION_ACID_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return ModBlocks.PRECIPITATION_ACID_BLOCK.getDefaultState().with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }
    
    public static class Flowing extends PrecipitationAcidFluid {
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
    
    public static class Source extends PrecipitationAcidFluid {
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
