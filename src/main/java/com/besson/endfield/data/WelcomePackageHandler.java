package com.besson.endfield.data;

import com.besson.endfield.ArknightsEndfield;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.List;
import java.util.NavigableMap;
import java.util.Optional;

public final class WelcomePackageHandler {

	private WelcomePackageHandler() {}

	public static void register() {
		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> grantPending(handler.player, server));
	}

	public static int grantPending(ServerPlayerEntity player, MinecraftServer server) {
		WelcomeData data = WelcomeData.get(server);
		int current = data.getVersion(player.getUuid());

		NavigableMap<Integer, List<JsonElement>> packages = WelcomePackages.get(server);
		int latest = packages.isEmpty() ? 0 : packages.lastKey();
		if (latest <= current) {
			return 0;
		}

		int given = 0;
		int dropped = 0;
		for (List<JsonElement> items : packages.tailMap(current, false).values()) {
			for (JsonElement json : items) {
				Optional<ItemStack> parsed = ItemStack.CODEC.parse(JsonOps.INSTANCE, json).result();
				if (parsed.isEmpty() || parsed.get().isEmpty()) {
					ArknightsEndfield.LOGGER.error("欢迎礼包物品解析失败（玩家 {}）：{}",
							player.getName().getString(), json);
					continue;
				}

				ItemStack stack = parsed.get();
				int droppedThis = deliver(player, stack);
				given += stack.getCount() - droppedThis;
				dropped += droppedThis;
			}
		}

		data.setVersion(player.getUuid(), latest);
		ArknightsEndfield.LOGGER.info("已向 {} 发放欢迎礼包 v{} -> v{}，共 {} 件物品（掉落 {} 件）",
				player.getName().getString(), current, latest, given, dropped);

		if (given > 0) {
			player.sendMessage(Text.translatable("message.arknights_endfield.welcome_package"), false);
		}
		if (dropped > 0) {
			player.sendMessage(
					Text.translatable("message.arknights_endfield.welcome_package_dropped", dropped), false);
		}
		return given;
	}

	private static int deliver(ServerPlayerEntity player, ItemStack stack) {
		int count = stack.getCount();
		int toAdd = Math.min(count, freeSpaceFor(player, stack));

		if (toAdd > 0) {
			ItemStack part = stack.copyWithCount(toAdd);
			if (!player.getInventory().insertStack(part) && !part.isEmpty()) {
				player.dropItem(part, false);
				toAdd -= part.getCount();
			}
		}

		int rest = count - toAdd;
		if (rest > 0) {
			player.dropItem(stack.copyWithCount(rest), false);
		}
		return rest;
	}

	private static int freeSpaceFor(ServerPlayerEntity player, ItemStack stack) {
		PlayerInventory inventory = player.getInventory();
		int max = stack.getMaxCount();
		int room = 0;

		for (int slot = 0; slot < inventory.size(); slot++) {
			if (slot >= 36 && slot <= 39) {
				continue;
			}
			ItemStack existing = inventory.getStack(slot);
			if (existing.isEmpty()) {
				room += max;
			} else if (ItemStack.canCombine(existing, stack)) {
				room += Math.max(0, max - existing.getCount());
			}
		}
		return room;
	}
}
