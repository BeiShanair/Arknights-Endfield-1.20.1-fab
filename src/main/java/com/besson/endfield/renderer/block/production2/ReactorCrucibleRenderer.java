package com.besson.endfield.renderer.block.production2;

import com.besson.endfield.blockentity.custom.production2.ReactorCrucibleBlockEntity;
import com.besson.endfield.config.ModClientConfig;
import com.besson.endfield.model.block.production2.ReactorCrucibleModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ReactorCrucibleRenderer extends GeoBlockRenderer<ReactorCrucibleBlockEntity> {
    public ReactorCrucibleRenderer(BlockEntityRendererFactory.Context context) {
        super(new ReactorCrucibleModel());
    }

    @Override
    public int getRenderDistance() {
        return ModClientConfig.getInstance().getEquipmentRenderDistance();
    }
}
