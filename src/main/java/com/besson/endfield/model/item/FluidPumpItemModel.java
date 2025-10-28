package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.FluidPumpItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FluidPumpItemModel extends GeoModel<FluidPumpItem> {
    @Override
    public Identifier getModelResource(FluidPumpItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/fluid_pump.geo.json");
    }

    @Override
    public Identifier getTextureResource(FluidPumpItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/fluid_pump.png");
    }

    @Override
    public Identifier getAnimationResource(FluidPumpItem animatable) {
        return null;
    }
}
