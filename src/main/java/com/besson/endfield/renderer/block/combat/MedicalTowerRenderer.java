package com.besson.endfield.renderer.block.combat;

import com.besson.endfield.blockentity.custom.combat.MedicalTowerBlockEntity;
import com.besson.endfield.model.block.combat.MedicalTowerModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class MedicalTowerRenderer extends GeoBlockRenderer<MedicalTowerBlockEntity> {
    public MedicalTowerRenderer(BlockEntityRendererFactory.Context context) {
        super(new MedicalTowerModel());
    }
}
