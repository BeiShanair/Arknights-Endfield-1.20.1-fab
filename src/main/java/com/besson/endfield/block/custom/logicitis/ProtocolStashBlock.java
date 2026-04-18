package com.besson.endfield.block.custom.logicitis;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.logicitis.ProtocolStashBlockEntity;
import com.besson.endfield.blockentity.custom.logicitis.ProtocolStashSideBlockEntity;
import net.minecraft.block.*;
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

public class ProtocolStashBlock extends ModBlockEntityWithFacing {

    public ProtocolStashBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory factory = this.createScreenHandlerFactory(state, world, pos);
            if (factory != null){
                player.openHandledScreen(factory);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.CONSUME;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ProtocolStashBlockEntity chest) {
                ItemScatterer.spawn(world, pos, chest.getItems());
                world.updateComparators(pos, this);
            }

            BlockPos[] sidePos = getAdjacentPositions(state, pos);
            for (BlockPos p : sidePos) {
                if (world.getBlockState(p).isOf(ModBlocks.PROTOCOL_STASH_SIDE)) {
                    world.breakBlock(p, false);
                }
            }
        }
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ProtocolStashBlockEntity(pos, state);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.PROTOCOL_STASH, ProtocolStashBlockEntity::tick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient()) {
            BlockPos[] sidePos = getAdjacentPositions(state, pos);

            for (BlockPos p : sidePos) {
                world.setBlockState(p, ModBlocks.PROTOCOL_STASH_SIDE.getDefaultState().with(FACING, state.get(FACING)));
                BlockEntity blockEntity = world.getBlockEntity(p);
                if (blockEntity instanceof ProtocolStashSideBlockEntity sideBlockEntity) {
                    sideBlockEntity.setParentPos(pos);
                }
            }
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if (!world.isClient()) {
            BlockPos[] sidePos = getAdjacentPositions(state, pos);
            for (BlockPos p : sidePos) {
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
                pos.offset(facing),
                pos.offset(facing).offset(left), pos.offset(facing).offset(right),
                pos.offset(right), pos.offset(left),
                pos.offset(back),
                pos.offset(back).offset(backLeft), pos.offset(back).offset(backRight)
        };
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("protocol_stash.tooltip"));
    }
}
