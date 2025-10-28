package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.FluidPumpItem;
import com.besson.endfield.model.item.FluidPumpItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FluidPumpItemRenderer extends GeoItemRenderer<FluidPumpItem> {
    public FluidPumpItemRenderer() {
        super(new FluidPumpItemModel());
    }
}
