package com.besson.endfield.renderer.item.powering;

import com.besson.endfield.item.custom.powering.ThermalBankItem;
import com.besson.endfield.model.item.powering.ThermalBankItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ThermalBankItemRenderer extends GeoItemRenderer<ThermalBankItem> {
    public ThermalBankItemRenderer() {
        super(new ThermalBankItemModel());
    }
}
