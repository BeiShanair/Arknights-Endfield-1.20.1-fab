package com.besson.endfield.renderer.item.combat;

import com.besson.endfield.item.custom.combat.BeamTowerItem;
import com.besson.endfield.model.item.combat.BeamTowerItemModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BeamTowerItemRenderer extends GeoItemRenderer<BeamTowerItem> {
    public BeamTowerItemRenderer() {
        super(new BeamTowerItemModel());
    }
}
