package com.besson.endfield.block.custom.production2;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.production2.BaseCrucibleBlockEntity;
import com.besson.endfield.blockentity.custom.production2.ExpandedCrucibleBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExpandedCrucibleBlock extends BaseCrucibleBlock {
    public ExpandedCrucibleBlock(Settings settings) {
        super(settings, ExpandedCrucibleBlockEntity.POWER_COST_PER_TICK, "6×5");
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ExpandedCrucibleBlockEntity(pos, state);
    }

    @Override
    protected Block getSideBlock() {
        return ModBlocks.EXPANDED_CRUCIBLE_SIDE;
    }

    @Override
    protected BlockEntityType<? extends BaseCrucibleBlockEntity> crucibleType() {
        return ModBlockEntities.EXPANDED_CRUCIBLE;
    }
    
    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("endfield.powerCost", 100).formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("endfield.require_area", "6×5").formatted(Formatting.GRAY));
    }
}
