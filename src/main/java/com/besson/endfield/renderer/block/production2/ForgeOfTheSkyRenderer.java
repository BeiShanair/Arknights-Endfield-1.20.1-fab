package com.besson.endfield.renderer.block.production2;

import com.besson.endfield.blockentity.custom.production2.ForgeOfTheSkyBlockEntity;
import com.besson.endfield.config.ModClientConfig;
import com.besson.endfield.model.block.production2.ForgeOfTheSkyModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ForgeOfTheSkyRenderer extends GeoBlockRenderer<ForgeOfTheSkyBlockEntity> {
    public ForgeOfTheSkyRenderer(BlockEntityRendererFactory.Context context) {
        super(new ForgeOfTheSkyModel());
    }

    @Override
    public int getRenderDistance() {
        return ModClientConfig.getInstance().getEquipmentRenderDistance();
    }
}
