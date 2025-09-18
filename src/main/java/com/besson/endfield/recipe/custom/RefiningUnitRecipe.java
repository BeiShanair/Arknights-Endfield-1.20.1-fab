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
import net.minecraft.world.World;

public class RefiningUnitRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final InputEntry input;
    private final ItemStack output;

    public RefiningUnitRecipe(Identifier id, InputEntry input, ItemStack output) {
        this.id = id;
        this.input = input;
        this.output = output;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) return false;
        ItemStack inputs = inventory.getStack(0);
        return input.getIngredient().test(inputs);
    }

    public InputEntry getInput() {
        return input;
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
            int count = ingredients.has("count") ? ingredients.get("count").getAsInt() : 1;
            inputs = new InputEntry(ingredient, count);

            return new RefiningUnitRecipe(id, inputs, output);
        }

        @Override
        public RefiningUnitRecipe read(Identifier id, PacketByteBuf buf) {
            InputEntry inputs;
            ItemStack output = buf.readItemStack();

            Ingredient ingredient = Ingredient.fromPacket(buf);
            int count = buf.readInt();
            inputs = new InputEntry(ingredient, count);

            return new RefiningUnitRecipe(id, inputs, output);
        }

        @Override
        public void write(PacketByteBuf buf, RefiningUnitRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            InputEntry entry = recipe.input;
            entry.getIngredient().write(buf);
            buf.writeInt(entry.getCount());
            buf.writeItemStack(recipe.output);
        }
    }
}
