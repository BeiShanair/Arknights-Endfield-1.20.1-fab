package com.besson.endfield.block.custom;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.GearingUnitBlockEntity;
import com.besson.endfield.blockentity.custom.GearingUnitSideBlockEntity;
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

public class GearingUnitBlock extends ModBlockEntityWithFacing {
    public GearingUnitBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GearingUnitBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.GEARING_UNIT,
                (world1, pos, state1, blockEntity) ->
                    GearingUnitBlockEntity.tick(world1, pos, state1, (GearingUnitBlockEntity) blockEntity));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory screenHandlerFactory = ((GearingUnitBlockEntity) world.getBlockEntity(pos));
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
            if (blockEntity instanceof GearingUnitBlockEntity) {
                ItemScatterer.spawn(world, pos, (GearingUnitBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }

            Direction facing = state.get(FACING);
            Direction left = facing.rotateYCounterclockwise();
            Direction right = facing.rotateYClockwise();
            Direction back = facing.getOpposite();
            Direction backLeft = back.rotateYClockwise();
            Direction backRight = back.rotateYCounterclockwise();

            BlockPos[] adjacentPositions = {
                    pos.offset(facing),
                    pos.offset(facing).offset(left), pos.offset(facing).offset(right),
                    pos.offset(facing).offset(left, 2), pos.offset(facing).offset(right, 2), pos.offset(facing).offset(right, 3),
                    pos.offset(right), pos.offset(left),
                    pos.offset(right, 2), pos.offset(right, 3), pos.offset(left, 2),
                    pos.offset(back), pos.offset(back, 2),
                    pos.offset(back).offset(backLeft), pos.offset(back).offset(backRight),
                    pos.offset(back).offset(backLeft, 2), pos.offset(back).offset(backRight, 2), pos.offset(back).offset(backRight, 3),
                    pos.offset(back, 2).offset(backLeft), pos.offset(back, 2).offset(backRight),
                    pos.offset(back, 2).offset(backLeft, 2), pos.offset(back, 2).offset(backRight, 2), pos.offset(back, 2).offset(backRight, 3)
            };

            for (BlockPos p : adjacentPositions) {
                if (world.getBlockState(p).getBlock() == ModBlocks.GEARING_UNIT_SIDE) {
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

            BlockPos[] sidePositions = {
                    pos.offset(facing),
                    pos.offset(facing).offset(left), pos.offset(facing).offset(right),
                    pos.offset(facing).offset(left, 2), pos.offset(facing).offset(right, 2), pos.offset(facing).offset(right, 3),
                    pos.offset(right), pos.offset(left),
                    pos.offset(right, 2), pos.offset(right, 3), pos.offset(left, 2),
                    pos.offset(back), pos.offset(back, 2),
                    pos.offset(back).offset(backLeft), pos.offset(back).offset(backRight),
                    pos.offset(back).offset(backLeft, 2), pos.offset(back).offset(backRight, 2), pos.offset(back).offset(backRight, 3),
                    pos.offset(back, 2).offset(backLeft), pos.offset(back, 2).offset(backRight),
                    pos.offset(back, 2).offset(backLeft, 2), pos.offset(back, 2).offset(backRight, 2), pos.offset(back, 2).offset(backRight, 3)
            };

            for (BlockPos p : sidePositions) {
                world.setBlockState(p, ModBlocks.GEARING_UNIT_SIDE.getDefaultState().with(FACING, state.get(FACING)));
                BlockEntity blockEntity = world.getBlockEntity(p);
                if (blockEntity instanceof GearingUnitSideBlockEntity sideBlockEntity) {
                    sideBlockEntity.setParentPos(pos);
                }
            }
        }
    }
}
