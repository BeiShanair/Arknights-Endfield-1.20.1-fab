package com.besson.endfield.renderer.block.production1;

import com.besson.endfield.blockentity.custom.production1.SeedPickingUnitBlockEntity;
import com.besson.endfield.model.block.production1.SeedPickingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SeedPickingUnitRenderer extends GeoBlockRenderer<SeedPickingUnitBlockEntity> {
    public SeedPickingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new SeedPickingUnitModel());
    }
}
