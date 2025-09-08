package com.besson.endfield.renderer;

import com.besson.endfield.item.custom.ElectricPylonItem;
import com.besson.endfield.model.ElectricPylonItemModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ElectricPylonItemRenderer extends GeoItemRenderer<ElectricPylonItem> {

    public ElectricPylonItemRenderer() {
        super(new ElectricPylonItemModel());
    }
}
