package com.besson.endfield.screen.custom;

import com.besson.endfield.blockentity.custom.ProtocolAnchorCoreBlockEntity;
import com.besson.endfield.screen.LargeStackSlot;
import com.besson.endfield.screen.ModScreens;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class ProtocolAnchorCoreScreenHandler extends ScreenHandler {
    public final ProtocolAnchorCoreBlockEntity entity;
    private final BlockPos pos;
    public double supplyRatio;
    public int totalGenerated;
    public int totalDemand;
    public int storedEnergy;
    private final Inventory inventory;

    public ProtocolAnchorCoreScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(packetByteBuf.readBlockPos()));
        this.supplyRatio = packetByteBuf.readDouble();
        this.totalGenerated = packetByteBuf.readInt();
        this.totalDemand = packetByteBuf.readInt();
        this.storedEnergy = packetByteBuf.readInt();
    }

    public ProtocolAnchorCoreScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity) {
        super(ModScreens.PROTOCOL_ANCHOR_CORE_SCREEN, syncId);
        checkSize((Inventory) blockEntity, 54);
        this.inventory = (Inventory) blockEntity;
        inventory.onOpen(playerInventory.player);
        this.entity = (ProtocolAnchorCoreBlockEntity) blockEntity;
        this.pos = this.entity.getPos();

        int i = 36;
        for (int j = 0; j < 6; j++) {
            for (int k = 0; k < 9; k++) {
                this.addSlot(new Slot(this.inventory, k + j * 9, 8 + k * 18, j * 18 + 18));
            }
        }

        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 9; k++) {
                this.addSlot(new Slot(playerInventory, k + j * 9 + 9, 8 + k * 18, 103 + j * 18 + i));
            }
        }

        for (int j = 0; j < 9; j++) {
            this.addSlot(new Slot(playerInventory, j, 8 + j * 18, 161 + i));
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

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public BlockPos getPos() {
        return pos;
    }
}
