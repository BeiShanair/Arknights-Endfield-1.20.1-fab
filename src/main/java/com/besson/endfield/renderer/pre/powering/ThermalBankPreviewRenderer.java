package com.besson.endfield.renderer.pre.powering;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.powering.ThermalBankBlock;
import com.besson.endfield.blockentity.custom.powering.ThermalBankBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

public class ThermalBankPreviewRenderer extends BasePreviewRenderer<ThermalBankBlockEntity> {
    private static final ThermalBankPreviewRenderer INSTANCE = new ThermalBankPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.THERMAL_BANK.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.THERMAL_BANK.getDefaultState().with(ThermalBankBlock.FACING, facing).with(ThermalBankBlock.PREVIEW, true);
    }

    @Override
    protected ThermalBankBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new ThermalBankBlockEntity(pos, state);
    }
}
