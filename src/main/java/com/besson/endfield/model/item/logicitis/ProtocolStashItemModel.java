package com.besson.endfield.model.item.logicitis;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.logicitis.ProtocolStashItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ProtocolStashItemModel extends GeoModel<ProtocolStashItem> {
    @Override
    public Identifier getModelResource(ProtocolStashItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/protocol_stash.geo.json");
    }

    @Override
    public Identifier getTextureResource(ProtocolStashItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/protocol_stash.png");
    }

    @Override
    public Identifier getAnimationResource(ProtocolStashItem animatable) {
        return null;
    }
}
