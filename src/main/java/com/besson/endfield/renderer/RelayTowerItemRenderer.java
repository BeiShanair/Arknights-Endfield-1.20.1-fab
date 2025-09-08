package com.besson.endfield.renderer;

import com.besson.endfield.item.custom.RelayTowerItem;
import com.besson.endfield.model.RelayTowerItemModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class RelayTowerItemRenderer extends GeoItemRenderer<RelayTowerItem> {
    public RelayTowerItemRenderer() {
        super(new RelayTowerItemModel());
    }
}
