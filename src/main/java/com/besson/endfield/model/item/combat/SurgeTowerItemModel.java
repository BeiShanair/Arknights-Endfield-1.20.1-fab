package com.besson.endfield.model.item.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.combat.SurgeTowerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SurgeTowerItemModel extends GeoModel<SurgeTowerItem> {
    @Override
    public Identifier getModelResource(SurgeTowerItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/surge_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(SurgeTowerItem animatable) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/surge_tower.png");
    }

    @Override
    public Identifier getAnimationResource(SurgeTowerItem animatable) {
        return null;
    }
}
