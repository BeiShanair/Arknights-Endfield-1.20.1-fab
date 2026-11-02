package com.besson.endfield.compat.display;

import com.besson.endfield.blockentity.custom.resourcing.HydroMiningRigBlockEntity;
import com.besson.endfield.compat.category.HydroMiningRigCategory;
import com.besson.endfield.recipe.custom.OreRigRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.fluid.Fluids;

import java.util.List;

public class HydroMiningRigDisplay implements Display {
    private final EntryIngredient input;
    private final EntryIngredient water;
    private final EntryIngredient output;

    public HydroMiningRigDisplay(OreRigRecipe recipe) {
        this.input = EntryIngredients.ofIngredient(recipe.getInput());
        this.water = EntryIngredients.of(Fluids.WATER, HydroMiningRigBlockEntity.WATER_COST_PER_CRAFT);
        this.output = EntryIngredients.of(recipe.getOutput(null));
    }
    
    @Override
    public List<EntryIngredient> getInputEntries() {
        return List.of(input, water);
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return List.of(output);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return HydroMiningRigCategory.HYDRO_MINING_RIG;
    }
}
