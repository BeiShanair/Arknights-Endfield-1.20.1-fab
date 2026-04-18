package com.besson.endfield.model.item.logicitis;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.logicitis.DepotLoaderItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class DepotLoaderItemModel extends GeoModel<DepotLoaderItem> {
    @Override
    public Identifier getModelResource(DepotLoaderItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/depot_loader.geo.json");
    }

    @Override
    public Identifier getTextureResource(DepotLoaderItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/depot_loader.png");
    }

    @Override
    public Identifier getAnimationResource(DepotLoaderItem animatable) {
        return null;
    }
}
