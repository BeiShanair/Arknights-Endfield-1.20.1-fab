package com.besson.endfield.renderer.block.production2;

import com.besson.endfield.blockentity.custom.production2.SeparatingUnitBlockEntity;
import com.besson.endfield.config.ModClientConfig;
import com.besson.endfield.model.block.production2.SeparatingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SeparatingUnitRenderer extends GeoBlockRenderer<SeparatingUnitBlockEntity> {
    public SeparatingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new SeparatingUnitModel());
    }
    
    @Override
    public int getRenderDistance() {
        return ModClientConfig.getInstance().getEquipmentRenderDistance();
    }
}
