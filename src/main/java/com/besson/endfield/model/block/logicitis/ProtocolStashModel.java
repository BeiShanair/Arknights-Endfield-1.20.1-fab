package com.besson.endfield.model.block.logicitis;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.logicitis.ProtocolStashBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ProtocolStashModel extends GeoModel<ProtocolStashBlockEntity> {
    @Override
    public Identifier getModelResource(ProtocolStashBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/protocol_stash.geo.json");
    }

    @Override
    public Identifier getTextureResource(ProtocolStashBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/protocol_stash.png");
    }

    @Override
    public Identifier getAnimationResource(ProtocolStashBlockEntity animatable) {
        return null;
    }
}
