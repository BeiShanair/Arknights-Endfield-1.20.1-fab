package com.besson.endfield.renderer.block.depot;

import com.besson.endfield.blockentity.custom.depot.DepotLoaderBlockEntity;
import com.besson.endfield.config.ModClientConfig;
import com.besson.endfield.model.block.depot.DepotLoaderModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DepotLoaderRenderer extends GeoBlockRenderer<DepotLoaderBlockEntity> {
    public DepotLoaderRenderer(BlockEntityRendererFactory.Context context) {
        super(new DepotLoaderModel());
    }

    @Override
    public int getRenderDistance() {
        return ModClientConfig.getInstance().getEquipmentRenderDistance();
    }
}
