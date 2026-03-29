package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.combat.HeavyGunTowerBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class HeavyGunTowerModel extends GeoModel<HeavyGunTowerBlockEntity> {
    @Override
    public Identifier getModelResource(HeavyGunTowerBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/heavy_gun_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(HeavyGunTowerBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/heavy_gun_tower.png");
    }

    @Override
    public Identifier getAnimationResource(HeavyGunTowerBlockEntity animatable) {
        return null;
    }
}
