package com.besson.endfield.renderer.block.production1;

import com.besson.endfield.blockentity.custom.production1.FittingUnitBlockEntity;
import com.besson.endfield.model.block.production1.FittingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FittingUnitRenderer extends GeoBlockRenderer<FittingUnitBlockEntity> {
    public FittingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new FittingUnitModel());
    }
}
