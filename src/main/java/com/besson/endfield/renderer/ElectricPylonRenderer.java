package com.besson.endfield.renderer;

import com.besson.endfield.blockentity.custom.ElectricPylonBlockEntity;
import com.besson.endfield.model.ElectricPylonModel;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ElectricPylonRenderer extends GeoBlockRenderer<ElectricPylonBlockEntity> {
    public ElectricPylonRenderer(BlockEntityRendererFactory.Context context) {
        super(new ElectricPylonModel());
    }
}
