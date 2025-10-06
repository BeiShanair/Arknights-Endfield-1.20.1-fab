package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.FittingUnitItem;
import com.besson.endfield.model.item.FittingUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FittingUnitItemRenderer extends GeoItemRenderer<FittingUnitItem> {
    public FittingUnitItemRenderer() {
        super(new FittingUnitItemModel());
    }
}
