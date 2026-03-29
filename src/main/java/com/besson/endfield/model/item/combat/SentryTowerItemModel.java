package com.besson.endfield.model.item.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.combat.SentryTowerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SentryTowerItemModel extends GeoModel<SentryTowerItem> {
    @Override
    public Identifier getModelResource(SentryTowerItem sentryTowerItem) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/sentry_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(SentryTowerItem sentryTowerItem) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/sentry_tower.png");
    }

    @Override
    public Identifier getAnimationResource(SentryTowerItem sentryTowerItem) {
        return null;
    }
}
