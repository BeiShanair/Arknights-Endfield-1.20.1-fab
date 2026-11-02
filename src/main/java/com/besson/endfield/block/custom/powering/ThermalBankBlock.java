package com.besson.endfield.block.custom.powering;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.powering.ThermalBankBlockEntity;
import com.besson.endfield.blockentity.custom.powering.ThermalBankSideBlockEntity;
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
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
        return checkType(type, ModBlockEntities.THERMAL_BANK, ThermalBankBlockEntity::tick);
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
            if (blockEntity instanceof ThermalBankBlockEntity be) {
                ItemScatterer.spawn(world, pos, be.getItems());
                world.updateComparators(pos, this);
            }
            BlockPos[] positionsToCheck = getAdjacentPositions(state, pos);
            
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
            BlockPos[] positionsToSetParent = getAdjacentPositions(state, pos);
            
            for (BlockPos checkPos : positionsToSetParent) {
                world.setBlockState(checkPos, ModBlocks.THERMAL_BANK_SIDE.getDefaultState().with(FACING, state.get(FACING)));
                BlockEntity entity = world.getBlockEntity(checkPos);
                if (entity instanceof ThermalBankSideBlockEntity entity1) {
                    entity1.setParentPos(pos);
                }
            }
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos[] sidePositions = getAdjacentPositions(state, pos);

        for (BlockPos p : sidePositions) {
            if (!MultiBlockPlacementUtil.isReplaceable(world, p)) {
                return false;
            }
        }
        return true;
    }
    
    private BlockPos[] getAdjacentPositions(BlockState state, BlockPos pos) {
        Direction facing = state.get(FACING);
        Direction right = facing.rotateYClockwise();
        Direction back = facing.getOpposite();
        Direction backRight = back.rotateYCounterclockwise();

        return new BlockPos[]{
                pos.offset(back),
                pos.offset(back).offset(backRight),
                pos.offset(right)
        };
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("endfield.require_area", "2×2").formatted(Formatting.GRAY));
    }
}
