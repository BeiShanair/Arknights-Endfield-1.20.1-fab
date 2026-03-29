package com.besson.endfield.renderer.block.powering;

import com.besson.endfield.blockentity.custom.powering.ProtocolAnchorCoreBlockEntity;
import com.besson.endfield.model.block.powering.ProtocolAnchorCoreModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ProtocolAnchorCoreRenderer extends GeoBlockRenderer<ProtocolAnchorCoreBlockEntity> {
    public ProtocolAnchorCoreRenderer(BlockEntityRendererFactory.Context context) {
        super(new ProtocolAnchorCoreModel());
    }
}
