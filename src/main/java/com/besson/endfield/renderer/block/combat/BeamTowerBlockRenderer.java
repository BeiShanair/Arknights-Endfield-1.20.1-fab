package com.besson.endfield.renderer.block.combat;

import com.besson.endfield.blockentity.custom.combat.BeamTowerBlockEntity;
import com.besson.endfield.model.block.combat.BeamTowerModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class BeamTowerBlockRenderer extends GeoBlockRenderer<BeamTowerBlockEntity> {
    public BeamTowerBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new BeamTowerModel());
    }
}
