package com.besson.endfield.renderer.item.combat;

import com.besson.endfield.item.custom.combat.LNTowerItem;
import com.besson.endfield.model.item.combat.LNTowerItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LNTowerItemRenderer extends GeoItemRenderer<LNTowerItem> {
    public LNTowerItemRenderer() {
        super(new LNTowerItemModel());
    }
}
