package com.besson.endfield.model.block.powering;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.powering.XiraniteRelayBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class XiraniteRelayModel extends GeoModel<XiraniteRelayBlockEntity> {
    @Override
    public Identifier getModelResource(XiraniteRelayBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/xiranite_relay.geo.json");
    }

    @Override
    public Identifier getTextureResource(XiraniteRelayBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/xiranite_relay.png");
    }

    @Override
    public Identifier getAnimationResource(XiraniteRelayBlockEntity animatable) {
        return null;
    }
}
