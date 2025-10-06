package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.GrindingUnitItem;
import com.besson.endfield.model.item.GrindingUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GrindingUnitItemRenderer extends GeoItemRenderer<GrindingUnitItem> {
    public GrindingUnitItemRenderer() {
        super(new GrindingUnitItemModel());
    }
}
