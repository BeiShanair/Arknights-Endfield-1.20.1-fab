package com.besson.endfield.model.item.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.item.custom.combat.MedicalTowerItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MedicalTowerItemModel extends GeoModel<MedicalTowerItem> {
    @Override
    public Identifier getModelResource(MedicalTowerItem medicalTowerItem) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/medical_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(MedicalTowerItem medicalTowerItem) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/medical_tower.png");
    }

    @Override
    public Identifier getAnimationResource(MedicalTowerItem medicalTowerItem) {
        return null;
    }
}
