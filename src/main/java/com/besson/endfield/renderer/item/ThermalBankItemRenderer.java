package com.besson.endfield.renderer.item;

import com.besson.endfield.item.custom.ThermalBankItem;
import com.besson.endfield.model.item.ThermalBankItemModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ThermalBankItemRenderer extends GeoItemRenderer<ThermalBankItem> {
    public ThermalBankItemRenderer() {
        super(new ThermalBankItemModel());
    }
}
