package com.besson.endfield.model.block.resourcing;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.resourcing.HydroMiningRigBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class HydroMiningRigModel extends GeoModel<HydroMiningRigBlockEntity> {
    @Override
    public Identifier getModelResource(HydroMiningRigBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/hydro_mining_rig.geo.json");
    }

    @Override
    public Identifier getTextureResource(HydroMiningRigBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/hydro_mining_rig.png");
    }

    @Override
    public Identifier getAnimationResource(HydroMiningRigBlockEntity animatable) {
        return null;
    }
}
