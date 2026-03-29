package com.besson.endfield.renderer.item.combat;

import com.besson.endfield.item.custom.combat.HeavyGunTowerItem;
import com.besson.endfield.model.item.combat.HeavyGunTowerItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class HeavyGunTowerItemRenderer extends GeoItemRenderer<HeavyGunTowerItem> {
    public HeavyGunTowerItemRenderer() {
        super(new HeavyGunTowerItemModel());
    }
}
