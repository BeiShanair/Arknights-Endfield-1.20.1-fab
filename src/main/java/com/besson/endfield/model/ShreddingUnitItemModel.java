package com.besson.endfield.model;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.ShreddingUnitItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ShreddingUnitItemModel extends GeoModel<ShreddingUnitItem> {
    @Override
    public Identifier getModelResource(ShreddingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/shredding_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(ShreddingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/shredding_unit.png");
    }

    @Override
    public Identifier getAnimationResource(ShreddingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/shredding_unit.animation.json");
    }
}
