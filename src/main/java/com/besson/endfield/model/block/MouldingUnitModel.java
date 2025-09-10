package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.MouldingUnitBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MouldingUnitModel extends GeoModel<MouldingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(MouldingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/moulding_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(MouldingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/moulding_unit.png");
    }

    @Override
    public Identifier getAnimationResource(MouldingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/moulding_unit.animation.json");
    }
}
