package com.besson.endfield.recipe.builder;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.recipe.ItemCountInput;
import com.besson.endfield.recipe.custom.SeparatingUnitRecipe;
import com.google.gson.JsonObject;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class SeparatingUnitRecipeBuilder {
    private final ItemCountInput input;
    private final ItemConvertible output;
    private int outputCount;
    private Identifier outputFluidId;
    private long outputFluidAmount;

    private SeparatingUnitRecipeBuilder(ItemCountInput input, ItemConvertible output, int outputCount) {
        this.input = input;
        this.output = output;
        this.outputCount = outputCount;
    }

    public static SeparatingUnitRecipeBuilder create(ItemCountInput input, ItemConvertible output) {
        return new SeparatingUnitRecipeBuilder(input, output, 1);
    }

    public static SeparatingUnitRecipeBuilder create(ItemConvertible input, ItemConvertible output) {
        return new SeparatingUnitRecipeBuilder(new ItemCountInput(input, 1), output, 1);
    }

    public SeparatingUnitRecipeBuilder withOutputCount(int count) {
        this.outputCount = count;
        return this;
    }

    public SeparatingUnitRecipeBuilder withOutputFluid(Identifier fluidId, long amount) {
        this.outputFluidId = fluidId;
        this.outputFluidAmount = amount;
        return this;
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier id) {
        final Identifier capturedOutputFluidId = this.outputFluidId;
        final long capturedOutputFluidAmount = this.outputFluidAmount;

        exporter.accept(new RecipeJsonProvider() {
            @Override
            public void serialize(JsonObject json) {
                json.addProperty("type", ArknightsEndfield.MOD_ID + ":separating_unit");
                JsonObject inputJson = new JsonObject();
                inputJson.addProperty("item", Registries.ITEM.getId(input.itemConvertible().asItem()).toString());
                inputJson.addProperty("count", input.count());
                json.add("input", inputJson);

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
                return SeparatingUnitRecipe.Serializer.INSTANCE;
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
