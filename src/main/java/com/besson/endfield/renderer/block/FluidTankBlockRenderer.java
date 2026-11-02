package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.depot.FluidTankBlockEntity;
import com.besson.endfield.config.ModClientConfig;
import com.besson.endfield.model.block.FluidTankModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FluidTankBlockRenderer extends GeoBlockRenderer<FluidTankBlockEntity> {
    public FluidTankBlockRenderer(BlockEntityRendererFactory.Context context) {
        super(new FluidTankModel());
    }

    @Override
    public int getRenderDistance() {
        return ModClientConfig.getInstance().getEquipmentRenderDistance();
    }
}
