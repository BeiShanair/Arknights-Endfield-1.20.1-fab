package com.besson.endfield.network;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.*;
import com.besson.endfield.screen.custom.CrafterScreenHandler;
import com.mojang.datafixers.kinds.IdF;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModNetWorking {
    public static final Identifier CYCLE_RECIPE_PACKET_ID = new Identifier(ArknightsEndfield.MOD_ID, "cycle_recipe");
    public static final Identifier SUPPLY_TERMINAL_SYNC_PACKET_ID = new Identifier(ArknightsEndfield.MOD_ID, "supply_terminal_sync");
    public static final Identifier SWITCH_PACKET_ID = new Identifier(ArknightsEndfield.MOD_ID, "switch_packet");

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

        ServerPlayNetworking.registerGlobalReceiver(SWITCH_PACKET_ID, ((minecraftServer, serverPlayerEntity, serverPlayNetworkHandler, packetByteBuf, packetSender) -> {
            BlockPos pos = packetByteBuf.readBlockPos();
            boolean enable = packetByteBuf.readBoolean();
            minecraftServer.execute(() -> {
                BlockEntity be = serverPlayerEntity.getWorld().getBlockEntity(pos);
                if (be instanceof BaseRigBlockEntity<?> rig) {
                    rig.setEnable(enable);
                }
                if (be instanceof BaseIOBlockEntity<?> b) {
                    b.setEnable(enable);
                }
                if (be instanceof ThermalBankBlockEntity tb) {
                    tb.setEnable(enable);
                }
            });
        }));
    }
}
