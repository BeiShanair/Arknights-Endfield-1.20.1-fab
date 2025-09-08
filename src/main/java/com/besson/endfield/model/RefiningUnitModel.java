package com.besson.endfield.model;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.RefiningUnitBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class RefiningUnitModel extends GeoModel<RefiningUnitBlockEntity> {
    @Override
    public Identifier getModelResource(RefiningUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/refining_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(RefiningUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/refining_unit.png");
    }

    @Override
    public Identifier getAnimationResource(RefiningUnitBlockEntity animatable) {
        return null;
    }
}
