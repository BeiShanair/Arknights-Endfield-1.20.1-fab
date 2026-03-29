package com.besson.endfield.block.custom.powering;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.powering.ProtocolAnchorCoreBlockEntity;
import com.besson.endfield.blockentity.custom.powering.ProtocolAnchorCorePortBlockEntity;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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
        if (world.isClient()) return ActionResult.CONSUME;

        BlockEntity entity = world.getBlockEntity(pos);
        if (!(entity instanceof ProtocolAnchorCorePortBlockEntity port)) return ActionResult.CONSUME;

        ItemStack heldItem = player.getStackInHand(hand);
        if (heldItem.isEmpty() && !player.isSneaking()) {
            ProtocolAnchorCoreBlockEntity parent = port.getParentBlock();
            if (parent != null) {
                player.openHandledScreen(parent);
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }

        if (heldItem.isEmpty() && player.isSneaking()) {
            port.clearFilter();
            if (player instanceof ServerPlayerEntity) {
                ((ServerPlayerEntity) player).sendMessage(Text.literal("Cleared filter"), false);
            }
        } else {
            port.setFilter(heldItem);
            if (player instanceof ServerPlayerEntity) {
                ((ServerPlayerEntity) player).sendMessage(Text.literal("Set filter to: " + heldItem.getName().getString()), false);
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

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("protocol_anchor_core_port.tooltip1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("protocol_anchor_core_port.tooltip2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("protocol_anchor_core_port.tooltip3").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("protocol_anchor_core_port.tooltip4").formatted(Formatting.GRAY));
    }
}
