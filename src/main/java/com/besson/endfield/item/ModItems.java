package com.besson.endfield.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.item.custom.*;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item PROTOCOL_ANCHOR_CORE_ITEM = registerItems("protocol_anchor_core",
            new ProtocolAnchorCoreItem(ModBlocks.PROTOCOL_ANCHOR_CORE, new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item RELAY_TOWER_ITEM = registerItems("relay_tower",
            new RelayTowerItem(ModBlocks.RELAY_TOWER, new Item.Settings()));
    public static final Item ELECTRIC_PYLON_ITEM = registerItems("electric_pylon",
            new ElectricPylonItem(ModBlocks.ELECTRIC_PYLON, new Item.Settings()));
    public static final Item ELECTRIC_MINING_RIG_ITEM = registerItems("electric_mining_rig",
            new ElectricMiningRigItem(ModBlocks.ELECTRIC_MINING_RIG, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item ELECTRIC_MINING_RIG_MK_II_ITEM = registerItems("electric_mining_rig_mk_ii",
            new ElectricMiningRigMkIIItem(ModBlocks.ELECTRIC_MINING_RIG_MK_II, new Item.Settings().rarity(Rarity.RARE)));
    public static final Item PORTABLE_ORIGINIUM_RIG_ITEM = registerItems("portable_originium_rig",
            new PortableOriginiumRigItem(ModBlocks.PORTABLE_ORIGINIUM_RIG, new Item.Settings()));

    public static final Item REFINING_UNIT_ITEM = registerSameBlockItem("refining_unit",
            new RefiningUnitItem(ModBlocks.REFINING_UNIT, new Item.Settings()), ModBlocks.REFINING_UNIT_SIDE);
    public static final Item SHREDDING_UNIT_ITEM = registerSameBlockItem("shredding_unit",
            new ShreddingUnitItem(ModBlocks.SHREDDING_UNIT, new Item.Settings()), ModBlocks.SHREDDING_UNIT_SIDE);
    public static final Item FILLING_UNIT_ITEM = registerSameBlockItem("filling_unit",
            new FillingUnitItem(ModBlocks.FILLING_UNIT, new Item.Settings().rarity(Rarity.RARE)), ModBlocks.FILLING_UNIT_SIDE);
    public static final Item FITTING_UNIT_ITEM = registerSameBlockItem("fitting_unit",
            new FittingUnitItem(ModBlocks.FITTING_UNIT, new Item.Settings().rarity(Rarity.UNCOMMON)), ModBlocks.FITTING_UNIT_SIDE);
    public static final Item GEARING_UNIT_ITEM = registerSameBlockItem("gearing_unit",
            new GearingUnitItem(ModBlocks.GEARING_UNIT, new Item.Settings().rarity(Rarity.RARE)), ModBlocks.GEARING_UNIT_SIDE);
    public static final Item GRINDING_UNIT_ITEM = registerSameBlockItem("grinding_unit",
            new GrindingUnitItem(ModBlocks.GRINDING_UNIT, new Item.Settings().rarity(Rarity.EPIC)), ModBlocks.GRINDING_UNIT_SIDE);
    public static final Item MOULDING_UNIT_ITEM = registerSameBlockItem("moulding_unit",
            new MouldingUnitItem(ModBlocks.MOULDING_UNIT, new Item.Settings().rarity(Rarity.UNCOMMON)), ModBlocks.MOULDING_UNIT_SIDE);
    public static final Item PACKAGING_UNIT_ITEM = registerSameBlockItem("packaging_unit",
            new PackagingUnitItem(ModBlocks.PACKAGING_UNIT, new Item.Settings().rarity(Rarity.RARE)), ModBlocks.PACKAGING_UNIT_SIDE);
    public static final Item PLANTING_UNIT_ITEM = registerSameBlockItem("planting_unit",
            new PlantingUnitItem(ModBlocks.PLANTING_UNIT, new Item.Settings().rarity(Rarity.RARE)), ModBlocks.PLANTING_UNIT_SIDE);
    public static final Item SEED_PICKING_UNIT_ITEM = registerSameBlockItem("seed_picking_unit",
            new SeedPickingUnitItem(ModBlocks.SEED_PICKING_UNIT, new Item.Settings().rarity(Rarity.RARE)), ModBlocks.SEED_PICKING_UNIT_SIDE);
    public static final Item THERMAL_BANK_ITEM = registerSameBlockItem("thermal_bank",
            new ThermalBankItem(ModBlocks.THERMAL_BANK, new Item.Settings().rarity(Rarity.UNCOMMON)), ModBlocks.THERMAL_BANK_SIDE);

    public static final Item AGGAGRIT = registerItems("aggagrit",new Item(new Item.Settings()));
    public static final Item AGGAGRIT_BLOCK = registerItems("aggagrit_block",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item AGGAGRIT_CLUSTER = registerItems("aggagrit_cluster",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item AKETINE = registerItems("aketine",new BlockItem(ModBlocks.AKETINE_BLOCK, new Item.Settings()));
    public static final Item AKETINE_POWDER = registerItems("aketine_powder",new Item(new Item.Settings()));
    public static final Item AKETINE_SEED = registerItems("aketine_seed",new Item(new Item.Settings()));
    public static final Item AMBER_RICE = registerItems("amber_rice",new BlockItem(ModBlocks.AMBER_RICE_BLOCK, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item AMBER_RICE_SEED = registerItems("amber_rice_seed",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item AMETHYST_BOTTLE = registerItems("amethyst_bottle",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item AMETHYST_COMPONENT = registerItems("amethyst_component",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item AMETHYST_FIBER = registerItems("amethyst_fiber",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item AMETHYST_ORE = registerItems("amethyst_ore",new Item(new Item.Settings()));
    public static final Item AMETHYST_PART = registerItems("amethyst_part",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item AMETHYST_POWDER = registerItems("amethyst_powder",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item ARTS_TUBE = registerItems("arts_tube",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.ART_TUBE)));
    public static final Item ARTS_VIAL = registerItems("arts_vial",new Item(new Item.Settings().rarity(Rarity.RARE).food(ModFoodComponents.ART_VIAL)));
    public static final Item ASHPIN_REMEDY = registerItems("ashpin_remedy",new Item(new Item.Settings().rarity(Rarity.RARE).food(ModFoodComponents.ASHPIN_REMEDY)));
    public static final Item BIZARROTACK = registerItems("bizarrotack",new Item(new Item.Settings().rarity(Rarity.UNCOMMON).food(ModFoodComponents.BIZARROTACK)));
    public static final Item BIZARRO_CHILI = registerItems("bizarro_chili",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.BIZARRO_CHILI)));
    public static final Item BLANCHED_REMEDY = registerItems("blanched_remedy",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.BLANCHED_REMEDY)));
    public static final Item BUCKFLOWER = registerItems("buckflower",new BlockItem(ModBlocks.BUCKFLOWER_BLOCK, new Item.Settings()));
    public static final Item BUCKFLOWER_POWDER = registerItems("buckflower_powder",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item BUCKFLOWER_SEED = registerItems("buckflower_seed",new Item(new Item.Settings()));
    public static final Item BUCKPILL_L = registerItems("buckpill_l",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.BUCKPILL_L)));
    public static final Item BUCKPILL_RF = registerItems("buckpill_rf",new ModEpicItem(new Item.Settings().food(ModFoodComponents.BUCKPILL_RF)));
    public static final Item BUCKPILL_S = registerItems("buckpill_s",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.BUCKPILL_S)));
    public static final Item BUCK_CAPSULE_A = registerItems("buck_capsule_a",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.BUCK_CAPSULE_A)));
    public static final Item BUCK_CAPSULE_B = registerItems("buck_capsule_b",new Item(new Item.Settings().rarity(Rarity.RARE).food(ModFoodComponents.BUCK_CAPSULE_B)));
    public static final Item BUCK_CAPSULE_C = registerItems("buck_capsule_c",new Item(new Item.Settings().rarity(Rarity.RARE).food(ModFoodComponents.BUCK_CAPSULE_C)));
    public static final Item BUGTACK = registerItems("bugtack",new Item(new Item.Settings().rarity(Rarity.RARE).food(ModFoodComponents.BUGTACK)));
    public static final Item CANNED_CITROME_A = registerItems("canned_citrome_a",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.CANNED_CITROME_A)));
    public static final Item CANNED_CITROME_B = registerItems("canned_citrome_b",new Item(new Item.Settings().rarity(Rarity.RARE).food(ModFoodComponents.CANNED_CITROME_B)));
    public static final Item CANNED_CITROME_C = registerItems("canned_citrome_c",new Item(new Item.Settings().rarity(Rarity.RARE).food(ModFoodComponents.CANNED_CITROME_C)));
    public static final Item CARBON = registerItems("carbon",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item CARBON_POWDER = registerItems("carbon_powder",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item CARTILAGE_BIT = registerItems("cartilage_bit",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CARTILAGE_TACK = registerItems("cartilage_tack",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.CARTILAGE_TACK)));
    public static final Item CHITIN_BIT = registerItems("chitin_bit",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item CITROBUCKY_MIX = registerItems("citrobucky_mix",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.CITROBUCKY_MIX)));
    public static final Item CITROME = registerItems("citrome",new BlockItem(ModBlocks.CITROME_BLOCK, new Item.Settings()));
    public static final Item CITROME_JAM = registerItems("citrome_jam",new Item(new Item.Settings().rarity(Rarity.UNCOMMON).food(ModFoodComponents.CITROME_JAM)));
    public static final Item CITROME_JELLY = registerItems("citrome_jelly",new Item(new Item.Settings().food(ModFoodComponents.CITROME_JELLY)));
    public static final Item CITROME_POWDER = registerItems("citrome_powder",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item CITROME_PUDDING = registerItems("citrome_pudding",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.CITROME_PUDDING)));
    public static final Item CITROME_SEED = registerItems("citrome_seed",new Item(new Item.Settings()));
    public static final Item CITROMIX = registerItems("citromix",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CITROMIX_L = registerItems("citromix_l",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.CITROMIX_L)));
    public static final Item CITROMIX_RF = registerItems("citromix_rf",new ModEpicItem(new Item.Settings().food(ModFoodComponents.CITROMIX_RF)));
    public static final Item CITROMIX_S = registerItems("citromix_ss",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.CITROMIX_S)));
    public static final Item COARSE_FLATBREAD = registerItems("coarse_flatbread",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.COARSE_FLATBREAD)));
    public static final Item CORRECTIVE_REMEDY = registerItems("corrective_remedy",new ModEpicItem(new Item.Settings().food(ModFoodComponents.CORRECTIVE_REMEDY)));
    public static final Item CRYSTON_BOTTLE = registerItems("cryston_bottle",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CRYSTON_COMPONENT = registerItems("cryston_component",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CRYSTON_FIBER = registerItems("cryston_fiber",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CRYSTON_PART = registerItems("cryston_part",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CRYSTON_POWDER = registerItems("cryston_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CUPRIUM = registerItems("cuprium",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item CUPRIUM_COMPONENT = registerItems("cuprium_component",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CUPRIUM_JAR = registerItems("cuprium_jar",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CUPRIUM_ORE = registerItems("cuprium_ore",new Item(new Item.Settings()));
    public static final Item CUPRIUM_PART = registerItems("cuprium_part",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CUPRIUM_POWDER = registerItems("cuprium_powder",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item DENSE_CARBON_POWDER = registerItems("dense_carbon_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item DENSE_FERRIUM_POWDER = registerItems("dense_ferrium_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item DENSE_ORIGINIUM_POWDER = registerItems("dense_originium_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item DENSE_ORIGOCRUST_POWDER = registerItems("dense_origocrust_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item FERRIUM = registerItems("ferrium",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item FERRIUM_BOTTLE = registerItems("ferrium_bottle",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item FERRIUM_COMPONENT = registerItems("ferrium_component",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item FERRIUM_ORE = registerItems("ferrium_ore",new Item(new Item.Settings()));
    public static final Item FERRIUM_PART = registerItems("ferrium_part",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item FERRIUM_POWDER = registerItems("ferrium_powder",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item FILLET = registerItems("fillet",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item FILLET_CONFIT = registerItems("fillet_confit",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.FILLET_CONFIT)));
    public static final Item FIREBUCKLE = registerItems("firebuckle",new BlockItem(ModBlocks.FIREBUCKLE_BLOCK, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item FIREBUCKLE_POWDER = registerItems("firebuckle_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item FIRESTOVE_RICE = registerItems("firestove_rice",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.FIRESTOVE_RICE)));
    public static final Item FIRETACK = registerItems("firetack",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.FIRETACK)));
    public static final Item FLUFFED_JINCAO = registerItems("fluffed_jincao",new BlockItem(ModBlocks.FLUFFED_JINCAO_BLOCK, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item FLUFFED_JINCAO_POWDER = registerItems("fluffed_jincao_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item FORTIFYING_INFUSION = registerItems("fortifying_infusion",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.FORTIFYING_INFUSION)));
    public static final Item GARDEN_FRIED_RICE = registerItems("garden_fried_rice",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.GARDEN_FRIED_RICE)));
    public static final Item GARDEN_STIR_FRY = registerItems("garden_stir_fry",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.GARDEN_STIR_FRY)));
    public static final Item GINSENG_MEAT_STEW = registerItems("ginseng_meat_stew",new ModEpicItem(new Item.Settings().food(ModFoodComponents.GINSENG_MEAT_STEW)));
    public static final Item GLOWBUG = registerItems("glowbug",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item GRASS_CHAFF = registerItems("grass_chaff",new Item(new Item.Settings()));
    public static final Item GROUND_BUCKFLOWER_POWDER = registerItems("ground_buckflower_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item GROUND_CITROME_POWDER = registerItems("ground_citrome_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item HC_BATTERY = registerItems("hc_battery",new Item(new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item HOLLOW_BONE = registerItems("hollow_bone",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item HOLLOW_BONECHIP = registerItems("hollow_bonechip",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item HOT_CRUNCHY_RIBS = registerItems("hot_crunchy_ribs",new ModEpicItem(new Item.Settings().food(ModFoodComponents.HOT_CRUNCHY_RIBS)));
    public static final Item INDUSTRIAL_EXPLOSIVE = registerItems("industrial_explosive",new IndustrialExplosiveItem(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item JINCAO = registerItems("jincao",new BlockItem(ModBlocks.JINCAO_BLOCK, new Item.Settings()));
    public static final Item JINCAO_DRINK = registerItems("jincao_drink",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item JINCAO_INFUSION = registerItems("jincao_infusion",new ModEpicItem(new Item.Settings().food(ModFoodComponents.JINCAO_INFUSION)));
    public static final Item JINCAO_POWDER = registerItems("jincao_powder",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item JINCAO_SEED = registerItems("jincao_seed",new Item(new Item.Settings()));
    public static final Item JINCAO_TEA = registerItems("jincao_tea",new Item(new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item JINCAO_TISANE = registerItems("jincao_tisane",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.JINCAO_TISANE)));
    public static final Item KUNST_TUBE = registerItems("kunst_tube",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.KUBST_TUBE)));
    public static final Item KUNST_VIAL = registerItems("kunst_vial",new Item(new Item.Settings().rarity(Rarity.RARE).food(ModFoodComponents.KUNST_VIAL)));
    public static final Item LC_BATTERY = registerItems("lc_battery",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item MEAT_STIR_FRY = registerItems("meat_stir_fry",new ModEpicItem(new Item.Settings().food(ModFoodComponents.MEAT_STIR_FRY)));
    public static final Item MOSSFIELD_PIE = registerItems("mossfield_pie",new ModEpicItem(new Item.Settings().food(ModFoodComponents.MOSSFIELD_PIE)));
    public static final Item ORIGINIUM_ORE = registerItems("originium_ore",new Item(new Item.Settings()));
    public static final Item ORIGINIUM_POWDER = registerItems("originium_powder",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item ORIGOCRUST = registerItems("origocrust",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item ORIGOCRUST_POWDER = registerItems("origocrust_powder",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item PACKED_ORIGOCRUST = registerItems("packed_origocrust",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item PERPLEXING_MEDICATION = registerItems("perplexing_medication",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.PERPLEXING_MEDICATION)));
    public static final Item PRESERVE_STEW = registerItems("preserve_stew",new Item(new Item.Settings().rarity(Rarity.RARE).food(ModFoodComponents.PRESERVE_STEW)));
    public static final Item REDJADE_GINSENG = registerItems("redjade_ginseng",new BlockItem(ModBlocks.REDJADE_GINSENG_BLOCK, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item REDJADE_GINSENG_SEED = registerItems("redjade_ginseng_seed",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item REED_RYE = registerItems("reed_rye",new BlockItem(ModBlocks.REED_RYE_BLOCK, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item REED_RYE_SEED = registerItems("reed_rye_seed",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item SANDLEAF = registerItems("sandleaf",new BlockItem(ModBlocks.SANDLEAF_BLOCK, new Item.Settings()));
    public static final Item SANDLEAF_POWDER = registerItems("sandleaf_powder",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item SANDLEAF_SEED = registerItems("sandleaf_seed",new Item(new Item.Settings()));
    public static final Item SAVORY_FILLET = registerItems("savory_fillet",new Item(new Item.Settings().rarity(Rarity.UNCOMMON).food(ModFoodComponents.SAVORY_FILLET)));
    public static final Item SAVORY_TANGBAO = registerItems("savory_tangbao",new ModEpicItem(new Item.Settings().food(ModFoodComponents.SAVORY_TANGBAO)));
    public static final Item SAVORY_TANGMIAN = registerItems("savory_tangmian",new Item(new Item.Settings().rarity(Rarity.UNCOMMON).food(ModFoodComponents.SAVORY_TANGMIAN)));
    public static final Item SCORCHBUG = registerItems("scorchbug",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item SC_BATTERY = registerItems("sc_battery",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item SENSORY_REMEDY = registerItems("sensory_remedy",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.SENSORY_REMEDY)));
    public static final Item SMOKED_RICEBALL = registerItems("smoked_riceball",new ModEpicItem(new Item.Settings().food(ModFoodComponents.SMOKED_RICEBALL)));
    public static final Item SOAKED_WOOD = registerItems("soaked_wood",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item SOAKED_WOODCHIP = registerItems("soaked_woodchip",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item STABILIZED_CARBON = registerItems("stabilized_carbon",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item STEEL = registerItems("steel",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item STEEL_BOTTLE = registerItems("steel_bottle",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item STEEL_JAR = registerItems("steel_jar",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item STEEL_PART = registerItems("steel_part",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item TARTPEPPER = registerItems("tartpepper",new BlockItem(ModBlocks.TARTPEPPER_BLOCK, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item TARTPEPPER_PICKLE = registerItems("tartpepper_pickle",new Item(new Item.Settings().rarity(Rarity.RARE).food(ModFoodComponents.TARTPEPPER_PICKLE)));
    public static final Item TARTPEPPER_SALAD = registerItems("tartpepper_salad",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.TARTPEPPER_SALAD)));
    public static final Item TARTPEPPER_SEED = registerItems("tartpepper_seed",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item THORNY_YAZHEN = registerItems("thorny_yazhen",new BlockItem(ModBlocks.THORNY_YAZHEN_BLOCK, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item THORNY_YAZHEN_POWDER = registerItems("thorny_yazhen_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item UMBRALINE = registerItems("umbraline",new BlockItem(ModBlocks.UMBRALINE_BLOCK, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item VALLEY_GRAYBREAD = registerItems("valley_graybread",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.VALLEY_GRAYBREAD)));
    public static final Item WOOD = registerItems("wood",new Item(new Item.Settings()));
    public static final Item WULING_FRIED_RICE = registerItems("wuling_fried_rice",new ModEpicItem(new Item.Settings().food(ModFoodComponents.WULING_FRIED_RICE)));
    public static final Item YAZHEN = registerItems("yazhen",new BlockItem(ModBlocks.YAZHEN_BLOCK, new Item.Settings()));
    public static final Item YAZHEN_POWDER = registerItems("yazhen_powder",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item YAZHEN_SEED = registerItems("yazhen_seed",new Item(new Item.Settings()));
    public static final Item YAZHEN_SPRAY_L = registerItems("yazhen_spray_l",new ModEpicItem(new Item.Settings().food(ModFoodComponents.YAZHEN_SPRAY_S)));
    public static final Item YAZHEN_SPRAY_S = registerItems("yazhen_spray_s",new Item(new Item.Settings().rarity(Rarity.EPIC).food(ModFoodComponents.YAZHEN_SPRAY_L)));
    public static final Item YAZHEN_SYRINGE_A = registerItems("yazhen_syringe_a",new Item(new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item YAZHEN_SYRINGE_C = registerItems("yazhen_syringe_c",new Item(new Item.Settings().rarity(Rarity.RARE)));

    private static Item registerSameBlockItem(String name, BlockItem blockItem, Block... blocks){
        for (Block b : blocks) {
            Item.BLOCK_ITEMS.put(b, blockItem);
        }
        return Registry.register(Registries.ITEM, new Identifier(ArknightsEndfield.MOD_ID, name), blockItem);
    }

    private static Item registerItems(String name, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
        return Registry.register(Registries.ITEM, new Identifier(ArknightsEndfield.MOD_ID, name), item);
    }

    public static void registerModItems() {

    }
}
