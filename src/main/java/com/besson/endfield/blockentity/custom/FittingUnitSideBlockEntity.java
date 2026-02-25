package com.besson.endfield.blockentity.custom;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class FittingUnitSideBlockEntity extends BaseIOSideBlockEntity {
    public FittingUnitSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FITTING_UNIT_SIDE, pos, state);
    }

    public @Nullable FittingUnitBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = this.world.getBlockEntity(parentPos);
        if (entity instanceof FittingUnitBlockEntity parent) {
            return parent;
        }
        return null;
    }
}
