package com.besson.endfield.screen.custom.screenHandler;

import com.besson.endfield.screen.ModScreens;
import com.besson.endfield.utils.storage.GlobalStorageManager;
import com.besson.endfield.utils.storage.StorageEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.Registries;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class StorageScreenHandler extends ScreenHandler {
    private static final int STORAGE_ROWS = 6;
    private static final int STORAGE_COLS = 9;
    private static final int STORAGE_SLOT_COUNT = STORAGE_ROWS * STORAGE_COLS;

    private List<StorageEntry> entries;
    public int scrollOffset = 0;
    private final SimpleInventory storageInventory = new SimpleInventory(STORAGE_SLOT_COUNT);

    public StorageScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, null);
    }

    public StorageScreenHandler(int syncId, PlayerInventory playerInv, PacketByteBuf buf) {
        super(ModScreens.STORAGE_SCREEN, syncId);

        this.entries = new ArrayList<>();
        if (buf != null) {
            int size = buf.readInt();
            for (int i = 0; i < size; i++) {
                Identifier id = buf.readIdentifier();
                Item item = Registries.ITEM.get(id);
                NbtCompound tag = buf.readNbt();
                long count = buf.readLong();
                entries.add(new StorageEntry(item, tag, count, Math.max(count, 1L)));
            }

            for (int i = 0; i < Math.min(entries.size(), STORAGE_SLOT_COUNT); i++) {
                storageInventory.setStack(i, entries.get(i).toStack(1));
            }
        }

        int index = 0;
        for (int y = 0; y < STORAGE_ROWS; y++) {
            for (int x = 0; x < STORAGE_COLS; x++) {
                final int slotIndex = index;
                addSlot(new Slot(storageInventory, slotIndex, 8 + x * 18, 18 + y * 18) {
                    @Override
                    public boolean canInsert(ItemStack stack) {
                        return false;
                    }

                    @Override
                    public boolean canTakeItems(PlayerEntity playerEntity) {
                        return false;
                    }
                });
                index++;
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                int playerSlotIndex = col + row * 9 + 9;
                addSlot(new Slot(playerInv, playerSlotIndex, 8 + col * 18, 103 + row * 18 + 36));
            }
        }

        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInv, col, 8 + col * 18, 197));
        }
    }
    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    public List<StorageEntry> getEntries() {
        return entries;
    }

    public List<StorageEntry> getVisibleEntries() {
        int start = MathHelper.clamp(this.scrollOffset, 0, Math.max(0, this.entries.size() - 1));
        int end = Math.min(start + STORAGE_SLOT_COUNT, this.entries.size());
        return this.entries.subList(start, end);
    }

    public void refreshSlots() {
        List<StorageEntry> visible = getVisibleEntries();

        int storageSlotsToUpdate = Math.min(STORAGE_SLOT_COUNT, slots.size());

        for (int i = 0; i < storageSlotsToUpdate; i++) {
            Slot slot = slots.get(i);
            if (i < visible.size()) {
                slot.setStack(visible.get(i).toStack(1));
            } else {
                slot.setStack(ItemStack.EMPTY);
            }
        }
    }

    public int getStorageSlotCount() {
        return STORAGE_SLOT_COUNT;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);

        if (player instanceof ServerPlayerEntity serverPlayer) {
            GlobalStorageManager manager = GlobalStorageManager.get(serverPlayer.getServerWorld());
            manager.removeListener(serverPlayer);
        }
    }

    public void updateEntries(List<StorageEntry> newEntries) {
        this.entries = newEntries;
        int maxOffset = Math.max(0, entries.size() - STORAGE_SLOT_COUNT);
        this.scrollOffset = MathHelper.clamp(this.scrollOffset, 0, maxOffset);
        refreshSlots();
    }
}
