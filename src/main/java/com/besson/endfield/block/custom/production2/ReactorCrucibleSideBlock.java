package com.besson.endfield.block.custom.production2;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.production2.BaseCrucibleSideBlockEntity;
import com.besson.endfield.blockentity.custom.production2.ReactorCrucibleSideBlockEntity;
import com.besson.endfield.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ReactorCrucibleSideBlock extends BaseCrucibleSideBlock {
    public ReactorCrucibleSideBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ReactorCrucibleSideBlockEntity(pos, state);
    }

    @Override
    protected BlockEntityType<? extends BaseCrucibleSideBlockEntity> sideType() {
        return ModBlockEntities.REACTOR_CRUCIBLE_SIDE;
    }

    @Override
    protected Item getPickItem() {
        return ModItems.REACTOR_CRUCIBLE;
    }
}
