package com.besson.endfield.blockentity.custom.resourcing;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOSideBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class FluidPumpSideBlockEntity extends BaseIOSideBlockEntity {
    public FluidPumpSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLUID_PUMP_SIDE, pos, state);
    }

    @Override
    public @Nullable FluidPumpBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof FluidPumpBlockEntity parent) {
            return parent;
        }
        return null;
    }
}
