package com.besson.endfield.renderer.pre.combat;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.combat.HeavyGunTowerBlock;
import com.besson.endfield.blockentity.custom.combat.HeavyGunTowerBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class HeavyGunTowerPreviewRenderer extends BasePreviewRenderer<HeavyGunTowerBlockEntity> {
    private static final HeavyGunTowerPreviewRenderer INSTANCE = new HeavyGunTowerPreviewRenderer();
    
    public static void register() {
        INSTANCE.start();
    }
    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.HEAVY_GUN_TOWER.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        return ModBlocks.HEAVY_GUN_TOWER.getDefaultState().with(HeavyGunTowerBlock.PREVIEW, true);
    }

    @Override
    protected HeavyGunTowerBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new HeavyGunTowerBlockEntity(pos, state);
    }
}
