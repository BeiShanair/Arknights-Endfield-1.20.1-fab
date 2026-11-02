package com.besson.endfield.renderer.pre.combat;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.combat.SentryTowerBlock;
import com.besson.endfield.blockentity.custom.combat.SentryTowerBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class SentryTowerPreviewRenderer extends BasePreviewRenderer<SentryTowerBlockEntity> {
    private static final SentryTowerPreviewRenderer INSTANCE = new SentryTowerPreviewRenderer();
    
    public static void register() {
        INSTANCE.start();
    }
    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.SENTRY_TOWER.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        return ModBlocks.SENTRY_TOWER.getDefaultState().with(SentryTowerBlock.PREVIEW, true);
    }

    @Override
    protected SentryTowerBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new SentryTowerBlockEntity(pos, state);
    }
}
