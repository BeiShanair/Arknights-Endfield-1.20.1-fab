package com.besson.endfield.recipe.custom;

import com.besson.endfield.recipe.InputEntry;
import com.besson.endfield.recipe.ReactorResource;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.Map;

public class ReactorCrucibleRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final DefaultedList<InputEntry> itemInputs;
    private final DefaultedList<FluidInputEntry> fluidInputs;
    private final DefaultedList<ItemStack> itemOutputs;
    private final DefaultedList<FluidOutputEntry> fluidOutputs;

    public ReactorCrucibleRecipe(Identifier id,
                                 DefaultedList<InputEntry> itemInputs,
                                 DefaultedList<FluidInputEntry> fluidInputs,
                                 DefaultedList<ItemStack> itemOutputs,
                                 DefaultedList<FluidOutputEntry> fluidOutputs) {
        this.id = id;
        this.itemInputs = itemInputs;
        this.fluidInputs = fluidInputs;
        this.itemOutputs = itemOutputs;
        this.fluidOutputs = fluidOutputs;
    }

    public boolean canCraft(Map<ReactorResource, Long> resources) {
        for (InputEntry input : itemInputs) {
            long available = 0;
            for (Map.Entry<ReactorResource, Long> entry : resources.entrySet()) {
                ReactorResource resource = entry.getKey();
                if (resource.isItem() && input.ingredient().test(resource.itemVariant().toStack())) {
                    available += entry.getValue();
                }
            }
            if (available < input.count()) return false;
        }

        for (FluidInputEntry input : fluidInputs) {
            if (resources.getOrDefault(ReactorResource.fluid(input.variant()), 0L) < input.amount()) return false;
        }
        return true;
    }

    public boolean canFitOutputs(Map<ReactorResource, Long> resources, int maxResourceTypes) {
        Map<ReactorResource, Long> simulated = new java.util.LinkedHashMap<>(resources);
        for (ItemStack output : itemOutputs) {
            if (!canAdd(simulated, ReactorResource.item(ItemVariant.of(output)), output.getCount(), maxResourceTypes)) return false;
        }
        for (FluidOutputEntry output : fluidOutputs) {
            if (!canAdd(simulated, ReactorResource.fluid(output.variant()), output.amount(), maxResourceTypes)) return false;
        }
        return true;
    }

    public void consumeInputs(Map<ReactorResource, Long> resources) {
        for (InputEntry input : itemInputs) {
            int remaining = input.count();
            Iterator<Map.Entry<ReactorResource, Long>> iterator = resources.entrySet().iterator();
            while (iterator.hasNext() && remaining > 0) {
                Map.Entry<ReactorResource, Long> entry = iterator.next();
                ReactorResource resource = entry.getKey();
                if (!resource.isItem() || !input.ingredient().test(resource.itemVariant().toStack())) continue;
                long consumed = Math.min(entry.getValue(), remaining);
                entry.setValue(entry.getValue() - consumed);
                remaining -= (int) consumed;
                if (entry.getValue() <= 0) iterator.remove();
            }
        }

        for (FluidInputEntry input : fluidInputs) {
            ReactorResource resource = ReactorResource.fluid(input.variant());
            long stored = resources.getOrDefault(resource, 0L);
            long remaining = stored - input.amount();
            if (remaining > 0) {
                resources.put(resource, remaining);
            } else {
                resources.remove(resource);
            }
        }
    }

    public void addOutputs(Map<ReactorResource, Long> resources) {
        for (ItemStack output : itemOutputs) {
            resources.merge(ReactorResource.item(ItemVariant.of(output)), (long) output.getCount(), Long::sum);
        }
        for (FluidOutputEntry output : fluidOutputs) {
            resources.merge(ReactorResource.fluid(output.variant()), output.amount(), Long::sum);
        }
    }

    private static boolean canAdd(Map<ReactorResource, Long> resources, ReactorResource resource, long amount, int maxResourceTypes) {
        if (resource.isBlank() || amount <= 0) return false;
        long stored = resources.getOrDefault(resource, 0L);
        if (stored <= 0 && resources.size() >= maxResourceTypes) return false;
        long nextAmount = stored + amount;
        if (nextAmount > resource.getMaxAmount()) return false;
        resources.put(resource, nextAmount);
        return true;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        return false;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return getOutput(registryManager).copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return itemOutputs.isEmpty() ? ItemStack.EMPTY : itemOutputs.get(0);
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> ingredients = DefaultedList.of();
        for (InputEntry entry : itemInputs) {
            ingredients.add(entry.ingredient());
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

    @Override
    public boolean isIgnoredInRecipeBook() {
        return true;
    }

    public DefaultedList<InputEntry> getItemInputs() {
        return itemInputs;
    }

    public DefaultedList<FluidInputEntry> getFluidInputs() {
        return fluidInputs;
    }

    public DefaultedList<ItemStack> getItemOutputs() {
        return itemOutputs;
    }

    public DefaultedList<FluidOutputEntry> getFluidOutputs() {
        return fluidOutputs;
    }

    public record FluidInputEntry(FluidVariant variant, long amount) {
    }

    public record FluidOutputEntry(FluidVariant variant, long amount) {
    }

    public static class Type implements RecipeType<ReactorCrucibleRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "reactor_crucible";
    }

    public static class Serializer implements RecipeSerializer<ReactorCrucibleRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "reactor_crucible";

        @Override
        public ReactorCrucibleRecipe read(Identifier id, JsonObject json) {
            JsonArray input = JsonHelper.getArray(json, "input");
            DefaultedList<InputEntry> itemInputs = DefaultedList.of();
            DefaultedList<FluidInputEntry> fluidInputs = DefaultedList.of();
            for (int i = 0; i < input.size(); i++) {
                JsonObject object = input.get(i).getAsJsonObject();
                if (object.has("fluid")) {
                    fluidInputs.add(new FluidInputEntry(readFluidVariant(object), JsonHelper.getLong(object, "amount", 1000)));
                } else {
                    Ingredient ingredient = Ingredient.fromJson(object);
                    itemInputs.add(new InputEntry(ingredient, JsonHelper.getInt(object, "count", 1)));
                }
            }

            JsonArray output = JsonHelper.getArray(json, "output");
            DefaultedList<ItemStack> itemOutputs = DefaultedList.of();
            DefaultedList<FluidOutputEntry> fluidOutputs = DefaultedList.of();
            for (int i = 0; i < output.size(); i++) {
                JsonObject object = output.get(i).getAsJsonObject();
                if (object.has("fluid")) {
                    fluidOutputs.add(new FluidOutputEntry(readFluidVariant(object), JsonHelper.getLong(object, "amount", 1000)));
                } else {
                    itemOutputs.add(ShapedRecipe.outputFromJson(object));
                }
            }

            return new ReactorCrucibleRecipe(id, itemInputs, fluidInputs, itemOutputs, fluidOutputs);
        }

        @Override
        public ReactorCrucibleRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<InputEntry> itemInputs = DefaultedList.of();
            int itemInputSize = buf.readInt();
            for (int i = 0; i < itemInputSize; i++) {
                itemInputs.add(new InputEntry(Ingredient.fromPacket(buf), buf.readInt()));
            }

            DefaultedList<FluidInputEntry> fluidInputs = DefaultedList.of();
            int fluidInputSize = buf.readInt();
            for (int i = 0; i < fluidInputSize; i++) {
                fluidInputs.add(new FluidInputEntry(FluidVariant.fromPacket(buf), buf.readLong()));
            }

            DefaultedList<ItemStack> itemOutputs = DefaultedList.of();
            int itemOutputSize = buf.readInt();
            for (int i = 0; i < itemOutputSize; i++) {
                itemOutputs.add(buf.readItemStack());
            }

            DefaultedList<FluidOutputEntry> fluidOutputs = DefaultedList.of();
            int fluidOutputSize = buf.readInt();
            for (int i = 0; i < fluidOutputSize; i++) {
                fluidOutputs.add(new FluidOutputEntry(FluidVariant.fromPacket(buf), buf.readLong()));
            }

            return new ReactorCrucibleRecipe(id, itemInputs, fluidInputs, itemOutputs, fluidOutputs);
        }

        @Override
        public void write(PacketByteBuf buf, ReactorCrucibleRecipe recipe) {
            buf.writeInt(recipe.itemInputs.size());
            for (InputEntry input : recipe.itemInputs) {
                input.ingredient().write(buf);
                buf.writeInt(input.count());
            }

            buf.writeInt(recipe.fluidInputs.size());
            for (FluidInputEntry input : recipe.fluidInputs) {
                input.variant().toPacket(buf);
                buf.writeLong(input.amount());
            }

            buf.writeInt(recipe.itemOutputs.size());
            for (ItemStack output : recipe.itemOutputs) {
                buf.writeItemStack(output);
            }

            buf.writeInt(recipe.fluidOutputs.size());
            for (FluidOutputEntry output : recipe.fluidOutputs) {
                output.variant().toPacket(buf);
                buf.writeLong(output.amount());
            }
        }

        private static FluidVariant readFluidVariant(JsonObject object) {
            Identifier id = new Identifier(JsonHelper.getString(object, "fluid"));
            Fluid fluid = Registries.FLUID.get(id);
            return FluidVariant.of(fluid);
        }
    }
}
