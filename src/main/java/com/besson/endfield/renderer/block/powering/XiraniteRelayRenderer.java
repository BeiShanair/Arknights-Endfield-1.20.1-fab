package com.besson.endfield.renderer.block.powering;

import com.besson.endfield.blockentity.custom.powering.XiraniteRelayBlockEntity;
import com.besson.endfield.model.block.powering.XiraniteRelayModel;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class XiraniteRelayRenderer extends GeoBlockRenderer<XiraniteRelayBlockEntity> {
    public XiraniteRelayRenderer(BlockEntityRendererFactory.Context context) {
        super(new XiraniteRelayModel());
    }
}
