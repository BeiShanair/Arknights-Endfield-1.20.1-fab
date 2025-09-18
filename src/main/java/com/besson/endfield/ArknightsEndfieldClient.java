package com.besson.endfield;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.ElectricMiningRigMkIIBlockEntity;
import com.besson.endfield.renderer.block.*;
import com.besson.endfield.screen.ModScreens;
import com.besson.endfield.screen.custom.*;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class ArknightsEndfieldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockEntityRendererFactories.register(ModBlockEntities.PROTOCOL_ANCHOR_CORE, ProtocolAnchorCoreRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.RELAY_TOWER, RelayTowerEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ELECTRIC_PYLON, ElectricPylonEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ELECTRIC_MINING_RIG, ElectricMiningRigRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ELECTRIC_MINING_RIG_MK_II, ElectricMiningRigMkIIRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.PORTABLE_ORIGINIUM_RIG, PortableOriginiumRigEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.REFINING_UNIT, RefiningUnitRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.SHREDDING_UNIT, ShreddingUnitRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.FILLING_UNIT, FillingUnitRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.FITTING_UNIT, FittingUnitRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.GEARING_UNIT, GearingUnitRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.GRINDING_UNIT, GrindingUnitRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.MOULDING_UNIT, MouldingUnitRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.PACKAGING_UNIT, PackagingUnitRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.PLANTING_UNIT, PlantingUnitRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.SEED_PICKING_UNIT, SeedPickingUnitRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.THERMAL_BANK, ThermalBankRenderer::new);

        HandledScreens.register(ModScreens.PORTABLE_ORIGINIUM_RIG_SCREEN, PortableOriginiumRigScreen::new);
        HandledScreens.register(ModScreens.PROTOCOL_ANCHOR_CORE_SCREEN, ProtocolAnchorCoreScreen::new);
        HandledScreens.register(ModScreens.THERMAL_BANK_SCREEN, ThermalBankScreen::new);
        HandledScreens.register(ModScreens.REFINING_UNIT_SCREEN, RefiningUnitScreen::new);
        HandledScreens.register(ModScreens.GEARING_UNIT_SCREEN, GearingUnitScreen::new);
        HandledScreens.register(ModScreens.ELECTRIC_MINING_RIG_SCREEN, ElectricMiningRigScreen::new);
        HandledScreens.register(ModScreens.ELECTRIC_MINING_RIG_MK_II_SCREEN, ElectricMiningRigMkIIScreen::new);
        HandledScreens.register(ModScreens.SHREDDING_UNIT_SCREEN, ShreddingUnitScreen::new);
        HandledScreens.register(ModScreens.FITTING_UNIT_SCREEN, FittingUnitScreen::new);
        HandledScreens.register(ModScreens.MOULDING_UNIT_SCREEN, MouldingUnitScreen::new);
        HandledScreens.register(ModScreens.PLANTING_UNIT_SCREEN, PlantingUnitScreen::new);
        HandledScreens.register(ModScreens.SEED_PICKING_UNIT_SCREEN, SeedPickingUnitScreen::new);
        HandledScreens.register(ModScreens.FILLING_UNIT_SCREEN, FillingUnitScreen::new);
        HandledScreens.register(ModScreens.GRINDING_UNIT_SCREEN, GrindingUnitScreen::new);
        HandledScreens.register(ModScreens.PACKAGING_UNIT_SCREEN, PackagingUnitScreen::new);
    }
}
