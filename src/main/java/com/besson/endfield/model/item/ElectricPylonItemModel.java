package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.ElectricPylonItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ElectricPylonItemModel extends GeoModel<ElectricPylonItem> {
    @Override
    public Identifier getModelResource(ElectricPylonItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/electric_pylon.geo.json");
    }

    @Override
    public Identifier getTextureResource(ElectricPylonItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/electric_pylon.png");
    }

    @Override
    public Identifier getAnimationResource(ElectricPylonItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/electric_pylon.animation.json");
    }
}
