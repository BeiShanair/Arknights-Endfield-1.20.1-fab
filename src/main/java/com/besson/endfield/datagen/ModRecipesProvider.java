package com.besson.endfield.datagen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.recipe.ItemCountInput;
import com.besson.endfield.recipe.builder.*;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipesProvider extends FabricRecipeProvider {
    public ModRecipesProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
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

        RefiningUnitRecipeBuilder.create(Items.RAW_COPPER, Items.COPPER_INGOT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/raw_copper"));
        RefiningUnitRecipeBuilder.create(Items.RAW_IRON, Items.IRON_INGOT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/raw_iron"));
        RefiningUnitRecipeBuilder.create(Items.RAW_GOLD, Items.GOLD_INGOT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/raw_gold"));
        RefiningUnitRecipeBuilder.create(Items.COPPER_ORE, Items.COPPER_INGOT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/copper_ore"));
        RefiningUnitRecipeBuilder.create(Items.IRON_ORE, Items.IRON_INGOT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/iron_ore"));
        RefiningUnitRecipeBuilder.create(Items.GOLD_ORE, Items.GOLD_INGOT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit/gold_ore"));

        ShreddingUnitRecipeBuilder.create(Items.IRON_ORE, Items.RAW_IRON)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit/iron_ore"));

        FittingUnitRecipeBuilder.create(Items.IRON_INGOT, Items.IRON_NUGGET)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "fitting_unit/iron_ingot"));

        MouldingUnitRecipeBuilder.create(Items.IRON_NUGGET, Items.IRON_INGOT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit/iron_nugget"));

        PlantingUnitRecipeBuilder.create(Items.WHEAT_SEEDS, Items.WHEAT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit/wheat_seeds"));

        SeedPickingUnitRecipeBuilder.create(Items.WHEAT, Items.WHEAT_SEEDS)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit/wheat"));

        List<ItemCountInput> TEST = List.of(
                new ItemCountInput(Items.COAL_BLOCK ,5),
                new ItemCountInput(Items.OBSIDIAN, 10));
        GearingUnitRecipeBuilder.create(TEST, Items.DIAMOND)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "gearing_unit/test"));
    }
}
