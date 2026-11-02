package com.besson.endfield.recipe.custom;

import com.besson.endfield.recipe.InputEntry;
import com.google.gson.JsonArray;
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

import java.util.ArrayList;
import java.util.List;

public class FillingUnitRecipe implements Recipe<SimpleInventory> {
    public static final int FLUID_PROCESSING_TIME = 40;
    public static final int DEFAULT_PROCESSING_TIME = 200;

    private final Identifier id;
    private final DefaultedList<InputEntry> input;
    private final ItemStack output;
    private final FluidVariant requiredFluid;
    private final long requiredFluidAmount;

    public FillingUnitRecipe(Identifier id, DefaultedList<InputEntry> input, ItemStack output) {
        this(id, input, output, FluidVariant.blank(), 0);
    }

    public FillingUnitRecipe(Identifier id, DefaultedList<InputEntry> input, ItemStack output,
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
    
    public int getProcessingTime() {
        return requiresFluid() ? FLUID_PROCESSING_TIME : DEFAULT_PROCESSING_TIME;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) return false;

        List<ItemStack> inputs = new ArrayList<>();
        for (int i = 0; i < inventory.size(); i++) {
            inputs.add(inventory.getStack(i));
        }

        for (InputEntry inputEntry : input) {
            boolean matched = false;
            for (ItemStack stack : inputs) {
                if (inputEntry.ingredient().test(stack)) {
                    matched = true;
                    break;
                }
            }
            if (!matched) return false;
        }
        return true;
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

    public DefaultedList<InputEntry> getInput() {
        return input;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> ingredients = DefaultedList.of();
        for (InputEntry entry : input) {
            ingredients.add(entry.ingredient());
        }
        return ingredients;
    }

    public static class Type implements RecipeType<FillingUnitRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "filling_unit";
    }

    public static class Serializer implements RecipeSerializer<FillingUnitRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "filling_unit";

        @Override
        public FillingUnitRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            JsonArray ingredients = JsonHelper.getArray(json, "input");
            DefaultedList<InputEntry> inputs = DefaultedList.ofSize(ingredients.size(), InputEntry.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                JsonObject obj = ingredients.get(i).getAsJsonObject();
                Ingredient ingredient = Ingredient.fromJson(obj);
                int count = obj.has("count") ? obj.get("count").getAsInt() : 1;
                inputs.set(i, new InputEntry(ingredient, count));
            }
            
            FluidVariant requiredFluid = FluidVariant.blank();
            long requiredFluidAmount = 0;
            if (json.has("fluid")) {
                JsonObject fluidObj = JsonHelper.getObject(json, "fluid");
                Identifier fluidId = new Identifier(JsonHelper.getString(fluidObj, "id"));
                Fluid fluid = Registries.FLUID.get(fluidId);
                requiredFluid = FluidVariant.of(fluid);
                requiredFluidAmount = fluidObj.has("amount") ? JsonHelper.getLong(fluidObj, "amount") : 1000;
            }

            return new FillingUnitRecipe(id, inputs, output, requiredFluid, requiredFluidAmount);
        }

        @Override
        public FillingUnitRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<InputEntry> inputs = DefaultedList.ofSize(buf.readInt(), InputEntry.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                Ingredient ingredient = Ingredient.fromPacket(buf);
                int count = buf.readInt();
                inputs.set(i, new InputEntry(ingredient, count));
            }
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

            return new FillingUnitRecipe(id, inputs, output, requiredFluid, requiredFluidAmount);
        }

        @Override
        public void write(PacketByteBuf buf, FillingUnitRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (InputEntry entry: recipe.input) {
                entry.ingredient().write(buf);
                buf.writeInt(entry.count());
            }
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
