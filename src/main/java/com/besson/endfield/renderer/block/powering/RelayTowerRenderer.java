package com.besson.endfield.renderer.block.powering;

import com.besson.endfield.blockentity.custom.powering.RelayTowerBlockEntity;
import com.besson.endfield.model.block.powering.RelayTowerModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class RelayTowerRenderer extends GeoBlockRenderer<RelayTowerBlockEntity> {
    public RelayTowerRenderer(BlockEntityRendererFactory.Context context) {
        super(new RelayTowerModel());
    }
}
