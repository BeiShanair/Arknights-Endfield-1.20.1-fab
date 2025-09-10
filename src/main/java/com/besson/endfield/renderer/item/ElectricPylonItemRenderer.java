package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.ElectricPylonItem;
import com.besson.endfield.model.item.ElectricPylonItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ElectricPylonItemRenderer extends GeoItemRenderer<ElectricPylonItem> {

    public ElectricPylonItemRenderer() {
        super(new ElectricPylonItemModel());
    }
}
