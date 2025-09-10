package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.RefiningUnitItem;
import com.besson.endfield.model.item.RefiningUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class RefiningUnitItemRenderer extends GeoItemRenderer<RefiningUnitItem> {
    public RefiningUnitItemRenderer() {
        super(new RefiningUnitItemModel());
    }
}
