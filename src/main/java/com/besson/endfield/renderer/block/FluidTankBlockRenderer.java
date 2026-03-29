package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.FluidTankBlockEntity;
import com.besson.endfield.model.block.FluidTankModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FluidTankBlockRenderer extends GeoBlockRenderer<FluidTankBlockEntity> {
    public FluidTankBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new FluidTankModel());
    }
}
