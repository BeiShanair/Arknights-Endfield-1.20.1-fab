package com.besson.endfield.block.custom;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.MouldingUnitBlockEntity;
import com.besson.endfield.blockentity.custom.MouldingUnitSideBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MouldingUnitBlock extends ModBlockEntityWithFacing {
    public MouldingUnitBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MouldingUnitBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.MOULDING_UNIT, MouldingUnitBlockEntity::tick);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory screenHandlerFactory = (MouldingUnitBlockEntity) world.getBlockEntity(pos);
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
            if (blockEntity instanceof MouldingUnitBlockEntity be) {
                ItemScatterer.spawn(world, pos, be.getItems());
                world.updateComparators(pos, this);
            }

            BlockPos[] adjacentPositions = getAdjacentPositions(state, pos);

            for (BlockPos p : adjacentPositions) {
                if (world.getBlockState(p).getBlock() == ModBlocks.MOULDING_UNIT_SIDE) {
                    world.breakBlock(p, false);
                }
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient()) {
            BlockPos[] adjacentPositions = getAdjacentPositions(state, pos);

            for (BlockPos p : adjacentPositions) {
                world.setBlockState(p, ModBlocks.MOULDING_UNIT_SIDE.getDefaultState().with(FACING, state.get(FACING)));
                BlockEntity be = world.getBlockEntity(p);
                if (be instanceof MouldingUnitSideBlockEntity adjunct) {
                    adjunct.setParentPos(pos);
                }
            }
        }
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
    
    private BlockPos[] getAdjacentPositions(BlockState state, BlockPos pos) {
        Direction facing = state.get(FACING);
        Direction left = facing.rotateYCounterclockwise();
        Direction right = facing.rotateYClockwise();
        Direction back = facing.getOpposite();
        Direction backLeft = back.rotateYClockwise();
        Direction backRight = back.rotateYCounterclockwise();

        return new BlockPos[]{
                pos.offset(facing), pos.offset(facing).offset(left),
                pos.offset(right), pos.offset(left),
                pos.offset(facing).offset(right), pos.offset(back),
                pos.offset(back).offset(backLeft), pos.offset(back).offset(backRight)
        };
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("endfield.powerCost", 10).formatted(Formatting.GRAY));
    }
}
