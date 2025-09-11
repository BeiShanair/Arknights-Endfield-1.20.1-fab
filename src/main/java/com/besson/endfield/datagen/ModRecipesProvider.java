package com.besson.endfield.datagen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.recipe.builder.PortableOriginiumRigRecipeBuilder;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipesProvider extends FabricRecipeProvider {
    public ModRecipesProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> consumer) {
        PortableOriginiumRigRecipeBuilder.create(ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK, Items.AMETHYST_SHARD)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/amethyst_mineral_vein"));
        PortableOriginiumRigRecipeBuilder.create(ModBlocks.COAL_MINERAL_VEIN_BLOCK, Items.COAL)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/coal_mineral_vein"));
        PortableOriginiumRigRecipeBuilder.create(ModBlocks.COPPER_MINERAL_VEIN_BLOCK, Items.RAW_COPPER)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/copper_mineral_vein"));
        PortableOriginiumRigRecipeBuilder.create(ModBlocks.DIAMOND_MINERAL_VEIN_BLOCK, Items.DIAMOND)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/diamond_mineral_vein"));
        PortableOriginiumRigRecipeBuilder.create(ModBlocks.EMERALD_MINERAL_VEIN_BLOCK, Items.EMERALD)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/emerald_mineral_vein"));
        PortableOriginiumRigRecipeBuilder.create(ModBlocks.GOLD_MINERAL_VEIN_BLOCK, Items.RAW_GOLD)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/gold_mineral_vein"));
        PortableOriginiumRigRecipeBuilder.create(ModBlocks.IRON_MINERAL_VEIN_BLOCK, Items.RAW_IRON)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/iron_mineral_vein"));
        PortableOriginiumRigRecipeBuilder.create(ModBlocks.LAPIS_MINERAL_VEIN_BLOCK, Items.LAPIS_LAZULI)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/lapis_mineral_vein"));
        PortableOriginiumRigRecipeBuilder.create(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK, Items.IRON_INGOT)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/originium_mineral_vein"));
        PortableOriginiumRigRecipeBuilder.create(ModBlocks.REDSTONE_MINERAL_VEIN_BLOCK, Items.REDSTONE)
                .offerTo(consumer, new Identifier(ArknightsEndfield.MOD_ID, "rig/redstone_mineral_vein"));
    }
}
