package com.besson.endfield.renderer.item.combat;

import com.besson.endfield.item.custom.combat.MedicalTowerItem;
import com.besson.endfield.model.item.combat.MedicalTowerItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MedicalTowerItemRenderer extends GeoItemRenderer<MedicalTowerItem> {
    public MedicalTowerItemRenderer() {
        super(new MedicalTowerItemModel());
    }
}
