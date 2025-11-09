package com.besson.endfield.screen.custom;

import com.besson.endfield.screen.ModScreens;
import com.besson.endfield.trade.SupplyTrade;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

import java.util.List;

public class SupplyTerminalScreenHandler extends ScreenHandler {
    public final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public SupplyTerminalScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(packetByteBuf.readBlockPos()),
                new ArrayPropertyDelegate(2));
    }
    public SupplyTerminalScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreens.SUPPLY_TERMINAL_SCREEN, syncId);
        this.inventory = (Inventory) blockEntity;
        this.propertyDelegate = propertyDelegate;
        addProperties(propertyDelegate);

        this.addSlot(new Slot(this.inventory, 0, 136, 37));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 108 + j * 18, 84 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 108 + i * 18, 142));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    public int getLevel() {
        return propertyDelegate.get(0);
    }
    public int getTradePoints() {
        return propertyDelegate.get(1);
    }

    public List<SupplyTrade> getAvailableTrades() {
        return SupplyTrade.getTradesForLevel(getLevel());
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return inventory.canPlayerUse(player);
    }
}
