package com.besson.endfield.recipe.builder;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.recipe.ItemCountInput;
import com.besson.endfield.recipe.custom.PackagingUnitRecipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class PackagingUnitRecipeBuilder {
    private final List<ItemCountInput> inputs;
    private final ItemConvertible output;
    private final int outputCount;

    private PackagingUnitRecipeBuilder(List<ItemCountInput> inputs, ItemConvertible output, int outputCount) {
        this.inputs = inputs;
        this.output = output;
        this.outputCount = outputCount;
    }

    public static PackagingUnitRecipeBuilder create(List<ItemCountInput> inputs, ItemConvertible output) {
        return new PackagingUnitRecipeBuilder(inputs, output, 1);
    }

    public static PackagingUnitRecipeBuilder create(List<ItemCountInput> inputs, ItemConvertible output, int outputCount) {
        return new PackagingUnitRecipeBuilder(inputs, output, outputCount);
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier id) {
        exporter.accept(new RecipeJsonProvider() {
            @Override
            public void serialize(JsonObject json) {
                json.addProperty("type", ArknightsEndfield.MOD_ID + ":packaging_unit");
                JsonArray inputsArray = new JsonArray();
                for (ItemCountInput input : inputs) {
                    JsonObject inputJson = new JsonObject();
                    inputJson.addProperty("item", Registries.ITEM.getId(input.getItemConvertible().asItem()).toString());
                    inputJson.addProperty("count", input.getCount());
                    inputsArray.add(inputJson);
                }
                json.add("input", inputsArray);

                JsonObject outputJson = new JsonObject();
                outputJson.addProperty("item", Registries.ITEM.getId(output.asItem()).toString());
                outputJson.addProperty("count", outputCount);
                json.add("output", outputJson);
            }

            @Override
            public Identifier getRecipeId() {
                return id;
            }

            @Override
            public RecipeSerializer<?> getSerializer() {
                return PackagingUnitRecipe.Serializer.INSTANCE;
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
