package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.SeedPickingUnitItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SeedPickingUnitItemModel extends GeoModel<SeedPickingUnitItem> {
    @Override
    public Identifier getModelResource(SeedPickingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/seed_picking_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(SeedPickingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/seed_picking_unit.png");
    }

    @Override
    public Identifier getAnimationResource(SeedPickingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/seed_picking_unit.animation.json");
    }
}
