package com.besson.endfield.renderer.item.production2;

import com.besson.endfield.item.custom.production2.FillingUnitItem;
import com.besson.endfield.model.item.production2.FillingUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FillingUnitItemRenderer extends GeoItemRenderer<FillingUnitItem> {
    public FillingUnitItemRenderer() {
        super(new FillingUnitItemModel());
    }
}
