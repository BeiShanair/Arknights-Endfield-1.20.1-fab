package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.GrindingUnitItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GrindingUnitItemModel extends GeoModel<GrindingUnitItem> {
    @Override
    public Identifier getModelResource(GrindingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/grinding_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(GrindingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/grinding_unit.png");
    }

    @Override
    public Identifier getAnimationResource(GrindingUnitItem animatable) {
        return null;
    }
}
