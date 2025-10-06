package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.GearingUnitBlockEntity;
import com.besson.endfield.model.block.GearingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class GearingUnitRenderer extends GeoBlockRenderer<GearingUnitBlockEntity> {
    public GearingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new GearingUnitModel());
    }
}
