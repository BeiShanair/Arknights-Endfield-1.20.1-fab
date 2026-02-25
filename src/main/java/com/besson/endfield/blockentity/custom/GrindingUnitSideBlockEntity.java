package com.besson.endfield.blockentity.custom;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class GrindingUnitSideBlockEntity extends BaseIOSideBlockEntity {

    public GrindingUnitSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GRINDING_UNIT_SIDE, pos, state);
    }

    public @Nullable GrindingUnitBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = this.world.getBlockEntity(parentPos);
        if (entity instanceof GrindingUnitBlockEntity parent) {
            return parent;
        }
        return null;
    }
}
