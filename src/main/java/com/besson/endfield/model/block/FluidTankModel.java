package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.FluidTankBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FluidTankModel extends GeoModel<FluidTankBlockEntity> {
    @Override
    public Identifier getModelResource(FluidTankBlockEntity fluidTankBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/fluid_tank.geo.json");
    }

    @Override
    public Identifier getTextureResource(FluidTankBlockEntity fluidTankBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/fluid_tank.png");
    }

    @Override
    public Identifier getAnimationResource(FluidTankBlockEntity fluidTankBlockEntity) {
        return null;
    }
}
