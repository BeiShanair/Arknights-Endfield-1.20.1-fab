package com.besson.endfield.screen.custom;

import com.besson.endfield.blockentity.custom.ProtocolAnchorCoreBlockEntity;
import com.besson.endfield.screen.ModScreens;
import com.besson.endfield.utils.ProtocolAnchorCoreStatus;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.math.BlockPos;

public class ProtocolAnchorCoreScreenHandler extends ScreenHandler {
    private final PropertyDelegate propertyDelegate;
    public final ProtocolAnchorCoreBlockEntity entity;
    private final BlockPos pos;
    private ProtocolAnchorCoreStatus status;

    public ProtocolAnchorCoreScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(packetByteBuf.readBlockPos()),
                new ArrayPropertyDelegate(5));
    }
    public ProtocolAnchorCoreScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreens.PROTOCOL_ANCHOR_CORE_SCREEN, syncId);
        this.propertyDelegate = propertyDelegate;
        this.entity = (ProtocolAnchorCoreBlockEntity) blockEntity;
        this.pos = this.entity.getPos();
        this.status = this.entity.getStatus();

        addProperties(propertyDelegate);

    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return entity != null;
    }

    public BlockPos getPos() {
        return pos;
    }

    public ProtocolAnchorCoreStatus getStatus() {
        return new ProtocolAnchorCoreStatus(propertyDelegate.get(0),
                propertyDelegate.get(1),
                propertyDelegate.get(2),
                propertyDelegate.get(3),
                propertyDelegate.get(4));
    }
}
