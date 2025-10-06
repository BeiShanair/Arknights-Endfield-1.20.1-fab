package com.besson.endfield.block.custom;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.ProtocolAnchorCoreBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ProtocolAnchorCoreBlock extends ModBlockEntityWithFacing {

    public ProtocolAnchorCoreBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ProtocolAnchorCoreBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory screenHandlerFactory = ((ProtocolAnchorCoreBlockEntity) world.getBlockEntity(pos));
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.CONSUME;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.PROTOCOL_ANCHOR_CORE,
                (world1, pos, state1, blockEntity) ->
                    ProtocolAnchorCoreBlockEntity.tick(world1, pos, state1, (ProtocolAnchorCoreBlockEntity) blockEntity));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient()) {
            for (BlockPos p: BlockPos.iterate(pos.add(4, 0, 4), pos.add(-4, 0, -4))) {
                BlockState checkState = world.getBlockState(p);
                if (checkState.isOf(this)) {
                    continue;
                }
                world.setBlockState(p, Blocks.BARRIER.getDefaultState());
            }
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            for (BlockPos p: BlockPos.iterate(pos.add(4, 0, 4), pos.add(-4, 0, -4))) {
                BlockState checkState = world.getBlockState(p);
                if (checkState.isOf(Blocks.BARRIER)) {
                    world.breakBlock(p, false);
                }
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }
}
