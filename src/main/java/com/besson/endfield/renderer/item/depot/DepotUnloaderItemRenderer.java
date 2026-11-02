package com.besson.endfield.renderer.item.depot;

import com.besson.endfield.item.custom.depot.DepotUnloaderItem;
import com.besson.endfield.model.item.depot.DepotUnloaderItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DepotUnloaderItemRenderer extends GeoItemRenderer<DepotUnloaderItem> {
    public DepotUnloaderItemRenderer() {
        super(new DepotUnloaderItemModel());
    }
}
