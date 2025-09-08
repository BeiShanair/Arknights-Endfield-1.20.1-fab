package com.besson.endfield.model;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.PortableOriginiumRigBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PortableOriginiumModel extends GeoModel<PortableOriginiumRigBlockEntity> {
    @Override
    public Identifier getModelResource(PortableOriginiumRigBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/portable_originium_rig.geo.json");
    }

    @Override
    public Identifier getTextureResource(PortableOriginiumRigBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/portable_originium_rig.png");
    }

    @Override
    public Identifier getAnimationResource(PortableOriginiumRigBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/portable_originium_rig.animation.json");
    }
}
