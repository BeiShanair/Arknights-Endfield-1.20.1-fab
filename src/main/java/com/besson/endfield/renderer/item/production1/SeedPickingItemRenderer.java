package com.besson.endfield.renderer.item.production1;

import com.besson.endfield.item.custom.production1.SeedPickingUnitItem;
import com.besson.endfield.model.item.production1.SeedPickingUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SeedPickingItemRenderer extends GeoItemRenderer<SeedPickingUnitItem> {
    public SeedPickingItemRenderer() {
        super(new SeedPickingUnitItemModel());
    }
}
