package com.besson.endfield.model;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.ProtocolAnchorCoreItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ProtocolAnchorCoreItemModel extends GeoModel<ProtocolAnchorCoreItem> {
    @Override
    public Identifier getModelResource(ProtocolAnchorCoreItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/protocol_anchor_core.geo.json");
    }

    @Override
    public Identifier getTextureResource(ProtocolAnchorCoreItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/protocol_anchor_core.png");
    }

    @Override
    public Identifier getAnimationResource(ProtocolAnchorCoreItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/protocol_anchor_core.animation.json");
    }
}
