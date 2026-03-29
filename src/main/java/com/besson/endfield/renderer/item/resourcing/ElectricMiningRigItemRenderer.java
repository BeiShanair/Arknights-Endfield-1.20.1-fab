package com.besson.endfield.renderer.item.resourcing;

import com.besson.endfield.item.custom.resourcing.ElectricMiningRigItem;
import com.besson.endfield.model.item.resourcing.ElectricMiningRigItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ElectricMiningRigItemRenderer extends GeoItemRenderer<ElectricMiningRigItem> {
    public ElectricMiningRigItemRenderer() {
        super(new ElectricMiningRigItemModel());
    }
}
