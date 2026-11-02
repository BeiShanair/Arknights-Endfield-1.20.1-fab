package com.besson.endfield.recipe;

import net.minecraft.recipe.Ingredient;

public record InputEntry(Ingredient ingredient, int count) {
    public static final InputEntry EMPTY = new InputEntry(Ingredient.EMPTY, 0);
}
