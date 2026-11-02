package com.besson.endfield.renderer.item.resourcing;

import com.besson.endfield.item.custom.resourcing.HydroMiningRigItem;
import com.besson.endfield.model.item.resourcing.HydroMiningRigItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class HydroMiningRigItemRenderer extends GeoItemRenderer<HydroMiningRigItem> {
    public HydroMiningRigItemRenderer() {
        super(new HydroMiningRigItemModel());
    }
}
