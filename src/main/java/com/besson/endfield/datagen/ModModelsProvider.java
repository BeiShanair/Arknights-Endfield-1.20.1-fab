package com.besson.endfield.datagen;

import com.besson.endfield.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.PROTOCOL_ANCHOR_CORE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.RELAY_TOWER);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.ELECTRIC_PYLON);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.ELECTRIC_MINING_RIG);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.ELECTRIC_MINING_RIG_MK_II);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.PORTABLE_ORIGINIUM_RIG);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.REFINING_UNIT);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.SHREDDING_UNIT);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.FILLING_UNIT);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.FITTING_UNIT);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.GEARING_UNIT);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.GRINDING_UNIT);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.MOULDING_UNIT);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.PACKAGING_UNIT);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.PLANTING_UNIT);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.SEED_PICKING_UNIT);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.THERMAL_BANK);

        blockStateModelGenerator.registerSimpleState(ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.COAL_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.COPPER_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.DIAMOND_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.EMERALD_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.GOLD_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.IRON_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.LAPIS_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.REDSTONE_MINERAL_VEIN_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
