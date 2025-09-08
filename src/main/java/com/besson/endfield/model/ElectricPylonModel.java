package com.besson.endfield.model;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.ElectricPylonBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ElectricPylonModel extends GeoModel<ElectricPylonBlockEntity> {
    @Override
    public Identifier getModelResource(ElectricPylonBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/electric_pylon.geo.json");
    }

    @Override
    public Identifier getTextureResource(ElectricPylonBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/electric_pylon.png");
    }

    @Override
    public Identifier getAnimationResource(ElectricPylonBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/electric_pylon.animation.json");
    }
}
