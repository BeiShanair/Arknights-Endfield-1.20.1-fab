package com.besson.endfield.network;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.screen.custom.CrafterScreenHandler;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class ModNetWorking {
    public static final Identifier CYCLE_RECIPE_PACKET_ID = new Identifier(ArknightsEndfield.MOD_ID, "cycle_recipe");

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(CYCLE_RECIPE_PACKET_ID, (minecraftServer, serverPlayerEntity, serverPlayNetworkHandler, packetByteBuf, packetSender) -> {
            if (serverPlayerEntity.currentScreenHandler instanceof CrafterScreenHandler screenHandler) {
                minecraftServer.execute(screenHandler::changeRecipe);
            }
        });
    }
}
