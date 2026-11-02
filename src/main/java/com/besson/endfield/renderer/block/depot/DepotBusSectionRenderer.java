package com.besson.endfield.renderer.block.depot;

import com.besson.endfield.blockentity.custom.depot.DepotBusSectionBlockEntity;
import com.besson.endfield.config.ModClientConfig;
import com.besson.endfield.model.block.depot.DepotBusSectionModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class DepotBusSectionRenderer extends GeoBlockRenderer<DepotBusSectionBlockEntity> {
    public DepotBusSectionRenderer(BlockEntityRendererFactory.Context context) {
        super(new DepotBusSectionModel());
    }

    @Override
    public int getRenderDistance() {
        return ModClientConfig.getInstance().getEquipmentRenderDistance();
    }
}
