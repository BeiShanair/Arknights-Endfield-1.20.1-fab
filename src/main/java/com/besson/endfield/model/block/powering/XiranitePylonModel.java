package com.besson.endfield.model.block.powering;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.powering.XiranitePylonBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class XiranitePylonModel extends GeoModel<XiranitePylonBlockEntity> {
    @Override
    public Identifier getModelResource(XiranitePylonBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/xiranite_pylon.geo.json");
    }

    @Override
    public Identifier getTextureResource(XiranitePylonBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/xiranite_pylon.png");
    }

    @Override
    public Identifier getAnimationResource(XiranitePylonBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/xiranite_pylon.animation.json");
    }
}
