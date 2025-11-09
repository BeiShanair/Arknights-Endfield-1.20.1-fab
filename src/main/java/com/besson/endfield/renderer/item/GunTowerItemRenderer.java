package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.GunTowerItem;
import com.besson.endfield.model.item.GunTowerItemModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GunTowerItemRenderer extends GeoItemRenderer<GunTowerItem> {
    public GunTowerItemRenderer() {
        super(new GunTowerItemModel());
    }
}
