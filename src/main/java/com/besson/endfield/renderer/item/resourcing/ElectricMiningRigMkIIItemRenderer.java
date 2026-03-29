package com.besson.endfield.renderer.item.resourcing;

import com.besson.endfield.item.custom.resourcing.ElectricMiningRigMkIIItem;
import com.besson.endfield.model.item.resourcing.ElectricMiningRigMkIIItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ElectricMiningRigMkIIItemRenderer extends GeoItemRenderer<ElectricMiningRigMkIIItem> {
    public ElectricMiningRigMkIIItemRenderer() {
        super(new ElectricMiningRigMkIIItemModel());
    }
}
