package com.besson.endfield.datagen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.item.ModItems;
import com.besson.endfield.recipe.ItemCountInput;
import com.besson.endfield.recipe.builder.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipesProvider extends FabricRecipeProvider {
    public ModRecipesProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModItems.PORTABLE_ORIGINIUM_RIG_ITEM)
                .input(ModItems.ORIGOCRUST)
                .input(ModItems.ORIGOCRUST)
                .input(ModItems.ORIGOCRUST)
                .input(ModItems.ORIGOCRUST)
                .input(ModItems.ORIGOCRUST)
                .criterion("has_origocrust", conditionsFromItem(ModItems.ORIGOCRUST))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "portable_originium_rig"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModItems.REFINING_UNIT_ITEM)
                .input(ModItems.ORIGINIUM_ORE)
                .input(ModItems.ORIGINIUM_ORE)
                .input(ModItems.ORIGINIUM_ORE)
                .input(ModItems.ORIGINIUM_ORE)
                .input(ModItems.ORIGINIUM_ORE)
                .criterion("has_originium_ore", conditionsFromItem(ModItems.ORIGINIUM_ORE))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.ORIGOCRUST9)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.ORIGOCRUST)
                .criterion("has_origocrust", conditionsFromItem(ModItems.ORIGOCRUST))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "origocrust9"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModItems.FITTING_UNIT_ITEM)
                .input(ModItems.ORIGOCRUST)
                .input(ModItems.ORIGOCRUST9)
                .criterion("has_origocrust", conditionsFromItem(ModItems.ORIGOCRUST))
                .criterion("has_origocrust9", conditionsFromItem(ModItems.ORIGOCRUST9))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "fitting_unit"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModItems.MOULDING_UNIT_ITEM)
                .input(ModItems.ORIGOCRUST)
                .input(ModItems.ORIGOCRUST9)
                .criterion("has_origocrust", conditionsFromItem(ModItems.ORIGOCRUST))
                .criterion("has_origocrust9", conditionsFromItem(ModItems.ORIGOCRUST9))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit"));

        OreRigRecipeBuilder.create(ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK, Items.AMETHYST_SHARD)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/amethyst_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.COAL_MINERAL_VEIN_BLOCK, Items.COAL)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/coal_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.COPPER_MINERAL_VEIN_BLOCK, Items.RAW_COPPER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/copper_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.DIAMOND_MINERAL_VEIN_BLOCK, Items.DIAMOND)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/diamond_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.EMERALD_MINERAL_VEIN_BLOCK, Items.EMERALD)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/emerald_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.GOLD_MINERAL_VEIN_BLOCK, Items.RAW_GOLD)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/gold_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.IRON_MINERAL_VEIN_BLOCK, Items.RAW_IRON)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/iron_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.LAPIS_MINERAL_VEIN_BLOCK, Items.LAPIS_LAZULI)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/lapis_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK, Items.IRON_INGOT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/originium_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.REDSTONE_MINERAL_VEIN_BLOCK, Items.REDSTONE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/redstone_mineral_vein"));

        RefiningUnitRecipeBuilder.create(ModItems.DENSE_CARBON_POWDER, ModItems.STABILIZED_CARBON)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/dense_carbon_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.GROUND_BUCKFLOWER_POWDER, ModItems.DENSE_CARBON_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/ground_buckflower_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.GROUND_CITROME_POWDER, ModItems.DENSE_CARBON_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/ground_citrome_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.BUCKFLOWER, ModItems.CARBON)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/buckflower"));
        RefiningUnitRecipeBuilder.create(ModItems.CITROME, ModItems.CARBON)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/citrome"));
        RefiningUnitRecipeBuilder.create(new ItemCountInput(ModItems.SANDLEAF, 3), ModItems.CARBON)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/sandleaf"));
        RefiningUnitRecipeBuilder.create(ModItems.WOOD, ModItems.CARBON)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/wood"));
        RefiningUnitRecipeBuilder.create(ModItems.JINCAO, ModItems.CARBON)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/jincao"));
        RefiningUnitRecipeBuilder.create(ModItems.YAZHEN, ModItems.CARBON)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/yazhen"));
        RefiningUnitRecipeBuilder.create(ModItems.BUCKFLOWER_POWDER, ModItems.CARBON_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/buckflower_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.CITROME_POWDER, ModItems.CARBON_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/citrome_powder"));
        RefiningUnitRecipeBuilder.create(new ItemCountInput(ModItems.SANDLEAF_POWDER, 3), ModItems.CARBON_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/sandleaf_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.JINCAO_POWDER, ModItems.CARBON_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/jincao_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.YAZHEN_POWDER, ModItems.CARBON_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/yazhen_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.DENSE_ORIGOCRUST_POWDER, ModItems.PACKED_ORIGOCRUST)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/dense_origocrust_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.DENSE_ORIGINIUM_POWDER, ModItems.DENSE_ORIGOCRUST_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/dense_originium_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.ORIGINIUM_POWDER, ModItems.ORIGOCRUST_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/originium_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.ORIGINIUM_ORE, ModItems.ORIGOCRUST)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/originium_ore"));
        RefiningUnitRecipeBuilder.create(ModItems.ORIGOCRUST_POWDER, ModItems.ORIGOCRUST)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/origocrust_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.DENSE_FERRIUM_POWDER, ModItems.STEEL)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/dense_ferrium_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.FERRIUM_ORE, ModItems.FERRIUM)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/ferrium_ore"));
        RefiningUnitRecipeBuilder.create(ModItems.FERRIUM_POWDER, ModItems.FERRIUM)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/ferrium_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.CRYSTON_POWDER, ModItems.CRYSTON_FIBER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/cryston_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.AMETHYST_ORE, ModItems.AMETHYST_FIBER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/amethyst_ore"));
        RefiningUnitRecipeBuilder.create(ModItems.AMETHYST_POWDER, ModItems.AMETHYST_FIBER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/amethyst_powder"));

        ShreddingUnitRecipeBuilder.create(ModItems.CARBON, ModItems.CARBON_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/carbon"));
        ShreddingUnitRecipeBuilder.create(ModItems.ORIGOCRUST, ModItems.ORIGOCRUST_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/origocrust"));
        ShreddingUnitRecipeBuilder.create(ModItems.FERRIUM, ModItems.FERRIUM_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/ferrium"));
        ShreddingUnitRecipeBuilder.create(ModItems.ORIGINIUM_ORE, ModItems.ORIGINIUM_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/originium_ore"));
        ShreddingUnitRecipeBuilder.create(ModItems.AKETINE, ModItems.AKETINE_POWDER, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/aketine"));
        ShreddingUnitRecipeBuilder.create(ModItems.JINCAO, ModItems.JINCAO_POWDER, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/jincao"));
        ShreddingUnitRecipeBuilder.create(ModItems.YAZHEN, ModItems.YAZHEN_POWDER,2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/yazhen"));
        ShreddingUnitRecipeBuilder.create(ModItems.BUCKFLOWER, ModItems.BUCKFLOWER_POWDER, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/buckflower"));
        ShreddingUnitRecipeBuilder.create(ModItems.CITROME, ModItems.CITROME_POWDER, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/citrome"));
        ShreddingUnitRecipeBuilder.create(ModItems.SANDLEAF, ModItems.SANDLEAF_POWDER, 3)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/sandleaf"));
        ShreddingUnitRecipeBuilder.create(ModItems.AMETHYST_FIBER, ModItems.AMETHYST_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/amethyst_fiber"));

        FittingUnitRecipeBuilder.create(ModItems.CUPRIUM, ModItems.CUPRIUM_PART)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "fitting_unit/cuprium"));
        FittingUnitRecipeBuilder.create(ModItems.AMETHYST_FIBER, ModItems.AMETHYST_PART)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "fitting_unit/amethyst_fiber"));
        FittingUnitRecipeBuilder.create(ModItems.CRYSTON_FIBER, ModItems.CRYSTON_PART)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "fitting_unit/cryston_fiber"));
        FittingUnitRecipeBuilder.create(ModItems.STEEL, ModItems.STEEL_PART)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "fitting_unit/steel"));
        FittingUnitRecipeBuilder.create(ModItems.FERRIUM, ModItems.FERRIUM_PART)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "fitting_unit/ferrium"));

        MouldingUnitRecipeBuilder.create(new ItemCountInput(ModItems.AMETHYST_FIBER, 2), ModItems.AMETHYST_BOTTLE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit/amethyst_bottle"));
        MouldingUnitRecipeBuilder.create(new ItemCountInput(ModItems.CRYSTON_FIBER, 2), ModItems.CRYSTON_BOTTLE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit/cryston_bottle"));
        MouldingUnitRecipeBuilder.create(new ItemCountInput(ModItems.FERRIUM, 2), ModItems.FERRIUM_BOTTLE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit/ferrium_bottle"));
        MouldingUnitRecipeBuilder.create(new ItemCountInput(ModItems.STEEL, 2), ModItems.STEEL_BOTTLE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit/steel_bottle"));

        PlantingUnitRecipeBuilder.create(ModItems.AKETINE_SEED, ModItems.AKETINE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/aketine"));
        PlantingUnitRecipeBuilder.create(ModItems.BUCKFLOWER_SEED, ModItems.BUCKFLOWER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/buckflower"));
        PlantingUnitRecipeBuilder.create(ModItems.CITROME_SEED, ModItems.CITROME)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/citrome"));
        PlantingUnitRecipeBuilder.create(ModItems.SANDLEAF_SEED, ModItems.SANDLEAF)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/sandleaf"));

        SeedPickingUnitRecipeBuilder.create(ModItems.AKETINE, ModItems.AKETINE_SEED, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/aketine"));
        SeedPickingUnitRecipeBuilder.create(ModItems.JINCAO, ModItems.JINCAO_POWDER, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/jincao"));
        SeedPickingUnitRecipeBuilder.create(ModItems.YAZHEN, ModItems.YAZHEN_SEED, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/yazhen"));
        SeedPickingUnitRecipeBuilder.create(ModItems.BUCKFLOWER, ModItems.BUCKFLOWER_SEED, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/buckflower"));
        SeedPickingUnitRecipeBuilder.create(ModItems.CITROME, ModItems.CITROME_SEED, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/citrome"));
        SeedPickingUnitRecipeBuilder.create(ModItems.SANDLEAF, ModItems.SANDLEAF_SEED, 3)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/sandleaf"));
        SeedPickingUnitRecipeBuilder.create(ModItems.REED_RYE, ModItems.REED_RYE_SEED, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/reed_rye"));
        SeedPickingUnitRecipeBuilder.create(ModItems.TARTPEPPER, ModItems.TARTPEPPER_SEED, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/tartpepper"));
        SeedPickingUnitRecipeBuilder.create(ModItems.REDJADE_GINSENG, ModItems.REDJADE_GINSENG_SEED, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/redjade_ginseng"));
        SeedPickingUnitRecipeBuilder.create(ModItems.AMBER_RICE, ModItems.AMBER_RICE_SEED, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/amber_rice"));

        List<ItemCountInput> CANNED_CITROME_C = List.of(
                new ItemCountInput(ModItems.AMETHYST_BOTTLE, 5),
                new ItemCountInput(ModItems.CITROME_POWDER, 5));
        FillingUnitRecipeBuilder.create(CANNED_CITROME_C, ModItems.CANNED_CITROME_C)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "filling_unit/canned_citrome_c"));
        List<ItemCountInput> CANNED_CITROME_B = List.of(
                new ItemCountInput(ModItems.FERRIUM_BOTTLE, 10),
                new ItemCountInput(ModItems.CITROME_POWDER, 10));
        FillingUnitRecipeBuilder.create(CANNED_CITROME_B, ModItems.CANNED_CITROME_B)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "filling_unit/canned_citrome_b"));
        List<ItemCountInput> CANNED_CITROME_A = List.of(
                new ItemCountInput(ModItems.STEEL_BOTTLE, 10),
                new ItemCountInput(ModItems.GROUND_CITROME_POWDER, 10));
        FillingUnitRecipeBuilder.create(CANNED_CITROME_A, ModItems.CANNED_CITROME_A)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "filling_unit/canned_citrome_a"));
        List<ItemCountInput> BUCK_CAPSULE_C = List.of(
                new ItemCountInput(ModItems.AMETHYST_BOTTLE, 5),
                new ItemCountInput(ModItems.BUCKFLOWER_POWDER, 5));
        FillingUnitRecipeBuilder.create(BUCK_CAPSULE_C, ModItems.BUCK_CAPSULE_C)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "filling_unit/buck_capsule_c"));
        List<ItemCountInput> BUCK_CAPSULE_B = List.of(
                new ItemCountInput(ModItems.FERRIUM_BOTTLE, 10),
                new ItemCountInput(ModItems.BUCKFLOWER_POWDER, 10));
        FillingUnitRecipeBuilder.create(BUCK_CAPSULE_B, ModItems.BUCK_CAPSULE_B)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "filling_unit/buck_capsule_b"));
        List<ItemCountInput> BUCK_CAPSULE_A = List.of(
                new ItemCountInput(ModItems.STEEL_BOTTLE, 10),
                new ItemCountInput(ModItems.GROUND_BUCKFLOWER_POWDER, 10));
        FillingUnitRecipeBuilder.create(BUCK_CAPSULE_A, ModItems.BUCK_CAPSULE_A)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "filling_unit/buck_capsule_a"));

        List<ItemCountInput> AMETHYST_COMPONENT = List.of(
                new ItemCountInput(ModItems.ORIGOCRUST, 5),
                new ItemCountInput(ModItems.AMETHYST_FIBER, 5));
        GearingUnitRecipeBuilder.create(AMETHYST_COMPONENT, ModItems.AMETHYST_COMPONENT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "gearing_unit/amethyst_component"));
        List<ItemCountInput> FERRIUM_COMPONENT = List.of(
                new ItemCountInput(ModItems.ORIGOCRUST, 10),
                new ItemCountInput(ModItems.FERRIUM, 10));
        GearingUnitRecipeBuilder.create(FERRIUM_COMPONENT, ModItems.FERRIUM_COMPONENT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "gearing_unit/ferrium_component"));
        List<ItemCountInput> CRYSTON_COMPONENT = List.of(
                new ItemCountInput(ModItems.PACKED_ORIGOCRUST, 10),
                new ItemCountInput(ModItems.CRYSTON_FIBER, 10));
        GearingUnitRecipeBuilder.create(CRYSTON_COMPONENT, ModItems.CRYSTON_COMPONENT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "gearing_unit/cryston_component"));
        List<ItemCountInput> CUPRIUM_COMPONENT = List.of(
                new ItemCountInput(ModItems.PACKED_ORIGOCRUST, 10),
                new ItemCountInput(ModItems.CUPRIUM, 5));
        GearingUnitRecipeBuilder.create(CUPRIUM_COMPONENT, ModItems.CUPRIUM_COMPONENT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "gearing_unit/cuprium_component"));

        List<ItemCountInput> DENSE_CARBON_POWDER = List.of(
                new ItemCountInput(ModItems.CARBON_POWDER, 2),
                new ItemCountInput(ModItems.SANDLEAF_POWDER, 1));
        GrindingUnitRecipeBuilder.create(DENSE_CARBON_POWDER, ModItems.DENSE_CARBON_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "grinding_unit/dense_carbon_powder"));
        List<ItemCountInput> DENSE_ORIGOCRUST_POWDER = List.of(
                new ItemCountInput(ModItems.ORIGOCRUST_POWDER, 2),
                new ItemCountInput(ModItems.SANDLEAF_POWDER, 1));
        GrindingUnitRecipeBuilder.create(DENSE_ORIGOCRUST_POWDER, ModItems.DENSE_ORIGOCRUST_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "grinding_unit/dense_origocrust_powder"));
        List<ItemCountInput> DENSE_FERRIUM_POWDER = List.of(
                new ItemCountInput(ModItems.FERRIUM_POWDER, 2),
                new ItemCountInput(ModItems.SANDLEAF_POWDER, 1));
        GrindingUnitRecipeBuilder.create(DENSE_FERRIUM_POWDER, ModItems.DENSE_FERRIUM_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "grinding_unit/dense_ferrium_powder"));
        List<ItemCountInput> DENSE_ORIGINIUM_POWDER = List.of(
                new ItemCountInput(ModItems.ORIGINIUM_POWDER, 2),
                new ItemCountInput(ModItems.SANDLEAF_POWDER, 1));
        GrindingUnitRecipeBuilder.create(DENSE_ORIGINIUM_POWDER, ModItems.DENSE_ORIGINIUM_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "grinding_unit/dense_originium_powder"));
        List<ItemCountInput> GROUND_CITROME_POWDER = List.of(
                new ItemCountInput(ModItems.CITROME_POWDER, 2),
                new ItemCountInput(ModItems.SANDLEAF_POWDER, 1));
        GrindingUnitRecipeBuilder.create(GROUND_CITROME_POWDER, ModItems.GROUND_CITROME_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "grinding_unit/ground_citrome_powder"));
        List<ItemCountInput> GROUND_BUCKFLOWER_POWDER = List.of(
                new ItemCountInput(ModItems.BUCKFLOWER_POWDER, 2),
                new ItemCountInput(ModItems.SANDLEAF_POWDER, 1));
        GrindingUnitRecipeBuilder.create(GROUND_BUCKFLOWER_POWDER, ModItems.GROUND_BUCKFLOWER_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "grinding_unit/ground_buckflower_powder"));
        List<ItemCountInput> CRYSTON_POWDER = List.of(
                new ItemCountInput(ModItems.AMETHYST_POWDER, 2),
                new ItemCountInput(ModItems.SANDLEAF_POWDER, 1));
        GrindingUnitRecipeBuilder.create(CRYSTON_POWDER, ModItems.CRYSTON_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "grinding_unit/cryston_powder"));

        List<ItemCountInput> LC_BATTERY = List.of(
                new ItemCountInput(ModItems.AMETHYST_PART, 5),
                new ItemCountInput(ModItems.ORIGINIUM_POWDER, 10));
        PackagingUnitRecipeBuilder.create(LC_BATTERY, ModItems.LC_BATTERY)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "packaging_unit/lc_battery"));
        List<ItemCountInput> SC_BATTERY = List.of(
                new ItemCountInput(ModItems.FERRIUM_PART, 10),
                new ItemCountInput(ModItems.ORIGINIUM_POWDER, 20));
        PackagingUnitRecipeBuilder.create(SC_BATTERY, ModItems.SC_BATTERY)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "packaging_unit/sc_battery"));
        List<ItemCountInput> HC_BATTERY = List.of(
                new ItemCountInput(ModItems.STEEL_PART, 10),
                new ItemCountInput(ModItems.DENSE_ORIGINIUM_POWDER, 20));
        PackagingUnitRecipeBuilder.create(HC_BATTERY, ModItems.HC_BATTERY)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "packaging_unit/hc_battery"));
        List<ItemCountInput> INDUSTRIAL_EXPLOSIVE = List.of(
                new ItemCountInput(ModItems.AMETHYST_PART, 5),
                new ItemCountInput(ModItems.AKETINE_POWDER, 1));
        PackagingUnitRecipeBuilder.create(INDUSTRIAL_EXPLOSIVE, ModItems.INDUSTRIAL_EXPLOSIVE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "packaging_unit/industrial_explosive"));
    }
}
