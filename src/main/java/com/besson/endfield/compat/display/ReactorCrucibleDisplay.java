package com.besson.endfield.compat.display;

import com.besson.endfield.compat.category.ReactorCrucibleCategory;
import com.besson.endfield.recipe.InputEntry;
import com.besson.endfield.recipe.custom.ReactorCrucibleRecipe;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ReactorCrucibleDisplay implements Display {
    private final List<EntryIngredient> inputs;
    private final List<EntryIngredient> outputs;

    public ReactorCrucibleDisplay(ReactorCrucibleRecipe recipe) {
        List<EntryIngredient> inputList = new ArrayList<>();
        
        for (InputEntry entry : recipe.getItemInputs()) {
            List<ItemStack> stacks = List.of(entry.ingredient().getMatchingStacks());
            inputList.add(EntryIngredients.ofItemStacks(
                    stacks.stream().map(stack -> {
                        ItemStack copy = stack.copy();
                        copy.setCount(entry.count());
                        return copy;
                    }).toList()));
        }
        
        for (ReactorCrucibleRecipe.FluidInputEntry entry : recipe.getFluidInputs()) {
            inputList.add(EntryIngredients.of(entry.variant().getFluid(), entry.amount()));
        }
        this.inputs = List.copyOf(inputList);

        List<EntryIngredient> outputList = new ArrayList<>();
        
        for (ItemStack stack : recipe.getItemOutputs()) {
            outputList.add(EntryIngredients.of(stack));
        }
        
        for (ReactorCrucibleRecipe.FluidOutputEntry entry : recipe.getFluidOutputs()) {
            outputList.add(EntryIngredients.of(entry.variant().getFluid(), entry.amount()));
        }
        this.outputs = List.copyOf(outputList);
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
        return ReactorCrucibleCategory.REACTOR_CRUCIBLE;
    }
}
