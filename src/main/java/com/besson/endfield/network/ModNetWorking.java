package com.besson.endfield.network;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.*;
import com.besson.endfield.blockentity.custom.logicitis.ProtocolStashBlockEntity;
import com.besson.endfield.blockentity.custom.powering.ThermalBankBlockEntity;
import com.besson.endfield.blockentity.custom.resourcing.BaseRigBlockEntity;
import com.besson.endfield.screen.custom.StorageScreenHandlerFactory;
import com.besson.endfield.screen.custom.screenHandler.CrafterScreenHandler;
import com.besson.endfield.utils.storage.GlobalStorageManager;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModNetWorking {
    public static final Identifier CYCLE_RECIPE_PACKET_ID = new Identifier(ArknightsEndfield.MOD_ID, "cycle_recipe");
    public static final Identifier SUPPLY_TERMINAL_SYNC_PACKET_ID = new Identifier(ArknightsEndfield.MOD_ID, "supply_terminal_sync");
    public static final Identifier SWITCH_PACKET_ID = new Identifier(ArknightsEndfield.MOD_ID, "switch_packet");
    public static final Identifier STORAGE_OPEN_ID = new Identifier(ArknightsEndfield.MOD_ID, "storage_open");
    public static final Identifier REQUEST_ITEM = new Identifier(ArknightsEndfield.MOD_ID, "request_item");
    public static final Identifier SYNC_STORAGE = new Identifier(ArknightsEndfield.MOD_ID, "sync_storage");
    
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

        ServerPlayNetworking.registerGlobalReceiver(SWITCH_PACKET_ID, (minecraftServer, serverPlayerEntity, serverPlayNetworkHandler, packetByteBuf, packetSender) -> {
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
                if (be instanceof ProtocolStashBlockEntity ps) {
                    ps.setEnable(enable);
                }
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(STORAGE_OPEN_ID, (minecraftServer, serverPlayerEntity, serverPlayNetworkHandler, packetByteBuf, packetSender) -> {
            minecraftServer.execute(() -> {
                serverPlayerEntity.openHandledScreen(new StorageScreenHandlerFactory());
            });
        });
        
        ServerPlayNetworking.registerGlobalReceiver(REQUEST_ITEM, (server, player, handler, buf, responseSender) -> {
            Identifier id = buf.readIdentifier();
            int amount = buf.readInt();
            server.execute(() -> {
                Item item = Registries.ITEM.get(id);
                ItemStack extracted = GlobalStorageManager.get(player.getServerWorld()).extract(item, amount);
                
                if (!player.getInventory().insertStack(extracted)) {
                    player.dropItem(extracted, false);
                }
            });
        });
    }
}
