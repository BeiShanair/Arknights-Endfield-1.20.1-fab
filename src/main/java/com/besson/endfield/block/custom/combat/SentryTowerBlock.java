package com.besson.endfield.block.custom.combat;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.combat.SentryTowerBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SentryTowerBlock extends BlockWithEntity {
    public SentryTowerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SentryTowerBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.SENTRY_TOWER, SentryTowerBlockEntity::tick);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("endfield.range", 20).formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("endfield.attack", 116).formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("endfield.cooldown", 5).formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("endfield.powerCost", 20).formatted(Formatting.GRAY));
    }
}
