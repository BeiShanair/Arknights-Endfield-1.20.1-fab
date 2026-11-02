package com.besson.endfield.renderer.pre.combat;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.combat.LNTowerBlock;
import com.besson.endfield.blockentity.custom.combat.LNTowerBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class LNTowerPreviewRenderer extends BasePreviewRenderer<LNTowerBlockEntity> {
    private static final LNTowerPreviewRenderer INSTANCE = new LNTowerPreviewRenderer();
    
    public static void register() {
        INSTANCE.start();
    }
    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.LN_TOWER.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        return ModBlocks.LN_TOWER.getDefaultState().with(LNTowerBlock.PREVIEW, true);
    }

    @Override
    protected LNTowerBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new LNTowerBlockEntity(pos, state);
    }
}
