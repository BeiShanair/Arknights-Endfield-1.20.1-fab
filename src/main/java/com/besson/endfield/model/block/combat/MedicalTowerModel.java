package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.combat.MedicalTowerBlockEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MedicalTowerModel extends GeoModel<MedicalTowerBlockEntity> {
    @Override
    public Identifier getModelResource(MedicalTowerBlockEntity medicalTowerBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/medical_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(MedicalTowerBlockEntity medicalTowerBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/medical_tower.png");
    }

    @Override
    public Identifier getAnimationResource(MedicalTowerBlockEntity medicalTowerBlockEntity) {
        return null;
    }
}
