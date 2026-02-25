package com.besson.endfield.screen.custom;

import com.besson.endfield.blockentity.custom.BaseIOBlockEntity;
import com.besson.endfield.blockentity.custom.BaseRigBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

public abstract class BaseRigScreenHandler<B extends BaseRigBlockEntity<?>> extends ScreenHandler {
    protected final SimpleInventory outputInv;
    protected final PropertyDelegate propertyDelegate;
    
    public final B entity;
    public BaseRigScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, B blockEntity, PropertyDelegate propertyDelegate, int size) {
        super(type, syncId);
        checkSize(playerInventory, size);
        this.outputInv = blockEntity.getOutputInv();
        this.propertyDelegate = propertyDelegate;
        this.entity = blockEntity;
        
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);

        addProperties(propertyDelegate);
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

    public boolean isCrafting(){
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
        int progressArrowSize = 26;

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }
}
