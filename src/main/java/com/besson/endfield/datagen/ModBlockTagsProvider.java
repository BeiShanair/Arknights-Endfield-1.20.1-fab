package com.besson.endfield.datagen;

import com.besson.endfield.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.COAL_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.COPPER_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.DIAMOND_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.EMERALD_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.GOLD_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.IRON_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.LAPIS_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.REDSTONE_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.AMETHYST_ORE_BLOCK)
                .add(ModBlocks.CUPRIUM_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.CUPRIUM_ORE_BLOCK)
                .add(ModBlocks.DEEPSLATE_AMETHYST_ORE)
                .add(ModBlocks.DEEPSLATE_FERRIUM_ORE)
                .add(ModBlocks.DEEPSLATE_CUPRIUM_ORE)
                .add(ModBlocks.DEEPSLATE_ORIGINIUM_ORE)
                .add(ModBlocks.FERRIUM_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.FERRIUM_ORE_BLOCK)
                .add(ModBlocks.ORIGINIUM_ORE_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.COAL_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.COPPER_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.DIAMOND_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.EMERALD_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.GOLD_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.IRON_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.LAPIS_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.REDSTONE_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.CUPRIUM_MINERAL_VEIN_BLOCK)
                .add(ModBlocks.FERRIUM_MINERAL_VEIN_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.ORIGINIUM_ORE_BLOCK)
                .add(ModBlocks.DEEPSLATE_ORIGINIUM_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.FERRIUM_ORE_BLOCK)
                .add(ModBlocks.DEEPSLATE_FERRIUM_ORE)
                .add(ModBlocks.AMETHYST_ORE_BLOCK)
                .add(ModBlocks.DEEPSLATE_AMETHYST_ORE)
                .add(ModBlocks.CUPRIUM_ORE_BLOCK)
                .add(ModBlocks.DEEPSLATE_CUPRIUM_ORE);
    }
}
