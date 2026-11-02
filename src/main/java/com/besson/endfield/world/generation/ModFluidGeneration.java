package com.besson.endfield.world.generation;

import com.besson.endfield.world.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModFluidGeneration {
    public static void registerFluidGeneration() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.LAKES, ModPlacedFeatures.LAKE_PRECIPITATION_ACID);
    }
}
