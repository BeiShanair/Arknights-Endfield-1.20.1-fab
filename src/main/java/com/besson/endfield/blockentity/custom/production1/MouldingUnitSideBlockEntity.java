package com.besson.endfield.blockentity.custom.production1;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOSideBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class MouldingUnitSideBlockEntity extends BaseIOSideBlockEntity {
    public MouldingUnitSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MOULDING_UNIT_SIDE, pos, state);
    }

    public @Nullable MouldingUnitBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = this.world.getBlockEntity(parentPos);
        if (entity instanceof MouldingUnitBlockEntity parent) {
            return parent;
        }
        return null;
    }
}
