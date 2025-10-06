package com.besson.endfield.compat.display;

import com.besson.endfield.compat.category.PortableOriginiumRigCategory;
import com.besson.endfield.recipe.custom.OreRigRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.List;

public class PortableOriginiumRigDisplay implements Display {

    private final EntryIngredient input;
    private final EntryIngredient output;

    public PortableOriginiumRigDisplay(OreRigRecipe recipe) {
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
        return PortableOriginiumRigCategory.PORTABLE_ORIGINIUM_RIG;
    }
}
