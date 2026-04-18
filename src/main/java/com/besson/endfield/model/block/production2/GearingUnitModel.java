package com.besson.endfield.model.block.production2;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.production2.GearingUnitBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GearingUnitModel extends GeoModel<GearingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(GearingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/gearing_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(GearingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/gearing_unit.png");
    }

    @Override
    public Identifier getAnimationResource(GearingUnitBlockEntity animatable) {
        return null;
    }
}
