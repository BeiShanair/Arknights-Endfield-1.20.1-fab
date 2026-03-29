package com.besson.endfield.block.custom.powering;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.powering.RelayTowerBlockEntity;
import com.besson.endfield.utils.PowerNetworkManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RelayTowerBlock extends ModBlockEntityWithFacing {

    public RelayTowerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RelayTowerBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.RELAY_TOWER,
                (world1, pos, state1, blockEntity) ->
                        RelayTowerBlockEntity.tick(world1, pos, state1, (RelayTowerBlockEntity) blockEntity));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
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
        return ActionResult.PASS;
    }
}
