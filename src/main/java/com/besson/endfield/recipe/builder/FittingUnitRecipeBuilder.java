package com.besson.endfield.recipe.builder;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.recipe.custom.FittingUnitRecipe;
import com.google.gson.JsonObject;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class FittingUnitRecipeBuilder {
    private static ItemConvertible input;
    private static ItemConvertible output;
    private final int outputCount;

    private FittingUnitRecipeBuilder(ItemConvertible input, ItemConvertible output, int outputCount) {
        this.input = input;
        this.output = output;
        this.outputCount = outputCount;
    }

    public static FittingUnitRecipeBuilder create(ItemConvertible input, ItemConvertible output) {
        return new FittingUnitRecipeBuilder(input, output, 1);
    }

    public FittingUnitRecipeBuilder outputCount(int outputCount) {
        return new FittingUnitRecipeBuilder(input, output, outputCount);
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier id) {
        exporter.accept(new RecipeJsonProvider() {
            @Override
            public void serialize(JsonObject json) {
                json.addProperty("type", ArknightsEndfield.MOD_ID + ":shredding_unit");
                JsonObject inputJson = new JsonObject();
                inputJson.addProperty("item", Registries.ITEM.getId(input.asItem()).toString());
                json.add("input", inputJson);

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
                return FittingUnitRecipe.Serializer.INSTANCE;
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
