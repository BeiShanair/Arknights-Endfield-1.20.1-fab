package com.besson.endfield.renderer.item.combat;

import com.besson.endfield.item.custom.combat.HeGrenadeTowerItem;
import com.besson.endfield.model.item.combat.HeGrenadeTowerItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class HeGrenadeTowerItemRenderer extends GeoItemRenderer<HeGrenadeTowerItem> {
    public HeGrenadeTowerItemRenderer() {
        super(new HeGrenadeTowerItemModel());
    }
}
