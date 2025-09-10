package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.FittingUnitItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FittingUnitItemModel extends GeoModel<FittingUnitItem> {
    @Override
    public Identifier getModelResource(FittingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/fitting_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(FittingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/fitting_unit.png");
    }

    @Override
    public Identifier getAnimationResource(FittingUnitItem animatable) {
        return null;
    }
}
