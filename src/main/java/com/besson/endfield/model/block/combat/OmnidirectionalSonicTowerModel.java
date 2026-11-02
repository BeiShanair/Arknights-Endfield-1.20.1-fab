package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.combat.OmnidirectionalSonicTowerBlock;
import com.besson.endfield.blockentity.custom.combat.OmnidirectionalSonicTowerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class OmnidirectionalSonicTowerModel extends GeoModel<OmnidirectionalSonicTowerBlockEntity> {
    @Override
    public Identifier getModelResource(OmnidirectionalSonicTowerBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/omnidirectional_sonic_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(OmnidirectionalSonicTowerBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof OmnidirectionalSonicTowerBlock b) {
            if (b.getDefaultState().get(OmnidirectionalSonicTowerBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/omnidirectional_sonic_tower_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/omnidirectional_sonic_tower.png");
    }

    @Override
    public Identifier getAnimationResource(OmnidirectionalSonicTowerBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(OmnidirectionalSonicTowerBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
