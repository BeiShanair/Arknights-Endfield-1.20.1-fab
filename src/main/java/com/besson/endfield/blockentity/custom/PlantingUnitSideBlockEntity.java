package com.besson.endfield.blockentity.custom;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class PlantingUnitSideBlockEntity extends BaseIOSideBlockEntity {
    public PlantingUnitSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PLANTING_UNIT_SIDE, pos, state);
    }

    public @Nullable PlantingUnitBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = this.world.getBlockEntity(parentPos);
        if (entity instanceof PlantingUnitBlockEntity parent) {
            return parent;
        }
        return null;
    }
}
