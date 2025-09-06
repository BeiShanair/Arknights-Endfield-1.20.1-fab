package com.besson.endfield;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.renderer.ProtocolAnchorCoreRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class ArknightsEndfieldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockEntityRendererFactories.register(ModBlockEntities.PROTOCOL_ANCHOR_CORE, ProtocolAnchorCoreRenderer::new);

    }
}
