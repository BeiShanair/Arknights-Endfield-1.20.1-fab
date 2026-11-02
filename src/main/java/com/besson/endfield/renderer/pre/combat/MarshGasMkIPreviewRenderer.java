package com.besson.endfield.renderer.pre.combat;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.block.custom.combat.MarshGasMkIBlock;
import com.besson.endfield.blockentity.custom.combat.MarshGasMkIBlockEntity;
import com.besson.endfield.renderer.pre.BasePreviewRenderer;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class MarshGasMkIPreviewRenderer extends BasePreviewRenderer<MarshGasMkIBlockEntity> {
    private static final MarshGasMkIPreviewRenderer INSTANCE = new MarshGasMkIPreviewRenderer();
    
    public static void register() {
        INSTANCE.start();
    }
    @Override
    protected boolean matchesItem(ItemStack stack) {
        return stack.getItem() == ModBlocks.MARSH_GAS_MK_I.asItem();
    }

    @Override
    protected BlockState getPreviewState(ItemStack stack, BlockHitResult bhr, MinecraftClient client) {
        return ModBlocks.MARSH_GAS_MK_I.getDefaultState().with(MarshGasMkIBlock.PREVIEW, true);
    }

    @Override
    protected MarshGasMkIBlockEntity createFakeBe(BlockPos pos, BlockState state) {
        return new MarshGasMkIBlockEntity(pos, state);
    }
}
