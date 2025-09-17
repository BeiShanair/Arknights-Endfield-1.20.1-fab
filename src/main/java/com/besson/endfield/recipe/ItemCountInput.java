package com.besson.endfield.recipe;

import net.minecraft.item.ItemConvertible;

public class ItemCountInput {
    private final ItemConvertible itemConvertible;
    private final int count;

    public ItemCountInput(ItemConvertible itemConvertible, int count) {
        this.itemConvertible = itemConvertible;
        this.count = count;
    }

    public ItemConvertible getItemConvertible() {
        return itemConvertible;
    }

    public int getCount() {
        return count;
    }
}
