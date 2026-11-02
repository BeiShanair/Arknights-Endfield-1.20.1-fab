package com.besson.endfield.recipe.builder;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.recipe.custom.PlantingUnitRecipe;
import com.google.gson.JsonObject;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class PlantingUnitRecipeBuilder {
    private final ItemConvertible input;
    private final ItemConvertible output;
    private final int outputCount;
    private Identifier fluidId;
    private long fluidAmount;

    private PlantingUnitRecipeBuilder(ItemConvertible input, ItemConvertible output, int outputCount) {
        this.input = input;
        this.output = output;
        this.outputCount = outputCount;
    }

    public static PlantingUnitRecipeBuilder create(ItemConvertible input, ItemConvertible output) {
        return new PlantingUnitRecipeBuilder(input, output, 1);
    }

    public static PlantingUnitRecipeBuilder create(ItemConvertible input, ItemConvertible output, int outputCount) {
        return new PlantingUnitRecipeBuilder(input, output, outputCount);
    }

    /** 声明流体模式配方所需流体（如 1000 mB 水） */
    public PlantingUnitRecipeBuilder withFluid(Identifier fluidId, long amount) {
        this.fluidId = fluidId;
        this.fluidAmount = amount;
        return this;
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier id) {
        final Identifier fFluidId = this.fluidId;
        final long fFluidAmount = this.fluidAmount;
        exporter.accept(new RecipeJsonProvider() {
            @Override
            public void serialize(JsonObject json) {
                json.addProperty("type", ArknightsEndfield.MOD_ID + ":planting_unit");
                JsonObject inputJson = new JsonObject();
                inputJson.addProperty("item", Registries.ITEM.getId(input.asItem()).toString());
                json.add("input", inputJson);

                JsonObject outputJson = new JsonObject();
                outputJson.addProperty("item", Registries.ITEM.getId(output.asItem()).toString());
                outputJson.addProperty("count", outputCount);
                json.add("output", outputJson);

                if (fFluidId != null && fFluidAmount > 0) {
                    JsonObject fluidJson = new JsonObject();
                    fluidJson.addProperty("id", fFluidId.toString());
                    fluidJson.addProperty("amount", fFluidAmount);
                    json.add("fluid", fluidJson);
                }
            }

            @Override
            public Identifier getRecipeId() {
                return id;
            }

            @Override
            public RecipeSerializer<?> getSerializer() {
                return PlantingUnitRecipe.Serializer.INSTANCE;
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
