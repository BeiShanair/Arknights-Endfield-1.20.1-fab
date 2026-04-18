package com.besson.endfield.renderer.item.logicitis;

import com.besson.endfield.item.custom.logicitis.DepotBusSectionItem;
import com.besson.endfield.model.item.logicitis.DepotBusSectionItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class DepotBusSectionItemRenderer extends GeoItemRenderer<DepotBusSectionItem> {
    public DepotBusSectionItemRenderer() {
        super(new DepotBusSectionItemModel());
    }
}
