package com.besson.endfield.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup ARKNIGHTS_ENDFIELD = Registry.register(Registries.ITEM_GROUP,
            new Identifier(ArknightsEndfield.MOD_ID, "arknights_endfield"),
            ItemGroup.create(null, -1)
                    .displayName(Text.translatable("itemGroup.arknights_endfield"))
                    .icon(() -> new ItemStack(ModBlocks.PROTOCOL_ANCHOR_CORE))
                    .entries((displayContext, entries) -> {
                        entries.add(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK);
                        entries.add(ModBlocks.ORIGINIUM_ORE_BLOCK);
                        entries.add(ModBlocks.DEEPSLATE_ORIGINIUM_ORE);
                        entries.add(ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK);
                        entries.add(ModBlocks.AMETHYST_ORE_BLOCK);
                        entries.add(ModBlocks.DEEPSLATE_AMETHYST_ORE);
                        entries.add(ModBlocks.FERRIUM_MINERAL_VEIN_BLOCK);
                        entries.add(ModBlocks.FERRIUM_ORE_BLOCK);
                        entries.add(ModBlocks.DEEPSLATE_FERRIUM_ORE);
                        entries.add(ModBlocks.CUPRIUM_MINERAL_VEIN_BLOCK);
                        entries.add(ModBlocks.CUPRIUM_ORE_BLOCK);
                        entries.add(ModBlocks.DEEPSLATE_CUPRIUM_ORE);

                        entries.add(ModBlocks.COAL_MINERAL_VEIN_BLOCK);
                        entries.add(ModBlocks.COPPER_MINERAL_VEIN_BLOCK);
                        entries.add(ModBlocks.DIAMOND_MINERAL_VEIN_BLOCK);
                        entries.add(ModBlocks.EMERALD_MINERAL_VEIN_BLOCK);
                        entries.add(ModBlocks.GOLD_MINERAL_VEIN_BLOCK);
                        entries.add(ModBlocks.IRON_MINERAL_VEIN_BLOCK);
                        entries.add(ModBlocks.LAPIS_MINERAL_VEIN_BLOCK);
                        entries.add(ModBlocks.REDSTONE_MINERAL_VEIN_BLOCK);

                        entries.add(ModBlocks.CRAFTER);
                        entries.add(ModItems.PROTOCOL_ANCHOR_CORE_ITEM);
                        entries.add(ModBlocks.PROTOCOL_ANCHOR_CORE_PORT);
                        entries.add(ModItems.RELAY_TOWER_ITEM);
                        entries.add(ModItems.ELECTRIC_PYLON_ITEM);
                        entries.add(ModItems.ELECTRIC_MINING_RIG_ITEM);
                        entries.add(ModItems.ELECTRIC_MINING_RIG_MK_II_ITEM);
                        entries.add(ModItems.PORTABLE_ORIGINIUM_RIG_ITEM);
                        entries.add(ModItems.REFINING_UNIT_ITEM);
                        entries.add(ModItems.SHREDDING_UNIT_ITEM);
                        entries.add(ModItems.FILLING_UNIT_ITEM);
                        entries.add(ModItems.FITTING_UNIT_ITEM);
                        entries.add(ModItems.GEARING_UNIT_ITEM);
                        entries.add(ModItems.GRINDING_UNIT_ITEM);
                        entries.add(ModItems.MOULDING_UNIT_ITEM);
                        entries.add(ModItems.PACKAGING_UNIT_ITEM);
                        entries.add(ModItems.PLANTING_UNIT_ITEM);
                        entries.add(ModItems.SEED_PICKING_UNIT_ITEM);
                        entries.add(ModItems.THERMAL_BANK_ITEM);
//                        entries.add(ModItems.FLUID_PUMP_ITEM);
//                        entries.add(ModBlocks.PIPE_BLOCK);
//                        entries.add(ModBlocks.BIG_STORAGE);

                        entries.add(ModBlocks.SUPPLY_TERMINAL);
                        entries.add(ModItems.GUN_TOWER_ITEM);

                        entries.add(ModItems.ORIGINIUM_ORE);
                        entries.add(ModItems.ORIGINIUM_POWDER);
                        entries.add(ModItems.ORIGOCRUST);
                        entries.add(ModItems.ORIGOCRUST_POWDER);
                        entries.add(ModItems.PACKED_ORIGOCRUST);
                        entries.add(ModItems.AMETHYST_ORE);
                        entries.add(ModItems.AMETHYST_POWDER);
                        entries.add(ModItems.AMETHYST_FIBER);
                        entries.add(ModItems.AMETHYST_BOTTLE);
                        entries.add(ModItems.AMETHYST_PART);
                        entries.add(ModItems.AMETHYST_COMPONENT);
                        entries.add(ModItems.FERRIUM_ORE);
                        entries.add(ModItems.FERRIUM_POWDER);
                        entries.add(ModItems.FERRIUM);
                        entries.add(ModItems.FERRIUM_BOTTLE);
                        entries.add(ModItems.FERRIUM_PART);
                        entries.add(ModItems.FERRIUM_COMPONENT);
                        entries.add(ModItems.CUPRIUM_ORE);
                        entries.add(ModItems.CUPRIUM_POWDER);
                        entries.add(ModItems.CUPRIUM);
                        entries.add(ModItems.CUPRIUM_PART);
                        entries.add(ModItems.CUPRIUM_COMPONENT);
                        entries.add(ModItems.CUPRIUM_JAR);
                        entries.add(ModItems.CRYSTON_PART);
                        entries.add(ModItems.CRYSTON_POWDER);
                        entries.add(ModItems.CRYSTON_FIBER);
                        entries.add(ModItems.CRYSTON_BOTTLE);
                        entries.add(ModItems.CRYSTON_COMPONENT);
                        entries.add(ModItems.STEEL);
                        entries.add(ModItems.STEEL_BOTTLE);
                        entries.add(ModItems.STEEL_PART);
                        entries.add(ModItems.STEEL_JAR);
                        entries.add(ModItems.DENSE_ORIGINIUM_POWDER);
                        entries.add(ModItems.DENSE_ORIGOCRUST_POWDER);
                        entries.add(ModItems.DENSE_FERRIUM_POWDER);
                        entries.add(ModItems.DENSE_CARBON_POWDER);

                        entries.add(ModItems.BUCKFLOWER);
                        entries.add(ModItems.BUCKFLOWER_SEED);
                        entries.add(ModItems.BUCKFLOWER_POWDER);
                        entries.add(ModItems.BUCK_CAPSULE_C);
                        entries.add(ModItems.BUCK_CAPSULE_B);
                        entries.add(ModItems.BUCK_CAPSULE_A);
                        entries.add(ModItems.BUCKPILL_S);
                        entries.add(ModItems.BUCKPILL_L);
                        entries.add(ModItems.BUCKPILL_RF);
                        entries.add(ModItems.GROUND_BUCKFLOWER_POWDER);

                        entries.add(ModItems.CITROME);
                        entries.add(ModItems.CITROME_SEED);
                        entries.add(ModItems.CITROME_POWDER);
                        entries.add(ModItems.CANNED_CITROME_C);
                        entries.add(ModItems.CANNED_CITROME_B);
                        entries.add(ModItems.CANNED_CITROME_A);
                        entries.add(ModItems.CITROME_JAM);
                        entries.add(ModItems.CITROME_JELLY);
                        entries.add(ModItems.CITROME_PUDDING);
                        entries.add(ModItems.CITROBUCKY_MIX);
                        entries.add(ModItems.UMBRALINE);
                        entries.add(ModItems.CITROMIX);
                        entries.add(ModItems.CITROMIX_S);
                        entries.add(ModItems.CITROMIX_L);
                        entries.add(ModItems.CITROMIX_RF);
                        entries.add(ModItems.FIREBUCKLE);
                        entries.add(ModItems.FIREBUCKLE_POWDER);
                        entries.add(ModItems.GROUND_CITROME_POWDER);

                        entries.add(ModItems.JINCAO);
                        entries.add(ModItems.JINCAO_SEED);
                        entries.add(ModItems.JINCAO_POWDER);
                        entries.add(ModItems.JINCAO_DRINK);
                        entries.add(ModItems.JINCAO_INFUSION);
                        entries.add(ModItems.JINCAO_TEA);
                        entries.add(ModItems.JINCAO_TISANE);
                        entries.add(ModItems.FLUFFED_JINCAO);
                        entries.add(ModItems.FLUFFED_JINCAO_POWDER);

                        entries.add(ModItems.YAZHEN);
                        entries.add(ModItems.YAZHEN_SEED);
                        entries.add(ModItems.YAZHEN_POWDER);
                        entries.add(ModItems.YAZHEN_SPRAY_S);
                        entries.add(ModItems.YAZHEN_SPRAY_L);
                        entries.add(ModItems.YAZHEN_SYRINGE_C);
                        entries.add(ModItems.YAZHEN_SYRINGE_A);
                        entries.add(ModItems.THORNY_YAZHEN);
                        entries.add(ModItems.THORNY_YAZHEN_POWDER);

                        entries.add(ModItems.AKETINE);
                        entries.add(ModItems.AKETINE_SEED);
                        entries.add(ModItems.AKETINE_POWDER);
                        entries.add(ModItems.AMBER_RICE);
                        entries.add(ModItems.AMBER_RICE_SEED);
                        entries.add(ModItems.REDJADE_GINSENG);
                        entries.add(ModItems.REDJADE_GINSENG_SEED);
                        entries.add(ModItems.REED_RYE);
                        entries.add(ModItems.REED_RYE_SEED);
                        entries.add(ModItems.SANDLEAF);
                        entries.add(ModItems.SANDLEAF_SEED);
                        entries.add(ModItems.SANDLEAF_POWDER);

                        entries.add(ModItems.TARTPEPPER);
                        entries.add(ModItems.TARTPEPPER_SEED);
                        entries.add(ModItems.TARTPEPPER_PICKLE);
                        entries.add(ModItems.TARTPEPPER_SALAD);

                        entries.add(ModItems.CARBON);
                        entries.add(ModItems.CARBON_POWDER);
                        entries.add(ModItems.STABILIZED_CARBON);

                        entries.add(ModItems.AGGAGRIT);
                        entries.add(ModItems.AGGAGRIT_BLOCK);
                        entries.add(ModItems.AGGAGRIT_CLUSTER);

                        entries.add(ModItems.LC_BATTERY);
                        entries.add(ModItems.SC_BATTERY);
                        entries.add(ModItems.HC_BATTERY);
                        entries.add(ModItems.INDUSTRIAL_EXPLOSIVE);

                        entries.add(ModItems.ARTS_TUBE);
                        entries.add(ModItems.ARTS_VIAL);
                        entries.add(ModItems.KUNST_TUBE);
                        entries.add(ModItems.KUNST_VIAL);
                        entries.add(ModItems.PERPLEXING_MEDICATION);

                        entries.add(ModItems.ASHPIN_REMEDY);
                        entries.add(ModItems.BLANCHED_REMEDY);
                        entries.add(ModItems.BIZARROTACK);
                        entries.add(ModItems.BIZARRO_CHILI);
                        entries.add(ModItems.BUGTACK);
                        entries.add(ModItems.CARTILAGE_TACK);

                        entries.add(ModItems.COARSE_FLATBREAD);
                        entries.add(ModItems.CORRECTIVE_REMEDY);

                        entries.add(ModItems.FILLET);
                        entries.add(ModItems.FILLET_CONFIT);
                        entries.add(ModItems.FIRESTOVE_RICE);
                        entries.add(ModItems.FIRETACK);
                        entries.add(ModItems.FORTIFYING_INFUSION);
                        entries.add(ModItems.GARDEN_FRIED_RICE);
                        entries.add(ModItems.GARDEN_STIR_FRY);
                        entries.add(ModItems.GINSENG_MEAT_STEW);
                        entries.add(ModItems.GRASS_CHAFF);
                        entries.add(ModItems.CHITIN_BIT);
                        entries.add(ModItems.CARTILAGE_BIT);
                        entries.add(ModItems.HOLLOW_BONE);
                        entries.add(ModItems.HOLLOW_BONECHIP);
                        entries.add(ModItems.HOT_CRUNCHY_RIBS);

                        entries.add(ModItems.MEAT_STIR_FRY);
                        entries.add(ModItems.MOSSFIELD_PIE);
                        entries.add(ModItems.PRESERVE_STEW);
                        entries.add(ModItems.SAVORY_FILLET);
                        entries.add(ModItems.SAVORY_TANGBAO);
                        entries.add(ModItems.SAVORY_TANGMIAN);
                        entries.add(ModItems.SCORCHBUG);
                        entries.add(ModItems.GLOWBUG);
                        entries.add(ModItems.SENSORY_REMEDY);
                        entries.add(ModItems.SMOKED_RICEBALL);
                        entries.add(ModItems.WOOD);
                        entries.add(ModItems.SOAKED_WOOD);
                        entries.add(ModItems.SOAKED_WOODCHIP);
                        entries.add(ModItems.VALLEY_GRAYBREAD);
                        entries.add(ModItems.WULING_FRIED_RICE);
                    }).build());

    public static void registerItemGroup(){

    }
}
