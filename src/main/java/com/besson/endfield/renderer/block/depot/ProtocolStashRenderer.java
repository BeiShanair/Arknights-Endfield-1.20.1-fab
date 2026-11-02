package com.besson.endfield.renderer.block.depot;

import com.besson.endfield.blockentity.custom.depot.ProtocolStashBlockEntity;
import com.besson.endfield.config.ModClientConfig;
import com.besson.endfield.model.block.depot.ProtocolStashModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ProtocolStashRenderer extends GeoBlockRenderer<ProtocolStashBlockEntity> {
    public ProtocolStashRenderer(BlockEntityRendererFactory.Context context) {
        super(new ProtocolStashModel());
    }

    @Override
    public int getRenderDistance() {
        return ModClientConfig.getInstance().getEquipmentRenderDistance();
    }
}
