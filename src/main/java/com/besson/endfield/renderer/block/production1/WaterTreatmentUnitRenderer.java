package com.besson.endfield.renderer.block.production1;

import com.besson.endfield.blockentity.custom.production1.WaterTreatmentUnitBlockEntity;
import com.besson.endfield.config.ModClientConfig;
import com.besson.endfield.model.block.production1.WaterTreatmentUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class WaterTreatmentUnitRenderer extends GeoBlockRenderer<WaterTreatmentUnitBlockEntity> {
    public WaterTreatmentUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new WaterTreatmentUnitModel());
    }
    
    @Override
    public int getRenderDistance() {
        return ModClientConfig.getInstance().getEquipmentRenderDistance();
    }
}
