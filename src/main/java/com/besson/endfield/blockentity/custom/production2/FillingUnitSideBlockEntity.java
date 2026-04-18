package com.besson.endfield.blockentity.custom.production2;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOSideBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class FillingUnitSideBlockEntity extends BaseIOSideBlockEntity {
    public FillingUnitSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FILLING_UNIT_SIDE, pos, state);
    }

    public @Nullable FillingUnitBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = this.world.getBlockEntity(parentPos);
        if (entity instanceof FillingUnitBlockEntity parent) {
            return parent;
        }
        return null;
    }
}
