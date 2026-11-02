package com.besson.endfield.model.item.resourcing;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.resourcing.AcidResistantPumpMkIIItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class AcidResistantPumpMkIIItemModel extends GeoModel<AcidResistantPumpMkIIItem> {
    @Override
    public Identifier getModelResource(AcidResistantPumpMkIIItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/acid_resistant_pump_mk_ii.geo.json");
    }

    @Override
    public Identifier getTextureResource(AcidResistantPumpMkIIItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/acid_resistant_pump_mk_ii.png");
    }

    @Override
    public Identifier getAnimationResource(AcidResistantPumpMkIIItem animatable) {
        return null;
    }
}
