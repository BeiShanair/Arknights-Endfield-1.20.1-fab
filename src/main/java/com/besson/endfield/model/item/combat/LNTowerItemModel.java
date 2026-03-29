package com.besson.endfield.model.item.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.combat.LNTowerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class LNTowerItemModel extends GeoModel<LNTowerItem> {
    @Override
    public Identifier getModelResource(LNTowerItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/ln_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(LNTowerItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/ln_tower.png");
    }

    @Override
    public Identifier getAnimationResource(LNTowerItem animatable) {
        return null;
    }
}
