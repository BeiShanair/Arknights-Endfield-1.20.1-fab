package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.FluidTankItem;
import com.besson.endfield.model.item.FluidTankItemModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FluidTankItemRenderer extends GeoItemRenderer<FluidTankItem> {
    public FluidTankItemRenderer() {
        super(new FluidTankItemModel());
    }
}
