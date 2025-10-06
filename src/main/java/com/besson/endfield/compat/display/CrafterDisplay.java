package com.besson.endfield.compat.display;

import com.besson.endfield.compat.category.CrafterCategory;
import com.besson.endfield.recipe.custom.CrafterRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.List;
import java.util.stream.Collectors;

public class CrafterDisplay implements Display {
    private final List<EntryIngredient> inputs;
    private final List<EntryIngredient> outputs;

    public CrafterDisplay(CrafterRecipe recipe) {
        this.inputs = recipe.getRequired().entrySet().stream().map(entry ->
                EntryIngredients.of(entry.getKey().asItem(), entry.getValue())).collect(Collectors.toList());
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
        return CrafterCategory.CRAFTER;
    }
}
