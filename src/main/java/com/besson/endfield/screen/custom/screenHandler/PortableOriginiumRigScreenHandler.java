package com.besson.endfield.screen.custom.screenHandler;

import com.besson.endfield.blockentity.custom.resourcing.PortableOriginiumRigBlockEntity;
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

public class PortableOriginiumRigScreenHandler extends BaseRigScreenHandler<PortableOriginiumRigBlockEntity> { 
    public PortableOriginiumRigScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, Objects.requireNonNull(getClientEntity(playerInventory, buf)),
                new ArrayPropertyDelegate(3));
    }

    public PortableOriginiumRigScreenHandler(int syncId, PlayerInventory playerInventory, PortableOriginiumRigBlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreens.PORTABLE_ORIGINIUM_RIG_SCREEN, syncId, playerInventory, blockEntity, propertyDelegate, 1);

        this.addSlot(new Slot(outputInv, 0, 104, 37));
    }

    @Environment(EnvType.CLIENT)
    private static PortableOriginiumRigBlockEntity getClientEntity(PlayerInventory playerInventory, PacketByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        BlockEntity be = playerInventory.player.getWorld().getBlockEntity(pos);
        return be instanceof PortableOriginiumRigBlockEntity e ? e : null;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.outputInv.size()) {
                if (!this.insertItem(originalStack, this.outputInv.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.outputInv.size(), false)) {
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
