package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.SeedPickingUnitBlockEntity;
import com.besson.endfield.model.block.SeedPickingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SeedPickingUnitRenderer extends GeoBlockRenderer<SeedPickingUnitBlockEntity> {
    public SeedPickingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new SeedPickingUnitModel());
    }
}
