package com.besson.endfield.blockentity.custom.production2;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOSideBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class PackagingUnitSideBlockEntity extends BaseIOSideBlockEntity {

    public PackagingUnitSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PACKAGING_UNIT_SIDE, pos, state);
    }

    public @Nullable PackagingUnitBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof PackagingUnitBlockEntity entity1) {
            return entity1;
        }
        return null;
    }
}
