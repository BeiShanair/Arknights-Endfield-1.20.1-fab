package com.besson.endfield.recipe.builder;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.recipe.custom.CrafterRecipe;
import com.google.gson.JsonObject;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CrafterRecipeBuilder {
    private final Map<ItemConvertible, Integer> inputs = new LinkedHashMap<>();
    private final ItemStack output;

    private CrafterRecipeBuilder(ItemStack output) {
        this.output = output;
    }

    public static CrafterRecipeBuilder create(ItemConvertible output, int outputCount) {
        return new CrafterRecipeBuilder(new ItemStack(output, outputCount));
    }

    public static CrafterRecipeBuilder create(ItemConvertible output) {
        return new CrafterRecipeBuilder(new ItemStack(output, 1));
    }

    public CrafterRecipeBuilder input(ItemConvertible item, int count) {
        inputs.put(item, count);
        return this;
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier id) {
        exporter.accept(new RecipeJsonProvider() {
            @Override
            public void serialize(JsonObject json) {
                json.addProperty("type", ArknightsEndfield.MOD_ID + ":crafter");
                JsonObject inputJson = new JsonObject();
                inputs.forEach((item, count) -> {
                    inputJson.addProperty(Registries.ITEM.getId(item.asItem()).toString(), count);
                });
                json.add("input", inputJson);

                JsonObject outputJson = new JsonObject();
                outputJson.addProperty("item", Registries.ITEM.getId(output.getItem()).toString());
                outputJson.addProperty("count", output.getCount());
                json.add("output", outputJson);
            }

            @Override
            public Identifier getRecipeId() {
                return id;
            }

            @Override
            public RecipeSerializer<?> getSerializer() {
                return CrafterRecipe.Serializer.INSTANCE;
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
