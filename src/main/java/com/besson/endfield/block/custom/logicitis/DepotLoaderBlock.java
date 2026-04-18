package com.besson.endfield.block.custom.logicitis;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.logicitis.DepotLoaderBlockEntity;
import com.besson.endfield.blockentity.custom.logicitis.DepotLoaderSideBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DepotLoaderBlock extends ModBlockEntityWithFacing {
    public DepotLoaderBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DepotLoaderBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.DEPOT_LOADER, DepotLoaderBlockEntity::tick);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockPos[] adjacentPositions = getAdjacentPositions(state, pos);
            for (BlockPos p : adjacentPositions) {
                if (world.getBlockState(p).getBlock() == ModBlocks.DEPOT_LOADER_SIDE) {
                    world.breakBlock(p, false);
                }
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient()) {
            BlockPos[] sidePos = getAdjacentPositions(state, pos);

            for (BlockPos p : sidePos) {
                world.setBlockState(p, ModBlocks.DEPOT_LOADER_SIDE.getDefaultState().with(FACING, state.get(FACING)));
                BlockEntity be = world.getBlockEntity(p);
                if (be instanceof DepotLoaderSideBlockEntity side) {
                    side.setParentPos(pos);
                }
            }
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (!world.isClient()) {
            BlockPos[] side = getAdjacentPositions(state, pos);
            for (BlockPos p : side) {
                if (!world.getBlockState(p).getBlock().getDefaultState().isAir()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private BlockPos[] getAdjacentPositions(BlockState state, BlockPos pos) {
        Direction facing = state.get(FACING);
        Direction left = facing.rotateYCounterclockwise();
        Direction right = facing.rotateYClockwise();

        return new BlockPos[]{
                pos.offset(left), pos.offset(left).up(),
                pos.up(),
                pos.offset(right), pos.offset(right).up()
        };
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("depot_unloader.tooltip"));
    }
}
