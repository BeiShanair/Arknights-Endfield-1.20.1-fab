package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.combat.HeGrenadeTowerBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class HeGrenadeTowerModel extends GeoModel<HeGrenadeTowerBlockEntity> {
    @Override
    public Identifier getModelResource(HeGrenadeTowerBlockEntity heGrenadeTowerBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/he_grenade_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(HeGrenadeTowerBlockEntity heGrenadeTowerBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/he_grenade_tower.png");
    }

    @Override
    public Identifier getAnimationResource(HeGrenadeTowerBlockEntity heGrenadeTowerBlockEntity) {
        return null;
    }
}
