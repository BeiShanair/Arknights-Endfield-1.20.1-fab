package com.besson.endfield.renderer.item.combat;

import com.besson.endfield.item.custom.combat.SurgeTowerItem;
import com.besson.endfield.model.item.combat.SurgeTowerItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class SurgeTowerItemRenderer extends GeoItemRenderer<SurgeTowerItem> {
    public SurgeTowerItemRenderer() {
        super(new SurgeTowerItemModel());
    }
}
