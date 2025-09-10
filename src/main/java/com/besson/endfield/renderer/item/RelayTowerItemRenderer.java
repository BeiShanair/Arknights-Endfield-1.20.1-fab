package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.RelayTowerItem;
import com.besson.endfield.model.item.RelayTowerItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class RelayTowerItemRenderer extends GeoItemRenderer<RelayTowerItem> {
    public RelayTowerItemRenderer() {
        super(new RelayTowerItemModel());
    }
}
