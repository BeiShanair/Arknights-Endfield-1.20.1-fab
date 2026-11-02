package com.besson.endfield.utils.storage;

import com.besson.endfield.network.ModNetWorking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentStateManager;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;

public class GlobalStorageManager {
    private static final Map<ServerWorld, GlobalStorageManager> INSTANCE = new WeakHashMap<>();
    private final ServerWorld world;
    private final StorageState state;
    private final Set<ServerPlayerEntity> listeners = new HashSet<>();

    public GlobalStorageManager(ServerWorld world) {
        this.world = world;
        PersistentStateManager manager = world.getPersistentStateManager();

        String dimKey = world.getRegistryKey().getValue().toString().replace(':', '_').replace('/', '_');
        String stateName = "storage_state_" + dimKey;

        this.state = manager.getOrCreate(StorageState::fromNbt, StorageState::new, stateName);
    }

    public static GlobalStorageManager get(ServerWorld world) {
        return INSTANCE.computeIfAbsent(world, GlobalStorageManager::new);
    }

    public void addListener(ServerPlayerEntity player) {
        listeners.add(player);
    }

    public void removeListener(ServerPlayerEntity serverPlayer) {
        listeners.remove(serverPlayer);
    }

    public long insert(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return 0;

        NbtCompound tag = StorageEntry.normalizeTag(stack.getNbt());
        long free = getFreeSpace(stack.getItem(), tag);
        if (free <= 0) return 0;

        long amount = Math.min(stack.getCount(), free);
        StorageEntry entry = state.getOrCreate(stack.getItem(), tag);
        long inserted = entry.insert(amount);
        if (inserted <= 0) return 0;

        state.markDirty();
        sync();
        return inserted;
    }

    /** 该变体还能容纳的数量（只读，不会创建条目）。 */
    public long getFreeSpace(Item item, @Nullable NbtCompound tag) {
        NbtCompound normalized = StorageEntry.normalizeTag(tag);
        StorageEntry entry = state.find(item, normalized);
        long capacity = entry == null ? state.getGlobalCapacity() : entry.getCapacity();
        long count = entry == null ? 0 : entry.getCount();
        return Math.max(0, capacity - count);
    }

    public ItemStack extract(Item item, long amount) {
        StorageEntry entry = state.findFirst(item);
        if (entry == null) return ItemStack.EMPTY;
        return extractFrom(entry, amount);
    }

    public ItemStack extract(ItemStack prototype, long amount) {
        if (prototype == null || prototype.isEmpty()) return ItemStack.EMPTY;
        StorageEntry entry = state.find(prototype.getItem(), prototype.getNbt());
        if (entry == null) return ItemStack.EMPTY;
        return extractFrom(entry, amount);
    }

    private ItemStack extractFrom(StorageEntry entry, long amount) {
        if (amount <= 0) return ItemStack.EMPTY;
        long max = Math.max(1, entry.getItem().getMaxCount());
        long toTake = Math.min(amount, max);
        long taken = entry.extract(toTake);
        if (taken <= 0) return ItemStack.EMPTY;

        if (entry.getCount() <= 0) {
            state.removeEntry(entry);
        }
        state.markDirty();
        sync();
        return entry.toStack((int) taken);
    }

    public static int removeFromInventory(PlayerInventory inv, Item item, @Nullable NbtCompound tag, int amount) {
        if (inv == null || item == null || amount <= 0) return 0;
        NbtCompound normalized = StorageEntry.normalizeTag(tag);
        int removed = 0;
        for (int i = 0; i < inv.size() && removed < amount; i++) {
            ItemStack stack = inv.getStack(i);
            if (!stack.isEmpty() && stack.isOf(item)
                    && Objects.equals(StorageEntry.normalizeTag(stack.getNbt()), normalized)) {
                int take = Math.min(stack.getCount(), amount - removed);
                stack.decrement(take);
                if (stack.isEmpty()) inv.setStack(i, ItemStack.EMPTY);
                removed += take;
            }
        }
        return removed;
    }

    private void sync() {
        for (ServerPlayerEntity player : listeners) {
            sendFullUpdate(player);
        }
    }

    private void sendFullUpdate(ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        List<StorageEntry> entries = state.getEntries();
        buf.writeInt(entries.size());
        for (StorageEntry entry : entries) {
            buf.writeIdentifier(Registries.ITEM.getId(entry.getItem()));
            buf.writeNbt(entry.getTag());
            buf.writeLong(entry.getCount());
        }
        ServerPlayNetworking.send(player, ModNetWorking.SYNC_STORAGE, buf);
    }

    public ServerWorld getWorld() {
        return world;
    }

    public StorageState getState() {
        return state;
    }
}
