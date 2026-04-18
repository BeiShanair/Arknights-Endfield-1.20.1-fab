package com.besson.endfield.block.custom.logicitis;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.custom.logicitis.DepotBusSectionBlockEntity;
import com.besson.endfield.blockentity.custom.logicitis.DepotBusSectionSideBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class DepotBusSectionBlock extends ModBlockEntityWithFacing {
    public DepotBusSectionBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DepotBusSectionBlockEntity(pos, state);
    }
    
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (!world.isClient()) {
            BlockPos[] sidePositions = getAdjacentPositions(state, pos);

            for (BlockPos p : sidePositions) {
                if (!world.getBlockState(p).getBlock().getDefaultState().isAir()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockPos[] sidePositions = getAdjacentPositions(state, pos);

            for (BlockPos p : sidePositions) {
                world.breakBlock(p, false);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient()) {
            BlockPos[] sidePos = getAdjacentPositions(state, pos);
            for (BlockPos p : sidePos) {
                world.setBlockState(p, ModBlocks.DEPOT_BUS_SECTION_SIDE.getDefaultState().with(FACING, state.get(FACING)));
                BlockEntity blockEntity = world.getBlockEntity(p);
                if (blockEntity instanceof DepotBusSectionSideBlockEntity side) {
                    side.setParentPos(pos);
                }
            }
        }
    }

    private BlockPos[] getAdjacentPositions(BlockState state, BlockPos pos) {
        Direction facing = state.get(FACING);
        Direction back = facing.getOpposite();

        return new BlockPos[]{
                pos.offset(facing), pos.offset(back),
                pos.offset(facing).up(), pos.offset(back).up(), pos.up(),
                pos.offset(facing).up(2), pos.offset(back).up(2), pos.up(2)
        };
    }
}
