package com.besson.endfield.model.item.production2;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.production2.ExpandedCrucibleItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ExpandedCrucibleItemModel extends GeoModel<ExpandedCrucibleItem> {
    @Override
    public Identifier getModelResource(ExpandedCrucibleItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/expanded_crucible.geo.json");
    }

    @Override
    public Identifier getTextureResource(ExpandedCrucibleItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/expanded_crucible.png");
    }

    @Override
    public Identifier getAnimationResource(ExpandedCrucibleItem animatable) {
        return null;
    }
}
