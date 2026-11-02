package com.besson.endfield.recipe.builder;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.recipe.ItemCountInput;
import com.besson.endfield.recipe.custom.ReactorCrucibleRecipe;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ReactorCrucibleRecipeBuilder {
    private final List<ItemCountInput> itemInputs = new ArrayList<>();
    private final List<FluidEntry> fluidInputs = new ArrayList<>();
    private final List<ItemCountInput> itemOutputs = new ArrayList<>();
    private final List<FluidEntry> fluidOutputs = new ArrayList<>();

    private ReactorCrucibleRecipeBuilder() {
    }

    public static ReactorCrucibleRecipeBuilder create() {
        return new ReactorCrucibleRecipeBuilder();
    }

    public static ReactorCrucibleRecipeBuilder create(ItemConvertible input, ItemConvertible output) {
        return create().input(input).output(output);
    }

    public ReactorCrucibleRecipeBuilder input(ItemConvertible item) {
        return input(item, 1);
    }

    public ReactorCrucibleRecipeBuilder input(ItemConvertible item, int count) {
        this.itemInputs.add(new ItemCountInput(item, count));
        return this;
    }

    public ReactorCrucibleRecipeBuilder input(ItemCountInput input) {
        this.itemInputs.add(input);
        return this;
    }

    public ReactorCrucibleRecipeBuilder fluidInput(Fluid fluid, long amount) {
        return fluidInput(Registries.FLUID.getId(fluid), amount);
    }

    public ReactorCrucibleRecipeBuilder fluidInput(Identifier fluidId, long amount) {
        this.fluidInputs.add(new FluidEntry(fluidId, amount));
        return this;
    }

    public ReactorCrucibleRecipeBuilder output(ItemConvertible item) {
        return output(item, 1);
    }

    public ReactorCrucibleRecipeBuilder output(ItemConvertible item, int count) {
        this.itemOutputs.add(new ItemCountInput(item, count));
        return this;
    }

    public ReactorCrucibleRecipeBuilder output(ItemCountInput output) {
        this.itemOutputs.add(output);
        return this;
    }

    public ReactorCrucibleRecipeBuilder fluidOutput(Fluid fluid, long amount) {
        return fluidOutput(Registries.FLUID.getId(fluid), amount);
    }

    public ReactorCrucibleRecipeBuilder fluidOutput(Identifier fluidId, long amount) {
        this.fluidOutputs.add(new FluidEntry(fluidId, amount));
        return this;
    }

    public void offerTo(Consumer<RecipeJsonProvider> exporter, Identifier id) {
        if (itemInputs.isEmpty() && fluidInputs.isEmpty()) {
            throw new IllegalStateException("Reactor crucible recipe " + id + " has no inputs");
        }
        if (itemOutputs.isEmpty() && fluidOutputs.isEmpty()) {
            throw new IllegalStateException("Reactor crucible recipe " + id + " has no outputs");
        }

        exporter.accept(new RecipeJsonProvider() {
            @Override
            public void serialize(JsonObject json) {
                json.addProperty("type", ArknightsEndfield.MOD_ID + ":reactor_crucible");
                json.add("input", serializeInputs());
                json.add("output", serializeOutputs());
            }

            @Override
            public Identifier getRecipeId() {
                return id;
            }

            @Override
            public RecipeSerializer<?> getSerializer() {
                return ReactorCrucibleRecipe.Serializer.INSTANCE;
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

    private JsonArray serializeInputs() {
        JsonArray inputs = new JsonArray();
        for (ItemCountInput input : itemInputs) {
            inputs.add(serializeItem(input));
        }
        for (FluidEntry input : fluidInputs) {
            inputs.add(serializeFluid(input));
        }
        return inputs;
    }

    private JsonArray serializeOutputs() {
        JsonArray outputs = new JsonArray();
        for (ItemCountInput output : itemOutputs) {
            outputs.add(serializeItem(output));
        }
        for (FluidEntry output : fluidOutputs) {
            outputs.add(serializeFluid(output));
        }
        return outputs;
    }

    private JsonObject serializeItem(ItemCountInput input) {
        JsonObject json = new JsonObject();
        json.addProperty("item", Registries.ITEM.getId(input.itemConvertible().asItem()).toString());
        json.addProperty("count", input.count());
        return json;
    }

    private JsonObject serializeFluid(FluidEntry input) {
        JsonObject json = new JsonObject();
        json.addProperty("fluid", input.fluidId().toString());
        json.addProperty("amount", input.amount());
        return json;
    }

    private record FluidEntry(Identifier fluidId, long amount) {
    }
}
