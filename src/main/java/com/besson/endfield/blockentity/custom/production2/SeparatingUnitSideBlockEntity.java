package com.besson.endfield.blockentity.custom.production2;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOSideBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class SeparatingUnitSideBlockEntity extends BaseIOSideBlockEntity {
    public SeparatingUnitSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SEPARATING_UNIT_SIDE, pos, state);
    }
    
    public @Nullable SeparatingUnitBlockEntity getParentBlock(){
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof SeparatingUnitBlockEntity parent) {
            return parent;
        }
        return null;
    }
}
