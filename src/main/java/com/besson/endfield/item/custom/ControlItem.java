package com.besson.endfield.item.custom;

import com.besson.endfield.block.custom.ElectricPylonBlock;
import com.besson.endfield.block.custom.ProtocolAnchorCoreBlock;
import com.besson.endfield.block.custom.RelayTowerBlock;
import com.besson.endfield.blockentity.custom.ElectricPylonBlockEntity;
import com.besson.endfield.blockentity.custom.RelayTowerBlockEntity;
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

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();
        BlockPos pos = context.getBlockPos();

        if (!world.isClient() && player != null) {
            // 蹲下：设置起点（只允许中继器或协议核心）
            if (player.isSneaking()) {
                if (clickedBlock instanceof ElectricPylonBlock) {
                    player.sendMessage(Text.literal("请选择协议核心或中继器开始电力运输！"));
                    return ActionResult.CONSUME;
                }

                if (clickedBlock instanceof RelayTowerBlock || clickedBlock instanceof ProtocolAnchorCoreBlock) {
                    startPos = pos;
                    player.sendMessage(Text.literal("开始电力运输，起点已记录： " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ()));
                    return ActionResult.CONSUME;
                }

                // 其他方块不处理
                return ActionResult.CONSUME;
            }

            // 非蹲下：尝试完成运输（点击中继器或供电桩）
            if (clickedBlock instanceof RelayTowerBlock || clickedBlock instanceof ElectricPylonBlock) {
                if (startPos != null) {
                    BlockPos recorded = startPos;
                    startPos = null;

                    // 设置目标方块实体的 connectedNode
                    BlockEntity be = world.getBlockEntity(pos);
                    if (be != null) {
                        if (be instanceof RelayTowerBlockEntity) {
                            ((RelayTowerBlockEntity) be).setConnectedNode(recorded);
                        } else if (be instanceof ElectricPylonBlockEntity) {
                            ((ElectricPylonBlockEntity) be).setConnectedNode(recorded);
                        }
                        // 标记脏数据/更新监听器（若需要）
                        be.markDirty();
                        world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
                    }

                    // 这里可以加入实际的电力传输逻辑
                    player.sendMessage(Text.literal("电力运输完成！起点：" + recorded.getX() + "," + recorded.getY() + "," + recorded.getZ()
                            + " -> 终点：" + pos.getX() + "," + pos.getY() + "," + pos.getZ()));
                    return ActionResult.CONSUME;
                } else {
                    player.sendMessage(Text.literal("未记录起点，请先蹲下并右键中继器或协议核心开始电力运输！"));
                    return ActionResult.CONSUME;
                }
            }
        }

        return ActionResult.CONSUME;
    }
}
