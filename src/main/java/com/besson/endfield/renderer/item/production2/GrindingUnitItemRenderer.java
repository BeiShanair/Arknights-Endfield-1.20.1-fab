package com.besson.endfield.renderer.item.production2;

import com.besson.endfield.item.custom.production2.GrindingUnitItem;
import com.besson.endfield.model.item.production2.GrindingUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GrindingUnitItemRenderer extends GeoItemRenderer<GrindingUnitItem> {
    public GrindingUnitItemRenderer() {
        super(new GrindingUnitItemModel());
    }
}
