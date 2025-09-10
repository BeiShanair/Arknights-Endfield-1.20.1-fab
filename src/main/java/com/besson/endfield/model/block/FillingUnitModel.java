package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.FillingUnitBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FillingUnitModel extends GeoModel<FillingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(FillingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/filling_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(FillingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/filling_unit.png");
    }

    @Override
    public Identifier getAnimationResource(FillingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/filling_unit.animation.json");
    }
}
