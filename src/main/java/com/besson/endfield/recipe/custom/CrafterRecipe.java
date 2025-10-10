package com.besson.endfield.recipe.custom;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class CrafterRecipe implements Recipe<Inventory> {
    private final Identifier id;
    private final Map<ItemConvertible, Integer> required;
    private final ItemStack output;

    public CrafterRecipe(Identifier id, Map<ItemConvertible, Integer> required, ItemStack output) {
        this.id = id;
        this.required = required;
        this.output = output;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        for (Map.Entry<ItemConvertible, Integer> entry : required.entrySet()) {
            int count = 0;
            for (int i = 0; i < inventory.size(); i++) {
                ItemStack stack = inventory.getStack(i);
                if (stack.getItem().equals(entry.getKey().asItem())) {
                    count += stack.getCount();
                }
            }
            if (count < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack craft(Inventory inventory, DynamicRegistryManager registryManager) {
        for (Map.Entry<ItemConvertible, Integer> entry : required.entrySet()) {
            int need = entry.getValue();
            for (int i = 0; i < inventory.size(); i++) {
                ItemStack stack = inventory.getStack(i);
                if (stack.getItem().equals(entry.getKey().asItem())) {
                    int removed = Math.min(stack.getCount(), need);
                    stack.decrement(removed);
                    need -= removed;
                    if (need <= 0) break;
                }
            }
        }
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

    public Map<ItemConvertible, Integer> getRequired() {
        return required;
    }

    public static class Type implements RecipeType<CrafterRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "crafter";
    }

    public static class Serializer implements RecipeSerializer<CrafterRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "crafter";

        @Override
        public CrafterRecipe read(Identifier id, JsonObject json) {
            JsonObject input = JsonHelper.getObject(json, "input");
            Map<ItemConvertible, Integer> required = new HashMap<>();
            for (Map.Entry<String, JsonElement> entry: input.entrySet()) {
                ItemConvertible item = Registries.ITEM.get(new Identifier(entry.getKey()));
                int count = entry.getValue().getAsInt();
                required.put(item, count);
            }

            JsonObject output = JsonHelper.getObject(json, "output");
            ItemConvertible result = Registries.ITEM.get(new Identifier(JsonHelper.getString(output, "item")));
            int count = JsonHelper.getInt(output, "count", 1);

            return new CrafterRecipe(id, required, new ItemStack(result.asItem(), count));
        }

        @Override
        public CrafterRecipe read(Identifier id, PacketByteBuf buf) {
            int size = buf.readInt();
            Map<ItemConvertible, Integer> required = new HashMap<>();
            for (int i = 0; i < size; i++) {
                ItemConvertible item = buf.readItemStack().getItem();
                int count = buf.readInt();
                required.put(item, count);
            }
            ItemStack output = buf.readItemStack();
            return new CrafterRecipe(id, required, output);
        }

        @Override
        public void write(PacketByteBuf buf, CrafterRecipe recipe) {
            buf.writeInt(recipe.getRequired().size());
            for (Map.Entry<ItemConvertible, Integer> entry : recipe.getRequired().entrySet()) {
                buf.writeItemStack(new ItemStack(entry.getKey().asItem()));
                buf.writeInt(entry.getValue());
            }
            buf.writeItemStack(recipe.output);
        }
    }
}
