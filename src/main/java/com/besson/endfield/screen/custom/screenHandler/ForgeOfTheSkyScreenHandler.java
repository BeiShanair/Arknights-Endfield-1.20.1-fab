package com.besson.endfield.screen.custom.screenHandler;

import com.besson.endfield.blockentity.custom.production2.ForgeOfTheSkyBlockEntity;
import com.besson.endfield.screen.ModScreens;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class ForgeOfTheSkyScreenHandler extends BaseIOScreenHandler<ForgeOfTheSkyBlockEntity> {
    public ForgeOfTheSkyScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(syncId, playerInventory, Objects.requireNonNull(getClientEntity(playerInventory, packetByteBuf)),
                new ArrayPropertyDelegate(7));
    }

    public ForgeOfTheSkyScreenHandler(int syncId, PlayerInventory playerInventory, ForgeOfTheSkyBlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreens.FORGE_OF_THE_SKY_SCREEN, syncId, playerInventory, blockEntity, propertyDelegate, 1);

        this.addSlot(new Slot(inputInv, 0, 80, 11));
        this.addSlot(new Slot(outputInv, 0, 80, 59) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
    }

    @Environment(EnvType.CLIENT)
    private static ForgeOfTheSkyBlockEntity getClientEntity(PlayerInventory playerInventory, PacketByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        BlockEntity be = playerInventory.player.getWorld().getBlockEntity(pos);
        return be instanceof ForgeOfTheSkyBlockEntity e ? e : null;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            
            int machineStart = this.slots.size() - inputInv.size() - outputInv.size();
            if (invSlot >= machineStart) {
                if (!this.insertItem(originalStack, 0, machineStart, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, machineStart, machineStart + inputInv.size(), false)) {
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

    public int getStoredPower() {
        return propertyDelegate.get(3);
    }

    public int getFluidAmount() {
        return propertyDelegate.get(4);
    }

    public int getFluidCapacity() {
        return propertyDelegate.get(5);
    }
    
    public int getFluidId() {
        return propertyDelegate.get(6);
    }
}
