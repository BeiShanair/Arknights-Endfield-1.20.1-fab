package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.FittingUnitBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FittingUnitModel extends GeoModel<FittingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(FittingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/fitting_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(FittingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/fitting_unit.png");
    }

    @Override
    public Identifier getAnimationResource(FittingUnitBlockEntity animatable) {
        return null;
    }
}
