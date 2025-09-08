package com.besson.endfield.renderer;

import com.besson.endfield.item.custom.RefiningUnitItem;
import com.besson.endfield.model.RefiningUnitItemModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class RefiningUnitItemRenderer extends GeoItemRenderer<RefiningUnitItem> {
    public RefiningUnitItemRenderer() {
        super(new RefiningUnitItemModel());
    }
}
