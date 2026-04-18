package com.besson.endfield.screen.custom.screenHandler;

import com.besson.endfield.blockentity.custom.powering.ThermalBankBlockEntity;
import com.besson.endfield.screen.ModScreens;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class ThermalBankScreenHandler extends ScreenHandler {
    private final SimpleInventory inputInv;
    private final PropertyDelegate propertyDelegate;
    public final ThermalBankBlockEntity entity;

    public  ThermalBankScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, Objects.requireNonNull(getClientEntity(playerInventory, buf)), 
                new ArrayPropertyDelegate(3));
    }

    public ThermalBankScreenHandler(int syncId, PlayerInventory playerInventory, ThermalBankBlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreens.THERMAL_BANK_SCREEN, syncId);
        checkSize(playerInventory, 1);
        this.inputInv = blockEntity.getInputInv();
        this.propertyDelegate = propertyDelegate;
        this.entity = blockEntity;

        this.addSlot(new Slot(inputInv, 0, 104, 37));

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(propertyDelegate);
    }

    @Environment(EnvType.CLIENT)
    private static ThermalBankBlockEntity getClientEntity(PlayerInventory playerInventory, PacketByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        BlockEntity be = playerInventory.player.getWorld().getBlockEntity(pos);
        return be instanceof ThermalBankBlockEntity e ? e : null;
    }
    
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inputInv.size()) {
                if (!this.insertItem(originalStack, this.inputInv.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inputInv.size(), false)) {
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
        return this.entity != null
                && this.entity.getWorld() != null
                && this.entity.getPos().isWithinDistance(player.getBlockPos(), 8);
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

    public boolean isEnabled() {
        return propertyDelegate.get(2) == 1;
    }

    public void setEnabled(boolean enabled) {
        propertyDelegate.set(2, enabled ? 1 : 0);
    }

    
    public boolean isBurning(){
        return propertyDelegate.get(0) > 0;
    }

    public int getScaledProgress() {
        int burnTime = this.propertyDelegate.get(0);
        int fuelTime = this.propertyDelegate.get(1);
        int progressArrowSize = 14;
        return burnTime != 0 && fuelTime != 0 ? burnTime * progressArrowSize / fuelTime : 0;
    }
}
