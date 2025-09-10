package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.PortableOriginiumRigItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PortableOriginiumItemModel extends GeoModel<PortableOriginiumRigItem> {
    @Override
    public Identifier getModelResource(PortableOriginiumRigItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/portable_originium_rig.geo.json");
    }

    @Override
    public Identifier getTextureResource(PortableOriginiumRigItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/portable_originium_rig.png");
    }

    @Override
    public Identifier getAnimationResource(PortableOriginiumRigItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/portable_originium_rig.animation.json");
    }
}
