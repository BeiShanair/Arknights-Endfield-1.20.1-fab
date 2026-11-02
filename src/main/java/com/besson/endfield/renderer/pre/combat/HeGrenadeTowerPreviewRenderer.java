package com.besson.endfield.renderer.pre.combat;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.combat.HeGrenadeTowerBlock;
import com.besson.endfield.blockentity.custom.combat.HeGrenadeTowerBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class HeGrenadeTowerPreviewRenderer extends BasePreviewRenderer<HeGrenadeTowerBlockEntity> {
    private static final HeGrenadeTowerPreviewRenderer INSTANCE = new HeGrenadeTowerPreviewRenderer();
    
    public static void register() {
        INSTANCE.start();
    }
    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.HE_GRENADE_TOWER.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        return ModBlocks.HE_GRENADE_TOWER.getDefaultState().with(HeGrenadeTowerBlock.PREVIEW, true);
    }

    @Override
    protected HeGrenadeTowerBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new HeGrenadeTowerBlockEntity(pos, state);
    }
}
