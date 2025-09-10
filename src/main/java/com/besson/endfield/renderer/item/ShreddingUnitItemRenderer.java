package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.ShreddingUnitItem;
import com.besson.endfield.model.item.ShreddingUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ShreddingUnitItemRenderer extends GeoItemRenderer<ShreddingUnitItem> {
    public ShreddingUnitItemRenderer() {
        super(new ShreddingUnitItemModel());
    }
}
