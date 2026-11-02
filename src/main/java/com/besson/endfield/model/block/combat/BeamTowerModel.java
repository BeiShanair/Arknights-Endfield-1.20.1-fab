package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.combat.BeamTowerBlock;
import com.besson.endfield.blockentity.custom.combat.BeamTowerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class BeamTowerModel extends GeoModel<BeamTowerBlockEntity> {
    @Override
    public Identifier getModelResource(BeamTowerBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/beam_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(BeamTowerBlockEntity animatable) {
        Block block = animatable.getCachedState().getBlock();
        if (block instanceof BeamTowerBlock b) {
            if (b.getDefaultState().get(BeamTowerBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/beam_tower_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/beam_tower.png");
    }

    @Override
    public Identifier getAnimationResource(BeamTowerBlockEntity animatable) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(BeamTowerBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
