package com.besson.endfield.recipe;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.recipe.custom.PortableOriginiumRigRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerModRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(ArknightsEndfield.MOD_ID, PortableOriginiumRigRecipe.Serializer.ID),
                PortableOriginiumRigRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(ArknightsEndfield.MOD_ID, PortableOriginiumRigRecipe.Type.ID),
                PortableOriginiumRigRecipe.Type.INSTANCE);
    }
}
