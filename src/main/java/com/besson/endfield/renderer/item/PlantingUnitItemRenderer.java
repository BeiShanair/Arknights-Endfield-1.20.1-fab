package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.PlantingUnitItem;
import com.besson.endfield.model.item.PlantingUnitItemModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class PlantingUnitItemRenderer extends GeoItemRenderer<PlantingUnitItem> {
    public PlantingUnitItemRenderer() {
        super(new PlantingUnitItemModel());
    }
}
