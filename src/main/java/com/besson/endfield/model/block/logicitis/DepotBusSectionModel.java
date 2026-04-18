package com.besson.endfield.model.block.logicitis;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.logicitis.DepotBusSectionBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class DepotBusSectionModel extends GeoModel<DepotBusSectionBlockEntity> {
    @Override
    public Identifier getModelResource(DepotBusSectionBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/depot_bus_section.geo.json");
    }

    @Override
    public Identifier getTextureResource(DepotBusSectionBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/depot_bus_section.png");
    }

    @Override
    public Identifier getAnimationResource(DepotBusSectionBlockEntity animatable) {
        return null;
    }
}
