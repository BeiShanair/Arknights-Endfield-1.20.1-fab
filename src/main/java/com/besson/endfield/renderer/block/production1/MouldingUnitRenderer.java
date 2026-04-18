package com.besson.endfield.renderer.block.production1;

import com.besson.endfield.blockentity.custom.production1.MouldingUnitBlockEntity;
import com.besson.endfield.model.block.production1.MouldingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class MouldingUnitRenderer extends GeoBlockRenderer<MouldingUnitBlockEntity> {
    public MouldingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new MouldingUnitModel());
    }
}
