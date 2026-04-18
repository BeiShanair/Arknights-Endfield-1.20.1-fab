package com.besson.endfield.screen.custom.screenHandler;

import com.besson.endfield.blockentity.custom.powering.ProtocolAnchorCorePortBlockEntity;
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

public class ProtocolAnchorCorePortScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public final ProtocolAnchorCorePortBlockEntity entity;

    public ProtocolAnchorCorePortScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, Objects.requireNonNull(getClientEntity(playerInventory, buf)),
                new ArrayPropertyDelegate(1));
    }

    public ProtocolAnchorCorePortScreenHandler(int syncId, PlayerInventory playerInventory, ProtocolAnchorCorePortBlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreens.PROTOCOL_ANCHOR_CORE_PORT_SCREEN, syncId);
        checkSize(playerInventory, 1);
        this.inventory = blockEntity.getFilterInventory();
        this.propertyDelegate = propertyDelegate;
        this.entity = blockEntity;
        
        this.addSlot(new Slot(inventory, 0, 104, 37) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return true;
            }

            @Override
            public boolean canTakeItems(PlayerEntity playerEntity) {
                return true;
            }

            @Override
            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                entity.clearFilter();
            }

            @Override
            public void markDirty() {
                super.markDirty();
                ProtocolAnchorCorePortScreenHandler.this.onContentChanged(this.inventory);
                if (!inventory.getStack(0).isEmpty()) {
                    entity.setFilter(inventory.getStack(0));
                } else {
                    entity.clearFilter();
                }
            }
        });

        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(propertyDelegate);
    }

    @Environment(EnvType.CLIENT)
    private static ProtocolAnchorCorePortBlockEntity getClientEntity(PlayerInventory playerInventory, PacketByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        BlockEntity be = playerInventory.player.getWorld().getBlockEntity(pos);
        return be instanceof ProtocolAnchorCorePortBlockEntity e ? e : null;
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
    public boolean canUse(PlayerEntity player) {
        return this.entity != null
                && this.entity.getWorld() != null
                && this.entity.getPos().isWithinDistance(player.getBlockPos(), 8);
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
}
