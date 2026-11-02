package com.besson.endfield.model.block.resourcing;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.resourcing.AcidResistantPumpMkIIBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class AcidResistantPumpMkIIModel extends GeoModel<AcidResistantPumpMkIIBlockEntity> {
    @Override
    public Identifier getModelResource(AcidResistantPumpMkIIBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/acid_resistant_pump_mk_ii.geo.json");
    }

    @Override
    public Identifier getTextureResource(AcidResistantPumpMkIIBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/acid_resistant_pump_mk_ii.png");
    }

    @Override
    public Identifier getAnimationResource(AcidResistantPumpMkIIBlockEntity animatable) {
        return null;
    }
}
