package com.besson.endfield.block.custom.logicitis;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.blockentity.custom.logicitis.DepotUnloaderBlockEntity;
import com.besson.endfield.blockentity.custom.logicitis.DepotUnloaderSideBlockEntity;
import com.besson.endfield.item.ModItems;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DepotUnloaderSideBlock extends ModBlockEntityWithFacing {
    public DepotUnloaderSideBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DepotUnloaderSideBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DepotUnloaderSideBlockEntity sideBlockEntity) {
                DepotUnloaderBlockEntity parent = sideBlockEntity.getParentBlock();
                if (parent != null) {
                    BlockPos parentPos = parent.getPos();
                    world.breakBlock(parentPos, true);
                }
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DepotUnloaderSideBlockEntity side) {
                DepotUnloaderBlockEntity parent = side.getParentBlock();
                if (parent != null) {
                    player.openHandledScreen(parent);
                    return ActionResult.SUCCESS;
                }
            }
        }
        return ActionResult.CONSUME;
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(ModItems.DEPOT_UNLOADER_ITEM);
    }
}
