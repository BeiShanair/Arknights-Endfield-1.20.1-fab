package com.besson.endfield.model.item.production2;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.production2.SeparatingUnitItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SeparatingUnitItemModel extends GeoModel<SeparatingUnitItem> {
    @Override
    public Identifier getModelResource(SeparatingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/separating_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(SeparatingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/separating_unit.png");
    }

    @Override
    public Identifier getAnimationResource(SeparatingUnitItem animatable) {
        return null;
    }
}
