package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.FittingUnitBlockEntity;
import com.besson.endfield.model.block.FittingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FittingUnitRenderer extends GeoBlockRenderer<FittingUnitBlockEntity> {
    public FittingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new FittingUnitModel());
    }
}
