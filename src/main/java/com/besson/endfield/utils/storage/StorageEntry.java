package com.besson.endfield.utils.storage;

import net.minecraft.item.Item;

public class StorageEntry {
    private final Item item;
    private long count;
    private long capacity;

    public StorageEntry(Item item, long capacity) {
        this.item = item;
        this.capacity = capacity;
        this.count = 0;
    }

    public StorageEntry(Item item, long count, long capacity) {
        this.item = item;
        this.capacity = capacity;
        this.count = count;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long insert(long amount) {
        long accepted = Math.min(amount, capacity - count);
        count += accepted;
        return accepted;
    }

    public long extract(long amount) {
        long taken = Math.min(amount, count);
        count -= taken;
        return taken;
    }

    public Item getItem() {
        return item;
    }

    public long getCapacity() {
        return capacity;
    }

    public long getCount() {
        return count;
    }
}
