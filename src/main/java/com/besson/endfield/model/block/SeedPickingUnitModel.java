package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.SeedPickingUnitBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SeedPickingUnitModel extends GeoModel<SeedPickingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(SeedPickingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/seed_picking_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(SeedPickingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/seed_picking_unit.png");
    }

    @Override
    public Identifier getAnimationResource(SeedPickingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/seed_picking_unit.animation.json");
    }
}
