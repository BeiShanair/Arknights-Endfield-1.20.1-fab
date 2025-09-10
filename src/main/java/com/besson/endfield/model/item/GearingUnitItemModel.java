package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.GearingUnitItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GearingUnitItemModel extends GeoModel<GearingUnitItem> {
    @Override
    public Identifier getModelResource(GearingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/gearing_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(GearingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/gearing_unit.png");
    }

    @Override
    public Identifier getAnimationResource(GearingUnitItem animatable) {
        return null;
    }
}
