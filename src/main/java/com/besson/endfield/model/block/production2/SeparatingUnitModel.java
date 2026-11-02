package com.besson.endfield.model.block.production2;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.production2.SeparatingUnitBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SeparatingUnitModel extends GeoModel<SeparatingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(SeparatingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/separating_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(SeparatingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/separating_unit.png");
    }

    @Override
    public Identifier getAnimationResource(SeparatingUnitBlockEntity animatable) {
        return null;
    }
}
