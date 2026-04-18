package com.besson.endfield.renderer.item.production1;

import com.besson.endfield.item.custom.production1.ShreddingUnitItem;
import com.besson.endfield.model.item.production1.ShreddingUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ShreddingUnitItemRenderer extends GeoItemRenderer<ShreddingUnitItem> {
    public ShreddingUnitItemRenderer() {
        super(new ShreddingUnitItemModel());
    }
}
