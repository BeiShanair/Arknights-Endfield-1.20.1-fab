package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.PackagingUnitItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class PackagingUnitItemModel extends GeoModel<PackagingUnitItem> {
    @Override
    public Identifier getModelResource(PackagingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/packaging_unit.geo.json");
    }

    @Override
    public Identifier getTextureResource(PackagingUnitItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/packaging_unit.png");
    }

    @Override
    public Identifier getAnimationResource(PackagingUnitItem animatable) {
        return null;
    }
}
