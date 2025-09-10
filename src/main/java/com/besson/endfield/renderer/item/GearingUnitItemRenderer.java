package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.GearingUnitItem;
import com.besson.endfield.model.item.GearingUnitItemModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GearingUnitItemRenderer extends GeoItemRenderer<GearingUnitItem> {
    public GearingUnitItemRenderer() {
        super(new GearingUnitItemModel());
    }
}
