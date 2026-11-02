package com.besson.endfield.model.item.powering;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.powering.XiranitePylonItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class XiranitePylonItemModel extends GeoModel<XiranitePylonItem> {
    @Override
    public Identifier getModelResource(XiranitePylonItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/xiranite_pylon.geo.json");
    }

    @Override
    public Identifier getTextureResource(XiranitePylonItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/xiranite_pylon.png");
    }

    @Override
    public Identifier getAnimationResource(XiranitePylonItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/xiranite_pylon.animation.json");
    }
}
