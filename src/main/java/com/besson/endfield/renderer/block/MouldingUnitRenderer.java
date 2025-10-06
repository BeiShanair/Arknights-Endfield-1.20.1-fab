package com.besson.endfield.renderer.block;

import com.besson.endfield.blockentity.custom.MouldingUnitBlockEntity;
import com.besson.endfield.model.block.MouldingUnitModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class MouldingUnitRenderer extends GeoBlockRenderer<MouldingUnitBlockEntity> {
    public MouldingUnitRenderer(BlockEntityRendererFactory.Context context) {
        super(new MouldingUnitModel());
    }
}
