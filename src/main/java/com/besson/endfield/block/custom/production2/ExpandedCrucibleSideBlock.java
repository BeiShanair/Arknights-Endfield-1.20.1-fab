package com.besson.endfield.block.custom.production2;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.production2.BaseCrucibleSideBlockEntity;
import com.besson.endfield.blockentity.custom.production2.ExpandedCrucibleSideBlockEntity;
import com.besson.endfield.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ExpandedCrucibleSideBlock extends BaseCrucibleSideBlock {
    public ExpandedCrucibleSideBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ExpandedCrucibleSideBlockEntity(pos, state);
    }

    @Override
    protected BlockEntityType<? extends BaseCrucibleSideBlockEntity> sideType() {
        return ModBlockEntities.EXPANDED_CRUCIBLE_SIDE;
    }

    @Override
    protected Item getPickItem() {
        return ModItems.EXPANDED_CRUCIBLE;
    }
}
