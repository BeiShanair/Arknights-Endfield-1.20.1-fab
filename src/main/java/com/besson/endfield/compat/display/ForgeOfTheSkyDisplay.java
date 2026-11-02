package com.besson.endfield.compat.display;

import com.besson.endfield.compat.category.ForgeOfTheSkyCategory;
import com.besson.endfield.recipe.InputEntry;
import com.besson.endfield.recipe.custom.ForgeOfTheSkyRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ForgeOfTheSkyDisplay implements Display {
    private final List<EntryIngredient> inputs;
    private final List<EntryIngredient> outputs;

    public ForgeOfTheSkyDisplay(ForgeOfTheSkyRecipe recipe) {
        InputEntry entry = recipe.getInput();
        List<ItemStack> stacks = List.of(entry.ingredient().getMatchingStacks());
        List<EntryIngredient> inputList = new ArrayList<>();
        inputList.add(EntryIngredients.ofItemStacks(
                stacks.stream().map(stack -> {
                    ItemStack copy = stack.copy();
                    copy.setCount(entry.count());
                    return copy;
                }).toList()));
        
        if (recipe.requiresFluid()) {
            inputList.add(EntryIngredients.of(recipe.getRequiredFluid().getFluid(), recipe.getRequiredFluidAmount()));
        }
        this.inputs = List.copyOf(inputList);
        this.outputs = List.of(EntryIngredients.of(recipe.getOutput(null)));
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return inputs;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return outputs;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ForgeOfTheSkyCategory.FORGE_OF_THE_SKY;
    }
}
