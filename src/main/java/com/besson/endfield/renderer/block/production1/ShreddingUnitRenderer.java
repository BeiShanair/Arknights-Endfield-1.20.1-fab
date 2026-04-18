package com.besson.endfield.renderer.block.production1;

import com.besson.endfield.blockentity.custom.production1.ShreddingUnitBlockEntity;
import com.besson.endfield.model.block.production1.ShreddingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ShreddingUnitRenderer extends GeoBlockRenderer<ShreddingUnitBlockEntity> {
    public ShreddingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new ShreddingUnitModel());
    }
}
