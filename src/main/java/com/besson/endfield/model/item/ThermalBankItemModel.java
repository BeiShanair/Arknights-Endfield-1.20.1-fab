package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.ThermalBankItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ThermalBankItemModel extends GeoModel<ThermalBankItem> {
    @Override
    public Identifier getModelResource(ThermalBankItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/thermal_bank.geo.json");
    }

    @Override
    public Identifier getTextureResource(ThermalBankItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/thermal_bank.png");
    }

    @Override
    public Identifier getAnimationResource(ThermalBankItem animatable) {
        return null;
    }
}
