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
    public double supplyRatio;
    public int totalGenerated;
    public int totalDemand;
    public int storedEnergy;

    public ProtocolAnchorCoreScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf packetByteBuf) {
        this(syncId, playerInventory, playerInventory.player.getWorld().getBlockEntity(packetByteBuf.readBlockPos()),
                new ArrayPropertyDelegate(4));
        this.supplyRatio = packetByteBuf.readDouble();
        this.totalGenerated = packetByteBuf.readInt();
        this.totalDemand = packetByteBuf.readInt();
        this.storedEnergy = packetByteBuf.readInt();
    }
    public ProtocolAnchorCoreScreenHandler(int syncId, PlayerInventory playerInventory, BlockEntity blockEntity, PropertyDelegate propertyDelegate) {
        super(ModScreens.PROTOCOL_ANCHOR_CORE_SCREEN, syncId);
        this.propertyDelegate = propertyDelegate;
        this.entity = (ProtocolAnchorCoreBlockEntity) blockEntity;
        this.pos = this.entity.getPos();

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
}
