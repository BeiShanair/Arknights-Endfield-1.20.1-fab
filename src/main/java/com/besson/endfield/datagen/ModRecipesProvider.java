package com.besson.endfield.datagen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.fluid.ModFluids;
import com.besson.endfield.item.ModItems;
import com.besson.endfield.item.custom.MaterialBottleItem;
import com.besson.endfield.recipe.ItemCountInput;
import com.besson.endfield.recipe.builder.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ModRecipesProvider extends FabricRecipeProvider {
    public ModRecipesProvider(FabricDataOutput output) {
        super(output);
    }

    private static final List<ItemConvertible> ORIGOCRUST = List.of(ModItems.ORIGINIUM_ORE);

    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
        // region Common Recipes
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.CRAFTER)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .input('#', ModItems.ORIGINIUM_ORE)
                .criterion("has_originium_ore", conditionsFromItem(ModItems.ORIGINIUM_ORE))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter"));

        offerSmelting(consumer, ORIGOCRUST, RecipeCategory.MISC, ModItems.ORIGOCRUST, 0.7f, 200, "origocrust");
        offerBlasting(consumer, ORIGOCRUST, RecipeCategory.MISC, ModItems.ORIGOCRUST, 0.7f, 100, "origocrust");
        
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.WOOD, 3)
                .pattern("###")
                .input('#', ItemTags.LOGS)
                .criterion("has_logs", conditionsFromTag(ItemTags.LOGS))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "wood_from_logs"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SOAKED_WOOD, 5)
                .input(ModItems.WOOD)
                .input(Items.WATER_BUCKET)
                .criterion("has_wood", conditionsFromItem(ModItems.WOOD))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "soaked_wood"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SOAKED_WOODCHIP, 9)
                .input(ModItems.WOOD)
                .input(Items.WATER_BUCKET)
                .criterion("has_woodchip", conditionsFromItem(ModItems.WOOD))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "soaked_woodchip"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.GRASS_CHAFF, 3)
                .input(Items.WHEAT_SEEDS)
                .input(Items.WHEAT_SEEDS)
                .input(Items.WHEAT_SEEDS)
                .criterion("has_wheat_seeds", conditionsFromItem(Items.WHEAT_SEEDS))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "grass_chaff"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CHITIN_BIT, 3)
                .input(ModItems.GLOWBUG)
                .input(ModItems.GLOWBUG)
                .input(ModItems.GLOWBUG)
                .criterion("has_glowbug", conditionsFromItem(ModItems.GLOWBUG))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "chitin_bit"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CHITIN_BIT, 3)
                .input(ModItems.SCORCHBUG)
                .criterion("has_scorchbug", conditionsFromItem(ModItems.SCORCHBUG))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "chitin_bit_from_scorchbug"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HOLLOW_BONE, 3)
                .input(Items.BONE)
                .input(Items.BONE)
                .input(Items.BONE)
                .criterion("has_bone", conditionsFromItem(Items.BONE))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "hollow_bone"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.HOLLOW_BONECHIP, 6)
                .input(Items.BONE)
                .input(Items.BONE)
                .criterion("has_bone", conditionsFromItem(Items.BONE))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "hollow_bonechip"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CARTILAGE_BIT, 3)
                .input(ModItems.HOLLOW_BONE)
                .criterion("has_hollow_bone", conditionsFromItem(ModItems.HOLLOW_BONE))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "cartilage_bit"));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.CARTILAGE_BIT, 3)
                .input(ModItems.HOLLOW_BONECHIP)
                .input(ModItems.HOLLOW_BONECHIP)
                .input(ModItems.HOLLOW_BONECHIP)
                .criterion("has_hollow_bonechip", conditionsFromItem(ModItems.HOLLOW_BONECHIP))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "cartilage_bit_from_bonechip"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.BELT, 9)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .input('#', ModItems.ORIGOCRUST)
                .criterion("has_origocrust", conditionsFromItem(ModItems.ORIGOCRUST))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "belt_from_origocrust"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.BELT_BRIDGE, 9)
                .pattern(" # ")
                .pattern("###")
                .pattern(" # ")
                .input('#', ModItems.ORIGOCRUST)
                .criterion("has_origocrust", conditionsFromItem(ModItems.ORIGOCRUST))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "belt_bridge_from_origocrust"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.CONVERGER, 9)
                .pattern(" # ")
                .pattern("###")
                .input('#', ModItems.ORIGOCRUST)
                .criterion("has_origocrust", conditionsFromItem(ModItems.ORIGOCRUST))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "converger_from_origocrust"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.SPLITTER, 9)
                .pattern("###")
                .pattern(" # ")
                .input('#', ModItems.ORIGOCRUST)
                .criterion("has_origocrust", conditionsFromItem(ModItems.ORIGOCRUST))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "splitter_from_origocrust"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.PIPE_BRIDGE, 9)
                .pattern(" # ")
                .pattern("# #")
                .pattern(" # ")
                .input('#', ModItems.ORIGOCRUST)
                .criterion("has_origocrust", conditionsFromItem(ModItems.ORIGOCRUST))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "pipe_bridge_from_origocrust"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.PIPE_CONVERGER, 9)
                .pattern(" # ")
                .pattern("# #")
                .input('#', ModItems.ORIGOCRUST)
                .criterion("has_origocrust", conditionsFromItem(ModItems.ORIGOCRUST))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "pipe_converger_from_origocrust"));
        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.PIPE_SPLITTER, 9)
                .pattern("# #")
                .pattern(" # ")
                .input('#', ModItems.ORIGOCRUST)
                .criterion("has_origocrust", conditionsFromItem(ModItems.ORIGOCRUST))
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "pipe_splitter_from_origocrust"));
        // endregion
        // region Ore Rig Recipes 
        OreRigRecipeBuilder.create(ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK, ModItems.AMETHYST_ORE, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/amethyst_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.COAL_MINERAL_VEIN_BLOCK, Items.COAL, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/coal_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.COPPER_MINERAL_VEIN_BLOCK, Items.RAW_COPPER, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/copper_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.DIAMOND_MINERAL_VEIN_BLOCK, Items.DIAMOND, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/diamond_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.EMERALD_MINERAL_VEIN_BLOCK, Items.EMERALD, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/emerald_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.GOLD_MINERAL_VEIN_BLOCK, Items.RAW_GOLD, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/gold_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.IRON_MINERAL_VEIN_BLOCK, Items.RAW_IRON, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/iron_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.LAPIS_MINERAL_VEIN_BLOCK, Items.LAPIS_LAZULI, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/lapis_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK, ModItems.ORIGINIUM_ORE, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/originium_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.REDSTONE_MINERAL_VEIN_BLOCK, Items.REDSTONE, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/redstone_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.FERRIUM_MINERAL_VEIN_BLOCK, ModItems.FERRIUM_ORE, 3)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/ferrium_mineral_vein"));
        OreRigRecipeBuilder.create(ModBlocks.CUPRIUM_MINERAL_VEIN_BLOCK, ModItems.CUPRIUM_ORE, 4)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/cuprium_mineral_vein"));
        // endregion
        // region Refining Unit Recipes
        RefiningUnitRecipeBuilder.create(Items.RAW_COPPER, Items.COPPER_INGOT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/raw_copper"));
        RefiningUnitRecipeBuilder.create(Items.RAW_IRON, Items.IRON_INGOT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/raw_iron"));
        RefiningUnitRecipeBuilder.create(Items.RAW_GOLD, Items.GOLD_INGOT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/raw_gold"));
        RefiningUnitRecipeBuilder.create(Items.RAW_COPPER_BLOCK, Items.COPPER_BLOCK)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/raw_copper_block"));
        RefiningUnitRecipeBuilder.create(Items.RAW_IRON_BLOCK, Items.IRON_BLOCK)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/raw_iron_block"));
        RefiningUnitRecipeBuilder.create(Items.RAW_GOLD_BLOCK, Items.GOLD_BLOCK)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/raw_gold_block"));
        RefiningUnitRecipeBuilder.create(Items.ANCIENT_DEBRIS, Items.NETHERITE_SCRAP)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/ancient_debris"));
        
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
        RefiningUnitRecipeBuilder.create(ModItems.SANDLEAF, ModItems.CARBON)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/sandleaf"));
        RefiningUnitRecipeBuilder.create(ModItems.WOOD, ModItems.CARBON)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/wood"));
        RefiningUnitRecipeBuilder.create(ModItems.JINCAO, ModItems.CARBON, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/jincao"));
        RefiningUnitRecipeBuilder.create(ModItems.YAZHEN, ModItems.CARBON, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/yazhen"));
        RefiningUnitRecipeBuilder.create(ModItems.BUCKFLOWER_POWDER, ModItems.CARBON_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/buckflower_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.CITROME_POWDER, ModItems.CARBON_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/citrome_powder"));
        RefiningUnitRecipeBuilder.create(new ItemCountInput(ModItems.SANDLEAF_POWDER, 3), ModItems.CARBON_POWDER, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/sandleaf_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.JINCAO_POWDER, ModItems.CARBON_POWDER, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/jincao_powder"));
        RefiningUnitRecipeBuilder.create(ModItems.YAZHEN_POWDER, ModItems.CARBON_POWDER, 2)
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
        RefiningUnitRecipeBuilder.create(ModItems.CUPRIUM_ORE, ModItems.CUPRIUM)
                .withFluid(new Identifier("minecraft", "water"), 1000)
                .withOutputFluid(new Identifier(ArknightsEndfield.MOD_ID, "sewage"), 1000)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/cuprium"));
        // endregion
        // region Separating Unit Recipes
        // 拆解机测试配方：水桶 -> 空桶 + 水
        SeparatingUnitRecipeBuilder.create(Items.WATER_BUCKET, Items.BUCKET)
                .withOutputFluid(new Identifier("minecraft", "water"), 1000)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "separating_unit/water_bucket"));
        // endregion
        // region Shredding Unit Recipes
        ShreddingUnitRecipeBuilder.create(Items.REDSTONE_BLOCK, Items.REDSTONE, 9)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/redstone_block"));
        ShreddingUnitRecipeBuilder.create(Items.BONE, Items.BONE_MEAL, 3)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/bone"));
        ShreddingUnitRecipeBuilder.create(Items.BONE_BLOCK, Items.BONE_MEAL, 9)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/bone_block"));
        ShreddingUnitRecipeBuilder.create(Items.CLAY, Items.CLAY_BALL, 4)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/clay"));
        ShreddingUnitRecipeBuilder.create(Items.GLOWSTONE, Items.GLOWSTONE_DUST, 4)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/glowstone"));
        ShreddingUnitRecipeBuilder.create(Items.BLAZE_ROD, Items.BLAZE_POWDER, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/blaze_rod"));
        ShreddingUnitRecipeBuilder.create(Items.SUGAR_CANE, Items.SUGAR, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/sugar_cane"));
        
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
        ShreddingUnitRecipeBuilder.create(ModItems.CUPRIUM, ModItems.CUPRIUM_POWDER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/cuprium"));
        // endregion
        // region Fitting Unit Recipes
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
        FittingUnitRecipeBuilder.create(ModItems.HETONITE, ModItems.HETONITE_PART)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "fitting_unit/hetonite"));
        // endregion
        // region Moulding Unit Recipes
        MouldingUnitRecipeBuilder.create(new ItemCountInput(Items.GLASS, 3), Items.GLASS_BOTTLE, 3)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit/glass_bottle"));
        MouldingUnitRecipeBuilder.create(new ItemCountInput(Items.IRON_INGOT, 3), Items.BUCKET)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit/bucket"));
        
        MouldingUnitRecipeBuilder.create(new ItemCountInput(ModItems.AMETHYST_FIBER, 2), ModItems.AMETHYST_BOTTLE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit/amethyst_bottle"));
        MouldingUnitRecipeBuilder.create(new ItemCountInput(ModItems.CRYSTON_FIBER, 2), ModItems.CRYSTON_BOTTLE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit/cryston_bottle"));
        MouldingUnitRecipeBuilder.create(new ItemCountInput(ModItems.FERRIUM, 2), ModItems.FERRIUM_BOTTLE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit/ferrium_bottle"));
        MouldingUnitRecipeBuilder.create(new ItemCountInput(ModItems.STEEL, 2), ModItems.STEEL_BOTTLE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit/steel_bottle"));
        MouldingUnitRecipeBuilder.create(new ItemCountInput(ModItems.CUPRIUM, 2), ModItems.CUPRIUM_BOTTLE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit/cuprium_bottle"));
        MouldingUnitRecipeBuilder.create(new ItemCountInput(ModItems.HETONITE, 2), ModItems.HETONITE_BOTTLE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit/hetonite_bottle"));
        // endregion
        // region Planting Unit Recipes
        PlantingUnitRecipeBuilder.create(Items.WHEAT_SEEDS, Items.WHEAT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/wheat"));
        PlantingUnitRecipeBuilder.create(Items.BEETROOT_SEEDS, Items.BEETROOT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/beetroot"));
        PlantingUnitRecipeBuilder.create(Items.MELON_SEEDS, Items.MELON_SEEDS)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/melon"));
        PlantingUnitRecipeBuilder.create(Items.PUMPKIN_SEEDS, Items.PUMPKIN_SEEDS)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/pumpkin"));
        PlantingUnitRecipeBuilder.create(Items.TORCHFLOWER_SEEDS, Items.TORCHFLOWER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/torchflower"));
        
        PlantingUnitRecipeBuilder.create(ModItems.AKETINE_SEED, ModItems.AKETINE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/aketine"));
        PlantingUnitRecipeBuilder.create(ModItems.BUCKFLOWER_SEED, ModItems.BUCKFLOWER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/buckflower"));
        PlantingUnitRecipeBuilder.create(ModItems.CITROME_SEED, ModItems.CITROME)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/citrome"));
        PlantingUnitRecipeBuilder.create(ModItems.SANDLEAF_SEED, ModItems.SANDLEAF)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/sandleaf"));
        PlantingUnitRecipeBuilder.create(ModItems.AMBER_RICE_SEED, ModItems.AMBER_RICE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/amber_rice"));
        PlantingUnitRecipeBuilder.create(ModItems.REDJADE_GINSENG_SEED, ModItems.REDJADE_GINSENG)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/redjade_ginseng"));
        PlantingUnitRecipeBuilder.create(ModItems.REED_RYE_SEED, ModItems.REED_RYE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/reed_rye"));
        PlantingUnitRecipeBuilder.create(ModItems.TARTPEPPER_SEED, ModItems.TARTPEPPER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/tartpepper"));
        
        PlantingUnitRecipeBuilder.create(ModItems.JINCAO_SEED, ModItems.JINCAO, 2)
                .withFluid(Registries.FLUID.getId(Fluids.WATER), 1000)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/jincao"));
        PlantingUnitRecipeBuilder.create(ModItems.YAZHEN_SEED, ModItems.YAZHEN, 2)
                .withFluid(Registries.FLUID.getId(Fluids.WATER), 1000)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/yazhen"));
        // endregion
        // region Seed Picking Unit Recipes
        SeedPickingUnitRecipeBuilder.create(Items.WHEAT, Items.WHEAT_SEEDS, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/wheat"));
        SeedPickingUnitRecipeBuilder.create(Items.BEETROOT, Items.BEETROOT_SEEDS, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/beetroot"));
        SeedPickingUnitRecipeBuilder.create(Items.MELON, Items.MELON_SEEDS, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/melon"));
        SeedPickingUnitRecipeBuilder.create(Items.PUMPKIN, Items.PUMPKIN_SEEDS, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/pumpkin"));
        SeedPickingUnitRecipeBuilder.create(Items.TORCHFLOWER, Items.TORCHFLOWER_SEEDS, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/torchflower"));
        
        SeedPickingUnitRecipeBuilder.create(ModItems.AKETINE, ModItems.AKETINE_SEED, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/aketine"));
        SeedPickingUnitRecipeBuilder.create(ModItems.JINCAO, ModItems.JINCAO_SEED, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/jincao"));
        SeedPickingUnitRecipeBuilder.create(ModItems.YAZHEN, ModItems.YAZHEN_SEED, 1)
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
        // endregion
        // region Filling Unit Recipes
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

        // 流体模式：各材质瓶装取各流体（1 空瓶 + 1000 mB 流体 → 1 瓶装流体）
        offerFluidBottlingRecipes(consumer, ModItems.AMETHYST_BOTTLE, "amethyst");
        offerFluidBottlingRecipes(consumer, ModItems.FERRIUM_BOTTLE, "ferrium");
        offerFluidBottlingRecipes(consumer, ModItems.CRYSTON_BOTTLE, "cryston");
        offerFluidBottlingRecipes(consumer, ModItems.STEEL_BOTTLE, "steel");
        offerFluidBottlingRecipes(consumer, ModItems.HETONITE_BOTTLE, "hetonite");
        offerFluidBottlingRecipes(consumer, ModItems.CUPRIUM_BOTTLE, "cuprium");
        // endregion
        // region Gearing Unit Recipes
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
                new ItemCountInput(ModItems.CUPRIUM_PART, 10),
                new ItemCountInput(ModItems.XIRANITE, 10));
        GearingUnitRecipeBuilder.create(CUPRIUM_COMPONENT, ModItems.CUPRIUM_COMPONENT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "gearing_unit/cuprium_component"));
        List<ItemCountInput> XIRANITE_COMPONENT = List.of(
                new ItemCountInput(ModItems.PACKED_ORIGOCRUST, 10),
                new ItemCountInput(ModItems.XIRANITE, 10));
        GearingUnitRecipeBuilder.create(XIRANITE_COMPONENT, ModItems.XIRANITE_COMPONENT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "gearing_unit/xiranite_component"));
        List<ItemCountInput> HETONITE_COMPONENT = List.of(
                new ItemCountInput(ModItems.HETONITE_PART, 2),
                new ItemCountInput(ModItems.HEAVY_XIRANITE, 2));
        GearingUnitRecipeBuilder.create(HETONITE_COMPONENT, ModItems.HETONITE_COMPONENT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "gearing_unit/hetonite_component"));
        // endregion
        // region Grinding Unit Recipes
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
        // endregion
        // region Packaging Unit Recipes
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
        List<ItemCountInput> LC_WULING_BATTERY = List.of(
                new ItemCountInput(ModItems.XIRANITE, 5),
                new ItemCountInput(ModItems.DENSE_ORIGINIUM_POWDER, 15));
        PackagingUnitRecipeBuilder.create(LC_WULING_BATTERY, ModItems.LC_WULING_BATTERY)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "packaging_unit/lc_wuling_battery"));
        List<ItemCountInput> SC_WULING_BATTERY = List.of(
                new ItemCountInput(ModItems.XIRCON, 5),
                new ItemCountInput(ModItems.DENSE_ORIGINIUM_POWDER, 20));
        PackagingUnitRecipeBuilder.create(SC_WULING_BATTERY, ModItems.SC_WULING_BATTERY)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "packaging_unit/sc_wuling_battery"));
        List<ItemCountInput> YAZHEN_SYRINGE_A = List.of(
                new ItemCountInput(ModItems.FERRIUM_PART, 10),
                new ItemCountInput(bottled(ModItems.FERRIUM_BOTTLE, ModFluids.STILL_YAZHEN_SOLUTION), 5));
        PackagingUnitRecipeBuilder.create(YAZHEN_SYRINGE_A, ModItems.YAZHEN_SYRINGE_A)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "packaging_unit/yazhen_syringe_a"));
        List<ItemCountInput> YAZHEN_SYRINGE_C = List.of(
                new ItemCountInput(ModItems.CUPRIUM_PART, 10),
                new ItemCountInput(bottled(ModItems.CUPRIUM_BOTTLE, ModFluids.STILL_YAZHEN_SOLUTION), 5));
        PackagingUnitRecipeBuilder.create(YAZHEN_SYRINGE_C, ModItems.YAZHEN_SYRINGE_C)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "packaging_unit/yazhen_syringe_c"));
        List<ItemCountInput> JINCAO_DRINK = List.of(
                new ItemCountInput(ModItems.FERRIUM_PART, 10),
                new ItemCountInput(bottled(ModItems.FERRIUM_BOTTLE, ModFluids.STILL_JINCAO_SOLUTION), 5));
        PackagingUnitRecipeBuilder.create(JINCAO_DRINK, ModItems.JINCAO_DRINK)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "packaging_unit/jincao_drink"));
        List<ItemCountInput> JINCAO_TEA = List.of(
                new ItemCountInput(ModItems.CUPRIUM_PART, 10),
                new ItemCountInput(bottled(ModItems.CUPRIUM_BOTTLE, ModFluids.STILL_JINCAO_SOLUTION), 5));
        PackagingUnitRecipeBuilder.create(JINCAO_TEA, ModItems.JINCAO_TEA)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "packaging_unit/jincao_tea"));
        // endregion
        // region Crafter Recipes
        CrafterRecipeBuilder.create(ModBlocks.PORTABLE_ORIGINIUM_RIG)
                .input(ModItems.ORIGOCRUST, 5)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/portable_originium_rig"));
        CrafterRecipeBuilder.create(ModBlocks.ELECTRIC_MINING_RIG)
                .input(ModItems.ORIGOCRUST, 5)
                .input(ModBlocks.PORTABLE_ORIGINIUM_RIG, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/electric_mining_rig"));
        CrafterRecipeBuilder.create(ModBlocks.ELECTRIC_MINING_RIG_MK_II)
                .input(ModItems.FERRIUM_PART, 10)
                .input(ModBlocks.PORTABLE_ORIGINIUM_RIG, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/electric_mining_rig_mk_ii"));
        CrafterRecipeBuilder.create(ModBlocks.REFINING_UNIT)
                .input(ModItems.ORIGINIUM_ORE, 5)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/refining_unit"));
        CrafterRecipeBuilder.create(ModBlocks.SHREDDING_UNIT)
                .input(ModItems.ORIGOCRUST, 5)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/shredding_unit"));
        CrafterRecipeBuilder.create(ModBlocks.FITTING_UNIT)
                .input(ModItems.ORIGOCRUST, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/fitting_unit"));
        CrafterRecipeBuilder.create(ModBlocks.MOULDING_UNIT)
                .input(ModItems.ORIGOCRUST, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/moulding_unit"));
        CrafterRecipeBuilder.create(ModBlocks.PLANTING_UNIT)
                .input(ModItems.CARBON, 10)
                .input(ModItems.AMETHYST_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/planting_unit"));
        CrafterRecipeBuilder.create(ModBlocks.SEED_PICKING_UNIT)
                .input(ModItems.AMETHYST_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/seed_picking_unit"));
        CrafterRecipeBuilder.create(ModBlocks.GEARING_UNIT)
                .input(ModItems.ORIGOCRUST, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/gearing_unit"));
        CrafterRecipeBuilder.create(ModBlocks.FILLING_UNIT)
                .input(ModItems.AMETHYST_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/filling_unit"));
        CrafterRecipeBuilder.create(ModBlocks.PACKAGING_UNIT)
                .input(ModItems.AMETHYST_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/packaging_unit"));
        CrafterRecipeBuilder.create(ModBlocks.GRINDING_UNIT)
                .input(ModItems.FERRIUM_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/grinding_unit"));
        CrafterRecipeBuilder.create(ModBlocks.ELECTRIC_PYLON)
                .input(ModItems.ORIGOCRUST, 5)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/electric_pylon"));
        CrafterRecipeBuilder.create(ModBlocks.RELAY_TOWER)
                .input(ModItems.ORIGOCRUST, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/relay_tower"));
        CrafterRecipeBuilder.create(ModBlocks.THERMAL_BANK)
                .input(ModItems.ORIGOCRUST, 10)
                .input(ModItems.AMETHYST_PART, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/thermal_bank"));

        CrafterRecipeBuilder.create(ModItems.SAVORY_TANGBAO)
                .input(ModItems.AGGAGRIT_CLUSTER, 2)
                .input(ModItems.REDJADE_GINSENG, 8)
                .input(ModItems.STEEL_PART, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/savory_tangbao"));
        CrafterRecipeBuilder.create(ModItems.CITROME_PUDDING)
                .input(ModItems.AGGAGRIT_CLUSTER, 2)
                .input(ModItems.CITROME_POWDER, 8)
                .input(ModItems.AMETHYST_BOTTLE, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/citrome_pudding"));
        CrafterRecipeBuilder.create(ModItems.VALLEY_GRAYBREAD)
                .input(ModItems.AGGAGRIT_BLOCK, 2)
                .input(ModItems.REDJADE_GINSENG, 8)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/valley_graybread"));
        CrafterRecipeBuilder.create(ModItems.CITROME_JELLY)
                .input(ModItems.AGGAGRIT_BLOCK, 2)
                .input(ModItems.CITROME_POWDER, 4)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/citrome_jelly"));
        CrafterRecipeBuilder.create(ModItems.SAVORY_TANGMIAN)
                .input(ModItems.AGGAGRIT, 2)
                .input(ModItems.REDJADE_GINSENG, 4)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/savory_tangmian"));
        CrafterRecipeBuilder.create(ModItems.CITROME_JAM)
                .input(ModItems.AGGAGRIT, 1)
                .input(ModItems.CITROME_POWDER, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/citrome_jam"));
        CrafterRecipeBuilder.create(ModItems.SAVORY_FILLET)
                .input(ModItems.FILLET, 2)
                .input(ModItems.AGGAGRIT, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/savory_fillet"));
        CrafterRecipeBuilder.create(ModItems.FILLET_CONFIT)
                .input(ModItems.FILLET, 4)
                .input(ModItems.SCORCHBUG, 2)
                .input(ModItems.AGGAGRIT, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/fillet_confit"));
        CrafterRecipeBuilder.create(ModItems.BUCKPILL_S)
                .input(ModItems.FIREBUCKLE_POWDER, 5)
                .input(ModItems.AMETHYST_BOTTLE, 5)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/buckpill_s"));
        CrafterRecipeBuilder.create(ModItems.BUCKPILL_L)
                .input(ModItems.FIREBUCKLE_POWDER, 10)
                .input(ModItems.FERRIUM_BOTTLE, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/buckpill_l"));
        CrafterRecipeBuilder.create(ModItems.BUCKPILL_RF)
                .input(ModItems.FIREBUCKLE_POWDER, 10)
                .input(ModItems.STEEL_BOTTLE, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/buckpill_rf"));
        CrafterRecipeBuilder.create(ModItems.CITROMIX_S)
                .input(ModItems.CITROMIX, 5)
                .input(ModItems.AMETHYST_BOTTLE, 5)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/citromix_s"));
        CrafterRecipeBuilder.create(ModItems.CITROMIX_L)
                .input(ModItems.CITROMIX, 10)
                .input(ModItems.FERRIUM_BOTTLE, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/citromix_l"));
        CrafterRecipeBuilder.create(ModItems.CITROMIX_RF)
                .input(ModItems.CITROMIX, 10)
                .input(ModItems.STEEL_BOTTLE, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/citromix_rf"));
        CrafterRecipeBuilder.create(ModItems.CANNED_CITROME_C)
                .input(ModItems.CITROME_POWDER, 5)
                .input(ModItems.AMETHYST_BOTTLE, 5)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/canned_citrome_c"));
        CrafterRecipeBuilder.create(ModItems.CANNED_CITROME_B)
                .input(ModItems.CITROME_POWDER, 10)
                .input(ModItems.FERRIUM_BOTTLE, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/canned_citrome_b"));
        CrafterRecipeBuilder.create(ModItems.CANNED_CITROME_A)
                .input(ModItems.GROUND_CITROME_POWDER, 10)
                .input(ModItems.STEEL_BOTTLE, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/canned_citrome_a"));
        CrafterRecipeBuilder.create(ModItems.JINCAO_TISANE)
                .input(ModItems.FLUFFED_JINCAO_POWDER, 5)
                .input(ModItems.STEEL_BOTTLE, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/jincao_tisane"));
        CrafterRecipeBuilder.create(ModItems.JINCAO_INFUSION)
                .input(ModItems.FLUFFED_JINCAO_POWDER, 5)
                .input(ModItems.CUPRIUM_JAR, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/jincao_infusion"));
        CrafterRecipeBuilder.create(ModItems.YAZHEN_SPRAY_S)
                .input(ModItems.THORNY_YAZHEN_POWDER, 5)
                .input(ModItems.STEEL_BOTTLE, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/yazhen_spray_s"));
        CrafterRecipeBuilder.create(ModItems.YAZHEN_SPRAY_L)
                .input(ModItems.THORNY_YAZHEN_POWDER, 5)
                .input(ModItems.CUPRIUM_JAR, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/yazhen_spray_l"));
        CrafterRecipeBuilder.create(ModItems.ARTS_VIAL)
                .input(ModItems.AMETHYST_BOTTLE, 1)
                .input(ModItems.GLOWBUG, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/arts_vial"));
        CrafterRecipeBuilder.create(ModItems.ARTS_TUBE)
                .input(ModItems.FERRIUM_BOTTLE, 1)
                .input(ModItems.GLOWBUG, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/arts_tube"));
        CrafterRecipeBuilder.create(ModItems.KUNST_VIAL)
                .input(ModItems.AMETHYST_BOTTLE, 1)
                .input(ModItems.SCORCHBUG, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/kunst_vial"));
        CrafterRecipeBuilder.create(ModItems.KUNST_TUBE)
                .input(ModItems.FERRIUM_BOTTLE, 1)
                .input(ModItems.SCORCHBUG, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/kunst_tube"));
        CrafterRecipeBuilder.create(ModItems.PERPLEXING_MEDICATION)
                .input(ModItems.BUCKFLOWER_POWDER, 8)
                .input(ModItems.CITROMIX, 8)
                .input(ModItems.AMETHYST_BOTTLE, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/perplexing_medication"));
        CrafterRecipeBuilder.create(ModItems.BIZARRO_CHILI)
                .input(ModItems.CITROME_POWDER, 8)
                .input(ModItems.FILLET, 8)
                .input(ModItems.AMETHYST_BOTTLE, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/bizarro_chili"));
        CrafterRecipeBuilder.create(ModItems.CITROBUCKY_MIX)
                .input(ModItems.FIREBUCKLE_POWDER, 8)
                .input(ModItems.CITROMIX, 8)
                .input(ModItems.AMETHYST_BOTTLE, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/citrobucky_mix"));
        CrafterRecipeBuilder.create(ModItems.BUCK_CAPSULE_C)
                .input(ModItems.BUCKFLOWER_POWDER, 5)
                .input(ModItems.AMETHYST_BOTTLE, 5)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/buck_capsule_c"));
        CrafterRecipeBuilder.create(ModItems.BUCK_CAPSULE_B)
                .input(ModItems.BUCKFLOWER_POWDER, 10)
                .input(ModItems.FERRIUM_BOTTLE, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/buck_capsule_b"));
        CrafterRecipeBuilder.create(ModItems.BUCK_CAPSULE_A)
                .input(ModItems.GROUND_BUCKFLOWER_POWDER, 10)
                .input(ModItems.STEEL_BOTTLE, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/buck_capsule_a"));
        CrafterRecipeBuilder.create(ModItems.MOSSFIELD_PIE)
                .input(ModItems.REED_RYE, 8)
                .input(ModItems.FILLET, 4)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/mossfield_pie"));
        CrafterRecipeBuilder.create(ModItems.COARSE_FLATBREAD)
                .input(ModItems.REED_RYE, 4)
                .input(ModItems.BUCKFLOWER_POWDER, 4)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/coarse_flatbread"));
        CrafterRecipeBuilder.create(ModItems.MEAT_STIR_FRY)
                .input(ModItems.TARTPEPPER, 8)
                .input(ModItems.FILLET, 4)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/meat_stir_fry"));
        CrafterRecipeBuilder.create(ModItems.TARTPEPPER_SALAD)
                .input(ModItems.TARTPEPPER, 4)
                .input(ModItems.CITROME_POWDER, 4)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/tartpepper_salad"));
        CrafterRecipeBuilder.create(ModItems.GINSENG_MEAT_STEW)
                .input(ModItems.REDJADE_GINSENG, 8)
                .input(ModItems.FILLET, 4)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/ginseng_meat_stew"));
        CrafterRecipeBuilder.create(ModItems.FORTIFYING_INFUSION)
                .input(ModItems.REDJADE_GINSENG, 8)
                .input(ModItems.JINCAO_POWDER, 8)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/fortifying_infusion"));
        CrafterRecipeBuilder.create(ModItems.WULING_FRIED_RICE)
                .input(ModItems.AMBER_RICE, 8)
                .input(ModItems.FILLET, 4)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/wuling_fried_rice"));
        CrafterRecipeBuilder.create(ModItems.GARDEN_FRIED_RICE)
                .input(ModItems.AMBER_RICE, 8)
                .input(ModItems.YAZHEN_POWDER, 8)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/garden_fried_rice"));
        CrafterRecipeBuilder.create(ModItems.HOT_CRUNCHY_RIBS)
                .input(ModItems.CARTILAGE_BIT, 2)
                .input(ModItems.TARTPEPPER, 8)
                .input(ModItems.STEEL_BOTTLE, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/hot_crunchy_ribs"));
        CrafterRecipeBuilder.create(ModItems.CARTILAGE_TACK)
                .input(ModItems.CARTILAGE_BIT, 2)
                .input(ModItems.BUCKFLOWER_POWDER, 8)
                .input(ModItems.AMETHYST_BOTTLE, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/cartilage_tack"));
        CrafterRecipeBuilder.create(ModItems.FIRETACK)
                .input(ModItems.CHITIN_BIT, 2)
                .input(ModItems.TARTPEPPER, 8)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/firetack"));
        CrafterRecipeBuilder.create(ModItems.BUGTACK)
                .input(ModItems.CHITIN_BIT, 2)
                .input(ModItems.BUCKFLOWER_POWDER, 4)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/bugtack"));
        CrafterRecipeBuilder.create(ModItems.TARTPEPPER_PICKLE)
                .input(ModItems.GRASS_CHAFF, 2)
                .input(ModItems.TARTPEPPER, 4)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/tartpepper_pickle"));
        CrafterRecipeBuilder.create(ModItems.BIZARROTACK)
                .input(ModItems.GRASS_CHAFF, 1)
                .input(ModItems.BUCKFLOWER_POWDER, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/bizarrotack"));
        CrafterRecipeBuilder.create(ModItems.CORRECTIVE_REMEDY)
                .input(ModItems.HOLLOW_BONE, 2)
                .input(ModItems.REDJADE_GINSENG, 8)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/corrective_remedy"));
        CrafterRecipeBuilder.create(ModItems.BLANCHED_REMEDY)
                .input(ModItems.HOLLOW_BONE, 2)
                .input(ModItems.YAZHEN_POWDER, 8)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/blanched_remedy"));
        CrafterRecipeBuilder.create(ModItems.SENSORY_REMEDY)
                .input(ModItems.HOLLOW_BONECHIP, 2)
                .input(ModItems.REDJADE_GINSENG, 8)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/sensory_remedy"));
        CrafterRecipeBuilder.create(ModItems.ASHPIN_REMEDY)
                .input(ModItems.HOLLOW_BONECHIP, 2)
                .input(ModItems.YAZHEN_POWDER, 8)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/ashpin_remedy"));
        CrafterRecipeBuilder.create(ModItems.SMOKED_RICEBALL)
                .input(ModItems.SOAKED_WOOD, 2)
                .input(ModItems.AMBER_RICE, 8)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/smoked_riceball"));
        CrafterRecipeBuilder.create(ModItems.GARDEN_STIR_FRY)
                .input(ModItems.SOAKED_WOOD, 2)
                .input(ModItems.JINCAO_POWDER, 8)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/garden_stir_fry"));
        CrafterRecipeBuilder.create(ModItems.FIRESTOVE_RICE)
                .input(ModItems.SOAKED_WOODCHIP, 2)
                .input(ModItems.AMBER_RICE, 8)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/firestove_rice"));
        CrafterRecipeBuilder.create(ModBlocks.PROTOCOL_ANCHOR_CORE_PORT)
                .input(ModItems.ORIGOCRUST, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/protocol_anchor_core_port"));
        CrafterRecipeBuilder.create(ModBlocks.SUPPLY_TERMINAL)
                .input(ModItems.ORIGOCRUST, 5)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/supply_terminal"));
        CrafterRecipeBuilder.create(ModBlocks.OUTPUT_PORT_BLOCK)
                .input(ModItems.ORIGOCRUST, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/output_port_block"));
        CrafterRecipeBuilder.create(ModBlocks.GUN_TOWER)
                .input(ModItems.ORIGOCRUST, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/gun_tower"));
        CrafterRecipeBuilder.create(ModBlocks.HEAVY_GUN_TOWER)
                .input(ModItems.FERRIUM_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/heavy_gun_tower"));
        CrafterRecipeBuilder.create(ModBlocks.GRENADE_TOWER)
                .input(ModItems.AMETHYST_PART, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/grenade_tower"));
        CrafterRecipeBuilder.create(ModBlocks.LN_TOWER)
                .input(ModItems.AMETHYST_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/ln_tower"));
        CrafterRecipeBuilder.create(ModBlocks.HE_GRENADE_TOWER)
                .input(ModItems.FERRIUM_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/he_grenade_tower"));
        CrafterRecipeBuilder.create(ModBlocks.OMNIDIRECTIONAL_SONIC_TOWER)
                .input(ModItems.FERRIUM_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/omnidirectional_sonic_tower"));
        CrafterRecipeBuilder.create(ModBlocks.SENTRY_TOWER)
                .input(ModItems.STEEL_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/sentry_tower"));
        CrafterRecipeBuilder.create(ModBlocks.MEDICAL_TOWER)
                .input(ModItems.ORIGOCRUST, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/medical_tower"));
        CrafterRecipeBuilder.create(ModBlocks.BEAM_TOWER)
                .input(ModItems.STEEL_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/beam_tower"));
        CrafterRecipeBuilder.create(ModBlocks.SURGE_TOWER)
                .input(ModItems.STEEL_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/surge_tower"));
        CrafterRecipeBuilder.create(ModBlocks.MARSH_GAS_MK_I)
                .input(ModItems.STEEL_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/marsh_gas_mk_i"));
        CrafterRecipeBuilder.create(ModBlocks.PROTOCOL_STASH)
                .input(ModItems.ORIGOCRUST, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/protocol_stash"));
        CrafterRecipeBuilder.create(ModBlocks.DEPOT_BUS_SECTION)
                .input(ModItems.ORIGOCRUST, 2)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/depot_bus_section"));
        CrafterRecipeBuilder.create(ModBlocks.DEPOT_LOADER)
                .input(ModItems.AMETHYST_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/depot_loader"));
        CrafterRecipeBuilder.create(ModBlocks.DEPOT_UNLOADER)
                .input(ModItems.AMETHYST_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/depot_unloader"));
        CrafterRecipeBuilder.create(ModItems.CONTROL_ITEM)
                .input(ModItems.ORIGOCRUST, 5)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/control_item"));
        CrafterRecipeBuilder.create(ModBlocks.XIRANITE_PYLON)
                .input(ModItems.XIRANITE, 5)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/xiranite_pylon"));
        CrafterRecipeBuilder.create(ModBlocks.XIRANITE_RELAY)
                .input(ModItems.XIRANITE, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/xiranite_relay"));
        CrafterRecipeBuilder.create(ModBlocks.FORGE_OF_THE_SKY)
                .input(ModItems.STEEL_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/forge_of_the_sky"));
        CrafterRecipeBuilder.create(ModBlocks.HYDRO_MINING_RIG)
                .input(ModItems.CUPRIUM_PART, 10)
                .input(ModBlocks.PORTABLE_ORIGINIUM_RIG, 1)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/hydro_mining_rig"));
        CrafterRecipeBuilder.create(ModBlocks.WATER_TREATMENT_UNIT)
                .input(ModItems.CUPRIUM_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/water_treatment_unit"));
        CrafterRecipeBuilder.create(ModBlocks.ACID_RESISTANT_PUMP_MK_II)
                .input(ModItems.CUPRIUM_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/acid_resistant_pump_mk_ii"));
        CrafterRecipeBuilder.create(ModBlocks.FLUID_PUMP)
                .input(ModItems.STEEL_PART, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/fluid_pump"));
        CrafterRecipeBuilder.create(ModBlocks.FLUID_TANK)
                .input(ModItems.STEEL_PART, 10)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/fluid_tank"));
        CrafterRecipeBuilder.create(ModBlocks.SEPARATING_UNIT)
                .input(ModItems.STEEL_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/separating_unit"));
        CrafterRecipeBuilder.create(ModBlocks.REACTOR_CRUCIBLE)
                .input(ModItems.STEEL_PART, 20)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/reactor_crucible"));
        CrafterRecipeBuilder.create(ModBlocks.EXPANDED_CRUCIBLE)
                .input(ModItems.CUPRIUM_PART, 40)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/expanded_crucible"));
        // endregion
        // region Reactor Crucible Recipes
        ReactorCrucibleRecipeBuilder.create()
                .input(ModItems.JINCAO_POWDER)
                .fluidInput(Fluids.WATER, 1000)
                .fluidOutput(ModFluids.STILL_JINCAO_SOLUTION, 1000)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "reactor_crucible/jincao_solution"));
        ReactorCrucibleRecipeBuilder.create()
                .input(ModItems.YAZHEN_POWDER)
                .fluidInput(Fluids.WATER, 1000)
                .fluidOutput(ModFluids.STILL_YAZHEN_SOLUTION, 1000)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "reactor_crucible/yazhen_solution"));
        ReactorCrucibleRecipeBuilder.create()
                .input(ModItems.XIRANITE)
                .fluidInput(Fluids.WATER, 1000)
                .fluidOutput(ModFluids.STILL_LIQUID_XIRANITE, 1000)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "reactor_crucible/xiranite_solution"));
        ReactorCrucibleRecipeBuilder.create()
                .fluidInput(ModFluids.STILL_LIQUID_XIRANITE, 1000)
                .fluidInput(ModFluids.STILL_SEWAGE, 1000)
                .fluidOutput(ModFluids.STILL_XIRCON_EFFLUENT, 1000)
                .fluidOutput(ModFluids.STILL_INERT_XIRCON_EFFLUENT, 1000)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "reactor_crucible/xircon_effluent"));
        ReactorCrucibleRecipeBuilder.create()
                .fluidInput(ModFluids.STILL_XIRCON_EFFLUENT, 2000)
                .input(ModItems.FERRIUM_POWDER)
                .fluidOutput(ModFluids.STILL_SEWAGE, 1000)
                .output(ModItems.XIRCON)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "reactor_crucible/xircon_effluent_to_ferrium"));
        // endregion
        // region Forge of the Sky Recipes
        ForgeOfTheSkyRecipeBuilder.create(ModItems.STABILIZED_CARBON, 2, ModItems.XIRANITE, 1)
                .fluid(Fluids.WATER, 1000)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "forge_of_the_sky/stabilized_carbon"));
        ForgeOfTheSkyRecipeBuilder.create(ModItems.XIRANITE, 10, ModItems.HEAVY_XIRANITE, 1)
                .fluid(ModFluids.STILL_XIRCON_EFFLUENT, 5000)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "forge_of_the_sky/heavy_xiranite"));
        // endregion
        // region Mineral Vein Recipes
        CrafterRecipeBuilder.create(ModBlocks.COAL_MINERAL_VEIN_BLOCK)
                .input(Items.COAL, 64)
                .input(ModItems.AMETHYST_PART, 64)
                .input(ModItems.ORIGINIUM_POWDER, 64)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/vein/coal_mineral_vein"));
        CrafterRecipeBuilder.create(ModBlocks.REDSTONE_MINERAL_VEIN_BLOCK)
                .input(Items.REDSTONE, 64)
                .input(ModItems.AMETHYST_PART, 64)
                .input(ModItems.ORIGINIUM_POWDER, 64)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/vein/redstone_mineral_vein"));
        CrafterRecipeBuilder.create(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK)
                .input(ModItems.ORIGINIUM_ORE, 64)
                .input(ModItems.AMETHYST_PART, 64)
                .input(ModItems.ORIGINIUM_POWDER, 64)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/vein/originium_mineral_vein"));
       
        CrafterRecipeBuilder.create(ModBlocks.COPPER_MINERAL_VEIN_BLOCK)
                .input(Items.RAW_COPPER, 64)
                .input(ModItems.AMETHYST_COMPONENT, 64)
                .input(ModItems.ORIGINIUM_POWDER, 64)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/vein/copper_mineral_vein"));
        CrafterRecipeBuilder.create(ModBlocks.IRON_MINERAL_VEIN_BLOCK)
                .input(Items.RAW_IRON, 64)
                .input(ModItems.AMETHYST_COMPONENT, 64)
                .input(ModItems.ORIGINIUM_POWDER, 64)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/vein/iron_mineral_vein"));
        CrafterRecipeBuilder.create(ModBlocks.LAPIS_MINERAL_VEIN_BLOCK)
                .input(Items.LAPIS_LAZULI, 64)
                .input(ModItems.AMETHYST_COMPONENT, 64)
                .input(ModItems.ORIGINIUM_POWDER, 64)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/vein/lapis_mineral_vein"));
        CrafterRecipeBuilder.create(ModBlocks.GOLD_MINERAL_VEIN_BLOCK)
                .input(Items.RAW_GOLD, 64)
                .input(ModItems.AMETHYST_COMPONENT, 64)
                .input(ModItems.ORIGINIUM_POWDER, 64)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/vein/gold_mineral_vein"));
        CrafterRecipeBuilder.create(ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK)
                .input(ModItems.AMETHYST_ORE, 64)
                .input(ModItems.AMETHYST_COMPONENT, 64)
                .input(ModItems.ORIGINIUM_POWDER, 64)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/vein/amethyst_mineral_vein"));
        
        CrafterRecipeBuilder.create(ModBlocks.DIAMOND_MINERAL_VEIN_BLOCK)
                .input(Items.DIAMOND, 64)
                .input(ModItems.HETONITE_COMPONENT, 64)
                .input(ModItems.ORIGINIUM_POWDER, 64)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/vein/diamond_mineral_vein"));
        CrafterRecipeBuilder.create(ModBlocks.EMERALD_MINERAL_VEIN_BLOCK)
                .input(Items.EMERALD, 64)
                .input(ModItems.HETONITE_COMPONENT, 64)
                .input(ModItems.ORIGINIUM_POWDER, 64)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/vein/emerald_mineral_vein"));
        
        CrafterRecipeBuilder.create(ModBlocks.FERRIUM_MINERAL_VEIN_BLOCK)
                .input(ModItems.FERRIUM_ORE, 64)
                .input(ModItems.STEEL_PART, 64)
                .input(ModItems.DENSE_ORIGINIUM_POWDER, 64)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/vein/ferrium_mineral_vein"));
        
        CrafterRecipeBuilder.create(ModBlocks.CUPRIUM_MINERAL_VEIN_BLOCK)
                .input(ModItems.CUPRIUM_ORE, 64)
                .input(ModItems.XIRANITE, 64)
                .input(ModItems.DENSE_ORIGINIUM_POWDER, 64)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "crafter/vein/cuprium_mineral_vein"));
        // endregion
    }
    
    private static Item bottled(MaterialBottleItem bottle, Fluid fluid) {
        Item item = bottle.getBottledItem(fluid);
        if (item == null) {
            throw new IllegalStateException("该材质瓶未注册流体瓶装变种: "
                    + Registries.ITEM.getId(bottle) + " + " + Registries.FLUID.getId(fluid));
        }
        return item;
    }

    private static void offerFluidBottlingRecipes(Consumer<RecipeJsonProvider> consumer,
                                                  MaterialBottleItem bottle, String materialId) {
        for (Map.Entry<String, Fluid> entry : ModItems.BOTTLED_FLUIDS.entrySet()) {
            Item bottled = bottle.getBottledItem(entry.getValue());
            if (bottled == null) continue;

            List<ItemCountInput> inputs = List.of(new ItemCountInput(bottle, 1));
            FillingUnitRecipeBuilder.create(inputs, bottled)
                    .withFluid(Registries.FLUID.getId(entry.getValue()), 1000)
                    .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID,
                            "filling_unit/" + materialId + "_bottled_" + entry.getKey()));
        }
    }
}
