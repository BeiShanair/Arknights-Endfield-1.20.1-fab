package com.besson.endfield.screen.custom;

import com.besson.endfield.blockentity.custom.FluidTankBlockEntity;
import com.besson.endfield.screen.ModScreens;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class FluidTankScreenHandler extends ScreenHandler {
    private final FluidTankBlockEntity entity;
    private final PropertyDelegate propertyDelegate;

    public FluidTankScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf byteBuf) {
        this(syncId, playerInventory, (FluidTankBlockEntity) playerInventory.player.getWorld().getBlockEntity(byteBuf.readBlockPos()),
                new ArrayPropertyDelegate(2));
    }

    public FluidTankScreenHandler(int syncId, PlayerInventory playerInventory, FluidTankBlockEntity entity, PropertyDelegate propertyDelegate) {
        super(ModScreens.FLUID_TANK_SCREEN, syncId);
        this.entity = entity;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    public int getFluidAmount() {
        return this.propertyDelegate.get(0);
    }
    public int getFluidCapacity() {
        return this.propertyDelegate.get(1);
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

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
