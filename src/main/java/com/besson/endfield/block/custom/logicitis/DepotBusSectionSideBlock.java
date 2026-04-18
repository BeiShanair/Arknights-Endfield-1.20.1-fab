package com.besson.endfield.block.custom.logicitis;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.blockentity.custom.logicitis.DepotBusSectionBlockEntity;
import com.besson.endfield.blockentity.custom.logicitis.DepotBusSectionSideBlockEntity;
import com.besson.endfield.item.ModItems;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DepotBusSectionSideBlock extends ModBlockEntityWithFacing {
    public DepotBusSectionSideBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DepotBusSectionSideBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DepotBusSectionSideBlockEntity sideBlockEntity) {
                DepotBusSectionBlockEntity parent = sideBlockEntity.getParentBlock();
                if (parent != null) {
                    BlockPos parentPos = parent.getPos();
                    world.breakBlock(parentPos, true);
                }
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.DEPOT_BUS_SECTION_ITEM);
    }
}
