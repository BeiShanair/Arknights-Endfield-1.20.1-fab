package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.combat.HeavyGunTowerBlock;
import com.besson.endfield.blockentity.custom.combat.HeavyGunTowerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class HeavyGunTowerModel extends GeoModel<HeavyGunTowerBlockEntity> {
    @Override
    public Identifier getModelResource(HeavyGunTowerBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/heavy_gun_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(HeavyGunTowerBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof HeavyGunTowerBlock b) {
            if (b.getDefaultState().get(HeavyGunTowerBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/heavy_gun_tower_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/heavy_gun_tower.png");
    }

    @Override
    public Identifier getAnimationResource(HeavyGunTowerBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(HeavyGunTowerBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
