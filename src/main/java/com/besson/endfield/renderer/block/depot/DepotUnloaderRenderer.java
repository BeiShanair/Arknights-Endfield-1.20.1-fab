package com.besson.endfield.renderer.block.depot;

import com.besson.endfield.blockentity.custom.depot.DepotUnloaderBlockEntity;
import com.besson.endfield.config.ModClientConfig;
import com.besson.endfield.model.block.depot.DepotUnloaderModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DepotUnloaderRenderer extends GeoBlockRenderer<DepotUnloaderBlockEntity> {
    public DepotUnloaderRenderer(BlockEntityRendererFactory.Context context) {
        super(new DepotUnloaderModel());
    }

    @Override
    public int getRenderDistance() {
        return ModClientConfig.getInstance().getEquipmentRenderDistance();
    }
}
