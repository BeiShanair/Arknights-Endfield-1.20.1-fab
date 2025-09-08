package com.besson.endfield.renderer;

import com.besson.endfield.item.custom.ElectricMiningRigItem;
import com.besson.endfield.model.ElectricMiningRigItemModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ElectricMiningRigItemRenderer extends GeoItemRenderer<ElectricMiningRigItem> {
    public ElectricMiningRigItemRenderer() {
        super(new ElectricMiningRigItemModel());
    }
}
