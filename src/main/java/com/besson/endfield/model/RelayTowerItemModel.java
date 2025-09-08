package com.besson.endfield.model;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.RelayTowerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class RelayTowerItemModel extends GeoModel<RelayTowerItem> {
    @Override
    public Identifier getModelResource(RelayTowerItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/relay_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(RelayTowerItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/relay_tower.png");
    }

    @Override
    public Identifier getAnimationResource(RelayTowerItem animatable) {
        return null;
    }
}
