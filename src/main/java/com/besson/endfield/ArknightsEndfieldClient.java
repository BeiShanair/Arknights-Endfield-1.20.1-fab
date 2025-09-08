package com.besson.endfield;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.renderer.*;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class ArknightsEndfieldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockEntityRendererFactories.register(ModBlockEntities.PROTOCOL_ANCHOR_CORE, ProtocolAnchorCoreRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.RELAY_TOWER, RelayTowerRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ELECTRIC_PYLON, ElectricPylonRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ELECTRIC_MINING_RIG, ElectricMiningRigRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ELECTRIC_MINING_RIG_MK_II, ElectricMiningRigMkIIRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.PORTABLE_ORIGINIUM_RIG, PortableOriginiumRigRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.REFINING_UNIT, RefiningUnitRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.SHREDDING_UNIT, ShreddingUnitRenderer::new);
    }
}
