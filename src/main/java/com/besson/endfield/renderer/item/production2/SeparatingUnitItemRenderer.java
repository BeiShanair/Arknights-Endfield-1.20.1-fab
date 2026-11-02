package com.besson.endfield.renderer.item.production2;

import com.besson.endfield.item.custom.production2.SeparatingUnitItem;
import com.besson.endfield.model.item.production2.SeparatingUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SeparatingUnitItemRenderer extends GeoItemRenderer<SeparatingUnitItem> {
    public SeparatingUnitItemRenderer() {
        super(new SeparatingUnitItemModel());
    }
}
