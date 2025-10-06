package com.besson.endfield.recipe.custom;

import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

public class SeedPickingUnitRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final Ingredient input;
    private final ItemStack output;

    public SeedPickingUnitRecipe(Identifier id, Ingredient input, ItemStack output) {
        this.id = id;
        this.input = input;
        this.output = output;
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
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public Ingredient getInput() {
        return input;
    }

    public static class Type implements RecipeType<SeedPickingUnitRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "seed_picking_unit";
    }

    public static class Serializer implements RecipeSerializer<SeedPickingUnitRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "seed_picking_unit";

        @Override
        public SeedPickingUnitRecipe read(Identifier id, JsonObject json) {
            Ingredient input = Ingredient.fromJson(json.get("input"));
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            return new SeedPickingUnitRecipe(id, input, output);
        }

        @Override
        public SeedPickingUnitRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient input = Ingredient.fromPacket(buf);
            ItemStack output = buf.readItemStack();
            return new SeedPickingUnitRecipe(id, input, output);
        }

        @Override
        public void write(PacketByteBuf buf, SeedPickingUnitRecipe recipe) {
            recipe.input.write(buf);
            buf.writeItemStack(recipe.output);
        }
    }
}
