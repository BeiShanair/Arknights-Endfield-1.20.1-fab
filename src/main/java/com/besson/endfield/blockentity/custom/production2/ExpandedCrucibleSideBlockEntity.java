package com.besson.endfield.blockentity.custom.production2;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ExpandedCrucibleSideBlockEntity extends BaseCrucibleSideBlockEntity {
    public ExpandedCrucibleSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EXPANDED_CRUCIBLE_SIDE, pos, state);
    }

    @Override
    public @Nullable ExpandedCrucibleBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof ExpandedCrucibleBlockEntity parent) {
            return parent;
        }
        return null;
    }
}
