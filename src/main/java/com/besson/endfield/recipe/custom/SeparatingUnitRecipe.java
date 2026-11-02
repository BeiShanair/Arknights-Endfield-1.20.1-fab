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

public class SeparatingUnitRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final InputEntry input;
    private final ItemStack output;
    private final FluidVariant outputFluid;
    private final long outputFluidAmount;

    public SeparatingUnitRecipe(Identifier id, InputEntry input, ItemStack output,
                                FluidVariant outputFluid, long outputFluidAmount) {
        this.id = id;
        this.input = input;
        this.output = output;
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

    public static class Type implements RecipeType<SeparatingUnitRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "separating_unit";
    }

    public static class Serializer implements RecipeSerializer<SeparatingUnitRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "separating_unit";

        @Override
        public SeparatingUnitRecipe read(Identifier id, JsonObject json) {
            JsonObject ingredients = JsonHelper.getObject(json, "input");
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));

            Ingredient ingredient = Ingredient.fromJson(ingredients);
            int count = ingredients.has("count") ? JsonHelper.getInt(ingredients, "count") : 1;
            InputEntry inputs = new InputEntry(ingredient, count);

            FluidVariant outputFluid = FluidVariant.blank();
            long outputFluidAmount = 0;
            if (json.has("output_fluid")) {
                JsonObject fluidObj = JsonHelper.getObject(json, "output_fluid");
                Identifier fluidId = new Identifier(JsonHelper.getString(fluidObj, "id"));
                Fluid fluid = Registries.FLUID.get(fluidId);
                outputFluid = FluidVariant.of(fluid);
                outputFluidAmount = fluidObj.has("amount") ? JsonHelper.getLong(fluidObj, "amount") : 1000;
            }

            return new SeparatingUnitRecipe(id, inputs, output, outputFluid, outputFluidAmount);
        }

        @Override
        public SeparatingUnitRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient ingredient = Ingredient.fromPacket(buf);
            int count = buf.readInt();
            InputEntry inputs = new InputEntry(ingredient, count);
            ItemStack output = buf.readItemStack();

            boolean hasOutputFluid = buf.readBoolean();
            FluidVariant outputFluid = FluidVariant.blank();
            long outputFluidAmount = 0;
            if (hasOutputFluid) {
                Identifier fluidId = buf.readIdentifier();
                Fluid fluid = Registries.FLUID.get(fluidId);
                outputFluid = FluidVariant.of(fluid);
                outputFluidAmount = buf.readLong();
            }

            return new SeparatingUnitRecipe(id, inputs, output, outputFluid, outputFluidAmount);
        }

        @Override
        public void write(PacketByteBuf buf, SeparatingUnitRecipe recipe) {
            InputEntry entry = recipe.getInput();
            entry.ingredient().write(buf);
            buf.writeInt(entry.count());
            buf.writeItemStack(recipe.output);

            boolean hasOutputFluid = recipe.producesFluid();
            buf.writeBoolean(hasOutputFluid);
            if (hasOutputFluid) {
                buf.writeIdentifier(Registries.FLUID.getId(recipe.outputFluid.getFluid()));
                buf.writeLong(recipe.outputFluidAmount);
            }
        }
    }
}
