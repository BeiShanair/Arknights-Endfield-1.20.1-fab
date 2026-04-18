package com.besson.endfield.renderer.block.logicitis;

import com.besson.endfield.blockentity.custom.logicitis.DepotLoaderBlockEntity;
import com.besson.endfield.model.block.logicitis.DepotLoaderModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DepotLoaderRenderer extends GeoBlockRenderer<DepotLoaderBlockEntity> {
    public DepotLoaderRenderer(BlockEntityRendererFactory.Context context) {
        super(new DepotLoaderModel());
    }
}
