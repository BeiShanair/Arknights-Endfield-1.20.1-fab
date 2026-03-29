package com.besson.endfield.model.item.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.combat.GrenadeTowerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class GrenadeTowerItemModel extends GeoModel<GrenadeTowerItem> {
    @Override
    public Identifier getModelResource(GrenadeTowerItem grenadeTowerItem) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/grenade_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(GrenadeTowerItem grenadeTowerItem) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/grenade_tower.png");
    }

    @Override
    public Identifier getAnimationResource(GrenadeTowerItem grenadeTowerItem) {
        return null;
    }
}
