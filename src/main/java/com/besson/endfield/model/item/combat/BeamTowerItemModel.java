package com.besson.endfield.model.item.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.combat.BeamTowerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class BeamTowerItemModel extends GeoModel<BeamTowerItem> {
    @Override
    public Identifier getModelResource(BeamTowerItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/beam_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(BeamTowerItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/beam_tower.png");
    }

    @Override
    public Identifier getAnimationResource(BeamTowerItem animatable) {
        return null;
    }
}
