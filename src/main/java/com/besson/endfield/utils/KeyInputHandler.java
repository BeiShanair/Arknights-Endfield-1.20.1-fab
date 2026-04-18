package com.besson.endfield.utils;

import com.besson.endfield.network.ModNetWorking;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;

public class KeyInputHandler {
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (ModKeyBindings.OPEN_STORAGE.wasPressed()) {
                sendOpenPacket();
            }
        });
    }

    private static void sendOpenPacket() {
        ClientPlayNetworking.send(ModNetWorking.STORAGE_OPEN_ID, PacketByteBufs.empty());
    }
}
