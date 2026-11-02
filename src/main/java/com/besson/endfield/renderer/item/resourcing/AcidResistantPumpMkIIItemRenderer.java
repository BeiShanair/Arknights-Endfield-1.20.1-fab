package com.besson.endfield.renderer.item.resourcing;

import com.besson.endfield.item.custom.resourcing.AcidResistantPumpMkIIItem;
import com.besson.endfield.model.item.resourcing.AcidResistantPumpMkIIItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class AcidResistantPumpMkIIItemRenderer extends GeoItemRenderer<AcidResistantPumpMkIIItem> {
    public AcidResistantPumpMkIIItemRenderer() {
        super(new AcidResistantPumpMkIIItemModel());
    }
}
