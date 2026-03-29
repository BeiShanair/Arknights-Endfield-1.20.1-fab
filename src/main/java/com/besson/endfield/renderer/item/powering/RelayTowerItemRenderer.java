package com.besson.endfield.renderer.item.powering;

import com.besson.endfield.item.custom.powering.RelayTowerItem;
import com.besson.endfield.model.item.powering.RelayTowerItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class RelayTowerItemRenderer extends GeoItemRenderer<RelayTowerItem> {
    public RelayTowerItemRenderer() {
        super(new RelayTowerItemModel());
    }
}
