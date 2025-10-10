package com.besson.endfield.utils;

import com.besson.endfield.recipe.custom.CrafterRecipe;
import com.besson.endfield.screen.custom.CrafterScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

import java.util.Optional;

public class CrafterResultSlot extends Slot {
    private final Inventory inputInventory;
    private final PlayerEntity player;
    private final ScreenHandler handler;

    public CrafterResultSlot(PlayerEntity player,Inventory input,  Inventory output, int index, int x, int y, ScreenHandler handler) {
        super(output, index, x, y);
        this.player = player;
        this.inputInventory = input;
        this.handler = handler;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }

    @Override
    public void onTakeItem(PlayerEntity player, ItemStack stack) {
        World world = player.getWorld();
        if (!world.isClient()) {
            if (handler instanceof CrafterScreenHandler crafterScreenHandler) {
                var recipes = crafterScreenHandler.getCurrentRecipes();
                int index = crafterScreenHandler.getSelectedRecipeIndex();
                if (!recipes.isEmpty() && index < recipes.size()) {
                    CrafterRecipe recipe = recipes.get(index);
                    recipe.craft(inputInventory, world.getRegistryManager());
                }
            }
        }
        handler.onContentChanged(inputInventory);
        super.onTakeItem(player, stack);
    }
}
