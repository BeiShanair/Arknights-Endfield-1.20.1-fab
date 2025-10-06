package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.PackagingUnitItem;
import com.besson.endfield.model.item.PackagingUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class PackagingUnitItemRenderer extends GeoItemRenderer<PackagingUnitItem> {
    public PackagingUnitItemRenderer() {
        super(new PackagingUnitItemModel());
    }
}
