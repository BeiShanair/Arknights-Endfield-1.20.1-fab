package com.besson.endfield.renderer.block.production2;

import com.besson.endfield.blockentity.custom.production2.GearingUnitBlockEntity;
import com.besson.endfield.model.block.production2.GearingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GearingUnitRenderer extends GeoBlockRenderer<GearingUnitBlockEntity> {
    public GearingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new GearingUnitModel());
    }
}
