package com.besson.endfield.model.item.production2;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.production2.ReactorCrucibleItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ReactorCrucibleItemModel extends GeoModel<ReactorCrucibleItem> {
    @Override
    public Identifier getModelResource(ReactorCrucibleItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/reactor_crucible.geo.json");
    }

    @Override
    public Identifier getTextureResource(ReactorCrucibleItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/reactor_crucible.png");
    }

    @Override
    public Identifier getAnimationResource(ReactorCrucibleItem animatable) {
        return null;
    }
}
