package com.besson.endfield.screen.custom.screenHandler;

import com.besson.endfield.blockentity.custom.production2.ReactorCrucibleBlockEntity;
import com.besson.endfield.screen.ModScreens;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;

import java.util.Objects;

public class ReactorCrucibleScreenHandler extends BaseCrucibleScreenHandler {
    public ReactorCrucibleScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, Objects.requireNonNull(getCrucibleEntity(playerInventory, buf)));
    }

    public ReactorCrucibleScreenHandler(int syncId, PlayerInventory playerInventory, ReactorCrucibleBlockEntity blockEntity) {
        super(ModScreens.REACTOR_CRUCIBLE_SCREEN, syncId, playerInventory, blockEntity);
    }

    @Environment(EnvType.CLIENT)
    private static ReactorCrucibleBlockEntity getCrucibleEntity(PlayerInventory playerInventory, PacketByteBuf buf) {
        return readCrucibleBlockEntity(playerInventory, buf) instanceof ReactorCrucibleBlockEntity reactor ? reactor : null;
    }

    @Override
    public ReactorCrucibleBlockEntity getBlockEntity() {
        return (ReactorCrucibleBlockEntity) super.getBlockEntity();
    }
}
