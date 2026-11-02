package com.besson.endfield.screen.custom.screenHandler;

import com.besson.endfield.blockentity.custom.production1.RefiningUnitBlockEntity;
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

public class RefiningUnitScreenHandler extends BaseIOScreenHandler<RefiningUnitBlockEntity> {
    public RefiningUnitScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(syncId, playerInventory, Objects.requireNonNull(getClientEntity(playerInventory, packetByteBuf)), new ArrayPropertyDelegate(11));
    }

    public RefiningUnitScreenHandler(int syncId, PlayerInventory playerInventory, RefiningUnitBlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreens.REFINING_UNIT_SCREEN, syncId, playerInventory, blockEntity, propertyDelegate, 2);
        
        this.addSlot(new Slot(inputInv, 0, 80, 11));
        this.addSlot(new Slot(outputInv, 0, 80, 59) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
    }

    @Environment(EnvType.CLIENT)
    private static RefiningUnitBlockEntity getClientEntity(PlayerInventory playerInventory, PacketByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        BlockEntity be = playerInventory.player.getWorld().getBlockEntity(pos);
        return be instanceof RefiningUnitBlockEntity e ? e : null;
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

    public boolean nowFluidMode() {
        return propertyDelegate.get(8) == 1;
    }

    public int getFluidAmount() {
        return propertyDelegate.get(3);
    }

    public int getFluidCapacity() {
        return propertyDelegate.get(4);
    }

    public int getSewageAmount() {
        return propertyDelegate.get(5);
    }

    public int getSewageCapacity() {
        return propertyDelegate.get(6);
    }

    public int getStoredPower() {
        return this.propertyDelegate.get(7);
    }

    /** 原料罐内流体的注册表 ID（空罐时为水），用于 GUI 按类型取色 */
    public int getFluidId() {
        return this.propertyDelegate.get(9);
    }

    /** 污水罐内流体的注册表 ID（空罐时为水），用于 GUI 按类型取色 */
    public int getSewageId() {
        return this.propertyDelegate.get(10);
    }
}
