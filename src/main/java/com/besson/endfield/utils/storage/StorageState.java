package com.besson.endfield.utils.storage;

import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.PersistentState;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StorageState extends PersistentState {

    private final List<StorageEntry> storage = new ArrayList<>();
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
            NbtCompound variantTag = tag.contains("tag", NbtElement.COMPOUND_TYPE)
                    ? tag.getCompound("tag") : null;

            StorageEntry entry = new StorageEntry(item, variantTag, count, cap);
            state.storage.add(entry);
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

        for (StorageEntry entry : storage) {
            NbtCompound tag = new NbtCompound();

            tag.putString("id", Registries.ITEM.getId(entry.getItem()).toString());
            tag.putLong("count", entry.getCount());
            tag.putLong("cap", entry.getCapacity());
            NbtCompound variantTag = entry.getTag();
            if (variantTag != null) {
                tag.put("tag", variantTag);
            }

            list.add(tag);
        }

        nbt.put("storage", list);
        nbt.putLong("globalCapacity", globalCapacity);
        return nbt;
    }

    public List<StorageEntry> getEntries() {
        return storage;
    }

    /** 精确变体查找；不存在返回 null。 */
    @Nullable
    public StorageEntry find(Item item, @Nullable NbtCompound tag) {
        NbtCompound normalized = StorageEntry.normalizeTag(tag);
        for (StorageEntry entry : storage) {
            if (entry.matches(item, normalized)) {
                return entry;
            }
        }
        return null;
    }

    @Nullable
    public StorageEntry findFirst(Item item) {
        for (StorageEntry entry : storage) {
            if (entry.isAnyOf(item)) {
                return entry;
            }
        }
        return null;
    }

    public StorageEntry getOrCreate(Item item, @Nullable NbtCompound tag) {
        NbtCompound normalized = StorageEntry.normalizeTag(tag);
        StorageEntry found = find(item, normalized);
        if (found != null) {
            return found;
        }
        StorageEntry entry = new StorageEntry(item, normalized, 0, globalCapacity);
        storage.add(entry);
        return entry;
    }

    public void removeEntry(StorageEntry entry) {
        if (storage.remove(entry)) {
            markDirty();
        }
    }

    public long getGlobalCapacity() {
        return globalCapacity;
    }

    public void setGlobalCapacity(long cap) {
        this.globalCapacity = cap;
        for (StorageEntry entry : storage) {
            entry.setCapacity(cap);
        }
        markDirty();
    }
}
