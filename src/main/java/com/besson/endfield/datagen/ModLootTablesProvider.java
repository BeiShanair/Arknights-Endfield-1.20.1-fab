package com.besson.endfield.datagen;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.item.ModItems;
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
        addDrop(ModBlocks.PROTOCOL_ANCHOR_CORE_PORT);
        
        addDrop(ModBlocks.PROTOCOL_STASH);
        addDrop(ModBlocks.DEPOT_BUS_SECTION);
        addDrop(ModBlocks.DEPOT_LOADER);
        addDrop(ModBlocks.DEPOT_UNLOADER);

        addDrop(ModBlocks.FLUID_PUMP);
        addDrop(ModBlocks.SUPPLY_TERMINAL);

        addDrop(ModBlocks.BELT);
        addDrop(ModBlocks.BELT_BRIDGE);
        addDrop(ModBlocks.CONVERGER);
        addDrop(ModBlocks.SPLITTER);
        addDrop(ModBlocks.OUTPUT_PORT_BLOCK);

        addDrop(ModBlocks.GRENADE_TOWER);
        addDrop(ModBlocks.HE_GRENADE_TOWER);
        addDrop(ModBlocks.HEAVY_GUN_TOWER);
        addDrop(ModBlocks.SENTRY_TOWER);
        addDrop(ModBlocks.GUN_TOWER);
        addDrop(ModBlocks.MEDICAL_TOWER);
        addDrop(ModBlocks.BEAM_TOWER);
        addDrop(ModBlocks.LN_TOWER);
        addDrop(ModBlocks.MARSH_GAS_MK_I);
        addDrop(ModBlocks.OMNIDIRECTIONAL_SONIC_TOWER);
        addDrop(ModBlocks.SURGE_TOWER);

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
        addDrop(ModBlocks.AMETHYST_ORE_BLOCK, oreDrops(ModBlocks.AMETHYST_ORE_BLOCK, ModItems.AMETHYST_ORE));
        addDrop(ModBlocks.CUPRIUM_MINERAL_VEIN_BLOCK);
        addDrop(ModBlocks.CUPRIUM_ORE_BLOCK, oreDrops(ModBlocks.CUPRIUM_ORE_BLOCK, ModItems.CUPRIUM_ORE));
        addDrop(ModBlocks.DEEPSLATE_AMETHYST_ORE, oreDrops(ModBlocks.DEEPSLATE_AMETHYST_ORE, ModItems.AMETHYST_ORE));
        addDrop(ModBlocks.DEEPSLATE_CUPRIUM_ORE, oreDrops(ModBlocks.DEEPSLATE_CUPRIUM_ORE, ModItems.CUPRIUM_ORE));
        addDrop(ModBlocks.DEEPSLATE_FERRIUM_ORE, oreDrops(ModBlocks.DEEPSLATE_FERRIUM_ORE, ModItems.FERRIUM_ORE));
        addDrop(ModBlocks.DEEPSLATE_ORIGINIUM_ORE, oreDrops(ModBlocks.DEEPSLATE_ORIGINIUM_ORE, ModItems.ORIGINIUM_ORE));
        addDrop(ModBlocks.FERRIUM_MINERAL_VEIN_BLOCK);
        addDrop(ModBlocks.FERRIUM_ORE_BLOCK, oreDrops(ModBlocks.FERRIUM_ORE_BLOCK, ModItems.FERRIUM_ORE));
        addDrop(ModBlocks.ORIGINIUM_ORE_BLOCK, oreDrops(ModBlocks.ORIGINIUM_ORE_BLOCK, ModItems.ORIGINIUM_ORE));

        addDrop(ModBlocks.AKETINE_BLOCK);
        addDrop(ModBlocks.AMBER_RICE_BLOCK);
        addDrop(ModBlocks.BUCKFLOWER_BLOCK);
        addDrop(ModBlocks.CITROME_BLOCK);
        addDrop(ModBlocks.FIREBUCKLE_BLOCK);
        addDrop(ModBlocks.FLUFFED_JINCAO_BLOCK);
        addDrop(ModBlocks.JINCAO_BLOCK);
        addDrop(ModBlocks.REDJADE_GINSENG_BLOCK);
        addDrop(ModBlocks.REED_RYE_BLOCK);
        addDrop(ModBlocks.SANDLEAF_BLOCK);
        addDrop(ModBlocks.TARTPEPPER_BLOCK);
        addDrop(ModBlocks.THORNY_YAZHEN_BLOCK);
        addDrop(ModBlocks.UMBRALINE_BLOCK);
        addDrop(ModBlocks.YAZHEN_BLOCK);

        addDrop(ModBlocks.CRAFTER);
    }
}
