package com.besson.endfield.renderer.pre.combat;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.combat.GrenadeTowerBlock;
import com.besson.endfield.blockentity.custom.combat.GrenadeTowerBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class GrenadeTowerPreviewRenderer extends BasePreviewRenderer<GrenadeTowerBlockEntity> {
    private static final GrenadeTowerPreviewRenderer INSTANCE = new GrenadeTowerPreviewRenderer();
    
    public static void register() {
        INSTANCE.start();
    }
    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.GRENADE_TOWER.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        return ModBlocks.GRENADE_TOWER.getDefaultState().with(GrenadeTowerBlock.PREVIEW, true);
    }

    @Override
    protected GrenadeTowerBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new GrenadeTowerBlockEntity(pos, state);
    }
}
