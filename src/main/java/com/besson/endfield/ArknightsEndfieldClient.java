package com.besson.endfield;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.entity.ModItemEntity;
import com.besson.endfield.network.ModNetWorking;
import com.besson.endfield.renderer.block.*;
import com.besson.endfield.renderer.item.IndustrialExplosiveEntityRenderer;
import com.besson.endfield.screen.ModScreens;
import com.besson.endfield.screen.custom.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ArknightsEndfieldClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModItemEntity.INDUSTRIAL_EXPLOSIVE, FlyingItemEntityRenderer::new);

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
        HandledScreens.register(ModScreens.CRAFTER_SCREEN, CrafterScreen::new);

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
    }
}
