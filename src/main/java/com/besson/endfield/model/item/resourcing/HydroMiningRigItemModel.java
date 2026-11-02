package com.besson.endfield.model.item.resourcing;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.resourcing.HydroMiningRigItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class HydroMiningRigItemModel extends GeoModel<HydroMiningRigItem> {
    @Override
    public Identifier getModelResource(HydroMiningRigItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/hydro_mining_rig.geo.json");
    }

    @Override
    public Identifier getTextureResource(HydroMiningRigItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/hydro_mining_rig.png");
    }

    @Override
    public Identifier getAnimationResource(HydroMiningRigItem animatable) {
        return null;
    }
}
