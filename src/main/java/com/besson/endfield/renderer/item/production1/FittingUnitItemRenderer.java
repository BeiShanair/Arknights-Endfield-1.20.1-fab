package com.besson.endfield.renderer.item.production1;

import com.besson.endfield.item.custom.production1.FittingUnitItem;
import com.besson.endfield.model.item.production1.FittingUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FittingUnitItemRenderer extends GeoItemRenderer<FittingUnitItem> {
    public FittingUnitItemRenderer() {
        super(new FittingUnitItemModel());
    }
}
