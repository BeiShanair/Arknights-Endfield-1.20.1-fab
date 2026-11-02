package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.combat.GunTowerBlock;
import com.besson.endfield.blockentity.custom.combat.GunTowerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GunTowerBlockModel extends GeoModel<GunTowerBlockEntity> {
    @Override
    public Identifier getModelResource(GunTowerBlockEntity gunTowerBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/gun_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(GunTowerBlockEntity gunTowerBlockEntity) {
        Block block = gunTowerBlockEntity.getCachedState().getBlock();
        if (block instanceof GunTowerBlock b) {
            if (b.getDefaultState().get(GunTowerBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/gun_tower_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/gun_tower.png");
    }

    @Override
    public Identifier getAnimationResource(GunTowerBlockEntity gunTowerBlockEntity) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(GunTowerBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
