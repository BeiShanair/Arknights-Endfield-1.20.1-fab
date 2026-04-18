package com.besson.endfield.renderer.item.logicitis;

import com.besson.endfield.item.custom.logicitis.DepotLoaderItem;
import com.besson.endfield.model.item.logicitis.DepotLoaderItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DepotLoaderItemRenderer extends GeoItemRenderer<DepotLoaderItem> {
    public DepotLoaderItemRenderer() {
        super(new DepotLoaderItemModel());
    }
}
