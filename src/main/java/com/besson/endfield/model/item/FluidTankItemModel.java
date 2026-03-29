package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.FluidTankItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FluidTankItemModel extends GeoModel<FluidTankItem> {
    @Override
    public Identifier getModelResource(FluidTankItem fluidTankItem) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/fluid_tank.geo.json");
    }

    @Override
    public Identifier getTextureResource(FluidTankItem fluidTankItem) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/fluid_tank.png");
    }

    @Override
    public Identifier getAnimationResource(FluidTankItem fluidTankItem) {
        return null;
    }
}
