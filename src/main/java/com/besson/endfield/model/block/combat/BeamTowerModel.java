package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.combat.BeamTowerBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class BeamTowerModel extends GeoModel<BeamTowerBlockEntity> {
    @Override
    public Identifier getModelResource(BeamTowerBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/beam_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(BeamTowerBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/beam_tower.png");
    }

    @Override
    public Identifier getAnimationResource(BeamTowerBlockEntity animatable) {
        return null;
    }
}
