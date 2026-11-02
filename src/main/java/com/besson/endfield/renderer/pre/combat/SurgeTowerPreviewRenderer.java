package com.besson.endfield.renderer.pre.combat;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.combat.SurgeTowerBlock;
import com.besson.endfield.blockentity.custom.combat.SurgeTowerBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class SurgeTowerPreviewRenderer extends BasePreviewRenderer<SurgeTowerBlockEntity> {
    private static final SurgeTowerPreviewRenderer INSTANCE = new SurgeTowerPreviewRenderer();
    
    public static void register() {
        INSTANCE.start();
    }
    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.SURGE_TOWER.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        return ModBlocks.SURGE_TOWER.getDefaultState().with(SurgeTowerBlock.PREVIEW, true);
    }

    @Override
    protected SurgeTowerBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new SurgeTowerBlockEntity(pos, state);
    }
}
