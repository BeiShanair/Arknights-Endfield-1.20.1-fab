package com.besson.endfield.model.item.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.combat.OmnidirectionalSonicTowerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class OmnidirectionalSonicTowerItemModel extends GeoModel<OmnidirectionalSonicTowerItem> {
    @Override
    public Identifier getModelResource(OmnidirectionalSonicTowerItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/omnidirectional_sonic_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(OmnidirectionalSonicTowerItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/omnidirectional_sonic_tower.png");
    }

    @Override
    public Identifier getAnimationResource(OmnidirectionalSonicTowerItem animatable) {
        return null;
    }
}
