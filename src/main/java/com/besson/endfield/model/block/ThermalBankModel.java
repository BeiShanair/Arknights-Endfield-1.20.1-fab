package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.ThermalBankBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ThermalBankModel extends GeoModel<ThermalBankBlockEntity> {
    @Override
    public Identifier getModelResource(ThermalBankBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/thermal_bank.geo.json");
    }

    @Override
    public Identifier getTextureResource(ThermalBankBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/thermal_bank.png");
    }

    @Override
    public Identifier getAnimationResource(ThermalBankBlockEntity animatable) {
        return null;
    }
}
