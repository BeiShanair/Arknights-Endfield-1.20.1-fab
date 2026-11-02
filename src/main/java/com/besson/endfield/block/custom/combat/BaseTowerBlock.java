package com.besson.endfield.block.custom.combat;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;

public abstract class BaseTowerBlock extends BlockWithEntity {
    public static BooleanProperty PREVIEW = ModBlockEntityWithFacing.PREVIEW;
    protected BaseTowerBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(PREVIEW, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PREVIEW);
    }
}
