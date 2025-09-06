package com.besson.endfield.model;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.ProtocolAnchorCoreBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ProtocolAnchorCoreModel extends GeoModel<ProtocolAnchorCoreBlockEntity> {
    @Override
    public Identifier getModelResource(ProtocolAnchorCoreBlockEntity protocolAnchorCoreBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/protocol_anchor_core.geo.json");
    }

    @Override
    public Identifier getTextureResource(ProtocolAnchorCoreBlockEntity protocolAnchorCoreBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/protocol_anchor_core.png");
    }

    @Override
    public Identifier getAnimationResource(ProtocolAnchorCoreBlockEntity protocolAnchorCoreBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/protocol_anchor_core.animation.json");
    }
}
