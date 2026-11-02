package com.besson.endfield.renderer.item.production1;

import com.besson.endfield.item.custom.production1.WaterTreatmentUnitItem;
import com.besson.endfield.model.item.production1.WaterTreatmentUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class WaterTreatmentUnitItemRenderer extends GeoItemRenderer<WaterTreatmentUnitItem> {
    public WaterTreatmentUnitItemRenderer() {
        super(new WaterTreatmentUnitItemModel());
    }
}
