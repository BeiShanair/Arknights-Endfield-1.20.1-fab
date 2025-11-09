package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.GunTowerBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GunTowerBlockModel extends GeoModel<GunTowerBlockEntity> {
    @Override
    public Identifier getModelResource(GunTowerBlockEntity gunTowerBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/gun_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(GunTowerBlockEntity gunTowerBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/gun_tower.png");
    }

    @Override
    public Identifier getAnimationResource(GunTowerBlockEntity gunTowerBlockEntity) {
        return null;
    }
}
