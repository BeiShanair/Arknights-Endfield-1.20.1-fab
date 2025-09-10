package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.ShreddingUnitBlockEntity;
import com.besson.endfield.model.block.ShreddingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ShreddingUnitRenderer extends GeoBlockRenderer<ShreddingUnitBlockEntity> {
    public ShreddingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new ShreddingUnitModel());
    }
}
