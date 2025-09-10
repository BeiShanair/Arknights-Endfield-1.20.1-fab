package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.FillingUnitItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FillingUnitItemModel extends GeoModel<FillingUnitItem> {
    @Override
    public Identifier getModelResource(FillingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/filling_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(FillingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/filling_unit.png");
    }

    @Override
    public Identifier getAnimationResource(FillingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/filling_unit.animation.json");
    }
}
