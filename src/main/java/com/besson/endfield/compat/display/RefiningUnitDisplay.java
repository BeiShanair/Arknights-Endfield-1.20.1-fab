package com.besson.endfield.compat.display;

import com.besson.endfield.compat.category.RefiningUnitCategory;
import com.besson.endfield.recipe.InputEntry;
import com.besson.endfield.recipe.custom.RefiningUnitRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.item.ItemStack;

import java.util.List;

public class RefiningUnitDisplay implements Display {
    private final List<EntryIngredient> inputs;
    private final List<EntryIngredient> outputs;

    public RefiningUnitDisplay(RefiningUnitRecipe recipe) {
        InputEntry entry = recipe.getInput();
        List<ItemStack> stacks = List.of(entry.getIngredient().getMatchingStacks());
        this.inputs = List.of(EntryIngredients.ofItemStacks(
                stacks.stream().map(stack -> {
                    ItemStack copy = stack.copy();
                    copy.setCount(entry.getCount());
                    return copy;
                }).toList()));
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
        return RefiningUnitCategory.REFINING_UNIT;
    }
}
