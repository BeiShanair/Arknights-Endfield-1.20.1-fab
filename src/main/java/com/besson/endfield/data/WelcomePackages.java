package com.besson.endfield.data;

import com.besson.endfield.ArknightsEndfield;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public final class WelcomePackages {

	public static final String DIRECTORY = "welcome_packages";

	private static ResourceManager cachedManager;
	private static NavigableMap<Integer, List<JsonElement>> cache = new TreeMap<>();

	private WelcomePackages() {}

	public static NavigableMap<Integer, List<JsonElement>> get(MinecraftServer server) {
		ResourceManager manager = server.getResourceManager();
		if (manager != cachedManager) {
			cache = load(manager);
			cachedManager = manager;
		}
		return cache;
	}

	private static NavigableMap<Integer, List<JsonElement>> load(ResourceManager manager) {
		NavigableMap<Integer, List<JsonElement>> parsed = new TreeMap<>();

		Map<Identifier, Resource> found = manager.findResources(DIRECTORY,
				id -> id.getPath().endsWith(".json"));

		found.forEach((id, resource) -> {
			try (InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
				JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
				int version = root.get("version").getAsInt();
				if (version < 1) {
					ArknightsEndfield.LOGGER.error("欢迎礼包 {} 的 version 必须 >= 1（当前 {}），已跳过", id, version);
					return;
				}
				List<JsonElement> items = new ArrayList<>();
				root.getAsJsonArray("items").forEach(items::add);
				parsed.computeIfAbsent(version, v -> new ArrayList<>()).addAll(items);
			} catch (Exception e) {
				ArknightsEndfield.LOGGER.error("解析欢迎礼包 {} 失败，已跳过", id, e);
			}
		});

		ArknightsEndfield.LOGGER.info("已加载 {} 个版本的欢迎礼包（最高 v{}）",
				parsed.size(), parsed.isEmpty() ? 0 : parsed.lastKey());
		return parsed;
	}
}
