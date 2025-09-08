package com.besson.endfield.model;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.ElectricMiningRigItem;
import com.besson.endfield.item.custom.ElectricMiningRigMkIIItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ElectricMiningRigMkIIItemModel extends GeoModel<ElectricMiningRigMkIIItem> {
    @Override
    public Identifier getModelResource(ElectricMiningRigMkIIItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/electric_mining_rig_mk_ii.geo.json");
    }

    @Override
    public Identifier getTextureResource(ElectricMiningRigMkIIItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/electric_mining_rig_mk_ii.png");
    }

    @Override
    public Identifier getAnimationResource(ElectricMiningRigMkIIItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/electric_mining_rig_mk_ii.animation.json");
    }
}
