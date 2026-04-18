package com.besson.endfield.renderer.item.logicitis;

import com.besson.endfield.item.custom.logicitis.DepotUnloaderItem;
import com.besson.endfield.model.item.logicitis.DepotUnloaderItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DepotUnloaderItemRenderer extends GeoItemRenderer<DepotUnloaderItem> {
    public DepotUnloaderItemRenderer() {
        super(new DepotUnloaderItemModel());
    }
}
