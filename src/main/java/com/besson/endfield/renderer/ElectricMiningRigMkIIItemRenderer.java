package com.besson.endfield.renderer;

import com.besson.endfield.item.custom.ElectricMiningRigMkIIItem;
import com.besson.endfield.model.ElectricMiningRigMkIIItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ElectricMiningRigMkIIItemRenderer extends GeoItemRenderer<ElectricMiningRigMkIIItem> {
    public ElectricMiningRigMkIIItemRenderer() {
        super(new ElectricMiningRigMkIIItemModel());
    }
}
