package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.PackagingUnitBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PackagingUnitModel extends GeoModel<PackagingUnitBlockEntity> {
    @Override
    public Identifier getModelResource(PackagingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/packaging_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(PackagingUnitBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/packaging_unit.png");
    }

    @Override
    public Identifier getAnimationResource(PackagingUnitBlockEntity animatable) {
        return null;
    }
}
