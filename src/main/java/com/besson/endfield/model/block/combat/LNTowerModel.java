package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.combat.LNTowerBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class LNTowerModel extends GeoModel<LNTowerBlockEntity> {
    @Override
    public Identifier getModelResource(LNTowerBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/ln_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(LNTowerBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/ln_tower.png");
    }

    @Override
    public Identifier getAnimationResource(LNTowerBlockEntity animatable) {
        return null;
    }
}
