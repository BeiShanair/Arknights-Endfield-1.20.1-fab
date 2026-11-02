package com.besson.endfield.screen.custom.screenHandler;

import com.besson.endfield.blockentity.custom.production1.WaterTreatmentUnitBlockEntity;
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
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class WaterTreatmentUnitScreenHandler extends ScreenHandler {
    private static final int PROGRESS_ARROW_SIZE = 26;

    public final WaterTreatmentUnitBlockEntity entity;
    private final PropertyDelegate propertyDelegate;

    public WaterTreatmentUnitScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory,
                Objects.requireNonNull(getClientEntity(playerInventory, buf)),
                new ArrayPropertyDelegate(8));
    }

    public WaterTreatmentUnitScreenHandler(int syncId, PlayerInventory playerInventory,
                                           WaterTreatmentUnitBlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreens.WATER_TREATMENT_UNIT_SCREEN, syncId);
        this.entity = blockEntity;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
    }

    @Environment(EnvType.CLIENT)
    private static WaterTreatmentUnitBlockEntity getClientEntity(PlayerInventory playerInventory, PacketByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        BlockEntity be = playerInventory.player.getWorld().getBlockEntity(pos);
        return be instanceof WaterTreatmentUnitBlockEntity e ? e : null;
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
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.entity != null
                && this.entity.getWorld() != null
                && this.entity.getPos().isWithinDistance(player.getBlockPos(), 8);
    }

    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    public boolean isEnabled() {
        return propertyDelegate.get(2) == 1;
    }

    public void setEnabled(boolean enabled) {
        propertyDelegate.set(2, enabled ? 1 : 0);
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        return maxProgress != 0 && progress != 0 ? progress * PROGRESS_ARROW_SIZE / maxProgress : 0;
    }

    public int getFluidAmount() {
        return propertyDelegate.get(3);
    }

    public int getFluidCapacity() {
        return propertyDelegate.get(4);
    }

    public int getStoredPower() {
        return propertyDelegate.get(5);
    }

    public int getFluidId() {
        return propertyDelegate.get(7);
    }
}
