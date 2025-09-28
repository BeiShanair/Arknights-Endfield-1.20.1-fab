package com.besson.endfield.recipe.custom;

import com.besson.endfield.recipe.InputEntry;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

public class MouldingUnitRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final InputEntry input;
    private final ItemStack output;

    public MouldingUnitRecipe(Identifier id, InputEntry input, ItemStack output) {
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

    public static class Type implements RecipeType<MouldingUnitRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "moulding_unit";
    }

    public static class Serializer implements RecipeSerializer<MouldingUnitRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "moulding_unit";

        @Override
        public MouldingUnitRecipe read(Identifier id, JsonObject json) {
            JsonObject ingredients = JsonHelper.getObject(json, "input");
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            InputEntry inputs;

            Ingredient ingredient = Ingredient.fromJson(ingredients);
            int count = ingredients.has("count") ? JsonHelper.getInt(ingredients, "count") : 1;
            inputs = new InputEntry(ingredient, count);

            return new MouldingUnitRecipe(id, inputs, output);
        }

        @Override
        public MouldingUnitRecipe read(Identifier id, PacketByteBuf buf) {
            InputEntry inputs;
            Ingredient ingredient = Ingredient.fromPacket(buf);
            int count = buf.readInt();
            inputs = new InputEntry(ingredient, count);
            ItemStack output = buf.readItemStack();
            return new MouldingUnitRecipe(id, inputs, output);
        }

        @Override
        public void write(PacketByteBuf buf, MouldingUnitRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            InputEntry entry = recipe.getInput();
            entry.getIngredient().write(buf);
            buf.writeInt(entry.getCount());
            buf.writeItemStack(recipe.output);
        }
    }
}
