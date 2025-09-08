package com.besson.endfield.renderer;

import com.besson.endfield.item.custom.ShreddingUnitItem;
import com.besson.endfield.model.ShreddingUnitItemModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ShreddingUnitItemRenderer extends GeoItemRenderer<ShreddingUnitItem> {
    public ShreddingUnitItemRenderer() {
        super(new ShreddingUnitItemModel());
    }
}
