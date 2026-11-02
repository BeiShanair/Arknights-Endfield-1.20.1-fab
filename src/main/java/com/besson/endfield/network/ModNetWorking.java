package com.besson.endfield.network;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.blockentity.custom.*;
import com.besson.endfield.blockentity.custom.depot.FluidTankBlockEntity;
import com.besson.endfield.blockentity.custom.depot.ProtocolStashBlockEntity;
import com.besson.endfield.blockentity.custom.powering.ThermalBankBlockEntity;
import com.besson.endfield.blockentity.custom.production1.PlantingUnitBlockEntity;
import com.besson.endfield.blockentity.custom.production1.RefiningUnitBlockEntity;
import com.besson.endfield.blockentity.custom.production1.WaterTreatmentUnitBlockEntity;
import com.besson.endfield.blockentity.custom.production2.BaseCrucibleBlockEntity;
import com.besson.endfield.blockentity.custom.production2.FillingUnitBlockEntity;
import com.besson.endfield.blockentity.custom.resourcing.BaseRigBlockEntity;
import com.besson.endfield.blockentity.custom.resourcing.FluidPumpBlockEntity;
import com.besson.endfield.blockentity.custom.resourcing.AcidResistantPumpMkIIBlockEntity;
import com.besson.endfield.screen.custom.StorageScreenHandlerFactory;
import com.besson.endfield.screen.custom.screenHandler.BaseCrucibleScreenHandler;
import com.besson.endfield.screen.custom.screenHandler.CrafterScreenHandler;
import com.besson.endfield.screen.custom.screenHandler.StorageScreenHandler;
import com.besson.endfield.utils.storage.GlobalStorageManager;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
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
    public static final Identifier DEPOSIT_ITEM = new Identifier(ArknightsEndfield.MOD_ID, "deposit_item");
    public static final Identifier FILL_BUCKET_FROM_PUMP = new Identifier(ArknightsEndfield.MOD_ID, "fill_bucket_from_pump");
    public static final Identifier FILL_BUCKET_FROM_ACID_PUMP = new Identifier(ArknightsEndfield.MOD_ID, "fill_bucket_from_acid_pump");
    public static final Identifier TANK_BOTTLE_TRANSFER = new Identifier(ArknightsEndfield.MOD_ID, "tank_bottle_transfer");
    public static final Identifier CRUCIBLE_SET_OUTPUT = new Identifier(ArknightsEndfield.MOD_ID, "crucible_set_output");
    public static final Identifier CRUCIBLE_CLEAR_OUTPUT = new Identifier(ArknightsEndfield.MOD_ID, "crucible_clear_output");
    public static final Identifier FLUID_MODE_CHANGE = new Identifier(ArknightsEndfield.MOD_ID, "fluid_mode_change");

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
                if (be instanceof WaterTreatmentUnitBlockEntity wtu) {
                    wtu.setEnable(enable);
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
            Item item = Registries.ITEM.get(buf.readIdentifier());
            NbtCompound tag = buf.readNbt();
            int amount = buf.readInt();
            server.execute(() -> {
                if (item == Items.AIR || amount <= 0) return;

                ItemStack prototype = new ItemStack(item);
                if (tag != null) prototype.setNbt(tag);
                
                ItemStack extracted = GlobalStorageManager.get(player.getServerWorld()).extract(prototype, amount);
                if (!extracted.isEmpty()) {
                    if (!player.getInventory().insertStack(extracted)) {
                        player.dropItem(extracted, false);
                    }
                }
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(DEPOSIT_ITEM, (server, player, handler, buf, responseSender) -> {
            Item item = Registries.ITEM.get(buf.readIdentifier());
            NbtCompound tag = buf.readNbt();
            int amount = buf.readInt();

            server.execute(() -> {
                if (!(player.currentScreenHandler instanceof StorageScreenHandler)) {
                    return;
                }
                if (item == Items.AIR || amount <= 0) return;

                GlobalStorageManager manager = GlobalStorageManager.get(player.getServerWorld());
                
                long free = manager.getFreeSpace(item, tag);
                if (free <= 0) return;
                int want = (int) Math.min((long) amount, free);
                
                int removed = GlobalStorageManager.removeFromInventory(player.getInventory(), item, tag, want);
                if (removed <= 0) return;

                ItemStack toInsert = new ItemStack(item, removed);
                if (tag != null) toInsert.setNbt(tag);
                manager.insert(toInsert);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(FILL_BUCKET_FROM_PUMP, (server, player, handler, buf, responseSender) -> {
            BlockPos pos = buf.readBlockPos();
            server.execute(() -> {
                if (!(player.getWorld().getBlockEntity(pos) instanceof FluidPumpBlockEntity be)) return;
                be.tryFillBucket(player);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(FILL_BUCKET_FROM_ACID_PUMP, (server, player, handler, buf, responseSender) -> {
            BlockPos pos = buf.readBlockPos();
            server.execute(() -> {
                if (!(player.getWorld().getBlockEntity(pos) instanceof AcidResistantPumpMkIIBlockEntity be)) return;
                be.tryFillBucket(player);
            });
        });
        
        ServerPlayNetworking.registerGlobalReceiver(TANK_BOTTLE_TRANSFER, (server, player, handler, buf, responseSender) -> {
            BlockPos pos = buf.readBlockPos();
            server.execute(() -> {
                if (!(player.getWorld().getBlockEntity(pos) instanceof FluidTankBlockEntity be)) return;
                be.tryTransferWithBottle(player);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(CRUCIBLE_SET_OUTPUT, (server, player, handler, buf, responseSender) -> {
            BlockPos pos = buf.readBlockPos();
            int slot = buf.readInt();
            server.execute(() -> {
                if (!(player.currentScreenHandler instanceof BaseCrucibleScreenHandler screenHandler)) return;
                if (!screenHandler.getBlockEntity().getPos().equals(pos)) return;
                if (!(player.getWorld().getBlockEntity(pos) instanceof BaseCrucibleBlockEntity be)) return;
                be.setOutputFromDisplaySlot(slot);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(CRUCIBLE_CLEAR_OUTPUT, (server, player, handler, buf, responseSender) -> {
            BlockPos pos = buf.readBlockPos();
            int slot = buf.readInt();
            server.execute(() -> {
                if (!(player.currentScreenHandler instanceof BaseCrucibleScreenHandler screenHandler)) return;
                if (!screenHandler.getBlockEntity().getPos().equals(pos)) return;
                if (!(player.getWorld().getBlockEntity(pos) instanceof BaseCrucibleBlockEntity be)) return;
                be.clearOutputFilter(slot);
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(FLUID_MODE_CHANGE, (server, player, handler, buf, responseSender) -> {
            BlockPos pos = buf.readBlockPos();
            server.execute(() -> {
                if (player.getWorld().getBlockEntity(pos) instanceof RefiningUnitBlockEntity be) {
                    be.changeFluidMode(player.getWorld());
                    player.sendMessage(be.getFluidMode(), true);
                } else if (player.getWorld().getBlockEntity(pos) instanceof FillingUnitBlockEntity be) {
                    be.changeFluidMode(player.getWorld(), player);
                    player.sendMessage(be.getFluidMode(), true);
                } else if (player.getWorld().getBlockEntity(pos) instanceof PlantingUnitBlockEntity be) {
                    be.changeFluidMode(player.getWorld(), player);
                    player.sendMessage(be.getFluidMode(), true);
                }
            });
        });
    }
}
