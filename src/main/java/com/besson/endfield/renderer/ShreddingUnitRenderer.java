package com.besson.endfield.renderer;

import com.besson.endfield.blockentity.custom.ShreddingUnitBlockEntity;
import com.besson.endfield.model.ShreddingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ShreddingUnitRenderer extends GeoBlockRenderer<ShreddingUnitBlockEntity> {
    public ShreddingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new ShreddingUnitModel());
    }
}
