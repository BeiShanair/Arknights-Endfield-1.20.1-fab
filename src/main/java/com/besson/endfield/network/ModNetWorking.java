package com.besson.endfield.network;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.SupplyTerminalBlockEntity;
import com.besson.endfield.screen.custom.CrafterScreenHandler;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModNetWorking {
    public static final Identifier CYCLE_RECIPE_PACKET_ID = new Identifier(ArknightsEndfield.MOD_ID, "cycle_recipe");
    public static final Identifier SUPPLY_TERMINAL_SYNC_PACKET_ID = new Identifier(ArknightsEndfield.MOD_ID, "supply_terminal_sync");

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(CYCLE_RECIPE_PACKET_ID, (minecraftServer, serverPlayerEntity, serverPlayNetworkHandler, packetByteBuf, packetSender) -> {
            if (serverPlayerEntity.currentScreenHandler instanceof CrafterScreenHandler screenHandler) {
                minecraftServer.execute(screenHandler::changeRecipe);
            }
        });

        ServerPlayNetworking.registerGlobalReceiver(SUPPLY_TERMINAL_SYNC_PACKET_ID, (minecraftServer, serverPlayerEntity, serverPlayNetworkHandler, packetByteBuf, packetSender) -> {
            BlockPos pos = packetByteBuf.readBlockPos();
            minecraftServer.execute(() -> {
                if (serverPlayerEntity.getWorld().getBlockEntity(pos) instanceof SupplyTerminalBlockEntity be) {
                    be.tryTrade(serverPlayerEntity);
                }
            });
        });
    }
}
