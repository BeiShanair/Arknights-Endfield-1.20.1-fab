package com.besson.endfield.screen.custom.screenHandler;

import com.besson.endfield.blockentity.custom.production2.ExpandedCrucibleBlockEntity;
import com.besson.endfield.screen.ModScreens;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;

import java.util.Objects;

public class ExpandedCrucibleScreenHandler extends BaseCrucibleScreenHandler {
    public ExpandedCrucibleScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, Objects.requireNonNull(getCrucibleEntity(playerInventory, buf)));
    }

    public ExpandedCrucibleScreenHandler(int syncId, PlayerInventory playerInventory, ExpandedCrucibleBlockEntity blockEntity) {
        super(ModScreens.EXPANDED_CRUCIBLE_SCREEN, syncId, playerInventory, blockEntity);
    }

    @Environment(EnvType.CLIENT)
    private static ExpandedCrucibleBlockEntity getCrucibleEntity(PlayerInventory playerInventory, PacketByteBuf buf) {
        return readCrucibleBlockEntity(playerInventory, buf) instanceof ExpandedCrucibleBlockEntity reactor ? reactor : null;
    }

    @Override
    public ExpandedCrucibleBlockEntity getBlockEntity() {
        return (ExpandedCrucibleBlockEntity) super.getBlockEntity();
    }
}
