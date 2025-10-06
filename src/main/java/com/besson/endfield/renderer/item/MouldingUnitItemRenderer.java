package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.MouldingUnitItem;
import com.besson.endfield.model.item.MouldingUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MouldingUnitItemRenderer extends GeoItemRenderer<MouldingUnitItem> {
    public MouldingUnitItemRenderer() {
        super(new MouldingUnitItemModel());
    }
}
