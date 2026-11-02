package com.besson.endfield.model.item.production1;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.production1.WaterTreatmentUnitItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class WaterTreatmentUnitItemModel extends GeoModel<WaterTreatmentUnitItem> {
    @Override
    public Identifier getModelResource(WaterTreatmentUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/water_treatment_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(WaterTreatmentUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/water_treatment_unit.png");
    }

    @Override
    public Identifier getAnimationResource(WaterTreatmentUnitItem animatable) {
        return null;
    }
}
