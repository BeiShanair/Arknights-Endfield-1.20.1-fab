package com.besson.endfield.renderer.item.combat;

import com.besson.endfield.item.custom.combat.OmnidirectionalSonicTowerItem;
import com.besson.endfield.model.item.combat.OmnidirectionalSonicTowerItemModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class OmnidirectionalSonicTowerItemRenderer extends GeoItemRenderer<OmnidirectionalSonicTowerItem> {
    public OmnidirectionalSonicTowerItemRenderer() {
        super(new OmnidirectionalSonicTowerItemModel());
    }
}
