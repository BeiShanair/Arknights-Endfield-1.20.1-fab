package com.besson.endfield.utils;

import com.besson.endfield.item.ModItems;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

public class JoinHandler {
    private static final String HAS_RECEIVED_PROTOCOL = "has_received_protocol";

    public static void register() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            ServerPlayerEntity player = handler.getPlayer();
            IPlayerData playerData = (IPlayerData) player;

            if (!playerData.hasReceivedProtocol()) {
                player.giveItemStack(new ItemStack(ModItems.PROTOCOL_ANCHOR_CORE_ITEM));
                playerData.setHasReceivedProtocol(true);
            }
        });
    }
}
