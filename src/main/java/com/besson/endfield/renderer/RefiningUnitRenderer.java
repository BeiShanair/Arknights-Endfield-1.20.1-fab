package com.besson.endfield.renderer;

import com.besson.endfield.blockentity.custom.RefiningUnitBlockEntity;
import com.besson.endfield.model.RefiningUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class RefiningUnitRenderer extends GeoBlockRenderer<RefiningUnitBlockEntity> {
    public RefiningUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new RefiningUnitModel());
    }
}
