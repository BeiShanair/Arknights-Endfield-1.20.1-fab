package com.besson.endfield.renderer.item.combat;

import com.besson.endfield.item.custom.combat.GrenadeTowerItem;
import com.besson.endfield.model.item.combat.GrenadeTowerItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GrenadeTowerItemRenderer extends GeoItemRenderer<GrenadeTowerItem> {
    public GrenadeTowerItemRenderer() {
        super(new GrenadeTowerItemModel());
    }
}
