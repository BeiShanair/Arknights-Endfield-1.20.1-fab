package com.besson.endfield.model.item.production1;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.production1.RefiningUnitItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class RefiningUnitItemModel extends GeoModel<RefiningUnitItem> {
    @Override
    public Identifier getModelResource(RefiningUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/refining_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(RefiningUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/refining_unit.png");
    }

    @Override
    public Identifier getAnimationResource(RefiningUnitItem animatable) {
        return null;
    }
}
