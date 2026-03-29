package com.besson.endfield.renderer.block.resourcing;

import com.besson.endfield.blockentity.custom.resourcing.FluidPumpBlockEntity;
import com.besson.endfield.model.block.resourcing.FluidPumpBlockModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FluidPumpBlockRenderer extends GeoBlockRenderer<FluidPumpBlockEntity> {
    public FluidPumpBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new FluidPumpBlockModel());
    }
}
