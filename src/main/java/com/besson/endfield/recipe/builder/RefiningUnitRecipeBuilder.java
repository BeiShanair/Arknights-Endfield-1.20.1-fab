package com.besson.endfield.recipe.builder;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.recipe.ItemCountInput;
import com.besson.endfield.recipe.custom.RefiningUnitRecipe;
import com.google.gson.JsonObject;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class RefiningUnitRecipeBuilder {
    private final ItemCountInput input;
    private final ItemConvertible output;
    private final int outputCount;
    private Identifier fluidId;
    private long fluidAmount;
    private Identifier outputFluidId;
    private long outputFluidAmount;

    private RefiningUnitRecipeBuilder(ItemCountInput input, ItemConvertible output, int outputCount) {
        this.input = input;
        this.output = output;
        this.outputCount = outputCount;
    }

    public static RefiningUnitRecipeBuilder create(ItemCountInput input, ItemConvertible output) {
        return new RefiningUnitRecipeBuilder(input, output, 1);
    }

    public static RefiningUnitRecipeBuilder create(ItemCountInput input, ItemConvertible output, int outputCount) {
        return new RefiningUnitRecipeBuilder(input, output, outputCount);
    }

    public static RefiningUnitRecipeBuilder create(ItemConvertible input, ItemConvertible output) {
        return new RefiningUnitRecipeBuilder(new ItemCountInput(input, 1), output, 1);
    }
    
    public static RefiningUnitRecipeBuilder create(ItemConvertible input, ItemConvertible output, int outputCount) {
        return new RefiningUnitRecipeBuilder(new ItemCountInput(input, 1), output, outputCount);
    }

    public RefiningUnitRecipeBuilder withFluid(Identifier fluidId, long amount) {
        this.fluidId = fluidId;
        this.fluidAmount = amount;
        return this;
    }

    public RefiningUnitRecipeBuilder withOutputFluid(Identifier fluidId, long amount) {
        this.outputFluidId = fluidId;
        this.outputFluidAmount = amount;
        return this;
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier id) {
        final Identifier capturedFluidId = this.fluidId;
        final long capturedFluidAmount = this.fluidAmount;
        final Identifier capturedOutputFluidId = this.outputFluidId;
        final long capturedOutputFluidAmount = this.outputFluidAmount;

        exporter.accept(new RecipeJsonProvider() {
            @Override
            public void serialize(JsonObject json) {
                json.addProperty("type", ArknightsEndfield.MOD_ID + ":refining_unit");
                JsonObject inputJson = new JsonObject();
                inputJson.addProperty("item", Registries.ITEM.getId(input.itemConvertible().asItem()).toString());
                inputJson.addProperty("count", input.count());
                json.add("input", inputJson);

                if (capturedFluidId != null && capturedFluidAmount > 0) {
                    JsonObject fluidJson = new JsonObject();
                    fluidJson.addProperty("id", capturedFluidId.toString());
                    fluidJson.addProperty("amount", capturedFluidAmount);
                    json.add("fluid", fluidJson);
                }

                if (capturedOutputFluidId != null && capturedOutputFluidAmount > 0) {
                    JsonObject outputFluidJson = new JsonObject();
                    outputFluidJson.addProperty("id", capturedOutputFluidId.toString());
                    outputFluidJson.addProperty("amount", capturedOutputFluidAmount);
                    json.add("output_fluid", outputFluidJson);
                }

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
                return RefiningUnitRecipe.Serializer.INSTANCE;
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
