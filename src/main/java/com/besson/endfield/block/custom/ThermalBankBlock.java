package com.besson.endfield.block.custom;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.ThermalBankBlockEntity;
import com.besson.endfield.blockentity.custom.ThermalBankSideBlockEntity;
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

public class ThermalBankBlock extends ModBlockEntityWithFacing {
    public ThermalBankBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ThermalBankBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.THERMAL_BANK,
                (world1, pos, state1, blockEntity) ->
                    ThermalBankBlockEntity.tick(world1, pos, state1, (ThermalBankBlockEntity) blockEntity));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory screenHandlerFactory = ((ThermalBankBlockEntity) world.getBlockEntity(pos));
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
            if (blockEntity instanceof ThermalBankBlockEntity) {
                ItemScatterer.spawn(world, pos, (ThermalBankBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }

            Direction facing = state.get(FACING);
            Direction right = facing.rotateYClockwise();
            Direction back = facing.getOpposite();
            Direction backRight = back.rotateYCounterclockwise();

            BlockPos[] positionsToCheck = {
                pos.offset(right),
                pos.offset(back),
                pos.offset(back).offset(backRight)
            };
            for (BlockPos checkPos : positionsToCheck) {
                if (world.getBlockState(checkPos).getBlock() == ModBlocks.THERMAL_BANK_SIDE) {
                    world.breakBlock(checkPos, false);
                }
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient()) {
            Direction facing = state.get(FACING);
            Direction right = facing.rotateYClockwise();
            Direction back = facing.getOpposite();
            Direction backRight = back.rotateYCounterclockwise();

            world.setBlockState(pos.offset(right), ModBlocks.THERMAL_BANK_SIDE.getDefaultState().with(FACING, state.get(FACING)));
            world.setBlockState(pos.offset(back), ModBlocks.THERMAL_BANK_SIDE.getDefaultState().with(FACING, state.get(FACING)));
            world.setBlockState(pos.offset(back).offset(backRight), ModBlocks.THERMAL_BANK_SIDE.getDefaultState().with(FACING, state.get(FACING)));

            BlockPos[] positionsToSetParent = {
                pos.offset(back),
                pos.offset(back).offset(backRight),
                pos.offset(right)
            };
            for (BlockPos checkPos : positionsToSetParent) {
                BlockEntity entity = world.getBlockEntity(checkPos);
                if (entity instanceof ThermalBankSideBlockEntity entity1) {
                    entity1.setParentPos(pos);
                }
            }
        }
    }
}
