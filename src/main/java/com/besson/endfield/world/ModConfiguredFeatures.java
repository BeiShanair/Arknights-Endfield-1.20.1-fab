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
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(ArknightsEndfield.MOD_ID, id));
    }
}
