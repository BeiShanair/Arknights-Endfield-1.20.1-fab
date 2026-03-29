package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.combat.OmnidirectionalSonicTowerBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class OmnidirectionalSonicTowerModel extends GeoModel<OmnidirectionalSonicTowerBlockEntity> {
    @Override
    public Identifier getModelResource(OmnidirectionalSonicTowerBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/omnidirectional_sonic_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(OmnidirectionalSonicTowerBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/omnidirectional_sonic_tower.png");
    }

    @Override
    public Identifier getAnimationResource(OmnidirectionalSonicTowerBlockEntity animatable) {
        return null;
    }
}
