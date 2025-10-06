package com.besson.endfield.compat.display;

import com.besson.endfield.compat.category.ElectricMiningRigMkIICategory;
import com.besson.endfield.recipe.custom.OreRigRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.List;

public class ElectricMiningRigMkIIDisplay implements Display {
    private final EntryIngredient input;
    private final EntryIngredient output;

    public ElectricMiningRigMkIIDisplay(OreRigRecipe recipe) {
        this.input = EntryIngredients.ofIngredient(recipe.getInput());
        this.output = EntryIngredients.of(recipe.getOutput(null));
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return List.of(input);
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return List.of(output);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ElectricMiningRigMkIICategory.ELECTRIC_MINING_RIG_MK_II;
    }
}
