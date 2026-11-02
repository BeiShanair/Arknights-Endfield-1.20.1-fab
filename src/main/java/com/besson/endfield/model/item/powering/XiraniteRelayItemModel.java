package com.besson.endfield.model.item.powering;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.powering.XiraniteRelayItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class XiraniteRelayItemModel extends GeoModel<XiraniteRelayItem> {
    @Override
    public Identifier getModelResource(XiraniteRelayItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/xiranite_relay.geo.json");
    }

    @Override
    public Identifier getTextureResource(XiraniteRelayItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/xiranite_relay.png");
    }

    @Override
    public Identifier getAnimationResource(XiraniteRelayItem animatable) {
        return null;
    }
}
