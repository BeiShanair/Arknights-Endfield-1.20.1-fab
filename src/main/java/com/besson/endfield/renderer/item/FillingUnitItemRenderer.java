package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.FillingUnitItem;
import com.besson.endfield.model.item.FillingUnitItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FillingUnitItemRenderer extends GeoItemRenderer<FillingUnitItem> {
    public FillingUnitItemRenderer() {
        super(new FillingUnitItemModel());
    }
}
