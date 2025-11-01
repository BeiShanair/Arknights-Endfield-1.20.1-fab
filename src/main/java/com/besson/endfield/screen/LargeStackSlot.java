package com.besson.endfield.screen;

import com.besson.endfield.blockentity.custom.BigStorageBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class LargeStackSlot extends Slot {
    private final BigStorageBlockEntity entity;
    private final ItemVariant variant;
    private final int index;

    public LargeStackSlot(BigStorageBlockEntity entity, Inventory dummyInventory, ItemVariant variant, int index, int x, int y) {
        super(dummyInventory, index, x, y);
        this.entity = entity;
        this.variant = variant;
        this.index = index;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return true;
    }

    @Override
    public boolean canTakeItems(PlayerEntity playerEntity) {
        return true;
    }

    @Override
    public ItemStack getStack() {
        // 直接从 Inventory 获取
        return super.getStack();
    }

    @Override
    public void setStack(ItemStack stack) {
        super.setStack(stack); // 更新 Inventory
        // 同步到 Map
        ItemVariant variant = ItemVariant.of(stack);
        long count = stack.getCount();
        if (stack.isEmpty()) {
            entity.getStorage().remove(variant);
        } else {
            entity.getStorage().put(variant, count);
        }
        entity.markDirty();
    }

    @Override
    public ItemStack takeStack(int amount) {
        ItemStack extracted = super.takeStack(amount);
        // 同步 Map
        ItemVariant variant = ItemVariant.of(extracted);
        long stored = entity.getStorage().getOrDefault(variant, 0L);
        long remaining = stored - extracted.getCount();
        if (remaining <= 0) entity.getStorage().remove(variant);
        else entity.getStorage().put(variant, remaining);
        entity.markDirty();
        return extracted;
    }
}
