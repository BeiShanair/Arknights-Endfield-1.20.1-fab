package com.besson.endfield.block.custom.depot;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.custom.depot.FluidTankBlockEntity;
import com.besson.endfield.blockentity.custom.depot.FluidTankSideBlockEntity;
import com.besson.endfield.util.MultiBlockPlacementUtil;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class FluidTankBlock extends ModBlockEntityWithFacing {

    public FluidTankBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FluidTankBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory screenHandlerFactory = (FluidTankBlockEntity) world.getBlockEntity(pos);
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockPos[] adjacentPositions = getAdjacentPositions(state, pos);
            for (BlockPos side : adjacentPositions) {
                if (world.getBlockState(side).getBlock() == ModBlocks.FLUID_TANK_SIDE) {
                    world.breakBlock(side, false);
                }
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient()) {
            BlockPos[] adjacentPositions = getAdjacentPositions(state, pos);
            for (BlockPos side : adjacentPositions) {
                world.setBlockState(side, ModBlocks.FLUID_TANK_SIDE.getDefaultState().with(FACING, state.get(FACING)));
                if (world.getBlockEntity(side) instanceof FluidTankSideBlockEntity sideBlock) {
                    sideBlock.setParentPos(pos);
                }
            }
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        for (BlockPos p : getAdjacentPositions(state, pos)) {
            if (!MultiBlockPlacementUtil.isReplaceable(world, p)) {
                return false;
            }
        }
        return true;
    }

    private BlockPos[] getAdjacentPositions(BlockState state, BlockPos pos) {
        Direction facing = state.get(FACING);

        return new BlockPos[]{
                pos.offset(facing).up(2),
                pos.offset(facing.getOpposite()).up(2),
        };
    }
}
