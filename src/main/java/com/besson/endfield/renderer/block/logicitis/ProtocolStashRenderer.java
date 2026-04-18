package com.besson.endfield.renderer.block.logicitis;

import com.besson.endfield.blockentity.custom.logicitis.ProtocolStashBlockEntity;
import com.besson.endfield.model.block.logicitis.ProtocolStashModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ProtocolStashRenderer extends GeoBlockRenderer<ProtocolStashBlockEntity> {
    public ProtocolStashRenderer(BlockEntityRendererFactory.Context context) {
        super(new ProtocolStashModel());
    }
}
