package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.combat.SentryTowerBlock;
import com.besson.endfield.blockentity.custom.combat.SentryTowerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SentryTowerModel extends GeoModel<SentryTowerBlockEntity> {
    @Override
    public Identifier getModelResource(SentryTowerBlockEntity sentryTowerBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/sentry_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(SentryTowerBlockEntity sentryTowerBlockEntity) {
        Block block = sentryTowerBlockEntity.getCachedState().getBlock();
        if (block instanceof SentryTowerBlock b) {
            if (b.getDefaultState().get(SentryTowerBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/sentry_tower_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/sentry_tower.png");
    }

    @Override
    public Identifier getAnimationResource(SentryTowerBlockEntity sentryTowerBlockEntity) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(SentryTowerBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
