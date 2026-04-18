package com.besson.endfield.model.block.logicitis;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.logicitis.DepotUnloaderBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class DepotUnloaderModel extends GeoModel<DepotUnloaderBlockEntity> {
    @Override
    public Identifier getModelResource(DepotUnloaderBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/depot_unloader.geo.json");
    }

    @Override
    public Identifier getTextureResource(DepotUnloaderBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/depot_unloader.png");
    }

    @Override
    public Identifier getAnimationResource(DepotUnloaderBlockEntity animatable) {
        return null;
    }
}
