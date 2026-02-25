package com.besson.endfield.blockentity.custom;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ShreddingUnitSideBlockEntity extends BaseIOSideBlockEntity {
    public ShreddingUnitSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SHREDDING_UNIT_SIDE, pos, state);
    }

    public @Nullable ShreddingUnitBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof ShreddingUnitBlockEntity entity1) {
            return entity1;
        }
        return null;
    }
}
