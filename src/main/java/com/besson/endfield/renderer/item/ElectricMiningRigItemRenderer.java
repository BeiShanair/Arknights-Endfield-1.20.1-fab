package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.ElectricMiningRigItem;
import com.besson.endfield.model.item.ElectricMiningRigItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ElectricMiningRigItemRenderer extends GeoItemRenderer<ElectricMiningRigItem> {
    public ElectricMiningRigItemRenderer() {
        super(new ElectricMiningRigItemModel());
    }
}
