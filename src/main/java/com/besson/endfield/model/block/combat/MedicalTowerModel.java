package com.besson.endfield.model.block.combat;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.combat.MedicalTowerBlock;
import com.besson.endfield.blockentity.custom.combat.MedicalTowerBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class MedicalTowerModel extends GeoModel<MedicalTowerBlockEntity> {
    @Override
    public Identifier getModelResource(MedicalTowerBlockEntity medicalTowerBlockEntity) {
        return new Identifier(ArknightsEndfield.MOD_ID, "geo/medical_tower.geo.json");
    }

    @Override
    public Identifier getTextureResource(MedicalTowerBlockEntity medicalTowerBlockEntity) {
        Block block = medicalTowerBlockEntity.getCachedState().getBlock();
        if (block instanceof MedicalTowerBlock b) {
            if (b.getDefaultState().get(MedicalTowerBlock.PREVIEW)) {
                return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/medical_tower_pre.png");
            }
        }
        return new Identifier(ArknightsEndfield.MOD_ID, "textures/block/medical_tower.png");
    }

    @Override
    public Identifier getAnimationResource(MedicalTowerBlockEntity medicalTowerBlockEntity) {
        return null;
    }

    @Override
    public RenderLayer getRenderType(MedicalTowerBlockEntity animatable, Identifier texture) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
