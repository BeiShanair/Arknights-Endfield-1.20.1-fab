package com.besson.endfield.model.item.production2;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.production2.ForgeOfTheSkyItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ForgeOfTheSkyItemModel extends GeoModel<ForgeOfTheSkyItem> {
    @Override
    public Identifier getModelResource(ForgeOfTheSkyItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/forge_of_the_sky.geo.json");
    }

    @Override
    public Identifier getTextureResource(ForgeOfTheSkyItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/forge_of_the_sky.png");
    }

    @Override
    public Identifier getAnimationResource(ForgeOfTheSkyItem animatable) {
        return null;
    }
}
