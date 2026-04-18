package com.besson.endfield.model.block.logicitis;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.logicitis.DepotLoaderBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class DepotLoaderModel extends GeoModel<DepotLoaderBlockEntity> {
    @Override
    public Identifier getModelResource(DepotLoaderBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/depot_loader.geo.json");
    }

    @Override
    public Identifier getTextureResource(DepotLoaderBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/depot_loader.png");
    }

    @Override
    public Identifier getAnimationResource(DepotLoaderBlockEntity animatable) {
        return null;
    }
}
