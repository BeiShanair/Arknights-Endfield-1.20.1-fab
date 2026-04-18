package com.besson.endfield.utils.storage;

import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.PersistentState;

import java.util.HashMap;
import java.util.Map;

public class StorageState extends PersistentState {

    private Map<Item, StorageEntry> storage = new HashMap<>();
    private long globalCapacity = 10000;

    public StorageState() {

    }

    public static StorageState fromNbt(NbtCompound nbt) {
        StorageState state = new StorageState();

        NbtList list = nbt.getList("storage", NbtElement.COMPOUND_TYPE);

        for (NbtElement element : list) {
            NbtCompound tag = (NbtCompound) element;

            Item item = Registries.ITEM.get(new Identifier(tag.getString("id")));
            long count = tag.getLong("count");
            long cap = tag.getLong("cap");

            StorageEntry entry = new StorageEntry(item, cap);
            entry.insert(count);

            state.storage.put(item, entry);
        }
        state.globalCapacity = nbt.getLong("globalCapacity");
        if (state.globalCapacity == 0) {
            state.globalCapacity = 10000;
        }
        return state;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        NbtList list = new NbtList();

        for (StorageEntry entry : storage.values()) {
            NbtCompound tag = new NbtCompound();

            tag.putString("id", Registries.ITEM.getId(entry.getItem()).toString());
            tag.putLong("count", entry.getCount());
            tag.putLong("cap", entry.getCapacity());

            list.add(tag);
        }

        nbt.put("storage", list);
        nbt.putLong("globalCapacity", globalCapacity);
        return nbt;
    }

    public Map<Item, StorageEntry> getStorage() {
        return storage;
    }

    public long getGlobalCapacity() {
        return globalCapacity;
    }

    public void setGlobalCapacity(long cap) {
        this.globalCapacity = cap;
        for (StorageEntry entry : storage.values()) {
            entry.setCapacity(cap);
        }
        markDirty();
    }
}
