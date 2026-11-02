package com.besson.endfield.recipe.custom;

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

public class PlantingUnitRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final Ingredient input;
    private final ItemStack output;
    
    private final FluidVariant requiredFluid;
    private final long requiredFluidAmount;

    public PlantingUnitRecipe(Identifier id, Ingredient input, ItemStack output) {
        this(id, input, output, FluidVariant.blank(), 0);
    }

    public PlantingUnitRecipe(Identifier id, Ingredient input, ItemStack output,
                              FluidVariant requiredFluid, long requiredFluidAmount) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.requiredFluid = requiredFluid;
        this.requiredFluidAmount = requiredFluidAmount;
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

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) return false;
        return input.test(inventory.getStack(0));
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
        ingredients.add(input);
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

    public Ingredient getInput() {
        return input;
    }

    public static class Type implements RecipeType<PlantingUnitRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "planting_unit";
    }

    public static class Serializer implements RecipeSerializer<PlantingUnitRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "planting_unit";

        @Override
        public PlantingUnitRecipe read(Identifier id, JsonObject json) {
            Ingredient input = Ingredient.fromJson(json.get("input"));
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            
            FluidVariant requiredFluid = FluidVariant.blank();
            long requiredFluidAmount = 0;
            if (json.has("fluid")) {
                JsonObject fluidObj = JsonHelper.getObject(json, "fluid");
                Identifier fluidId = new Identifier(JsonHelper.getString(fluidObj, "id"));
                Fluid fluid = Registries.FLUID.get(fluidId);
                requiredFluid = FluidVariant.of(fluid);
                requiredFluidAmount = fluidObj.has("amount") ? JsonHelper.getLong(fluidObj, "amount") : 1000;
            }

            return new PlantingUnitRecipe(id, input, output, requiredFluid, requiredFluidAmount);
        }

        @Override
        public PlantingUnitRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient input = Ingredient.fromPacket(buf);
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

            return new PlantingUnitRecipe(id, input, output, requiredFluid, requiredFluidAmount);
        }

        @Override
        public void write(PacketByteBuf buf, PlantingUnitRecipe recipe) {
            recipe.input.write(buf);
            buf.writeItemStack(recipe.output);

            boolean hasFluid = recipe.requiresFluid();
            buf.writeBoolean(hasFluid);
            if (hasFluid) {
                buf.writeIdentifier(Registries.FLUID.getId(recipe.requiredFluid.getFluid()));
                buf.writeLong(recipe.requiredFluidAmount);
            }
        }
    }
}
