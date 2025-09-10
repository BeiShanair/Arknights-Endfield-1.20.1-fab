package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.PlantingUnitBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PlantingUnitModel extends GeoModel<PlantingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(PlantingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/planting_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(PlantingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/planting_unit.png");
    }

    @Override
    public Identifier getAnimationResource(PlantingUnitBlockEntity animatable) {
        return null;
    }
}
