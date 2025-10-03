package com.besson.endfield.utils;

import com.besson.endfield.recipe.custom.CrafterRecipe;
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
            Optional<CrafterRecipe> match = world.getRecipeManager()
                    .getFirstMatch(CrafterRecipe.Type.INSTANCE, inputInventory, world);
            match.ifPresent(crafterRecipe -> crafterRecipe.craft(inputInventory, world.getRegistryManager()));
        }
        handler.onContentChanged(inputInventory);
        super.onTakeItem(player, stack);
    }
}
