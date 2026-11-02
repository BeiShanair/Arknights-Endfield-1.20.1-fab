package com.besson.endfield.compat.display;

import com.besson.endfield.compat.category.PlantingUnitCategory;
import com.besson.endfield.recipe.custom.PlantingUnitRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;

import java.util.ArrayList;
import java.util.List;

public class PlantingUnitDisplay implements Display {
    private final List<EntryIngredient> inputs;
    private final List<EntryIngredient> outputs;

    public PlantingUnitDisplay(PlantingUnitRecipe recipe) {
        List<EntryIngredient> inputList = new ArrayList<>();
        inputList.add(EntryIngredients.ofIngredient(recipe.getInput()));
        
        if (recipe.requiresFluid()) {
            inputList.add(EntryIngredients.of(recipe.getRequiredFluid().getFluid(),
                    recipe.getRequiredFluidAmount()));
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
        return PlantingUnitCategory.PLANTING_UNIT;
    }
}
