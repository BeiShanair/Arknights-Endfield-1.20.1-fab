package com.besson.endfield.datagen;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModModelsProvider extends FabricModelProvider {
    public ModModelsProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.PROTOCOL_ANCHOR_CORE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.RELAY_TOWER);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.ELECTRIC_PYLON);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.XIRANITE_PYLON);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.XIRANITE_RELAY);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.ELECTRIC_MINING_RIG);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.ELECTRIC_MINING_RIG_MK_II);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.PORTABLE_ORIGINIUM_RIG);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.HYDRO_MINING_RIG);
        
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
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.PROTOCOL_ANCHOR_CORE_PORT);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.THERMAL_BANK);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.SUPPLY_TERMINAL);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.SEPARATING_UNIT);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.FORGE_OF_THE_SKY);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.ACID_RESISTANT_PUMP_MK_II);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.WATER_TREATMENT_UNIT);
        
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.REFINING_UNIT_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.SHREDDING_UNIT_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.FILLING_UNIT_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.FITTING_UNIT_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.GEARING_UNIT_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.GRINDING_UNIT_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.MOULDING_UNIT_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.PACKAGING_UNIT_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.REACTOR_CRUCIBLE_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.EXPANDED_CRUCIBLE_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.PLANTING_UNIT_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.SEED_PICKING_UNIT_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.THERMAL_BANK_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.PROTOCOL_STASH_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.DEPOT_BUS_SECTION_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.DEPOT_LOADER_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.DEPOT_UNLOADER_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.HYDRO_MINING_RIG_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.FLUID_TANK_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.SEPARATING_UNIT_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.FORGE_OF_THE_SKY_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.ACID_RESISTANT_PUMP_MK_II_SIDE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.WATER_TREATMENT_UNIT_SIDE);

        blockStateModelGenerator.registerSimpleState(ModBlocks.PROTOCOL_ANCHOR_CORE_SIDE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.GUN_TOWER);
        blockStateModelGenerator.registerSimpleState(ModBlocks.HEAVY_GUN_TOWER);
        blockStateModelGenerator.registerSimpleState(ModBlocks.GRENADE_TOWER);
        blockStateModelGenerator.registerSimpleState(ModBlocks.HE_GRENADE_TOWER);
        blockStateModelGenerator.registerSimpleState(ModBlocks.SENTRY_TOWER);
        blockStateModelGenerator.registerSimpleState(ModBlocks.MEDICAL_TOWER);
        blockStateModelGenerator.registerSimpleState(ModBlocks.BEAM_TOWER);
        blockStateModelGenerator.registerSimpleState(ModBlocks.LN_TOWER);
        blockStateModelGenerator.registerSimpleState(ModBlocks.MARSH_GAS_MK_I);
        blockStateModelGenerator.registerSimpleState(ModBlocks.OMNIDIRECTIONAL_SONIC_TOWER);
        blockStateModelGenerator.registerSimpleState(ModBlocks.SURGE_TOWER);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.FLUID_PUMP);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.FLUID_TANK);

        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.BELT_BRIDGE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.CONVERGER);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.SPLITTER);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.PIPE_BRIDGE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.PIPE_CONVERGER);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.PIPE_SPLITTER);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.OUTPUT_PORT_BLOCK);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.PROTOCOL_STASH);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.DEPOT_BUS_SECTION);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.DEPOT_LOADER);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.DEPOT_UNLOADER);
        
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.REACTOR_CRUCIBLE);
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(ModBlocks.EXPANDED_CRUCIBLE);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.AMETHYST_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CUPRIUM_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_ORIGINIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_FERRIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_AMETHYST_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_CUPRIUM_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORIGINIUM_ORE_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FERRIUM_ORE_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COAL_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.COPPER_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CUPRIUM_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DIAMOND_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EMERALD_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FERRIUM_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.GOLD_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.IRON_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.LAPIS_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.REDSTONE_MINERAL_VEIN_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.CRAFTER);

        blockStateModelGenerator.registerSimpleState(ModBlocks.SEWAGE_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.PRECIPITATION_ACID_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.XIRCON_EFFLUENT_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.INERT_XIRCON_EFFLUENT_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.YAZHEN_SOLUTION_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.JINCAO_SOLUTION_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.LIQUID_XIRANITE_BLOCK);
        
        blockStateModelGenerator.registerSimpleState(ModBlocks.AMETHYST_DRUSE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.FERRIUM_DRUSE);
        blockStateModelGenerator.registerSimpleState(ModBlocks.ORIGINIUM_DRUSE);
    }
    
    private void registerSideBlock(BlockStateModelGenerator blockStateModelGenerator, Block block) {
        blockStateModelGenerator.registerNorthDefaultHorizontalRotation(block);
        Identifier blockId = Registries.BLOCK.getId(block);
        blockStateModelGenerator.registerParentedItemModel(block, new Identifier(blockId.getNamespace(), "block/" + blockId.getPath()));
    }
    
    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.AGGAGRIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.AGGAGRIT_BLOCK, Models.GENERATED);
        itemModelGenerator.register(ModItems.AGGAGRIT_CLUSTER, Models.GENERATED);
        itemModelGenerator.register(ModItems.AKETINE, Models.GENERATED);
        itemModelGenerator.register(ModItems.AKETINE_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.AKETINE_SEED, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMBER_RICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMBER_RICE_SEED, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_COMPONENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_FIBER, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_ORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_PART, Models.GENERATED);
        itemModelGenerator.register(ModItems.AMETHYST_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.ARTS_TUBE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ARTS_VIAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.ASHPIN_REMEDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.BIZARROTACK, Models.GENERATED);
        itemModelGenerator.register(ModItems.BIZARRO_CHILI, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLANCHED_REMEDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.BUCKFLOWER, Models.GENERATED);
        itemModelGenerator.register(ModItems.BUCKFLOWER_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.BUCKFLOWER_SEED, Models.GENERATED);
        itemModelGenerator.register(ModItems.BUCKPILL_L, Models.GENERATED);
        itemModelGenerator.register(ModItems.BUCKPILL_RF, Models.GENERATED);
        itemModelGenerator.register(ModItems.BUCKPILL_S, Models.GENERATED);
        itemModelGenerator.register(ModItems.BUCK_CAPSULE_A, Models.GENERATED);
        itemModelGenerator.register(ModItems.BUCK_CAPSULE_B, Models.GENERATED);
        itemModelGenerator.register(ModItems.BUCK_CAPSULE_C, Models.GENERATED);
        itemModelGenerator.register(ModItems.BUGTACK, Models.GENERATED);
        itemModelGenerator.register(ModItems.CANNED_CITROME_A, Models.GENERATED);
        itemModelGenerator.register(ModItems.CANNED_CITROME_B, Models.GENERATED);
        itemModelGenerator.register(ModItems.CANNED_CITROME_C, Models.GENERATED);
        itemModelGenerator.register(ModItems.CARBON, Models.GENERATED);
        itemModelGenerator.register(ModItems.CARBON_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CARTILAGE_BIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CARTILAGE_TACK, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHITIN_BIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CITROBUCKY_MIX, Models.GENERATED);
        itemModelGenerator.register(ModItems.CITROME, Models.GENERATED);
        itemModelGenerator.register(ModItems.CITROME_JAM, Models.GENERATED);
        itemModelGenerator.register(ModItems.CITROME_JELLY, Models.GENERATED);
        itemModelGenerator.register(ModItems.CITROME_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CITROME_PUDDING, Models.GENERATED);
        itemModelGenerator.register(ModItems.CITROME_SEED, Models.GENERATED);
        itemModelGenerator.register(ModItems.CITROMIX, Models.GENERATED);
        itemModelGenerator.register(ModItems.CITROMIX_L, Models.GENERATED);
        itemModelGenerator.register(ModItems.CITROMIX_RF, Models.GENERATED);
        itemModelGenerator.register(ModItems.CITROMIX_S, Models.GENERATED);
        itemModelGenerator.register(ModItems.COARSE_FLATBREAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORRECTIVE_REMEDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRYSTON_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRYSTON_COMPONENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRYSTON_FIBER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRYSTON_PART, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRYSTON_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.CUPRIUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.CUPRIUM_COMPONENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CUPRIUM_JAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.CUPRIUM_ORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CUPRIUM_PART, Models.GENERATED);
        itemModelGenerator.register(ModItems.CUPRIUM_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.DENSE_CARBON_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.DENSE_FERRIUM_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.DENSE_ORIGINIUM_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.DENSE_ORIGOCRUST_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.FERRIUM, Models.GENERATED);
        itemModelGenerator.register(ModItems.FERRIUM_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FERRIUM_COMPONENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.FERRIUM_ORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FERRIUM_PART, Models.GENERATED);
        itemModelGenerator.register(ModItems.FERRIUM_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.FILLET, Models.GENERATED);
        itemModelGenerator.register(ModItems.FILLET_CONFIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIREBUCKLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIREBUCKLE_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIRESTOVE_RICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.FIRETACK, Models.GENERATED);
        itemModelGenerator.register(ModItems.FLUFFED_JINCAO, Models.GENERATED);
        itemModelGenerator.register(ModItems.FLUFFED_JINCAO_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.FORTIFYING_INFUSION, Models.GENERATED);
        itemModelGenerator.register(ModItems.GARDEN_FRIED_RICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.GARDEN_STIR_FRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.GINSENG_MEAT_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOWBUG, Models.GENERATED);
        itemModelGenerator.register(ModItems.GRASS_CHAFF, Models.GENERATED);
        itemModelGenerator.register(ModItems.GROUND_BUCKFLOWER_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.GROUND_CITROME_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.HC_BATTERY, Models.GENERATED);
        itemModelGenerator.register(ModItems.HOLLOW_BONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HOLLOW_BONECHIP, Models.GENERATED);
        itemModelGenerator.register(ModItems.HOT_CRUNCHY_RIBS, Models.GENERATED);
        itemModelGenerator.register(ModItems.INDUSTRIAL_EXPLOSIVE, Models.GENERATED);
        itemModelGenerator.register(ModItems.JINCAO, Models.GENERATED);
        itemModelGenerator.register(ModItems.JINCAO_DRINK, Models.GENERATED);
        itemModelGenerator.register(ModItems.JINCAO_INFUSION, Models.GENERATED);
        itemModelGenerator.register(ModItems.JINCAO_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.JINCAO_SEED, Models.GENERATED);
        itemModelGenerator.register(ModItems.JINCAO_TEA, Models.GENERATED);
        itemModelGenerator.register(ModItems.JINCAO_TISANE, Models.GENERATED);
        itemModelGenerator.register(ModItems.KUNST_TUBE, Models.GENERATED);
        itemModelGenerator.register(ModItems.KUNST_VIAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.LC_BATTERY, Models.GENERATED);
        itemModelGenerator.register(ModItems.MEAT_STIR_FRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.MOSSFIELD_PIE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORIGINIUM_ORE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORIGINIUM_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORIGOCRUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.ORIGOCRUST_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.PACKED_ORIGOCRUST, Models.GENERATED);
        itemModelGenerator.register(ModItems.PERPLEXING_MEDICATION, Models.GENERATED);
        itemModelGenerator.register(ModItems.PRESERVE_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.REDJADE_GINSENG, Models.GENERATED);
        itemModelGenerator.register(ModItems.REDJADE_GINSENG_SEED, Models.GENERATED);
        itemModelGenerator.register(ModItems.REED_RYE, Models.GENERATED);
        itemModelGenerator.register(ModItems.REED_RYE_SEED, Models.GENERATED);
        itemModelGenerator.register(ModItems.SANDLEAF, Models.GENERATED);
        itemModelGenerator.register(ModItems.SANDLEAF_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.SANDLEAF_SEED, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAVORY_FILLET, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAVORY_TANGBAO, Models.GENERATED);
        itemModelGenerator.register(ModItems.SAVORY_TANGMIAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SCORCHBUG, Models.GENERATED);
        itemModelGenerator.register(ModItems.SC_BATTERY, Models.GENERATED);
        itemModelGenerator.register(ModItems.SENSORY_REMEDY, Models.GENERATED);
        itemModelGenerator.register(ModItems.SMOKED_RICEBALL, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOAKED_WOOD, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOAKED_WOODCHIP, Models.GENERATED);
        itemModelGenerator.register(ModItems.STABILIZED_CARBON, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL_JAR, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL_PART, Models.GENERATED);
        itemModelGenerator.register(ModItems.TARTPEPPER, Models.GENERATED);
        itemModelGenerator.register(ModItems.TARTPEPPER_PICKLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.TARTPEPPER_SALAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.TARTPEPPER_SEED, Models.GENERATED);
        itemModelGenerator.register(ModItems.THORNY_YAZHEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.THORNY_YAZHEN_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.UMBRALINE, Models.GENERATED);
        itemModelGenerator.register(ModItems.VALLEY_GRAYBREAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.WOOD, Models.GENERATED);
        itemModelGenerator.register(ModItems.WULING_FRIED_RICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.YAZHEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.YAZHEN_POWDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.YAZHEN_SEED, Models.GENERATED);
        itemModelGenerator.register(ModItems.YAZHEN_SPRAY_L, Models.GENERATED);
        itemModelGenerator.register(ModItems.YAZHEN_SPRAY_S, Models.GENERATED);
        itemModelGenerator.register(ModItems.YAZHEN_SYRINGE_A, Models.GENERATED);
        itemModelGenerator.register(ModItems.YAZHEN_SYRINGE_C, Models.GENERATED);
        itemModelGenerator.register(ModItems.SEWAGE_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.PRECIPITATION_ACID_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.XIRCON_EFFLUENT_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.INERT_XIRCON_EFFLUENT_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.YAZHEN_SOLUTION_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.JINCAO_SOLUTION_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.LIQUID_XIRANITE_BUCKET, Models.GENERATED);
        
        itemModelGenerator.register(ModItems.XIRANITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HEAVY_XIRANITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HETONITE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HETONITE_BOTTLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HETONITE_PART, Models.GENERATED);
        itemModelGenerator.register(ModItems.HETONITE_COMPONENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.LC_WULING_BATTERY, Models.GENERATED);
        itemModelGenerator.register(ModItems.SC_WULING_BATTERY, Models.GENERATED);
        itemModelGenerator.register(ModItems.XIRANITE_COMPONENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.XIRCON, Models.GENERATED);
        itemModelGenerator.register(ModItems.CUPRIUM_BOTTLE, Models.GENERATED);

        itemModelGenerator.register(ModItems.ORIGINIUM_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CONTROL_ITEM, Models.HANDHELD);

        for (Item bottled : ModItems.ALL_BOTTLED_FLUIDS) {
            itemModelGenerator.register(bottled, Models.GENERATED);
        }
    }
}
