package com.besson.endfield.block.custom;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.FittingUnitBlockEntity;
import com.besson.endfield.blockentity.custom.FittingUnitSideBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FittingUnitBlock extends ModBlockEntityWithFacing {
    public FittingUnitBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FittingUnitBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.FITTING_UNIT,
                (world1, pos, state1, blockEntity) ->
                    FittingUnitBlockEntity.tick(world1, pos, state1, (FittingUnitBlockEntity) blockEntity));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory screenHandlerFactory = (FittingUnitBlockEntity) world.getBlockEntity(pos);
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.CONSUME;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof FittingUnitBlockEntity) {
                ItemScatterer.spawn(world, pos, (FittingUnitBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }

            Direction facing = state.get(FACING);
            Direction left = facing.rotateYCounterclockwise();
            Direction right = facing.rotateYClockwise();
            Direction back = facing.getOpposite();
            Direction backLeft = back.rotateYClockwise();
            Direction backRight = back.rotateYCounterclockwise();

            BlockPos[] adjacentPositions = {
                    pos.offset(facing), pos.offset(facing).offset(left),
                    pos.offset(right), pos.offset(left),
                    pos.offset(facing).offset(right), pos.offset(back),
                    pos.offset(back).offset(backLeft), pos.offset(back).offset(backRight)
            };

            for (BlockPos p : adjacentPositions) {
                if (world.getBlockState(p).getBlock() == ModBlocks.FITTING_UNIT_SIDE) {
                    world.breakBlock(p, false);
                }
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient()) {
            Direction facing = state.get(FACING);
            Direction left = facing.rotateYCounterclockwise();
            Direction right = facing.rotateYClockwise();
            Direction back = facing.getOpposite();
            Direction backLeft = back.rotateYClockwise();
            Direction backRight = back.rotateYCounterclockwise();

            world.setBlockState(pos.offset(facing), ModBlocks.FITTING_UNIT_SIDE.getDefaultState().with(FACING, state.get(FACING)));
            world.setBlockState(pos.offset(right), ModBlocks.FITTING_UNIT_SIDE.getDefaultState().with(FACING, state.get(FACING)));
            world.setBlockState(pos.offset(left), ModBlocks.FITTING_UNIT_SIDE.getDefaultState().with(FACING, state.get(FACING)));
            world.setBlockState(pos.offset(facing).offset(right), ModBlocks.FITTING_UNIT_SIDE.getDefaultState().with(FACING, state.get(FACING)));
            world.setBlockState(pos.offset(facing).offset(left), ModBlocks.FITTING_UNIT_SIDE.getDefaultState().with(FACING, state.get(FACING)));
            world.setBlockState(pos.offset(back), ModBlocks.FITTING_UNIT_SIDE.getDefaultState().with(FACING, state.get(FACING)));
            world.setBlockState(pos.offset(back).offset(backLeft), ModBlocks.FITTING_UNIT_SIDE.getDefaultState().with(FACING, state.get(FACING)));
            world.setBlockState(pos.offset(back).offset(backRight), ModBlocks.FITTING_UNIT_SIDE.getDefaultState().with(FACING, state.get(FACING)));

            BlockPos[] adjacentPositions = {
                    pos.offset(facing), pos.offset(facing).offset(left),
                    pos.offset(right), pos.offset(left),
                    pos.offset(facing).offset(right), pos.offset(back),
                    pos.offset(back).offset(backLeft), pos.offset(back).offset(backRight)
            };

            for (BlockPos p : adjacentPositions) {
                BlockEntity be = world.getBlockEntity(p);
                if (be instanceof FittingUnitSideBlockEntity adjunct) {
                    adjunct.setParentPos(pos);
                }
            }
        }
    }
}
