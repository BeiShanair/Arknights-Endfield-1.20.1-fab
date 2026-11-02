package com.besson.endfield.renderer.block.production2;

import com.besson.endfield.blockentity.custom.production2.ExpandedCrucibleBlockEntity;
import com.besson.endfield.config.ModClientConfig;
import com.besson.endfield.model.block.production2.ExpandedCrucibleModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ExpandedCrucibleRenderer extends GeoBlockRenderer<ExpandedCrucibleBlockEntity> {
    public ExpandedCrucibleRenderer(BlockEntityRendererFactory.Context context) {
        super(new ExpandedCrucibleModel());
    }

    @Override
    public int getRenderDistance() {
        return ModClientConfig.getInstance().getEquipmentRenderDistance();
    }
}
