package com.besson.endfield.renderer;

import com.besson.endfield.blockentity.custom.ElectricMiningRigBlockEntity;
import com.besson.endfield.model.ElectricMiningRigModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ElectricMiningRigRenderer extends GeoBlockRenderer<ElectricMiningRigBlockEntity> {
    public ElectricMiningRigRenderer(BlockEntityRendererFactory.Context context) {
        super(new ElectricMiningRigModel());
    }
}
