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
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ForgeOfTheSkyRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final InputEntry input;
    private final ItemStack output;
    private final FluidVariant requiredFluid;
    private final long requiredFluidAmount;
    
    @Nullable
    private final Integer craftTime;

    public ForgeOfTheSkyRecipe(Identifier id, InputEntry input, ItemStack output,
                                FluidVariant requiredFluid, long requiredFluidAmount,
                                @Nullable Integer craftTime) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.requiredFluid = requiredFluid;
        this.requiredFluidAmount = requiredFluidAmount;
        this.craftTime = craftTime;
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
    
    public Optional<Integer> getCraftTime() {
        return Optional.ofNullable(craftTime);
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

    public static class Type implements RecipeType<ForgeOfTheSkyRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "forge_of_the_sky";
    }

    public static class Serializer implements RecipeSerializer<ForgeOfTheSkyRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "forge_of_the_sky";

        @Override
        public ForgeOfTheSkyRecipe read(Identifier id, JsonObject json) {
            JsonObject ingredientObj = JsonHelper.getObject(json, "input");
            Ingredient ingredient = Ingredient.fromJson(ingredientObj);
            int count = ingredientObj.has("count") ? JsonHelper.getInt(ingredientObj, "count") : 1;
            InputEntry input = new InputEntry(ingredient, count);

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

            Integer craftTime = json.has("craft_time") ? JsonHelper.getInt(json, "craft_time") : null;

            return new ForgeOfTheSkyRecipe(id, input, output, requiredFluid, requiredFluidAmount, craftTime);
        }

        @Override
        public ForgeOfTheSkyRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient ingredient = Ingredient.fromPacket(buf);
            int count = buf.readInt();
            InputEntry input = new InputEntry(ingredient, count);
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

            boolean hasCraftTime = buf.readBoolean();
            Integer craftTime = hasCraftTime ? buf.readInt() : null;

            return new ForgeOfTheSkyRecipe(id, input, output, requiredFluid, requiredFluidAmount, craftTime);
        }

        @Override
        public void write(PacketByteBuf buf, ForgeOfTheSkyRecipe recipe) {
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

            buf.writeBoolean(recipe.craftTime != null);
            if (recipe.craftTime != null) {
                buf.writeInt(recipe.craftTime);
            }
        }
    }
}
