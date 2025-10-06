package com.besson.endfield.compat.display;

import com.besson.endfield.compat.category.PackagingUnitCategory;
import com.besson.endfield.recipe.custom.PackagingUnitRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class PackagingUnitDisplay implements Display {
    private final List<EntryIngredient> inputs;
    private final List<EntryIngredient> outputs;

    public PackagingUnitDisplay(PackagingUnitRecipe recipe) {
        this.inputs = recipe.getInput().stream().map(entry -> {
            List<ItemStack> stacks = Arrays.asList(entry.getIngredient().getMatchingStacks());
            return EntryIngredients.ofItemStacks(
                    stacks.stream().map(stack -> {
                        ItemStack copy = stack.copy();
                        copy.setCount(entry.getCount());
                        return copy;
                    }).toList());
        }).toList();

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
        return PackagingUnitCategory.PACKAGING_UNIT;
    }
}
