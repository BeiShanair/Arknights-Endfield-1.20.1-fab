package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.GunTowerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GunTowerItemModel extends GeoModel<GunTowerItem> {
    @Override
    public Identifier getModelResource(GunTowerItem gunTowerItem) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/gun_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(GunTowerItem gunTowerItem) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/item/gun_tower.png");
    }

    @Override
    public Identifier getAnimationResource(GunTowerItem gunTowerItem) {
        return null;
    }
}
