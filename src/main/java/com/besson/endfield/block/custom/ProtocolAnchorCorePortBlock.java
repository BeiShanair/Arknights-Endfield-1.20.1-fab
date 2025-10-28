package com.besson.endfield.block.custom;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.ProtocolAnchorCoreBlockEntity;
import com.besson.endfield.blockentity.custom.ProtocolAnchorCorePortBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ProtocolAnchorCorePortBlock extends ModBlockEntityWithFacing {

    public ProtocolAnchorCorePortBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ProtocolAnchorCorePortBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            BlockEntity entity = world.getBlockEntity(pos);
            if (entity instanceof ProtocolAnchorCorePortBlockEntity entity1) {
                ProtocolAnchorCoreBlockEntity parent = entity1.getParentBlock();
                if (parent != null) {
                    player.openHandledScreen(parent);
                    return ActionResult.SUCCESS;
                }
            }
        }
        return ActionResult.CONSUME;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.PROTOCOL_ANCHOR_CORE_PORT,
                (world1, pos, state1, blockEntity) ->
                        ProtocolAnchorCorePortBlockEntity.tick(world1, pos, state1, (ProtocolAnchorCorePortBlockEntity) blockEntity));
    }
}
