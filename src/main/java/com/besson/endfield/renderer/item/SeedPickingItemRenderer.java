package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.SeedPickingUnitItem;
import com.besson.endfield.model.item.SeedPickingUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SeedPickingItemRenderer extends GeoItemRenderer<SeedPickingUnitItem> {
    public SeedPickingItemRenderer() {
        super(new SeedPickingUnitItemModel());
    }
}
