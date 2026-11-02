package com.besson.endfield.renderer.pre.powering;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.powering.ElectricPylonBlock;
import com.besson.endfield.blockentity.custom.powering.ElectricPylonBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;

public class ElectricPylonPreviewRenderer extends BasePreviewRenderer<ElectricPylonBlockEntity> {
    private static final ElectricPylonPreviewRenderer INSTANCE = new ElectricPylonPreviewRenderer();

    public static void register() {
        INSTANCE.start();
    }

    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.ELECTRIC_PYLON.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        Direction facing = client.player.getHorizontalFacing().getOpposite();
        return ModBlocks.ELECTRIC_PYLON.getDefaultState().with(ElectricPylonBlock.FACING, facing).with(ElectricPylonBlock.PREVIEW, true);
    }

    @Override
    protected ElectricPylonBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new ElectricPylonBlockEntity(pos, state);
    }
}
