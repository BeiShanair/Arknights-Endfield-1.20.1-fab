package com.besson.endfield.recipe.builder;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.recipe.custom.OreRigRecipe;
import com.google.gson.JsonObject;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class OreRigRecipeBuilder {
    private final ItemConvertible input;
    private final ItemConvertible output;
    private final int outputCount;
    private final int tier;

    private OreRigRecipeBuilder(ItemConvertible input, ItemConvertible output, int outputCount, int tier) {
        this.input = input;
        this.output = output;
        this.outputCount = outputCount;
        this.tier = tier;
    }

    public static OreRigRecipeBuilder create(ItemConvertible input, ItemConvertible output, int tier) {
        return new OreRigRecipeBuilder(input, output, 1, tier);
    }

    public static OreRigRecipeBuilder create(ItemConvertible input, ItemConvertible output, int outputCount, int tier) {
        return new OreRigRecipeBuilder(input, output, outputCount, tier);
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier id) {
        exporter.accept(new RecipeJsonProvider() {
            @Override
            public void serialize(JsonObject json) {
                json.addProperty("type", ArknightsEndfield.MOD_ID + ":ore_rig");
                JsonObject inputJson = new JsonObject();
                inputJson.addProperty("item", Registries.ITEM.getId(input.asItem()).toString());
                json.add("input", inputJson);

                JsonObject outputJson = new JsonObject();
                outputJson.addProperty("item", Registries.ITEM.getId(output.asItem()).toString());
                outputJson.addProperty("count", outputCount);
                json.add("output", outputJson);

                json.addProperty("tier", tier);
            }

            @Override
            public Identifier getRecipeId() {
                return id;
            }

            @Override
            public RecipeSerializer<?> getSerializer() {
                return OreRigRecipe.Serializer.INSTANCE;
            }

            @Override
            public @Nullable JsonObject toAdvancementJson() {
                return null;
            }

            @Override
            public @Nullable Identifier getAdvancementId() {
                return null;
            }
        });
    }
}
