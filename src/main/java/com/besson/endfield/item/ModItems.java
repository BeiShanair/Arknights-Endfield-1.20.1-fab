package com.besson.endfield.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.fluid.ModFluids;
import com.besson.endfield.item.custom.*;
import com.besson.endfield.item.custom.combat.*;
import com.besson.endfield.item.custom.depot.DepotLoaderItem;
import com.besson.endfield.item.custom.depot.DepotUnloaderItem;
import com.besson.endfield.item.custom.powering.*;
import com.besson.endfield.item.custom.production1.*;
import com.besson.endfield.item.custom.production2.*;
import com.besson.endfield.item.custom.resourcing.*;
import com.besson.endfield.item.custom.depot.DepotBusSectionItem;
import com.besson.endfield.item.custom.depot.ProtocolStashItem;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModItems {
    // region Powering
    public static final Item PROTOCOL_ANCHOR_CORE_ITEM = registerItems("protocol_anchor_core",
            new ProtocolAnchorCoreItem(ModBlocks.PROTOCOL_ANCHOR_CORE, new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item RELAY_TOWER_ITEM = registerItems("relay_tower",
            new RelayTowerItem(ModBlocks.RELAY_TOWER, new Item.Settings()));
    public static final Item ELECTRIC_PYLON_ITEM = registerItems("electric_pylon",
            new ElectricPylonItem(ModBlocks.ELECTRIC_PYLON, new Item.Settings()));
    public static final Item XIRANITE_PYLON_ITEM = registerItems("xiranite_pylon",
            new XiranitePylonItem(ModBlocks.XIRANITE_PYLON, new Item.Settings()));
    public static final Item XIRANITE_RELAY_ITEM = registerItems("xiranite_relay",
            new XiraniteRelayItem(ModBlocks.XIRANITE_RELAY, new Item.Settings()));
    public static final Item THERMAL_BANK_ITEM = registerSameBlockItem("thermal_bank",
            new ThermalBankItem(ModBlocks.THERMAL_BANK, new Item.Settings().rarity(Rarity.UNCOMMON)));
    // endregion
    // region Resourcing
    public static final Item ELECTRIC_MINING_RIG_ITEM = registerItems("electric_mining_rig",
            new ElectricMiningRigItem(ModBlocks.ELECTRIC_MINING_RIG, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item ELECTRIC_MINING_RIG_MK_II_ITEM = registerItems("electric_mining_rig_mk_ii",
            new ElectricMiningRigMkIIItem(ModBlocks.ELECTRIC_MINING_RIG_MK_II, new Item.Settings().rarity(Rarity.RARE)));
    public static final Item PORTABLE_ORIGINIUM_RIG_ITEM = registerItems("portable_originium_rig",
            new PortableOriginiumRigItem(ModBlocks.PORTABLE_ORIGINIUM_RIG, new Item.Settings()));
    public static final Item HYDRO_MINING_RIG_ITEM = registerItems("hydro_mining_rig",
            new HydroMiningRigItem(ModBlocks.HYDRO_MINING_RIG, new Item.Settings()));
    public static final Item FLUID_PUMP_ITEM = registerItems("fluid_pump",
            new FluidPumpItem(ModBlocks.FLUID_PUMP, new Item.Settings().rarity(Rarity.RARE)));
    public static final Item ACID_RESISTANT_PUMP_MK_II_ITEM = registerItems("acid_resistant_pump_mk_ii",
            new AcidResistantPumpMkIIItem(ModBlocks.ACID_RESISTANT_PUMP_MK_II, new Item.Settings().rarity(Rarity.RARE)));
    // endregion
    // region Production1
    public static final Item REFINING_UNIT_ITEM = registerSameBlockItem("refining_unit",
            new RefiningUnitItem(ModBlocks.REFINING_UNIT, new Item.Settings()));
    public static final Item SHREDDING_UNIT_ITEM = registerSameBlockItem("shredding_unit",
            new ShreddingUnitItem(ModBlocks.SHREDDING_UNIT, new Item.Settings()));
    public static final Item FITTING_UNIT_ITEM = registerSameBlockItem("fitting_unit",
            new FittingUnitItem(ModBlocks.FITTING_UNIT, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item PLANTING_UNIT_ITEM = registerSameBlockItem("planting_unit",
            new PlantingUnitItem(ModBlocks.PLANTING_UNIT, new Item.Settings().rarity(Rarity.RARE)));
    public static final Item SEED_PICKING_UNIT_ITEM = registerSameBlockItem("seed_picking_unit",
            new SeedPickingUnitItem(ModBlocks.SEED_PICKING_UNIT, new Item.Settings().rarity(Rarity.RARE)));
    public static final Item WATER_TREATMENT_UNIT_ITEM = registerItems("water_treatment_unit",
            new WaterTreatmentUnitItem(ModBlocks.WATER_TREATMENT_UNIT, new Item.Settings().rarity(Rarity.RARE)));
    // endregion
    // region Production2
    public static final Item FILLING_UNIT_ITEM = registerSameBlockItem("filling_unit",
            new FillingUnitItem(ModBlocks.FILLING_UNIT, new Item.Settings().rarity(Rarity.RARE)));
    public static final Item GEARING_UNIT_ITEM = registerSameBlockItem("gearing_unit",
            new GearingUnitItem(ModBlocks.GEARING_UNIT, new Item.Settings().rarity(Rarity.RARE)));
    public static final Item GRINDING_UNIT_ITEM = registerSameBlockItem("grinding_unit",
            new GrindingUnitItem(ModBlocks.GRINDING_UNIT, new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item MOULDING_UNIT_ITEM = registerSameBlockItem("moulding_unit",
            new MouldingUnitItem(ModBlocks.MOULDING_UNIT, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item PACKAGING_UNIT_ITEM = registerSameBlockItem("packaging_unit",
            new PackagingUnitItem(ModBlocks.PACKAGING_UNIT, new Item.Settings().rarity(Rarity.RARE)));
    public static final Item SEPARATING_UNIT_ITEM = registerItems("separating_unit",
            new SeparatingUnitItem(ModBlocks.SEPARATING_UNIT, new Item.Settings().rarity(Rarity.RARE)));
    public static final Item FORGE_OF_THE_SKY = registerItems("forge_of_the_sky",
            new ForgeOfTheSkyItem(ModBlocks.FORGE_OF_THE_SKY, new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item REACTOR_CRUCIBLE = registerSameBlockItem("reactor_crucible",
            new ReactorCrucibleItem(ModBlocks.REACTOR_CRUCIBLE, new Item.Settings().rarity(Rarity.RARE)));
    public static final Item EXPANDED_CRUCIBLE = registerSameBlockItem("expanded_crucible",
            new ExpandedCrucibleItem(ModBlocks.EXPANDED_CRUCIBLE, new Item.Settings().rarity(Rarity.RARE)));
    // endregion
    // region Logistics
    public static final Item PROTOCOL_STASH_ITEM = registerItems("protocol_stash",
            new ProtocolStashItem(ModBlocks.PROTOCOL_STASH, new Item.Settings()));
    public static final Item DEPOT_BUS_SECTION_ITEM = registerItems("depot_bus_section",
            new DepotBusSectionItem(ModBlocks.DEPOT_BUS_SECTION, new Item.Settings()));
    public static final Item DEPOT_LOADER_ITEM = registerItems("depot_loader",
            new DepotLoaderItem(ModBlocks.DEPOT_LOADER, new Item.Settings()));
    public static final Item DEPOT_UNLOADER_ITEM = registerItems("depot_unloader",
            new DepotUnloaderItem(ModBlocks.DEPOT_UNLOADER, new Item.Settings()));
    public static final Item FLUID_TANK_ITEM = registerItems("fluid_tank",
            new FluidTankItem(ModBlocks.FLUID_TANK, new Item.Settings().rarity(Rarity.RARE)));
    // endregion
    // region Fluid Buckets
    public static final Item SEWAGE_BUCKET = registerItems("sewage_bucket",
            new BucketItem(ModFluids.STILL_SEWAGE,
                    new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item PRECIPITATION_ACID_BUCKET = registerItems("precipitation_acid_bucket",
            new BucketItem(ModFluids.STILL_PRECIPITATION_ACID,
                    new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item XIRCON_EFFLUENT_BUCKET = registerItems("xircon_effluent_bucket",
            new BucketItem(ModFluids.STILL_XIRCON_EFFLUENT,
                    new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item INERT_XIRCON_EFFLUENT_BUCKET = registerItems("inert_xircon_effluent_bucket",
            new BucketItem(ModFluids.STILL_INERT_XIRCON_EFFLUENT,
                    new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item YAZHEN_SOLUTION_BUCKET = registerItems("yazhen_solution_bucket",
            new BucketItem(ModFluids.STILL_YAZHEN_SOLUTION,
                    new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item JINCAO_SOLUTION_BUCKET = registerItems("jincao_solution_bucket",
            new BucketItem(ModFluids.STILL_JINCAO_SOLUTION,
                    new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    public static final Item LIQUID_XIRANITE_BUCKET = registerItems("liquid_xiranite_bucket",
            new BucketItem(ModFluids.STILL_LIQUID_XIRANITE,
                    new Item.Settings().maxCount(1).recipeRemainder(Items.BUCKET)));
    // endregion
    // region Combat
    public static final Item BEAM_TOWER_ITEM = registerItems("beam_tower",
            new BeamTowerItem(ModBlocks.BEAM_TOWER, new Item.Settings()));
    public static final Item GRENADE_TOWER_ITEM = registerItems("grenade_tower",
            new GrenadeTowerItem(ModBlocks.GRENADE_TOWER, new Item.Settings()));
    public static final Item GUN_TOWER_ITEM = registerItems("gun_tower",
            new GunTowerItem(ModBlocks.GUN_TOWER, new Item.Settings()));
    public static final Item HEAVY_GUN_TOWER_ITEM = registerItems("heavy_gun_tower",
            new HeavyGunTowerItem(ModBlocks.HEAVY_GUN_TOWER, new Item.Settings()));
    public static final Item HE_GRENADE_TOWER_ITEM = registerItems("he_grenade_tower",
            new HeGrenadeTowerItem(ModBlocks.HE_GRENADE_TOWER, new Item.Settings().rarity(Rarity.RARE)));
    public static final Item LN_TOWER_ITEM = registerItems("ln_tower",
            new LNTowerItem(ModBlocks.LN_TOWER, new Item.Settings().rarity(Rarity.RARE)));
    public static final Item MARSH_GAS_MK_I_ITEM = registerItems("marsh_gas_mk_i",
            new MarshGasMkIItem(ModBlocks.MARSH_GAS_MK_I, new Item.Settings().rarity(Rarity.RARE)));
    public static final Item MEDICAL_TOWER_ITEM = registerItems("medical_tower",
            new MedicalTowerItem(ModBlocks.MEDICAL_TOWER, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item OMNIDIRECTIONAL_SONIC_TOWER_ITEM = registerItems("omnidirectional_sonic_tower",
            new OmnidirectionalSonicTowerItem(ModBlocks.OMNIDIRECTIONAL_SONIC_TOWER, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item SENTRY_TOWER_ITEM = registerItems("sentry_tower",
            new SentryTowerItem(ModBlocks.SENTRY_TOWER, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item SURGE_TOWER_ITEM = registerItems("surge_tower",
            new SurgeTowerItem(ModBlocks.SURGE_TOWER, new Item.Settings().rarity(Rarity.UNCOMMON)));
    // endregion
    public static final Item AGGAGRIT = registerItems("aggagrit",new Item(new Item.Settings()));
    public static final Item AGGAGRIT_BLOCK = registerItems("aggagrit_block",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item AGGAGRIT_CLUSTER = registerItems("aggagrit_cluster",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item AKETINE = registerItems("aketine",new BlockItem(ModBlocks.AKETINE_BLOCK, new Item.Settings()));
    public static final Item AKETINE_POWDER = registerItems("aketine_powder",new Item(new Item.Settings()));
    public static final Item AKETINE_SEED = registerItems("aketine_seed",new Item(new Item.Settings()));
    public static final Item AMBER_RICE = registerItems("amber_rice",new BlockItem(ModBlocks.AMBER_RICE_BLOCK, new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item AMBER_RICE_SEED = registerItems("amber_rice_seed",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final MaterialBottleItem AMETHYST_BOTTLE = registerMaterialBottle("amethyst_bottle",new Item.Settings().rarity(Rarity.UNCOMMON));
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
    public static final MaterialBottleItem CRYSTON_BOTTLE = registerMaterialBottle("cryston_bottle",new Item.Settings().rarity(Rarity.RARE));
    public static final Item CRYSTON_COMPONENT = registerItems("cryston_component",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CRYSTON_FIBER = registerItems("cryston_fiber",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CRYSTON_PART = registerItems("cryston_part",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CRYSTON_POWDER = registerItems("cryston_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CUPRIUM = registerItems("cuprium",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item CUPRIUM_COMPONENT = registerItems("cuprium_component",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CUPRIUM_JAR = registerItems("cuprium_jar",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final MaterialBottleItem CUPRIUM_BOTTLE = registerMaterialBottle("cuprium_bottle", new Item.Settings().rarity(Rarity.RARE));
    public static final Item CUPRIUM_ORE = registerItems("cuprium_ore",new Item(new Item.Settings()));
    public static final Item CUPRIUM_PART = registerItems("cuprium_part",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item CUPRIUM_POWDER = registerItems("cuprium_powder",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final Item DENSE_CARBON_POWDER = registerItems("dense_carbon_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item DENSE_FERRIUM_POWDER = registerItems("dense_ferrium_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item DENSE_ORIGINIUM_POWDER = registerItems("dense_originium_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item DENSE_ORIGOCRUST_POWDER = registerItems("dense_origocrust_powder",new Item(new Item.Settings().rarity(Rarity.RARE)));
    public static final Item FERRIUM = registerItems("ferrium",new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));
    public static final MaterialBottleItem FERRIUM_BOTTLE = registerMaterialBottle("ferrium_bottle",new Item.Settings().rarity(Rarity.UNCOMMON));
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
    public static final MaterialBottleItem STEEL_BOTTLE = registerMaterialBottle("steel_bottle",new Item.Settings().rarity(Rarity.RARE));
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
    
    public static final Item CONTROL_ITEM = registerItems("control_item",
            new ControlItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));
    
    public static final Item ORIGINIUM_HAMMER = registerItems("originium_hammer",
            new OriginiumHammer(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));

    public static final Item XIRANITE = registerItems("xiranite",new Item(new Item.Settings()));
    public static final Item HEAVY_XIRANITE = registerItems("heavy_xiranite",new Item(new Item.Settings()));
    public static final Item HETONITE = registerItems("hetonite",new Item(new Item.Settings()));
    public static final MaterialBottleItem HETONITE_BOTTLE = registerMaterialBottle("hetonite_bottle",new Item.Settings());
    public static final Item HETONITE_PART = registerItems("hetonite_part",new Item(new Item.Settings()));
    public static final Item HETONITE_COMPONENT = registerItems("hetonite_component",new Item(new Item.Settings()));
    public static final Item LC_WULING_BATTERY = registerItems("lc_wuling_battery",new Item(new Item.Settings()));
    public static final Item SC_WULING_BATTERY = registerItems("sc_wuling_battery",new Item(new Item.Settings()));
    public static final Item XIRANITE_COMPONENT = registerItems("xiranite_component",new Item(new Item.Settings()));
    public static final Item XIRCON = registerItems("xircon",new Item(new Item.Settings()));

    // region Fluid Bottles
    public static final Map<String, Fluid> BOTTLED_FLUIDS;
    static {
        Map<String, Fluid> m = new LinkedHashMap<>();
        m.put("water", Fluids.WATER);
        m.put("sewage", ModFluids.STILL_SEWAGE);
        m.put("precipitation_acid", ModFluids.STILL_PRECIPITATION_ACID);
        m.put("xircon_effluent", ModFluids.STILL_XIRCON_EFFLUENT);
        m.put("inert_xircon_effluent", ModFluids.STILL_INERT_XIRCON_EFFLUENT);
        m.put("yazhen_solution", ModFluids.STILL_YAZHEN_SOLUTION);
        m.put("jincao_solution", ModFluids.STILL_JINCAO_SOLUTION);
        m.put("liquid_xiranite", ModFluids.STILL_LIQUID_XIRANITE);
        BOTTLED_FLUIDS = Collections.unmodifiableMap(m);
    }
    public static final List<Item> AMETHYST_BOTTLED_SERIES = registerBottledSeries("amethyst", AMETHYST_BOTTLE);
    public static final List<Item> FERRIUM_BOTTLED_SERIES = registerBottledSeries("ferrium", FERRIUM_BOTTLE);
    public static final List<Item> CRYSTON_BOTTLED_SERIES = registerBottledSeries("cryston", CRYSTON_BOTTLE);
    public static final List<Item> STEEL_BOTTLED_SERIES = registerBottledSeries("steel", STEEL_BOTTLE);
    public static final List<Item> CUPRIUM_BOTTLED_SERIES = registerBottledSeries("cuprium", CUPRIUM_BOTTLE);
    public static final List<Item> HETONITE_BOTTLED_SERIES = registerBottledSeries("hetonite", HETONITE_BOTTLE);
    public static final List<Item> ALL_BOTTLED_FLUIDS = Stream.of(
            AMETHYST_BOTTLED_SERIES, FERRIUM_BOTTLED_SERIES, CRYSTON_BOTTLED_SERIES,
            STEEL_BOTTLED_SERIES, CUPRIUM_BOTTLED_SERIES, HETONITE_BOTTLED_SERIES
    ).flatMap(List::stream).toList();
    // endregion
    
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
    
    private static List<Item> registerBottledSeries(String materialId, MaterialBottleItem bottle) {
        List<Item> series = new ArrayList<>();
        for (Map.Entry<String, Fluid> entry : BOTTLED_FLUIDS.entrySet()) {
            FluidBottleItem bottled = new FluidBottleItem(entry.getValue(), bottle,
                    new Item.Settings().maxCount(64));
            registerItems(materialId + "_bottled_" + entry.getKey(), bottled);
            bottle.addBottled(entry.getValue(), bottled);
            series.add(bottled);
        }
        return Collections.unmodifiableList(series);
    }

    private static MaterialBottleItem registerMaterialBottle(String name, Item.Settings settings) {
        return (MaterialBottleItem) registerItems(name, new MaterialBottleItem(settings));
    }

    public static void registerModItems() {

    }
}
