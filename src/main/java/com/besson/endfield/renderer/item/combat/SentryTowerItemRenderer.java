package com.besson.endfield.renderer.item.combat;

import com.besson.endfield.item.custom.combat.SentryTowerItem;
import com.besson.endfield.model.item.combat.SentryTowerItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SentryTowerItemRenderer extends GeoItemRenderer<SentryTowerItem> {
    public SentryTowerItemRenderer() {
        super(new SentryTowerItemModel());
    }
}
