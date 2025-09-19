package com.besson.endfield.datagen;

import com.besson.endfield.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class ModLootTablesProvider extends FabricBlockLootTableProvider {
    public ModLootTablesProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.PROTOCOL_ANCHOR_CORE);
        addDrop(ModBlocks.RELAY_TOWER);
        addDrop(ModBlocks.ELECTRIC_PYLON);
        addDrop(ModBlocks.ELECTRIC_MINING_RIG);
        addDrop(ModBlocks.ELECTRIC_MINING_RIG_MK_II);
        addDrop(ModBlocks.PORTABLE_ORIGINIUM_RIG);
        addDrop(ModBlocks.REFINING_UNIT);
        addDrop(ModBlocks.SHREDDING_UNIT);
        addDrop(ModBlocks.FILLING_UNIT);
        addDrop(ModBlocks.FITTING_UNIT);
        addDrop(ModBlocks.GEARING_UNIT);
        addDrop(ModBlocks.GRINDING_UNIT);
        addDrop(ModBlocks.MOULDING_UNIT);
        addDrop(ModBlocks.PACKAGING_UNIT);
        addDrop(ModBlocks.PLANTING_UNIT);
        addDrop(ModBlocks.SEED_PICKING_UNIT);
        addDrop(ModBlocks.THERMAL_BANK);

        addDrop(ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK);
        addDrop(ModBlocks.COAL_MINERAL_VEIN_BLOCK);
        addDrop(ModBlocks.COPPER_MINERAL_VEIN_BLOCK);
        addDrop(ModBlocks.DIAMOND_MINERAL_VEIN_BLOCK);
        addDrop(ModBlocks.EMERALD_MINERAL_VEIN_BLOCK);
        addDrop(ModBlocks.GOLD_MINERAL_VEIN_BLOCK);
        addDrop(ModBlocks.IRON_MINERAL_VEIN_BLOCK);
        addDrop(ModBlocks.LAPIS_MINERAL_VEIN_BLOCK);
        addDrop(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK);
        addDrop(ModBlocks.REDSTONE_MINERAL_VEIN_BLOCK);
        addDrop(ModBlocks.AMETHYST_ORE_BLOCK);
        addDrop(ModBlocks.CUPRIUM_MINERAL_VEIN_BLOCK);
        addDrop(ModBlocks.CUPRIUM_ORE_BLOCK);
        addDrop(ModBlocks.DEEPSLATE_AMETHYST_ORE);
        addDrop(ModBlocks.DEEPSLATE_CUPRIUM_ORE);
        addDrop(ModBlocks.DEEPSLATE_FERRIUM_ORE);
        addDrop(ModBlocks.DEEPSLATE_ORIGINIUM_ORE);
        addDrop(ModBlocks.FERRIUM_MINERAL_VEIN_BLOCK);
        addDrop(ModBlocks.FERRIUM_ORE_BLOCK);
        addDrop(ModBlocks.ORIGINIUM_ORE_BLOCK);
    }
}
