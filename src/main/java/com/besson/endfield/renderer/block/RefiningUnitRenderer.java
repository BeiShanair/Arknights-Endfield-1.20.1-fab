package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.RefiningUnitBlockEntity;
import com.besson.endfield.model.block.RefiningUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class RefiningUnitRenderer extends GeoBlockRenderer<RefiningUnitBlockEntity> {
    public RefiningUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new RefiningUnitModel());
    }
}
