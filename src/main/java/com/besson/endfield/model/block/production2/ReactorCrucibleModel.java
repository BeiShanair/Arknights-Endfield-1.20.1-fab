package com.besson.endfield.model.block.production2;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.production2.ReactorCrucibleBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ReactorCrucibleModel extends GeoModel<ReactorCrucibleBlockEntity> {
    @Override
    public Identifier getModelResource(ReactorCrucibleBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/reactor_crucible.geo.json");
    }

    @Override
    public Identifier getTextureResource(ReactorCrucibleBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/reactor_crucible.png");
    }

    @Override
    public Identifier getAnimationResource(ReactorCrucibleBlockEntity animatable) {
        return null;
    }
}
