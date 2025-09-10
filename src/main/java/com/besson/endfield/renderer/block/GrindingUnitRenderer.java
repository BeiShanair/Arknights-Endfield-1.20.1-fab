package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.GrindingUnitBlockEntity;
import com.besson.endfield.model.block.GrindingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GrindingUnitRenderer extends GeoBlockRenderer<GrindingUnitBlockEntity> {
    public GrindingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new GrindingUnitModel());
    }
}
