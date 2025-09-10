package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.PortableOriginiumRigBlockEntity;
import com.besson.endfield.model.block.PortableOriginiumModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PortableOriginiumRigRenderer extends GeoBlockRenderer<PortableOriginiumRigBlockEntity> {
    public PortableOriginiumRigRenderer(BlockEntityRendererFactory.Context context) {
        super(new PortableOriginiumModel());
    }
}
