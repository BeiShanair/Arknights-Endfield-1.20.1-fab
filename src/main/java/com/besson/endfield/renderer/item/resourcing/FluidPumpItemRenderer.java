package com.besson.endfield.renderer.item.resourcing;

import com.besson.endfield.item.custom.resourcing.FluidPumpItem;
import com.besson.endfield.model.item.resourcing.FluidPumpItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FluidPumpItemRenderer extends GeoItemRenderer<FluidPumpItem> {
    public FluidPumpItemRenderer() {
        super(new FluidPumpItemModel());
    }
}
