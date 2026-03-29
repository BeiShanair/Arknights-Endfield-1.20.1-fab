package com.besson.endfield.utils;

import com.besson.endfield.block.ModBlocks;
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
            if (!playerData.hasReceivedOriginiumOre()) {
                player.giveItemStack(new ItemStack(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK, 10));
                playerData.setHasReceivedOriginiumOre(true);
            }
            if (!playerData.hasReceivedAmethystOre()) {
                player.giveItemStack(new ItemStack(ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK, 10));
                playerData.setHasReceivedAmethystOre(true);
            }
            if (!playerData.hasReceivedFerriumOre()) {
                player.giveItemStack(new ItemStack(ModBlocks.FERRIUM_MINERAL_VEIN_BLOCK, 10));
                playerData.setHasReceivedFerriumOre(true);
            }
        });
    }
}
