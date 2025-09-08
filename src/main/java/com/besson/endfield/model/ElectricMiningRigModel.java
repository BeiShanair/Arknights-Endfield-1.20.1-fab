package com.besson.endfield.model;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.ElectricMiningRigBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ElectricMiningRigModel extends GeoModel<ElectricMiningRigBlockEntity> {
    @Override
    public Identifier getModelResource(ElectricMiningRigBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/electric_mining_rig.geo.json");
    }

    @Override
    public Identifier getTextureResource(ElectricMiningRigBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/electric_mining_rig.png");
    }

    @Override
    public Identifier getAnimationResource(ElectricMiningRigBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/electric_mining_rig.animation.json");
    }
}
