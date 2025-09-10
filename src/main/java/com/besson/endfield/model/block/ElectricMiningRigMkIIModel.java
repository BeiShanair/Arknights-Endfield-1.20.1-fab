package com.besson.endfield.model.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.ElectricMiningRigMkIIBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ElectricMiningRigMkIIModel extends GeoModel<ElectricMiningRigMkIIBlockEntity> {
    @Override
    public Identifier getModelResource(ElectricMiningRigMkIIBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/electric_mining_rig_mk_ii.geo.json");
    }

    @Override
    public Identifier getTextureResource(ElectricMiningRigMkIIBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/electric_mining_rig_mk_ii.png");
    }

    @Override
    public Identifier getAnimationResource(ElectricMiningRigMkIIBlockEntity animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/electric_mining_rig_mk_ii.animation.json");
    }
}
