package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.ElectricPylonBlockEntity;
import com.besson.endfield.model.block.ElectricPylonModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ElectricPylonRenderer extends GeoBlockRenderer<ElectricPylonBlockEntity> {
    public ElectricPylonRenderer(BlockEntityRendererFactory.Context context) {
        super(new ElectricPylonModel());
    }
}
