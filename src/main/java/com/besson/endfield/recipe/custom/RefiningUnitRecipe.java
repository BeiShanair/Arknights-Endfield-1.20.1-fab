package com.besson.endfield.recipe.custom;

import com.besson.endfield.recipe.InputEntry;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class RefiningUnitRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final InputEntry input;
    private final ItemStack output;
    private final FluidVariant requiredFluid;
    private final long requiredFluidAmount;
    private final FluidVariant outputFluid;
    private final long outputFluidAmount;

    public RefiningUnitRecipe(Identifier id, InputEntry input, ItemStack output,
                              FluidVariant requiredFluid, long requiredFluidAmount,
                              FluidVariant outputFluid, long outputFluidAmount) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.requiredFluid = requiredFluid;
        this.requiredFluidAmount = requiredFluidAmount;
        this.outputFluid = outputFluid;
        this.outputFluidAmount = outputFluidAmount;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) return false;
        ItemStack inputs = inventory.getStack(0);
        return input.ingredient().test(inputs);
    }

    public InputEntry getInput() {
        return input;
    }

    public FluidVariant getRequiredFluid() {
        return requiredFluid;
    }

    public long getRequiredFluidAmount() {
        return requiredFluidAmount;
    }

    public boolean requiresFluid() {
        return !requiredFluid.isBlank() && requiredFluidAmount > 0;
    }

    public FluidVariant getOutputFluid() {
        return outputFluid;
    }

    public long getOutputFluidAmount() {
        return outputFluidAmount;
    }

    public boolean producesFluid() {
        return !outputFluid.isBlank() && outputFluidAmount > 0;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> ingredients = DefaultedList.of();
        ingredients.add(input.ingredient());
        return ingredients;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    public static class Type implements RecipeType<RefiningUnitRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "refining_unit";
    }

    public static class Serializer implements RecipeSerializer<RefiningUnitRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "refining_unit";

        @Override
        public RefiningUnitRecipe read(Identifier id, JsonObject json) {
            JsonObject ingredients = JsonHelper.getObject(json, "input");
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            InputEntry inputs;

            Ingredient ingredient = Ingredient.fromJson(ingredients);
            int count = ingredients.has("count") ? JsonHelper.getInt(ingredients, "count") : 1;
            inputs = new InputEntry(ingredient, count);

            FluidVariant requiredFluid = FluidVariant.blank();
            long requiredFluidAmount = 0;
            if (json.has("fluid")) {
                JsonObject fluidObj = JsonHelper.getObject(json, "fluid");
                Identifier fluidId = new Identifier(JsonHelper.getString(fluidObj, "id"));
                Fluid fluid = Registries.FLUID.get(fluidId);
                requiredFluid = FluidVariant.of(fluid);
                requiredFluidAmount = fluidObj.has("amount") ? JsonHelper.getLong(fluidObj, "amount") : 1000;
            }

            FluidVariant outputFluid = FluidVariant.blank();
            long outputFluidAmount = 0;
            if (json.has("output_fluid")) {
                JsonObject fluidObj = JsonHelper.getObject(json, "output_fluid");
                Identifier fluidId = new Identifier(JsonHelper.getString(fluidObj, "id"));
                Fluid fluid = Registries.FLUID.get(fluidId);
                outputFluid = FluidVariant.of(fluid);
                outputFluidAmount = fluidObj.has("amount") ? JsonHelper.getLong(fluidObj, "amount") : 1000;
            }

            return new RefiningUnitRecipe(id, inputs, output, requiredFluid, requiredFluidAmount, outputFluid, outputFluidAmount);
        }

        @Override
        public RefiningUnitRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient ingredient = Ingredient.fromPacket(buf);
            int count = buf.readInt();
            InputEntry inputs = new InputEntry(ingredient, count);
            ItemStack output = buf.readItemStack();

            boolean hasFluid = buf.readBoolean();
            FluidVariant requiredFluid = FluidVariant.blank();
            long requiredFluidAmount = 0;
            if (hasFluid) {
                Identifier fluidId = buf.readIdentifier();
                Fluid fluid = Registries.FLUID.get(fluidId);
                requiredFluid = FluidVariant.of(fluid);
                requiredFluidAmount = buf.readLong();
            }

            boolean hasOutputFluid = buf.readBoolean();
            FluidVariant outputFluid = FluidVariant.blank();
            long outputFluidAmount = 0;
            if (hasOutputFluid) {
                Identifier fluidId = buf.readIdentifier();
                Fluid fluid = Registries.FLUID.get(fluidId);
                outputFluid = FluidVariant.of(fluid);
                outputFluidAmount = buf.readLong();
            }

            return new RefiningUnitRecipe(id, inputs, output, requiredFluid, requiredFluidAmount, outputFluid, outputFluidAmount);
        }

        @Override
        public void write(PacketByteBuf buf, RefiningUnitRecipe recipe) {
            InputEntry entry = recipe.getInput();
            entry.ingredient().write(buf);
            buf.writeInt(entry.count());
            buf.writeItemStack(recipe.output);

            boolean hasFluid = recipe.requiresFluid();
            buf.writeBoolean(hasFluid);
            if (hasFluid) {
                buf.writeIdentifier(Registries.FLUID.getId(recipe.requiredFluid.getFluid()));
                buf.writeLong(recipe.requiredFluidAmount);
            }

            boolean hasOutputFluid = recipe.producesFluid();
            buf.writeBoolean(hasOutputFluid);
            if (hasOutputFluid) {
                buf.writeIdentifier(Registries.FLUID.getId(recipe.outputFluid.getFluid()));
                buf.writeLong(recipe.outputFluidAmount);
            }
        }
    }
}
