package com.besson.endfield.renderer.item.combat;

import com.besson.endfield.item.custom.combat.GunTowerItem;
import com.besson.endfield.model.item.combat.GunTowerItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GunTowerItemRenderer extends GeoItemRenderer<GunTowerItem> {
    public GunTowerItemRenderer() {
        super(new GunTowerItemModel());
    }
}
