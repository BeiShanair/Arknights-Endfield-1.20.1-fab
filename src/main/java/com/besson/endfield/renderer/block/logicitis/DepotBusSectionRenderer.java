package com.besson.endfield.renderer.block.logicitis;

import com.besson.endfield.blockentity.custom.logicitis.DepotBusSectionBlockEntity;
import com.besson.endfield.model.block.logicitis.DepotBusSectionModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DepotBusSectionRenderer extends GeoBlockRenderer<DepotBusSectionBlockEntity> {
    public DepotBusSectionRenderer(BlockEntityRendererFactory.Context context) {
        super(new DepotBusSectionModel());
    }
}
