package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.RelayTowerBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class RelayTowerModel extends GeoModel<RelayTowerBlockEntity> {
    @Override
    public Identifier getModelResource(RelayTowerBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/relay_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(RelayTowerBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/relay_tower.png");
    }

    @Override
    public Identifier getAnimationResource(RelayTowerBlockEntity animatable) {
        return null;
    }
}
