package com.besson.endfield.world;

import com.besson.endfield.ArknightsEndfield;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> AMETHYST_ORE_UPPER_PLACED_KEY =
            of("amethyst_ore_upper_placed");
    public static final RegistryKey<PlacedFeature> AMETHYST_ORE_MIDDLE_PLACED_KEY =
            of("amethyst_ore_middle_placed");
    public static final RegistryKey<PlacedFeature> AMETHYST_ORE_SMALL_PLACED_KEY =
            of("amethyst_ore_small_placed");
    public static final RegistryKey<PlacedFeature> AMETHYST_MINERAL_VEIN_PLACED_KEY =
            of("amethyst_mineral_vein_placed");
    public static final RegistryKey<PlacedFeature> ORIGINIUM_ORE_UPPER_PLACED_KEY =
            of("originum_ore_upper_placed");
    public static final RegistryKey<PlacedFeature> ORIGINIUM_ORE_MIDDLE_PLACED_KEY =
            of("originum_ore_middle_placed");
    public static final RegistryKey<PlacedFeature> ORIGINIUM_ORE_SMALL_PLACED_KEY =
            of("originum_ore_small_placed");
    public static final RegistryKey<PlacedFeature> ORIGINIUM_MINERAL_VEIN_PLACED_KEY =
            of("originum_mineral_vein_placed");
    public static final RegistryKey<PlacedFeature> FERRIUM_ORE_UPPER_PLACED_KEY =
            of("ferrium_ore_upper_placed");
    public static final RegistryKey<PlacedFeature> FERRIUM_ORE_MIDDLE_PLACED_KEY =
            of("ferrium_ore_middle_placed");
    public static final RegistryKey<PlacedFeature> FERRIUM_ORE_SMALL_PLACED_KEY =
            of("ferrium_ore_small_placed");
    public static final RegistryKey<PlacedFeature> FERRIUM_MINERAL_VEIN_PLACED_KEY =
            of("ferrium_mineral_vein_placed");
    public static final RegistryKey<PlacedFeature> CUPRIUM_ORE_UPPER_PLACED_KEY =
            of("cuprium_ore_upper_placed");
    public static final RegistryKey<PlacedFeature> CUPRIUM_ORE_MIDDLE_PLACED_KEY =
            of("cuprium_ore_middle_placed");
    public static final RegistryKey<PlacedFeature> CUPRIUM_ORE_SMALL_PLACED_KEY =
            of("cuprium_ore_small_placed");
    public static final RegistryKey<PlacedFeature> CUPRIUM_MINERAL_VEIN_PLACED_KEY =
            of("cuprium_mineral_vein_placed");
    public static final RegistryKey<PlacedFeature> COAL_MINERAL_VEIN_PLACED_KEY =
            of("coal_mineral_vein_placed");
    public static final RegistryKey<PlacedFeature> COPPER_MINERAL_VEIN_PLACED_KEY =
            of("copper_mineral_vein_placed");
    public static final RegistryKey<PlacedFeature> DIAMOND_MINERAL_VEIN_PLACED_KEY =
            of("diamond_mineral_vein_placed");
    public static final RegistryKey<PlacedFeature> EMERALD_MINERAL_VEIN_PLACED_KEY =
            of("emerald_mineral_vein_placed");
    public static final RegistryKey<PlacedFeature> GOLD_MINERAL_VEIN_PLACED_KEY =
            of("gold_mineral_vein_placed");
    public static final RegistryKey<PlacedFeature> IRON_MINERAL_VEIN_PLACED_KEY =
            of("iron_mineral_vein_placed");
    public static final RegistryKey<PlacedFeature> LAPIS_MINERAL_VEIN_PLACED_KEY =
            of("lapis_mineral_vein_placed");
    public static final RegistryKey<PlacedFeature> REDSTONE_MINERAL_VEIN_PLACED_KEY =
            of("redstone_mineral_vein_placed");

    public static final RegistryKey<PlacedFeature> AKETINE_PLACED_KEY = of("aketine_placed");
    public static final RegistryKey<PlacedFeature> AMBER_RICE_PLACED_KEY = of("amber_rice_placed");
    public static final RegistryKey<PlacedFeature> BUCKFLOWER_PLACED_KEY = of("buckflower_placed");
    public static final RegistryKey<PlacedFeature> CITROME_PLACED_KEY = of("citrome_placed");
    public static final RegistryKey<PlacedFeature> FIREBUCKLE_PLACED_KEY = of("firebuckle_placed");
    public static final RegistryKey<PlacedFeature> FLUFFED_JINCAO_PLACED_KEY = of("fluffed_jincao_placed");
    public static final RegistryKey<PlacedFeature> JINCAO_PLACED_KEY = of("jincao_placed");
    public static final RegistryKey<PlacedFeature> REDJADE_GINSENG_PLACED_KEY = of("redjade_ginseng_placed");
    public static final RegistryKey<PlacedFeature> REED_RYE_PLACED_KEY = of("reed_rye_placed");
    public static final RegistryKey<PlacedFeature> SANDLEAF_PLACED_KEY = of("sandleaf_placed");
    public static final RegistryKey<PlacedFeature> TARTPEPPER_PLACED_KEY = of("tartpepper_placed");
    public static final RegistryKey<PlacedFeature> THORNY_YAZHEN_PLACED_KEY = of("thorny_yazhen_placed");
    public static final RegistryKey<PlacedFeature> UMBRALINE_PLACED_KEY = of("umbraline_placed");
    public static final RegistryKey<PlacedFeature> YAZHEN_PLACED_KEY = of("yazhen_placed");

    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        PlacedFeatures.register(featureRegisterable, AKETINE_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.AKETINE),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, AMBER_RICE_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.AMBER_RICE),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, BUCKFLOWER_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.BUCKFLOWER),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, CITROME_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.CITROME),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, FIREBUCKLE_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.FIREBUCKLE),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, FLUFFED_JINCAO_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.FLUFFED_JINCAO),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, JINCAO_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.JINCAO),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, REDJADE_GINSENG_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.REDJADE_GINSENG),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, REED_RYE_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.REED_RYE),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, SANDLEAF_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.SANDLEAF),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, TARTPEPPER_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.TARTPEPPER),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, THORNY_YAZHEN_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.THORNY_YAZHEN),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, UMBRALINE_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.UMBRALINE),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, YAZHEN_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.YAZHEN),
                RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),
                PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

        PlacedFeatures.register(featureRegisterable, COAL_MINERAL_VEIN_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.COAL_MINERAL_VEIN_BLOCK_KEY),
                modifiersWithCount(6,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        PlacedFeatures.register(featureRegisterable, COPPER_MINERAL_VEIN_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.COPPER_MINERAL_VEIN_BLOCK_KEY),
                modifiersWithCount(6,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        PlacedFeatures.register(featureRegisterable, DIAMOND_MINERAL_VEIN_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.DIAMOND_MINERAL_VEIN_BLOCK_KEY),
                modifiersWithCount(6,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        PlacedFeatures.register(featureRegisterable, EMERALD_MINERAL_VEIN_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.EMERALD_MINERAL_VEIN_BLOCK_KEY),
                modifiersWithCount(6,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        PlacedFeatures.register(featureRegisterable, GOLD_MINERAL_VEIN_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.GOLD_MINERAL_VEIN_BLOCK_KEY),
                modifiersWithCount(6,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        PlacedFeatures.register(featureRegisterable, IRON_MINERAL_VEIN_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.IRON_MINERAL_VEIN_BLOCK_KEY),
                modifiersWithCount(6,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        PlacedFeatures.register(featureRegisterable, LAPIS_MINERAL_VEIN_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.LAPIS_MINERAL_VEIN_BLOCK_KEY),
                modifiersWithCount(6,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        PlacedFeatures.register(featureRegisterable, REDSTONE_MINERAL_VEIN_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.REDSTONE_MINERAL_VEIN_BLOCK_KEY),
                modifiersWithCount(6,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        PlacedFeatures.register(featureRegisterable, AMETHYST_ORE_UPPER_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.AMETHYST_ORE_BLOCK_KEY),
                modifiersWithCount(90,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(80), YOffset.fixed(384))));
        PlacedFeatures.register(
                featureRegisterable, AMETHYST_ORE_MIDDLE_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.AMETHYST_ORE_BLOCK_KEY),
                modifiersWithCount(40,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-24), YOffset.fixed(56))));
        PlacedFeatures.register(
                featureRegisterable, AMETHYST_ORE_SMALL_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.AMETHYST_ORE_BLOCK_SMALL_KEY),
                modifiersWithCount(40,
                        HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(72))));
        PlacedFeatures.register(
                featureRegisterable, AMETHYST_MINERAL_VEIN_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.AMETHYST_MINERAL_VEIN_BLOCK_KEY),
                modifiersWithCount(6,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        PlacedFeatures.register(featureRegisterable, ORIGINIUM_ORE_UPPER_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORIGINIUM_ORE_BLOCK_KEY),
                modifiersWithCount(90,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(80), YOffset.fixed(384))));
        PlacedFeatures.register(
                featureRegisterable, ORIGINIUM_ORE_MIDDLE_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORIGINIUM_ORE_BLOCK_KEY),
                modifiersWithCount(40,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-24), YOffset.fixed(56))));
        PlacedFeatures.register(
                featureRegisterable, ORIGINIUM_ORE_SMALL_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORIGINIUM_ORE_BLOCK_SMALL_KEY),
                modifiersWithCount(40,
                        HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(72))));
        PlacedFeatures.register(
                featureRegisterable, ORIGINIUM_MINERAL_VEIN_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.ORIGINIUM_MINERAL_VEIN_BLOCK_KEY),
                modifiersWithCount(6,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        PlacedFeatures.register(featureRegisterable, FERRIUM_ORE_UPPER_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.FERRIUM_ORE_BLOCK_KEY),
                modifiersWithCount(90,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(80), YOffset.fixed(384))));
        PlacedFeatures.register(
                featureRegisterable, FERRIUM_ORE_MIDDLE_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.FERRIUM_ORE_BLOCK_KEY),
                modifiersWithCount(40,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-24), YOffset.fixed(56))));
        PlacedFeatures.register(
                featureRegisterable, FERRIUM_ORE_SMALL_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.FERRIUM_ORE_BLOCK_SMALL_KEY),
                modifiersWithCount(40,
                        HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(72))));
        PlacedFeatures.register(
                featureRegisterable, FERRIUM_MINERAL_VEIN_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.FERRIUM_MINERAL_VEIN_BLOCK_KEY),
                modifiersWithCount(6,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
        PlacedFeatures.register(featureRegisterable, CUPRIUM_ORE_UPPER_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.CUPRIUM_ORE_BLOCK_KEY),
                modifiersWithCount(90,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(80), YOffset.fixed(384))));
        PlacedFeatures.register(
                featureRegisterable, CUPRIUM_ORE_MIDDLE_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.CUPRIUM_ORE_BLOCK_KEY),
                modifiersWithCount(40,
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-24), YOffset.fixed(56))));
        PlacedFeatures.register(
                featureRegisterable, CUPRIUM_ORE_SMALL_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.CUPRIUM_ORE_BLOCK_SMALL_KEY),
                modifiersWithCount(40,
                        HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(72))));
        PlacedFeatures.register(
                featureRegisterable, CUPRIUM_MINERAL_VEIN_PLACED_KEY,
                registryEntryLookup.getOrThrow(ModConfiguredFeatures.CUPRIUM_MINERAL_VEIN_BLOCK_KEY),
                modifiersWithCount(6,
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));
    }
    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }
    public static RegistryKey<PlacedFeature> of(String id) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(ArknightsEndfield.MOD_ID, id));
    }
}
