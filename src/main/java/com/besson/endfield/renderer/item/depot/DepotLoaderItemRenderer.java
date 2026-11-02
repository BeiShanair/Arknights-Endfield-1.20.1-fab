package com.besson.endfield.renderer.item.depot;

import com.besson.endfield.item.custom.depot.DepotLoaderItem;
import com.besson.endfield.model.item.depot.DepotLoaderItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DepotLoaderItemRenderer extends GeoItemRenderer<DepotLoaderItem> {
    public DepotLoaderItemRenderer() {
        super(new DepotLoaderItemModel());
    }
}
