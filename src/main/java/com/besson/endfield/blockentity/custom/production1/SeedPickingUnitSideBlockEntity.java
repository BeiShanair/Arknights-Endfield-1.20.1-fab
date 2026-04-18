package com.besson.endfield.blockentity.custom.production1;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOSideBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class SeedPickingUnitSideBlockEntity extends BaseIOSideBlockEntity {
    public SeedPickingUnitSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SEED_PICKING_UNIT_SIDE, pos, state);
    }

    public @Nullable SeedPickingUnitBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = this.world.getBlockEntity(parentPos);
        if (entity instanceof SeedPickingUnitBlockEntity parent) {
            return parent;
        }
        return null;
    }
}
