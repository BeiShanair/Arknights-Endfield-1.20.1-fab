package com.besson.endfield.recipe;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.recipe.custom.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerModRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(ArknightsEndfield.MOD_ID, OreRigRecipe.Serializer.ID),
                OreRigRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(ArknightsEndfield.MOD_ID, OreRigRecipe.Type.ID),
                OreRigRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(ArknightsEndfield.MOD_ID, RefiningUnitRecipe.Serializer.ID),
                RefiningUnitRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(ArknightsEndfield.MOD_ID, RefiningUnitRecipe.Type.ID),
                RefiningUnitRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(ArknightsEndfield.MOD_ID, GearingUnitRecipe.Serializer.ID),
                GearingUnitRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(ArknightsEndfield.MOD_ID, GearingUnitRecipe.Type.ID),
                GearingUnitRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(ArknightsEndfield.MOD_ID, ShreddingUnitRecipe.Serializer.ID),
                ShreddingUnitRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(ArknightsEndfield.MOD_ID, ShreddingUnitRecipe.Type.ID),
                ShreddingUnitRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(ArknightsEndfield.MOD_ID, FittingUnitRecipe.Serializer.ID),
                FittingUnitRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(ArknightsEndfield.MOD_ID, FittingUnitRecipe.Type.ID),
                FittingUnitRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(ArknightsEndfield.MOD_ID, MouldingUnitRecipe.Serializer.ID),
                MouldingUnitRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(ArknightsEndfield.MOD_ID, MouldingUnitRecipe.Type.ID),
                MouldingUnitRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(ArknightsEndfield.MOD_ID, PlantingUnitRecipe.Serializer.ID),
                PlantingUnitRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(ArknightsEndfield.MOD_ID, PlantingUnitRecipe.Type.ID),
                PlantingUnitRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(ArknightsEndfield.MOD_ID, SeedPickingUnitRecipe.Serializer.ID),
                SeedPickingUnitRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(ArknightsEndfield.MOD_ID, SeedPickingUnitRecipe.Type.ID),
                SeedPickingUnitRecipe.Type.INSTANCE);
    }
}
