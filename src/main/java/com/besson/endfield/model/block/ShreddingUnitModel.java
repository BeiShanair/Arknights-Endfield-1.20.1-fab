package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.ShreddingUnitBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ShreddingUnitModel extends GeoModel<ShreddingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(ShreddingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/shredding_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(ShreddingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/shredding_unit.png");
    }

    @Override
    public Identifier getAnimationResource(ShreddingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/shredding_unit.animation.json");
    }
}
