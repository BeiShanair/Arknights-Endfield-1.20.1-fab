package com.besson.endfield.model.item.logicitis;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.logicitis.DepotUnloaderItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class DepotUnloaderItemModel extends GeoModel<DepotUnloaderItem> {
    @Override
    public Identifier getModelResource(DepotUnloaderItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/depot_unloader.geo.json");
    }

    @Override
    public Identifier getTextureResource(DepotUnloaderItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/depot_unloader.png");
    }

    @Override
    public Identifier getAnimationResource(DepotUnloaderItem animatable) {
        return null;
    }
}
