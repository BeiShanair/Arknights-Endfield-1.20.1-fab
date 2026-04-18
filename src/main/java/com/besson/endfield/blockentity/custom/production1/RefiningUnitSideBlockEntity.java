package com.besson.endfield.blockentity.custom.production1;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOSideBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class RefiningUnitSideBlockEntity extends BaseIOSideBlockEntity {
    public RefiningUnitSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.REFINING_UNIT_SIDE, pos, state);
    }

    public @Nullable RefiningUnitBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof RefiningUnitBlockEntity entity1) {
            return entity1;
        }
        return null;
    }
}
