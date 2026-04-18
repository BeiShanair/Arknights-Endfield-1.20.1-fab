package com.besson.endfield.renderer.block.logicitis;

import com.besson.endfield.blockentity.custom.logicitis.DepotUnloaderBlockEntity;
import com.besson.endfield.model.block.logicitis.DepotUnloaderModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DepotUnloaderRenderer extends GeoBlockRenderer<DepotUnloaderBlockEntity> {
    public DepotUnloaderRenderer(BlockEntityRendererFactory.Context context) {
        super(new DepotUnloaderModel());
    }
}
