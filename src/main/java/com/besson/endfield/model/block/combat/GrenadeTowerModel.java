package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.combat.GrenadeTowerBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GrenadeTowerModel extends GeoModel<GrenadeTowerBlockEntity> {
    @Override
    public Identifier getModelResource(GrenadeTowerBlockEntity grenadeTowerBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/grenade_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(GrenadeTowerBlockEntity grenadeTowerBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/grenade_tower.png");
    }

    @Override
    public Identifier getAnimationResource(GrenadeTowerBlockEntity grenadeTowerBlockEntity) {
        return null;
    }
}
