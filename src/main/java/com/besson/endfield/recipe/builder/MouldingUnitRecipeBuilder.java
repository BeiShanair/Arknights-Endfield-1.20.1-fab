package com.besson.endfield.recipe.builder;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.recipe.ItemCountInput;
import com.besson.endfield.recipe.custom.FittingUnitRecipe;
import com.besson.endfield.recipe.custom.MouldingUnitRecipe;
import com.google.gson.JsonObject;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class MouldingUnitRecipeBuilder {
    private static ItemCountInput input;
    private static ItemConvertible output;
    private final int outputCount;

    private MouldingUnitRecipeBuilder(ItemCountInput input, ItemConvertible output, int outputCount) {
        this.input = input;
        this.output = output;
        this.outputCount = outputCount;
    }

    public static MouldingUnitRecipeBuilder create(ItemCountInput input, ItemConvertible output) {
        return new MouldingUnitRecipeBuilder(input, output, 1);
    }
    public static MouldingUnitRecipeBuilder create(ItemConvertible input, ItemConvertible output) {
        return new MouldingUnitRecipeBuilder(new ItemCountInput(input, 1), output, 1);
    }

    public static MouldingUnitRecipeBuilder create(ItemCountInput input, ItemConvertible output, int outputCount) {
        return new MouldingUnitRecipeBuilder(input, output, outputCount);
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier id) {
        exporter.accept(new RecipeJsonProvider() {
            @Override
            public void serialize(JsonObject json) {
                json.addProperty("type", ArknightsEndfield.MOD_ID + ":moulding_unit");
                JsonObject inputJson = new JsonObject();
                inputJson.addProperty("item", Registries.ITEM.getId(input.getItemConvertible().asItem()).toString());
                inputJson.addProperty("count", input.getCount());
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
                return MouldingUnitRecipe.Serializer.INSTANCE;
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
