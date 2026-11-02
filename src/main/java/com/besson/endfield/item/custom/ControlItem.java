package com.besson.endfield.item.custom;

import com.besson.endfield.block.custom.logicitis.FluidPipeBlock;
import com.besson.endfield.block.custom.powering.*;
import com.besson.endfield.block.custom.production2.BaseCrucibleSideBlock;
import com.besson.endfield.blockentity.custom.logicitis.FluidPipeBlockEntity;
import com.besson.endfield.blockentity.custom.powering.BasePylonBlockEntity;
import com.besson.endfield.blockentity.custom.powering.BaseRelayBlockEntity;
import com.besson.endfield.blockentity.custom.production2.BaseCrucibleSideBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ControlItem extends Item {
    private static BlockPos startPos = null;

    public ControlItem(Settings settings) {
        super(settings);
    }

    private static boolean canStartPowerTransport(Block block) {
        return block instanceof BaseRelayBlock
                || block instanceof BasePylonBlock
                || block instanceof ProtocolAnchorCoreBlock;
    }

    private static boolean canEndPowerTransport(Block block) {
        return block instanceof BaseRelayBlock || block instanceof BasePylonBlock;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getBlockPos();
        Block clickedBlock = world.getBlockState(pos).getBlock();

        if (world.isClient() || player == null) {
            return ActionResult.CONSUME;
        }

        // 流体管道
        if (clickedBlock instanceof FluidPipeBlock) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof FluidPipeBlockEntity pipe) {
                pipe.mode = pipe.mode.next();
                pipe.markDirty();
                world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 3);

                String msgKey = switch (pipe.mode) {
                    case TRANSFER -> "message.control_item.pipe_mode.transfer";
                    case EXTRACT -> "message.control_item.pipe_mode.extract";
                    case INSERT -> "message.control_item.pipe_mode.insert";
                };
                player.sendMessage(Text.translatable(msgKey));
            }
            return ActionResult.CONSUME;
        }

        // 反应池
        if (clickedBlock instanceof BaseCrucibleSideBlock) {
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof BaseCrucibleSideBlockEntity side) {
                side.cycleFluidPort();
                int portIndex = side.getFluidPortIndex();
                if (portIndex < 0) {
                    player.sendMessage(Text.translatable("message.control_item.crucible_port.unbound"));
                } else {
                    player.sendMessage(Text.translatable("message.control_item.crucible_port.bound", portIndex + 1, portIndex + 1));
                }
            }
            return ActionResult.CONSUME;
        }

        // 蹲下：记录电力运输起点（中继器 / 供电桩 / 协议核心均可）
        if (player.isSneaking()) {
            if (canStartPowerTransport(clickedBlock)) {
                startPos = pos;
                player.sendMessage(Text.translatable("message.control_item.start_recorded",
                        pos.getX(), pos.getY(), pos.getZ()));
            }
            return ActionResult.CONSUME;
        }

        // 非蹲下：尝试完成运输（点击中继器或供电桩）
        if (canEndPowerTransport(clickedBlock)) {
            if (startPos == null) {
                player.sendMessage(Text.translatable("message.control_item.no_start"));
                return ActionResult.CONSUME;
            }

            BlockPos recorded = startPos;
            startPos = null;

            // 设置目标方块实体的 connectedNode
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof BaseRelayBlockEntity relay) {
                relay.setConnectedNode(recorded);
            } else if (be instanceof BasePylonBlockEntity pylon) {
                pylon.setConnectedNode(recorded);
            }
            if (be != null) {
                be.markDirty();
                world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
            }

            player.sendMessage(Text.translatable("message.control_item.completed",
                    recorded.getX(), recorded.getY(), recorded.getZ(),
                    pos.getX(), pos.getY(), pos.getZ()));
            return ActionResult.CONSUME;
        }

        return ActionResult.CONSUME;
    }
}
