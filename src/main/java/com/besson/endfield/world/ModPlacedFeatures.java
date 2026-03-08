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
