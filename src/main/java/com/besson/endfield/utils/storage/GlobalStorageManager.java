package com.besson.endfield.utils.storage;

import com.besson.endfield.network.ModNetWorking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentStateManager;

import java.util.HashSet;
import java.util.Map;
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
        Map<Item, StorageEntry> storageEntryMap = state.getStorage();

        StorageEntry entry = storageEntryMap.computeIfAbsent(
                stack.getItem(),
                item -> new StorageEntry(item, state.getGlobalCapacity()));
        long inserted = entry.insert(stack.getCount());
        state.markDirty();
        sync();
        return inserted;
    }

    public ItemStack extract(Item item, long amount) {
        StorageEntry entry = state.getStorage().get(item);
        if (entry == null) return ItemStack.EMPTY;
        long taken = entry.extract(amount);
        if (entry.getCount() <= 0) {
            state.getStorage().remove(item);
        }
        state.markDirty();
        sync();
        return new ItemStack(item, (int) Math.min(taken, item.getMaxCount()));
    }

    private void sync() {
        for (ServerPlayerEntity player : listeners) {
            sendFullUpdate(player);
        }
    }

    private void sendFullUpdate(ServerPlayerEntity player) {
        PacketByteBuf buf = PacketByteBufs.create();
        var entries = state.getStorage().values();
        buf.writeInt(entries.size());
        for (StorageEntry entry : entries) {
            buf.writeIdentifier(Registries.ITEM.getId(entry.getItem()));
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
