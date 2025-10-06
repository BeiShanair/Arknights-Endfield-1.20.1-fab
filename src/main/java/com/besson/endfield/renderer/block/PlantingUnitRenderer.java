package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.PlantingUnitBlockEntity;
import com.besson.endfield.model.block.PlantingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PlantingUnitRenderer extends GeoBlockRenderer<PlantingUnitBlockEntity> {
    public PlantingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new PlantingUnitModel());
    }
}
