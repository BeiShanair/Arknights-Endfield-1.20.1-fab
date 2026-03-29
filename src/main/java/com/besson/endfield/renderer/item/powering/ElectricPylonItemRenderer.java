package com.besson.endfield.renderer.item.powering;

import com.besson.endfield.item.custom.powering.ElectricPylonItem;
import com.besson.endfield.model.item.powering.ElectricPylonItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ElectricPylonItemRenderer extends GeoItemRenderer<ElectricPylonItem> {

    public ElectricPylonItemRenderer() {
        super(new ElectricPylonItemModel());
    }
}
