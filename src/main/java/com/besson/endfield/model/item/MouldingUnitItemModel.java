package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.MouldingUnitItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MouldingUnitItemModel extends GeoModel<MouldingUnitItem> {
    @Override
    public Identifier getModelResource(MouldingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/moulding_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(MouldingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/moulding_unit.png");
    }

    @Override
    public Identifier getAnimationResource(MouldingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/moulding_unit.animation.json");
    }
}
