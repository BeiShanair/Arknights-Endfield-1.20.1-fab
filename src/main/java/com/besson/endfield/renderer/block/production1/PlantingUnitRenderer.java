package com.besson.endfield.renderer.block.production1;

import com.besson.endfield.blockentity.custom.production1.PlantingUnitBlockEntity;
import com.besson.endfield.model.block.production1.PlantingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PlantingUnitRenderer extends GeoBlockRenderer<PlantingUnitBlockEntity> {
    public PlantingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new PlantingUnitModel());
    }
}
