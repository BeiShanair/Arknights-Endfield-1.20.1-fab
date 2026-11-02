package com.besson.endfield.block.custom.powering;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.item.custom.ControlItem;
import com.besson.endfield.utils.power.PowerNetworkManager;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BasePylonBlock extends ModBlockEntityWithFacing {
    public BasePylonBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            Item item = player.getStackInHand(hand).getItem();
            if (item instanceof ControlItem) return super.onUse(state, world, pos, player, hand, hit);

            PowerNetworkManager manager = PowerNetworkManager.get((ServerWorld) world);
            if (player != null) {
                player.sendMessage(Text.literal("----------------------"));
                player.sendMessage(Text.translatable("electric_pylon.title"));
                player.sendMessage(Text.translatable("electric_pylon.total_generated", manager.getLastTotalGenerated()).formatted(Formatting.GREEN));
                player.sendMessage(Text.translatable("electric_pylon.total_demand", manager.getLastTotalDemand()).formatted(Formatting.RED));
                player.sendMessage(Text.translatable("electric_pylon.stored_power", manager.getCurrentStoredEnergy()).formatted(Formatting.YELLOW));
                player.sendMessage(Text.literal("----------------------"));
                return ActionResult.SUCCESS;
            }
            return ActionResult.CONSUME;
        }
        return ActionResult.CONSUME;
    }
}
