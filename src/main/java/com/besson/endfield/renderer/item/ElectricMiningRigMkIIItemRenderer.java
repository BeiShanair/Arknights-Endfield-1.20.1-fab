package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.ElectricMiningRigMkIIItem;
import com.besson.endfield.model.item.ElectricMiningRigMkIIItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ElectricMiningRigMkIIItemRenderer extends GeoItemRenderer<ElectricMiningRigMkIIItem> {
    public ElectricMiningRigMkIIItemRenderer() {
        super(new ElectricMiningRigMkIIItemModel());
    }
}
