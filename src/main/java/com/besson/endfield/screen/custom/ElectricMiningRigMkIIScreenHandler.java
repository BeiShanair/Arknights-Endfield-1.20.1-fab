package com.besson.endfield.screen.custom;

import com.besson.endfield.blockentity.custom.ElectricMiningRigMkIIBlockEntity;
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

public class ElectricMiningRigMkIIScreenHandler extends BaseRigScreenHandler<ElectricMiningRigMkIIBlockEntity> {
    public ElectricMiningRigMkIIScreenHandler(int sync, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(sync, playerInventory, Objects.requireNonNull(getClientEntity(playerInventory, packetByteBuf)),
                new ArrayPropertyDelegate(3));
    }
    public ElectricMiningRigMkIIScreenHandler(int syncId, PlayerInventory playerInventory, ElectricMiningRigMkIIBlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreens.ELECTRIC_MINING_RIG_MK_II_SCREEN, syncId,playerInventory, blockEntity, propertyDelegate, 1);

        this.addSlot(new Slot(outputInv, 0, 104, 37) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
    }

    @Environment(EnvType.CLIENT)
    private static ElectricMiningRigMkIIBlockEntity getClientEntity(PlayerInventory playerInventory, PacketByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        BlockEntity be = playerInventory.player.getWorld().getBlockEntity(pos);
        return be instanceof ElectricMiningRigMkIIBlockEntity e ? e : null;
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
