package com.besson.endfield.renderer.pre.combat;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.combat.MedicalTowerBlock;
import com.besson.endfield.blockentity.custom.combat.MedicalTowerBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class MedicalTowerPreviewRenderer extends BasePreviewRenderer<MedicalTowerBlockEntity> {
    private static final MedicalTowerPreviewRenderer INSTANCE = new MedicalTowerPreviewRenderer();
    
    public static void register() {
        INSTANCE.start();
    }
    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.MEDICAL_TOWER.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        return ModBlocks.MEDICAL_TOWER.getDefaultState().with(MedicalTowerBlock.PREVIEW, true);
    }

    @Override
    protected MedicalTowerBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new MedicalTowerBlockEntity(pos, state);
    }
}
