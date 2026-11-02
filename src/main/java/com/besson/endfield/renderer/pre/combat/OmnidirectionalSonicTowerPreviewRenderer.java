package com.besson.endfield.renderer.pre.combat;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.combat.OmnidirectionalSonicTowerBlock;
import com.besson.endfield.blockentity.custom.combat.OmnidirectionalSonicTowerBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class OmnidirectionalSonicTowerPreviewRenderer extends BasePreviewRenderer<OmnidirectionalSonicTowerBlockEntity> {
    private static final OmnidirectionalSonicTowerPreviewRenderer INSTANCE = new OmnidirectionalSonicTowerPreviewRenderer();
    
    public static void register() {
        INSTANCE.start();
    }
    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.OMNIDIRECTIONAL_SONIC_TOWER.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        return ModBlocks.OMNIDIRECTIONAL_SONIC_TOWER.getDefaultState().with(OmnidirectionalSonicTowerBlock.PREVIEW, true);
    }

    @Override
    protected OmnidirectionalSonicTowerBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new OmnidirectionalSonicTowerBlockEntity(pos, state);
    }
}
