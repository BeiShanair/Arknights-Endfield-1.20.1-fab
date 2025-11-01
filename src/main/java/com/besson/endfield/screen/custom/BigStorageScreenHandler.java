package com.besson.endfield.screen.custom;

import com.besson.endfield.blockentity.custom.BigStorageBlockEntity;
import com.besson.endfield.screen.LargeStackSlot;
import com.besson.endfield.screen.ModScreens;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BigStorageScreenHandler extends ScreenHandler {
    private final BigStorageBlockEntity entity;
    private final  PlayerEntity player;
    private List<ItemVariant> variants = new ArrayList<>(BigStorageBlockEntity.MAX_SLOTS);
    public static final int ITEMS_PER_PAGE = 54;
    private int page = 0;

    public BigStorageScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf byteBuf) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(byteBuf.readBlockPos()));
    }

    public BigStorageScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(ModScreens.BIG_STORAGE_SCREEN, syncId);
        this.entity = (BigStorageBlockEntity) blockEntity;
        this.player = playerInventory.player;

        updateDisplayedItems();
    }

    private void updateDisplayedItems() {
        this.slots.clear(); // 清除之前的槽
        variants.clear();

        variants.addAll(entity.getStorage().keySet());
        variants.sort(Comparator.comparing(v -> v.getItem().toString()));

        while (variants.size() < BigStorageBlockEntity.MAX_SLOTS) {
            variants.add(ItemVariant.blank());
        }

        int start = page * ITEMS_PER_PAGE;
        int end = Math.min(start + ITEMS_PER_PAGE, variants.size());

        for (int i = start; i < end; i++) {
            ItemVariant variant = variants.get(i);
            int index = i - start;
            int x = 8 + (index % 9) * 18;
            int y = 18 + (index / 9) * 18;
            this.addSlot(new LargeStackSlot(entity,(Inventory) entity, variant, i, x, y));
        }

        addPlayerInventorySlots();
    }

    public void nextPage() {
        if ((page + 1) * ITEMS_PER_PAGE < BigStorageBlockEntity.MAX_SLOTS) {
            page++;
            updateDisplayedItems();
        }
    }

    public void previousPage() {
        if (page > 0) {
            page--;
            updateDisplayedItems();
        }
    }

    private void addPlayerInventorySlots() {
        int i = 36;
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 9; k++) {
                this.addSlot(new Slot(player.getInventory(), k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
            }
        }
        for (int j = 0; j < 9; j++) {
            this.addSlot(new Slot(player.getInventory(), j, 8 + j * 18, 161 + i));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
