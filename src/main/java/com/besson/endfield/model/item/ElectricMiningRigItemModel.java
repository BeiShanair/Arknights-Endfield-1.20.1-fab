package com.besson.endfield.model.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.ElectricMiningRigItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ElectricMiningRigItemModel extends GeoModel<ElectricMiningRigItem> {
    @Override
    public Identifier getModelResource(ElectricMiningRigItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/electric_mining_rig.geo.json");
    }

    @Override
    public Identifier getTextureResource(ElectricMiningRigItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/electric_mining_rig.png");
    }

    @Override
    public Identifier getAnimationResource(ElectricMiningRigItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "animations/electric_mining_rig.animation.json");
    }
}
