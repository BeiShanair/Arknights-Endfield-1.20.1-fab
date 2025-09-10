package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.PlantingUnitItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PlantingUnitItemModel extends GeoModel<PlantingUnitItem> {
    @Override
    public Identifier getModelResource(PlantingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/planting_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(PlantingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/planting_unit.png");
    }

    @Override
    public Identifier getAnimationResource(PlantingUnitItem animatable) {
        return null;
    }
}
