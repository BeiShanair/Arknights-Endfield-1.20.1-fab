package com.besson.endfield.world;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> AMETHYST_MINERAL_VEIN_BLOCK_KEY =
            of("amethyst_mineral_vein");
    public static final RegistryKey<ConfiguredFeature<?, ?>> AMETHYST_ORE_BLOCK_KEY =
            of("amethyst_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> AMETHYST_ORE_BLOCK_SMALL_KEY =
            of("amethyst_ore_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORIGINIUM_ORE_BLOCK_KEY =
            of("originum_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORIGINIUM_ORE_BLOCK_SMALL_KEY =
            of("originum_ore_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORIGINIUM_MINERAL_VEIN_BLOCK_KEY =
            of("originium_mineral_vein");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FERRIUM_ORE_BLOCK_KEY =
            of("ferrium_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FERRIUM_ORE_BLOCK_SMALL_KEY =
            of("ferrium_ore_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FERRIUM_MINERAL_VEIN_BLOCK_KEY =
            of("ferrium_mineral_vein");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CUPRIUM_ORE_BLOCK_KEY =
            of("cuprium_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CUPRIUM_ORE_BLOCK_SMALL_KEY =
            of("cuprium_ore_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CUPRIUM_MINERAL_VEIN_BLOCK_KEY =
            of("cuprium_mineral_vein");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COAL_MINERAL_VEIN_BLOCK_KEY =
            of("coal_mineral_vein");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COPPER_MINERAL_VEIN_BLOCK_KEY =
            of("copper_mineral_vein");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DIAMOND_MINERAL_VEIN_BLOCK_KEY =
            of("diamond_mineral_vein");
    public static final RegistryKey<ConfiguredFeature<?, ?>> EMERALD_MINERAL_VEIN_BLOCK_KEY =
            of("emerald_mineral_vein");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GOLD_MINERAL_VEIN_BLOCK_KEY =
            of("gold_mineral_vein");
    public static final RegistryKey<ConfiguredFeature<?, ?>> IRON_MINERAL_VEIN_BLOCK_KEY =
            of("iron_mineral_vein");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LAPIS_MINERAL_VEIN_BLOCK_KEY =
            of("lapis_mineral_vein");
    public static final RegistryKey<ConfiguredFeature<?, ?>> REDSTONE_MINERAL_VEIN_BLOCK_KEY =
            of("redstone_mineral_vein");

    public static final RegistryKey<ConfiguredFeature<?, ?>> AKETINE = of("aketine");
    public static final RegistryKey<ConfiguredFeature<?, ?>> AMBER_RICE = of("amber_rice");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BUCKFLOWER = of("buckflower");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CITROME = of("citrome");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FIREBUCKLE = of("firebuckle");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FLUFFED_JINCAO = of("fluffed_jincao");
    public static final RegistryKey<ConfiguredFeature<?, ?>> JINCAO = of("jincao");
    public static final RegistryKey<ConfiguredFeature<?, ?>> REDJADE_GINSENG = of("redjade_ginseng");
    public static final RegistryKey<ConfiguredFeature<?, ?>> REED_RYE = of("reed_rye");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SANDLEAF = of("sandleaf");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TARTPEPPER = of("tartpepper");
    public static final RegistryKey<ConfiguredFeature<?, ?>> THORNY_YAZHEN = of("thorny_yazhen");
    public static final RegistryKey<ConfiguredFeature<?, ?>> UMBRALINE = of("umbraline");
    public static final RegistryKey<ConfiguredFeature<?, ?>> YAZHEN = of("yazhen");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        ConfiguredFeatures.register(featureRegisterable, AKETINE, Feature.FLOWER,
                new RandomPatchFeatureConfig(10, 4, 3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.AKETINE_BLOCK)))));
        ConfiguredFeatures.register(featureRegisterable, AMBER_RICE, Feature.FLOWER,
                new RandomPatchFeatureConfig(10, 4, 3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.AMBER_RICE_BLOCK)))));
        ConfiguredFeatures.register(featureRegisterable, BUCKFLOWER, Feature.FLOWER,
                new RandomPatchFeatureConfig(15, 4, 3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.BUCKFLOWER_BLOCK)))));
        ConfiguredFeatures.register(featureRegisterable, CITROME, Feature.FLOWER,
                new RandomPatchFeatureConfig(15, 4, 3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.CITROME_BLOCK)))));
        ConfiguredFeatures.register(featureRegisterable, FIREBUCKLE, Feature.FLOWER,
                new RandomPatchFeatureConfig(5, 4, 3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.FIREBUCKLE_BLOCK)))));
        ConfiguredFeatures.register(featureRegisterable, FLUFFED_JINCAO, Feature.FLOWER,
                new RandomPatchFeatureConfig(5, 4, 3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.FLUFFED_JINCAO_BLOCK)))));
        ConfiguredFeatures.register(featureRegisterable, JINCAO, Feature.FLOWER,
                new RandomPatchFeatureConfig(15, 4, 3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.JINCAO_BLOCK)))));
        ConfiguredFeatures.register(featureRegisterable, REDJADE_GINSENG, Feature.FLOWER,
                new RandomPatchFeatureConfig(10, 4, 3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.REDJADE_GINSENG_BLOCK)))));
        ConfiguredFeatures.register(featureRegisterable, REED_RYE, Feature.FLOWER,
                new RandomPatchFeatureConfig(10, 4, 3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.REED_RYE_BLOCK)))));
        ConfiguredFeatures.register(featureRegisterable, SANDLEAF, Feature.FLOWER,
                new RandomPatchFeatureConfig(10, 4, 3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.SANDLEAF_BLOCK)))));
        ConfiguredFeatures.register(featureRegisterable, TARTPEPPER, Feature.FLOWER,
                new RandomPatchFeatureConfig(10, 4, 3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.TARTPEPPER_BLOCK)))));
        ConfiguredFeatures.register(featureRegisterable, THORNY_YAZHEN, Feature.FLOWER,
                new RandomPatchFeatureConfig(5, 4, 3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.THORNY_YAZHEN_BLOCK)))));
        ConfiguredFeatures.register(featureRegisterable, UMBRALINE, Feature.FLOWER,
                new RandomPatchFeatureConfig(5, 4, 3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.UMBRALINE_BLOCK)))));
        ConfiguredFeatures.register(featureRegisterable, YAZHEN, Feature.FLOWER,
                new RandomPatchFeatureConfig(15, 4, 3,
                        PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                                new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.YAZHEN_BLOCK)))));

        RuleTest stoneReplace = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplace = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> coalMineralTargets = List.of(
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.COAL_MINERAL_VEIN_BLOCK.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, COAL_MINERAL_VEIN_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(coalMineralTargets, 2));

        List<OreFeatureConfig.Target> copperMineralTargets = List.of(
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.COPPER_MINERAL_VEIN_BLOCK.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, COPPER_MINERAL_VEIN_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(copperMineralTargets, 2));

        List<OreFeatureConfig.Target> diamondMineralTargets = List.of(
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.DIAMOND_MINERAL_VEIN_BLOCK.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, DIAMOND_MINERAL_VEIN_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(diamondMineralTargets, 1));

        List<OreFeatureConfig.Target> emeraldMineralTargets = List.of(
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.EMERALD_MINERAL_VEIN_BLOCK.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, EMERALD_MINERAL_VEIN_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(emeraldMineralTargets, 1));

        List<OreFeatureConfig.Target> goldMineralTargets = List.of(
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.GOLD_MINERAL_VEIN_BLOCK.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, GOLD_MINERAL_VEIN_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(goldMineralTargets, 2));

        List<OreFeatureConfig.Target> ironMineralTargets = List.of(
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.IRON_MINERAL_VEIN_BLOCK.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, IRON_MINERAL_VEIN_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(ironMineralTargets, 3));

        List<OreFeatureConfig.Target> lapisMineralTargets = List.of(
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.LAPIS_MINERAL_VEIN_BLOCK.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, LAPIS_MINERAL_VEIN_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(lapisMineralTargets, 1));

        List<OreFeatureConfig.Target> redstoneMineralTargets = List.of(
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.REDSTONE_MINERAL_VEIN_BLOCK.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, REDSTONE_MINERAL_VEIN_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(redstoneMineralTargets, 2));

        List<OreFeatureConfig.Target> overWorldTargets = List.of(
                OreFeatureConfig.createTarget(stoneReplace, ModBlocks.AMETHYST_ORE_BLOCK.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.DEEPSLATE_AMETHYST_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> amethystMineralTargets = List.of(
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, AMETHYST_ORE_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(overWorldTargets, 9));
        ConfiguredFeatures.register(featureRegisterable, AMETHYST_ORE_BLOCK_SMALL_KEY, Feature.ORE,
                new OreFeatureConfig(overWorldTargets, 4));
        ConfiguredFeatures.register(featureRegisterable, AMETHYST_MINERAL_VEIN_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(amethystMineralTargets, 4));

        List<OreFeatureConfig.Target> originumOverWorldTargets = List.of(
                OreFeatureConfig.createTarget(stoneReplace, ModBlocks.ORIGINIUM_ORE_BLOCK.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.DEEPSLATE_ORIGINIUM_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> originumMineralTargets = List.of(
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, ORIGINIUM_ORE_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(originumOverWorldTargets, 9));
        ConfiguredFeatures.register(featureRegisterable, ORIGINIUM_ORE_BLOCK_SMALL_KEY, Feature.ORE,
                new OreFeatureConfig(originumOverWorldTargets, 4));
        ConfiguredFeatures.register(featureRegisterable, ORIGINIUM_MINERAL_VEIN_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(originumMineralTargets, 4));

        List<OreFeatureConfig.Target> ferriumOverWorldTargets = List.of(
                OreFeatureConfig.createTarget(stoneReplace, ModBlocks.FERRIUM_ORE_BLOCK.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.DEEPSLATE_FERRIUM_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> ferriumMineralTargets = List.of(
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.FERRIUM_MINERAL_VEIN_BLOCK.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, FERRIUM_ORE_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(ferriumOverWorldTargets, 9));
        ConfiguredFeatures.register(featureRegisterable, FERRIUM_ORE_BLOCK_SMALL_KEY, Feature.ORE,
                new OreFeatureConfig(ferriumOverWorldTargets, 4));
        ConfiguredFeatures.register(featureRegisterable, FERRIUM_MINERAL_VEIN_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(ferriumMineralTargets, 4));

        List<OreFeatureConfig.Target> cupriumOverWorldTargets = List.of(
                OreFeatureConfig.createTarget(stoneReplace, ModBlocks.CUPRIUM_ORE_BLOCK.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.DEEPSLATE_CUPRIUM_ORE.getDefaultState()));
        List<OreFeatureConfig.Target> cupriumMineralTargets = List.of(
                OreFeatureConfig.createTarget(deepslateReplace, ModBlocks.CUPRIUM_MINERAL_VEIN_BLOCK.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, CUPRIUM_ORE_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(cupriumOverWorldTargets, 9));
        ConfiguredFeatures.register(featureRegisterable, CUPRIUM_ORE_BLOCK_SMALL_KEY, Feature.ORE,
                new OreFeatureConfig(cupriumOverWorldTargets, 4));
        ConfiguredFeatures.register(featureRegisterable, CUPRIUM_MINERAL_VEIN_BLOCK_KEY, Feature.ORE,
                new OreFeatureConfig(cupriumMineralTargets, 4));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(ArknightsEndfield.MOD_ID, id));
    }
}
