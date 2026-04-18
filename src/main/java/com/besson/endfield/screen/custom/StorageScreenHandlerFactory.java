package com.besson.endfield.screen.custom;

import com.besson.endfield.screen.custom.screenHandler.StorageScreenHandler;
import com.besson.endfield.utils.storage.GlobalStorageManager;
import com.besson.endfield.utils.storage.StorageEntry;
import com.besson.endfield.utils.storage.StorageState;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StorageScreenHandlerFactory implements ExtendedScreenHandlerFactory {
    @Override
    public Text getDisplayName() {
        return Text.literal("Global Storage");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        StorageScreenHandler handler = new StorageScreenHandler(syncId, playerInventory);

        if (player instanceof ServerPlayerEntity serverPlayer) {
            ServerWorld world = serverPlayer.getServerWorld();
            StorageState state = GlobalStorageManager.get(world).getState();

            List<StorageEntry> stateEntries = new ArrayList<>(state.getStorage().values());

            for (StorageEntry e : stateEntries) {
                StorageEntry copy = new StorageEntry(e.getItem(), e.getCapacity());
                copy.insert(e.getCount());
                handler.getEntries().add(copy);
            }
            GlobalStorageManager manager = GlobalStorageManager.get(world);
            manager.addListener(serverPlayer);
            handler.refreshSlots();
        }

        return handler;
    }
    
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        ServerWorld world = (ServerWorld) player.getWorld();
        StorageState state = GlobalStorageManager.get(world).getState();
    
        var entries = state.getStorage();
        buf.writeInt(entries.size());
    
        for (StorageEntry entry : entries.values()) {
            Identifier id = Registries.ITEM.getId(entry.getItem());
            long c = entry.getCount();
            buf.writeIdentifier(id);
            buf.writeLong(c);
        }
    }
}
