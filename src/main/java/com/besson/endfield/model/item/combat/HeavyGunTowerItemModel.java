package com.besson.endfield.model.item.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.combat.HeavyGunTowerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class HeavyGunTowerItemModel extends GeoModel<HeavyGunTowerItem> {
    @Override
    public Identifier getModelResource(HeavyGunTowerItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/heavy_gun_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(HeavyGunTowerItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/heavy_gun_tower.png");
    }

    @Override
    public Identifier getAnimationResource(HeavyGunTowerItem animatable) {
        return null;
    }
}
