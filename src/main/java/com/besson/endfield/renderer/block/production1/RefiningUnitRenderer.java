package com.besson.endfield.renderer.block.production1;

import com.besson.endfield.blockentity.custom.production1.RefiningUnitBlockEntity;
import com.besson.endfield.model.block.production1.RefiningUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class RefiningUnitRenderer extends GeoBlockRenderer<RefiningUnitBlockEntity> {
    public RefiningUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new RefiningUnitModel());
    }
}
