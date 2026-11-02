package com.besson.endfield.block.custom.resourcing;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.resourcing.FluidPumpBlockEntity;
import com.besson.endfield.blockentity.custom.resourcing.FluidPumpSideBlockEntity;
import com.besson.endfield.util.MultiBlockPlacementUtil;
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
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FluidPumpBlock extends ModBlockEntityWithFacing {
    public FluidPumpBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FluidPumpBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.FLUID_PUMP, FluidPumpBlockEntity::tick);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory screenHandlerFactory = ((FluidPumpBlockEntity) world.getBlockEntity(pos));
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
            for (BlockPos adjacentPos : adjacentPositions) {
                if (world.getBlockState(adjacentPos).getBlock() == ModBlocks.FLUID_PUMP_SIDE) {
                    world.breakBlock(adjacentPos, false);
                }
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient()) {
            BlockPos[] adjacentPositions = getAdjacentPositions(state, pos);
            for (BlockPos adjacentPos : adjacentPositions) {
                world.setBlockState(adjacentPos, ModBlocks.FLUID_PUMP_SIDE.getDefaultState().with(FACING, state.get(FACING)));
                BlockEntity blockEntity = world.getBlockEntity(adjacentPos);
                if (blockEntity instanceof FluidPumpSideBlockEntity sideBlockEntity) {
                    sideBlockEntity.setParentPos(pos);
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
                pos.offset(facing).up(2)
        };
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("endfield.powerCost", 10).formatted(Formatting.GRAY));
    }
}
