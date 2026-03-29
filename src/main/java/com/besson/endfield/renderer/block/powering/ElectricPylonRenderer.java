package com.besson.endfield.renderer.block.powering;

import com.besson.endfield.blockentity.custom.powering.ElectricPylonBlockEntity;
import com.besson.endfield.model.block.powering.ElectricPylonModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ElectricPylonRenderer extends GeoBlockRenderer<ElectricPylonBlockEntity> {
    public ElectricPylonRenderer(BlockEntityRendererFactory.Context context) {
        super(new ElectricPylonModel());
    }
}
