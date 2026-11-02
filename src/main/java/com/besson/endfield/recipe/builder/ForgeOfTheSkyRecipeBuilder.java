package com.besson.endfield.recipe.builder;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.recipe.custom.ForgeOfTheSkyRecipe;
import com.google.gson.JsonObject;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class ForgeOfTheSkyRecipeBuilder {
    private final ItemConvertible input;
    private final int inputCount;
    private final ItemConvertible output;
    private final int outputCount;
    @Nullable
    private Fluid fluid;
    private long fluidAmount;
    @Nullable
    private Integer craftTime;

    private ForgeOfTheSkyRecipeBuilder(ItemConvertible input, int inputCount,
                                       ItemConvertible output, int outputCount) {
        this.input = input;
        this.inputCount = inputCount;
        this.output = output;
        this.outputCount = outputCount;
    }

    public static ForgeOfTheSkyRecipeBuilder create(ItemConvertible input, ItemConvertible output) {
        return new ForgeOfTheSkyRecipeBuilder(input, 1, output, 1);
    }

    public static ForgeOfTheSkyRecipeBuilder create(ItemConvertible input, int inputCount,
                                                    ItemConvertible output, int outputCount) {
        return new ForgeOfTheSkyRecipeBuilder(input, inputCount, output, outputCount);
    }

    public ForgeOfTheSkyRecipeBuilder fluid(Fluid fluid, long amount) {
        this.fluid = fluid;
        this.fluidAmount = amount;
        return this;
    }

    public ForgeOfTheSkyRecipeBuilder craftTime(int ticks) {
        this.craftTime = ticks;
        return this;
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier id) {
        exporter.accept(new RecipeJsonProvider() {
            @Override
            public void serialize(JsonObject json) {
                json.addProperty("type", ArknightsEndfield.MOD_ID + ":forge_of_the_sky");

                JsonObject inputJson = new JsonObject();
                inputJson.addProperty("item", Registries.ITEM.getId(input.asItem()).toString());
                inputJson.addProperty("count", inputCount);
                json.add("input", inputJson);

                JsonObject outputJson = new JsonObject();
                outputJson.addProperty("item", Registries.ITEM.getId(output.asItem()).toString());
                outputJson.addProperty("count", outputCount);
                json.add("output", outputJson);

                if (fluid != null) {
                    JsonObject fluidJson = new JsonObject();
                    fluidJson.addProperty("id", Registries.FLUID.getId(fluid).toString());
                    fluidJson.addProperty("amount", fluidAmount);
                    json.add("fluid", fluidJson);
                }

                if (craftTime != null) {
                    json.addProperty("craft_time", craftTime);
                }
            }

            @Override
            public Identifier getRecipeId() {
                return id;
            }

            @Override
            public RecipeSerializer<?> getSerializer() {
                return ForgeOfTheSkyRecipe.Serializer.INSTANCE;
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
