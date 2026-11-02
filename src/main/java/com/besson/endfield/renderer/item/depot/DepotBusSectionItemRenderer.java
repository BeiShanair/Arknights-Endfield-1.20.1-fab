package com.besson.endfield.renderer.item.depot;

import com.besson.endfield.item.custom.depot.DepotBusSectionItem;
import com.besson.endfield.model.item.depot.DepotBusSectionItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DepotBusSectionItemRenderer extends GeoItemRenderer<DepotBusSectionItem> {
    public DepotBusSectionItemRenderer() {
        super(new DepotBusSectionItemModel());
    }
}
