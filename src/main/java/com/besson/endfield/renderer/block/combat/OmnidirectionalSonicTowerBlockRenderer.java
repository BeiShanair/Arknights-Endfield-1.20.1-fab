package com.besson.endfield.renderer.block.combat;

import com.besson.endfield.blockentity.custom.combat.OmnidirectionalSonicTowerBlockEntity;
import com.besson.endfield.model.block.combat.OmnidirectionalSonicTowerModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class OmnidirectionalSonicTowerBlockRenderer extends GeoBlockRenderer<OmnidirectionalSonicTowerBlockEntity> {
    public OmnidirectionalSonicTowerBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new OmnidirectionalSonicTowerModel());
    }
}
