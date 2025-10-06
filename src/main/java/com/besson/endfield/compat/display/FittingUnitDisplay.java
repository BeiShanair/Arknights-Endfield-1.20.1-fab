package com.besson.endfield.compat.display;

import com.besson.endfield.compat.category.FittingUnitCategory;
import com.besson.endfield.recipe.custom.FittingUnitRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.List;

public class FittingUnitDisplay implements Display {
    private final List<EntryIngredient> inputs;
    private final List<EntryIngredient> outputs;

    public FittingUnitDisplay(FittingUnitRecipe recipe) {
        this.inputs = List.of(EntryIngredients.ofIngredient(recipe.getInput()));
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
        return FittingUnitCategory.FITTING_UNIT;
    }
}
