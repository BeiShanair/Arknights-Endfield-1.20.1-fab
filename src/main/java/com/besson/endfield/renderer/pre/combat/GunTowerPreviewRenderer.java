package com.besson.endfield.renderer.pre.combat;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.combat.GunTowerBlock;
import com.besson.endfield.blockentity.custom.combat.GunTowerBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class GunTowerPreviewRenderer extends BasePreviewRenderer<GunTowerBlockEntity> {
    private static final GunTowerPreviewRenderer INSTANCE = new GunTowerPreviewRenderer();
    
    public static void register() {
        INSTANCE.start();
    }
    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.GUN_TOWER.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        return ModBlocks.GUN_TOWER.getDefaultState().with(GunTowerBlock.PREVIEW, true);
    }

    @Override
    protected GunTowerBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new GunTowerBlockEntity(pos, state);
    }
}
