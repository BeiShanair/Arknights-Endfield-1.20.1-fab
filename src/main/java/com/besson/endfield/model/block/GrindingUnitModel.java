package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.GrindingUnitBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GrindingUnitModel extends GeoModel<GrindingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(GrindingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/grinding_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(GrindingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/grinding_unit.png");
    }

    @Override
    public Identifier getAnimationResource(GrindingUnitBlockEntity animatable) {
        return null;
    }
}
