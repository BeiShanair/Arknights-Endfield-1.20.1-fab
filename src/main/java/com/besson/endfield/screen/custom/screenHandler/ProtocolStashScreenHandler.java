package com.besson.endfield.screen.custom.screenHandler;

import com.besson.endfield.blockentity.custom.logicitis.ProtocolStashBlockEntity;
import com.besson.endfield.screen.ModScreens;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class ProtocolStashScreenHandler extends ScreenHandler {
    public final ProtocolStashBlockEntity entity;
    private final Inventory inventory;
    protected final PropertyDelegate propertyDelegate;
    
    public ProtocolStashScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, Objects.requireNonNull(getClientEntity(playerInventory, buf)),
                new ArrayPropertyDelegate(1));
    }
    public ProtocolStashScreenHandler(int syncId, PlayerInventory playerInventory, ProtocolStashBlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreens.PROTOCOL_STASH_SCREEN, syncId);
        this.entity = blockEntity;
        this.inventory = blockEntity.getInventory();
        this.propertyDelegate = propertyDelegate;

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 9; k++) {
                this.addSlot(new Slot(this.inventory, k + j * 9, 8 + k * 18, j * 18 + 18));
            }
        }
        
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        addProperties(propertyDelegate);
    }

    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }
    
    @Environment(EnvType.CLIENT)
    private static ProtocolStashBlockEntity getClientEntity(PlayerInventory playerInventory, PacketByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        BlockEntity be = playerInventory.player.getWorld().getBlockEntity(pos);
        return be instanceof ProtocolStashBlockEntity e ? e : null;
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

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public boolean isEnabled() {
        return propertyDelegate.get(0) == 1;
    }

    public void setEnabled(boolean enabled) {
        propertyDelegate.set(0, enabled ? 1 : 0);
    }

}
