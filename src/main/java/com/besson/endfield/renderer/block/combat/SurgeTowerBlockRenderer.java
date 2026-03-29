package com.besson.endfield.renderer.block.combat;

import com.besson.endfield.blockentity.custom.combat.SurgeTowerBlockEntity;
import com.besson.endfield.model.block.combat.SurgeTowerModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SurgeTowerBlockRenderer extends GeoBlockRenderer<SurgeTowerBlockEntity> {
    public SurgeTowerBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new SurgeTowerModel());
    }
}
