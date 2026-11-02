package com.besson.endfield.model.block.production1;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.production1.WaterTreatmentUnitBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class WaterTreatmentUnitModel extends GeoModel<WaterTreatmentUnitBlockEntity> {
    @Override
    public Identifier getModelResource(WaterTreatmentUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/water_treatment_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(WaterTreatmentUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/water_treatment_unit.png");
    }

    @Override
    public Identifier getAnimationResource(WaterTreatmentUnitBlockEntity animatable) {
        return null;
    }
}
