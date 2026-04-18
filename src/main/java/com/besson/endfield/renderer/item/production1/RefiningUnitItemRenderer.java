package com.besson.endfield.renderer.item.production1;

import com.besson.endfield.item.custom.production1.RefiningUnitItem;
import com.besson.endfield.model.item.production1.RefiningUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class RefiningUnitItemRenderer extends GeoItemRenderer<RefiningUnitItem> {
    public RefiningUnitItemRenderer() {
        super(new RefiningUnitItemModel());
    }
}
