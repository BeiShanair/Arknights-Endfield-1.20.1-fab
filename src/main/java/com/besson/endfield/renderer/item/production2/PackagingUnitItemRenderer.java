package com.besson.endfield.renderer.item.production2;

import com.besson.endfield.item.custom.production2.PackagingUnitItem;
import com.besson.endfield.model.item.production2.PackagingUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class PackagingUnitItemRenderer extends GeoItemRenderer<PackagingUnitItem> {
    public PackagingUnitItemRenderer() {
        super(new PackagingUnitItemModel());
    }
}
