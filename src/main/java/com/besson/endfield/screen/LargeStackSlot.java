package com.besson.endfield.screen;

import net.minecraft.inventory.Inventory;
import net.minecraft.screen.slot.Slot;

public class LargeStackSlot extends Slot {

    public LargeStackSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public int getMaxItemCount() {
        return 512;
    }
}
