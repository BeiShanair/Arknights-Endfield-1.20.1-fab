package com.besson.endfield.screen.custom.screenHandler;

import com.besson.endfield.blockentity.custom.production2.FillingUnitBlockEntity;
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

public class FillingUnitScreenHandler extends BaseIOScreenHandler<FillingUnitBlockEntity> {
    public FillingUnitScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(syncId, playerInventory, Objects.requireNonNull(getBlockEntity(playerInventory, packetByteBuf)),
                new ArrayPropertyDelegate(3));
    }

    public FillingUnitScreenHandler(int syncId, PlayerInventory playerInventory, FillingUnitBlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreens.FILLING_UNIT_SCREEN, syncId, playerInventory, blockEntity, propertyDelegate, 3);

        this.addSlot(new Slot(inputInv, 0, 47, 22));
        this.addSlot(new Slot(inputInv, 1, 47, 49));
        this.addSlot(new Slot(outputInv, 0, 113, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
    }

    @Environment(EnvType.CLIENT)
    private static FillingUnitBlockEntity getBlockEntity(PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        BlockPos pos = packetByteBuf.readBlockPos();
        BlockEntity be = playerInventory.player.getWorld().getBlockEntity(pos);
        return be instanceof FillingUnitBlockEntity e ? e : null;
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
}
