package com.besson.endfield.model.item.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.combat.HeGrenadeTowerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class HeGrenadeTowerItemModel extends GeoModel<HeGrenadeTowerItem> {
    @Override
    public Identifier getModelResource(HeGrenadeTowerItem heGrenadeTowerItem) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/he_grenade_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(HeGrenadeTowerItem heGrenadeTowerItem) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/he_grenade_tower.png");
    }

    @Override
    public Identifier getAnimationResource(HeGrenadeTowerItem heGrenadeTowerItem) {
        return null;
    }
}
