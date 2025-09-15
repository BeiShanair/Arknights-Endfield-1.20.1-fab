package com.besson.endfield.recipe.builder;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.recipe.custom.GearingUnitRecipe;
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

public class GearingUnitRecipeBuilder {
    private final List<ItemConvertible> inputs;
    private final ItemConvertible output;
    private final int outputCount;

    private GearingUnitRecipeBuilder(List<ItemConvertible> inputs, ItemConvertible output, int outputCount) {
        this.inputs = inputs;
        this.output = output;
        this.outputCount = outputCount;
    }

    public static GearingUnitRecipeBuilder create(List<ItemConvertible> inputs, ItemConvertible output) {
        return new GearingUnitRecipeBuilder(inputs, output, 1);
    }

    public GearingUnitRecipeBuilder outputCount(int count) {
        return new GearingUnitRecipeBuilder(inputs, output, count);
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier id) {
        exporter.accept(new RecipeJsonProvider() {
            @Override
            public void serialize(JsonObject json) {
                json.addProperty("type", ArknightsEndfield.MOD_ID + ":gearing_unit");
                JsonArray inputsArray = new JsonArray();
                for (ItemConvertible input : inputs) {
                    JsonObject inputJson = new JsonObject();
                    inputJson.addProperty("item", Registries.ITEM.getId(input.asItem()).toString());
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
                return GearingUnitRecipe.Serializer.INSTANCE;
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
