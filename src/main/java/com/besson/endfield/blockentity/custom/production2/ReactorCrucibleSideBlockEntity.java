package com.besson.endfield.blockentity.custom.production2;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ReactorCrucibleSideBlockEntity extends BaseCrucibleSideBlockEntity {
    public ReactorCrucibleSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.REACTOR_CRUCIBLE_SIDE, pos, state);
    }

    @Override
    public @Nullable ReactorCrucibleBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof ReactorCrucibleBlockEntity parent) {
            return parent;
        }
        return null;
    }
}
