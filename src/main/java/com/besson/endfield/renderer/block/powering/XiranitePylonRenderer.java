package com.besson.endfield.renderer.block.powering;

import com.besson.endfield.blockentity.custom.powering.XiranitePylonBlockEntity;
import com.besson.endfield.model.block.powering.XiranitePylonModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class XiranitePylonRenderer extends GeoBlockRenderer<XiranitePylonBlockEntity> {
    public XiranitePylonRenderer(BlockEntityRendererFactory.Context context) {
        super(new XiranitePylonModel());
    }
}
