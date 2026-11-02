package com.besson.endfield.blockentity.custom.production1;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOSideBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class WaterTreatmentUnitSideBlockEntity extends BaseIOSideBlockEntity {
    public WaterTreatmentUnitSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WATER_TREATMENT_UNIT_SIDE, pos, state);
    }

    @Override
    public @Nullable WaterTreatmentUnitBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof WaterTreatmentUnitBlockEntity parent) {
            return parent;
        }
        return null;
    }
}
