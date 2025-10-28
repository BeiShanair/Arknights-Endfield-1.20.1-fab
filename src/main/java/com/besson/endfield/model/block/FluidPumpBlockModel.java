package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.FluidPumpBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FluidPumpBlockModel extends GeoModel<FluidPumpBlockEntity> {
    @Override
    public Identifier getModelResource(FluidPumpBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/fluid_pump.geo.json");
    }

    @Override
    public Identifier getTextureResource(FluidPumpBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/fluid_pump.png");
    }

    @Override
    public Identifier getAnimationResource(FluidPumpBlockEntity animatable) {
        return null;
    }
}
