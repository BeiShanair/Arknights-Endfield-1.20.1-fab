package com.besson.endfield.model.item.logicitis;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.logicitis.DepotBusSectionItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class DepotBusSectionItemModel extends GeoModel<DepotBusSectionItem> {
    @Override
    public Identifier getModelResource(DepotBusSectionItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/depot_bus_section.geo.json");
    }

    @Override
    public Identifier getTextureResource(DepotBusSectionItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/depot_bus_section.png");
    }

    @Override
    public Identifier getAnimationResource(DepotBusSectionItem animatable) {
        return null;
    }
}
