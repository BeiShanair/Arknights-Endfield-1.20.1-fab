package com.besson.endfield.recipe.custom;

import com.besson.endfield.recipe.InputEntry;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class GrindingUnitRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final DefaultedList<InputEntry> input;
    private final ItemStack output;

    public GrindingUnitRecipe(Identifier id, DefaultedList<InputEntry> input, ItemStack output) {
        this.id = id;
        this.input = input;
        this.output = output;
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
                if (inputEntry.getIngredient().test(stack)) {
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
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> ingredients = DefaultedList.of();
        for (InputEntry entry : input) {
            ingredients.add(entry.getIngredient());
        }
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

    public DefaultedList<InputEntry> getInput() {
        return input;
    }

    public static class Type implements RecipeType<GrindingUnitRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "grinding_unit";
    }

    public static class Serializer implements RecipeSerializer<GrindingUnitRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "grinding_unit";

        @Override
        public GrindingUnitRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            JsonArray ingredients = JsonHelper.getArray(json, "input");
            DefaultedList<InputEntry> inputs = DefaultedList.ofSize(ingredients.size(), InputEntry.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                JsonObject obj = ingredients.get(i).getAsJsonObject();
                Ingredient ingredient = Ingredient.fromJson(obj);
                int count = obj.has("count") ? obj.get("count").getAsInt() : 1;
                inputs.set(i, new InputEntry(ingredient, count));
            }

            return new GrindingUnitRecipe(id, inputs, output);
        }

        @Override
        public GrindingUnitRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<InputEntry> inputs = DefaultedList.ofSize(buf.readInt(), InputEntry.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                Ingredient ingredient = Ingredient.fromPacket(buf);
                int count = buf.readInt();
                inputs.set(i, new InputEntry(ingredient, count));
            }
            ItemStack output = buf.readItemStack();
            return new GrindingUnitRecipe(id, inputs, output);
        }

        @Override
        public void write(PacketByteBuf buf, GrindingUnitRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (InputEntry entry: recipe.input) {
                entry.getIngredient().write(buf);
                buf.writeInt(entry.getCount());
            }
            buf.writeItemStack(recipe.output);
        }
    }
}
