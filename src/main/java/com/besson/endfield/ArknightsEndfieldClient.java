package com.besson.endfield;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.config.ModClientConfig;
import com.besson.endfield.entity.ModItemEntity;
import com.besson.endfield.fluid.ModFluids;
import com.besson.endfield.network.ModNetWorking;
import com.besson.endfield.renderer.block.*;
import com.besson.endfield.renderer.block.combat.*;
import com.besson.endfield.renderer.block.depot.DepotBusSectionRenderer;
import com.besson.endfield.renderer.block.depot.DepotLoaderRenderer;
import com.besson.endfield.renderer.block.depot.DepotUnloaderRenderer;
import com.besson.endfield.renderer.block.depot.ProtocolStashRenderer;
import com.besson.endfield.renderer.block.logicitis.*;
import com.besson.endfield.renderer.block.powering.*;
import com.besson.endfield.renderer.block.production1.*;
import com.besson.endfield.renderer.block.production2.*;
import com.besson.endfield.renderer.block.resourcing.*;
import com.besson.endfield.renderer.pre.logicitis.DepotBusSectionPreviewRenderer;
import com.besson.endfield.renderer.pre.logicitis.DepotLoaderPreviewRenderer;
import com.besson.endfield.renderer.pre.logicitis.DepotUnloaderPreviewRenderer;
import com.besson.endfield.renderer.pre.logicitis.ProtocolStashPreviewRenderer;
import com.besson.endfield.renderer.pre.powering.ElectricPylonPreviewRenderer;
import com.besson.endfield.renderer.pre.combat.*;
import com.besson.endfield.renderer.pre.powering.ProtocolAnchorCorePreviewRenderer;
import com.besson.endfield.renderer.pre.powering.RelayTowerPreviewRenderer;
import com.besson.endfield.renderer.pre.powering.ThermalBankPreviewRenderer;
import com.besson.endfield.renderer.pre.production1.*;
import com.besson.endfield.renderer.pre.production2.FillingUnitPreviewRenderer;
import com.besson.endfield.renderer.pre.production2.GearingUnitPreviewRenderer;
import com.besson.endfield.renderer.pre.production2.GrindingUnitPreviewRenderer;
import com.besson.endfield.renderer.pre.production2.PackagingUnitPreviewRenderer;
import com.besson.endfield.renderer.pre.resourcing.ElectricMiningRigMkIIPreviewRenderer;
import com.besson.endfield.renderer.pre.resourcing.ElectricMiningRigPreviewRenderer;
import com.besson.endfield.renderer.pre.resourcing.PortableOriginiumRigPreviewRenderer;
import com.besson.endfield.screen.ModScreens;
import com.besson.endfield.screen.custom.screen.*;
import com.besson.endfield.screen.custom.screenHandler.StorageScreenHandler;
import com.besson.endfield.utils.KeyInputHandler;
import com.besson.endfield.utils.ModKeyBindings;
import com.besson.endfield.utils.storage.StorageEntry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ArknightsEndfieldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModItemEntity.INDUSTRIAL_EXPLOSIVE, FlyingItemEntityRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.PROTOCOL_ANCHOR_CORE, ProtocolAnchorCoreRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.RELAY_TOWER, RelayTowerEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ELECTRIC_PYLON, ElectricPylonEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.XIRANITE_PYLON, XiranitePylonEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.XIRANITE_RELAY, XiraniteRelayEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ELECTRIC_MINING_RIG, ElectricMiningRigRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ELECTRIC_MINING_RIG_MK_II, ElectricMiningRigMkIIRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.PORTABLE_ORIGINIUM_RIG, PortableOriginiumRigEntityRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.HYDRO_MINING_RIG, HydroMiningRigRenderer::new);
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

        BlockEntityRendererFactories.register(ModBlockEntities.FLUID_PUMP, FluidPumpBlockRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.FLUID_TANK, FluidTankBlockRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.BEAM_TOWER, BeamTowerBlockRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.GUN_TOWER, GunTowerBlockRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.GRENADE_TOWER, GrenadeTowerBlockRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.HEAVY_GUN_TOWER, HeavyGunTowerBlockRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.HE_GRENADE_TOWER, HeGrenadeTowerBlockRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.LN_TOWER, LNTowerBlockRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.MARSH_GAS_MK_I, MarshGasMkIBlockRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.MEDICAL_TOWER, MedicalTowerRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.OMNIDIRECTIONAL_SONIC_TOWER, OmnidirectionalSonicTowerBlockRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.SENTRY_TOWER, SentryTowerBlockRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.SURGE_TOWER, SurgeTowerBlockRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.BELT, BeltRenderer::new);

        BlockEntityRendererFactories.register(ModBlockEntities.PROTOCOL_STASH, ProtocolStashRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.DEPOT_BUS_SECTION, DepotBusSectionRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.DEPOT_LOADER, DepotLoaderRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.DEPOT_UNLOADER, DepotUnloaderRenderer::new);
        
        BlockEntityRendererFactories.register(ModBlockEntities.REACTOR_CRUCIBLE, ReactorCrucibleRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.EXPANDED_CRUCIBLE, ExpandedCrucibleRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.SEPARATING_UNIT, SeparatingUnitRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.FORGE_OF_THE_SKY, ForgeOfTheSkyRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.ACID_RESISTANT_PUMP_MK_II, AcidResistantPumpMkIIRenderer::new);
        BlockEntityRendererFactories.register(ModBlockEntities.WATER_TREATMENT_UNIT, WaterTreatmentUnitRenderer::new);

        HandledScreens.register(ModScreens.PORTABLE_ORIGINIUM_RIG_SCREEN, PortableOriginiumRigScreen::new);
        HandledScreens.register(ModScreens.HYDRO_MINING_RIG_SCREEN, HydroMiningRigScreen::new);
        HandledScreens.register(ModScreens.PROTOCOL_ANCHOR_CORE_SCREEN, ProtocolAnchorCoreScreen::new);
        HandledScreens.register(ModScreens.THERMAL_BANK_SCREEN, ThermalBankScreen::new);
        HandledScreens.register(ModScreens.REFINING_UNIT_SCREEN, RefiningUnitScreen::new);
        HandledScreens.register(ModScreens.WATER_TREATMENT_UNIT_SCREEN, WaterTreatmentUnitScreen::new);
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
        HandledScreens.register(ModScreens.SEPARATING_UNIT_SCREEN, SeparatingUnitScreen::new);
        HandledScreens.register(ModScreens.REACTOR_CRUCIBLE_SCREEN, ReactorCrucibleScreen::new);
        HandledScreens.register(ModScreens.EXPANDED_CRUCIBLE_SCREEN, ExpandedCrucibleScreen::new);
        HandledScreens.register(ModScreens.CRAFTER_SCREEN, CrafterScreen::new);
        HandledScreens.register(ModScreens.SUPPLY_TERMINAL_SCREEN, SupplyTerminalScreen::new);

        HandledScreens.register(ModScreens.FLUID_PUMP_SCREEN, FluidPumpScreen::new);
        HandledScreens.register(ModScreens.ACID_RESISTANT_PUMP_MK_II_SCREEN, AcidResistantPumpMkIIScreen::new);
        HandledScreens.register(ModScreens.FLUID_TANK_SCREEN, FluidTankScreen::new);

        HandledScreens.register(ModScreens.STORAGE_SCREEN, StorageScreen::new);
        HandledScreens.register(ModScreens.PROTOCOL_ANCHOR_CORE_PORT_SCREEN, ProtocolAnchorCorePortScreen::new);
        HandledScreens.register(ModScreens.DEPOT_UNLOADER_SCREEN, DepotUnloaderScreen::new);
        HandledScreens.register(ModScreens.PROTOCOL_STASH_SCREEN, ProtocolStashScreen::new);
        HandledScreens.register(ModScreens.FORGE_OF_THE_SKY_SCREEN, ForgeOfTheSkyScreen::new);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AKETINE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_AKETINE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AMBER_RICE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_AMBER_RICE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BUCKFLOWER_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_BUCKFLOWER_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CITROME_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_CITROME_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FIREBUCKLE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_FIREBUCKLE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLUFFED_JINCAO_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_FLUFFED_JINCAO_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JINCAO_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_JINCAO_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REDJADE_GINSENG_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_REDJADE_GINSENG_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REED_RYE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_REED_RYE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SANDLEAF_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_SANDLEAF_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TARTPEPPER_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_TARTPEPPER_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.THORNY_YAZHEN_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_THORNY_YAZHEN_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.UMBRALINE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_UMBRALINE_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.YAZHEN_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_YAZHEN_BLOCK, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.FLUID_PIPE, RenderLayer.getCutout());
        
        RenderLayer beltRenderLayer = ModClientConfig.getInstance().resolveBeltRenderLayer();
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BELT, beltRenderLayer);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SEWAGE_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_SEWAGE, ModFluids.FLOWING_SEWAGE);
        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_SEWAGE, ModFluids.FLOWING_SEWAGE,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0xFF2F4F4F
                )
        );
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PRECIPITATION_ACID_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_PRECIPITATION_ACID, ModFluids.FLOWING_PRECIPITATION_ACID);
        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_PRECIPITATION_ACID, ModFluids.FLOWING_PRECIPITATION_ACID,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0xFFFFD700
                )
        );

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.XIRCON_EFFLUENT_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_XIRCON_EFFLUENT, ModFluids.FLOWING_XIRCON_EFFLUENT);
        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_XIRCON_EFFLUENT, ModFluids.FLOWING_XIRCON_EFFLUENT,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0xFF557559
                )
        );
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INERT_XIRCON_EFFLUENT_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_INERT_XIRCON_EFFLUENT, ModFluids.FLOWING_INERT_XIRCON_EFFLUENT);
        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_INERT_XIRCON_EFFLUENT, ModFluids.FLOWING_INERT_XIRCON_EFFLUENT,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0xFF194B30
                )
        );
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.YAZHEN_SOLUTION_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_YAZHEN_SOLUTION, ModFluids.FLOWING_YAZHEN_SOLUTION);
        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_YAZHEN_SOLUTION, ModFluids.FLOWING_YAZHEN_SOLUTION,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0xFFC0FF99
                )
        );
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JINCAO_SOLUTION_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_JINCAO_SOLUTION, ModFluids.FLOWING_JINCAO_SOLUTION);
        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_JINCAO_SOLUTION, ModFluids.FLOWING_JINCAO_SOLUTION,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0xFFD7FCD5
                )
        );
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIQUID_XIRANITE_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), ModFluids.STILL_LIQUID_XIRANITE, ModFluids.FLOWING_LIQUID_XIRANITE);
        FluidRenderHandlerRegistry.INSTANCE.register(
                ModFluids.STILL_LIQUID_XIRANITE, ModFluids.FLOWING_LIQUID_XIRANITE,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0xFF376C0E
                )
        );

        DepotBusSectionPreviewRenderer.register();
        DepotLoaderPreviewRenderer.register();
        DepotUnloaderPreviewRenderer.register();
        ProtocolStashPreviewRenderer.register();
        
        ElectricPylonPreviewRenderer.register();
        ProtocolAnchorCorePreviewRenderer.register();
        RelayTowerPreviewRenderer.register();
        ThermalBankPreviewRenderer.register();
        
        BeamTowerPreviewRenderer.register();
        GrenadeTowerPreviewRenderer.register();
        GunTowerPreviewRenderer.register();
        HeavyGunTowerPreviewRenderer.register();
        HeGrenadeTowerPreviewRenderer.register();
        LNTowerPreviewRenderer.register();
        MarshGasMkIPreviewRenderer.register();
        MedicalTowerPreviewRenderer.register();
        OmnidirectionalSonicTowerPreviewRenderer.register();
        SentryTowerPreviewRenderer.register();
        SurgeTowerPreviewRenderer.register();

        FittingUnitPreviewRenderer.register();
        MouldingUnitPreviewRenderer.register();
        PlantingUnitPreviewRenderer.register();
        RefiningUnitPreviewRenderer.register();
        SeedPickingUnitPreviewRenderer.register();
        ShreddingUnitPreviewRenderer.register();

        FillingUnitPreviewRenderer.register();
        GearingUnitPreviewRenderer.register();
        GrindingUnitPreviewRenderer.register();
        PackagingUnitPreviewRenderer.register();

        ElectricMiningRigPreviewRenderer.register();
        ElectricMiningRigMkIIPreviewRenderer.register();
        PortableOriginiumRigPreviewRenderer.register();

        ClientPlayNetworking.registerGlobalReceiver(
                ModNetWorking.SYNC_STORAGE,
                (client, handler, buf, sender) -> {
                    int size = buf.readInt();
                    List<StorageEntry> newEntries = new ArrayList<>();

                    for (int i = 0; i < size; i++) {
                        Item item = Registries.ITEM.get(buf.readIdentifier());
                        NbtCompound tag = buf.readNbt();
                        long count = buf.readLong();
                        newEntries.add(new StorageEntry(item, tag, count, 0));
                    }

                    client.execute(() -> {
                        if (client.player != null && client.player.currentScreenHandler instanceof StorageScreenHandler screenHandler) {
                            screenHandler.updateEntries(newEntries);
                        }
                    });
                }
        );

        ModKeyBindings.register();
        KeyInputHandler.register();
    }
}
