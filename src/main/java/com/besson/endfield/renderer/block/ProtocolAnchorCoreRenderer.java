package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.ProtocolAnchorCoreBlockEntity;
import com.besson.endfield.model.block.ProtocolAnchorCoreModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ProtocolAnchorCoreRenderer extends GeoBlockRenderer<ProtocolAnchorCoreBlockEntity> {
    public ProtocolAnchorCoreRenderer(BlockEntityRendererFactory.Context context) {
        super(new ProtocolAnchorCoreModel());
    }
}
