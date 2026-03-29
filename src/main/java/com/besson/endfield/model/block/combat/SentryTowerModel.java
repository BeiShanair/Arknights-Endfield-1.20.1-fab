package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.combat.SentryTowerBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SentryTowerModel extends GeoModel<SentryTowerBlockEntity> {
    @Override
    public Identifier getModelResource(SentryTowerBlockEntity sentryTowerBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/sentry_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(SentryTowerBlockEntity sentryTowerBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/sentry_tower.png");
    }

    @Override
    public Identifier getAnimationResource(SentryTowerBlockEntity sentryTowerBlockEntity) {
        return null;
    }
}
