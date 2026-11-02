package com.besson.endfield.data;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.PersistentState;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WelcomeData extends PersistentState {
	private static final String SAVE_ID = "arknights_endfield_welcome";
	private final Map<UUID, Integer> versions = new HashMap<>();

	public static WelcomeData get(MinecraftServer server) {
		return server.getOverworld().getPersistentStateManager()
				.getOrCreate(WelcomeData::fromNbt, WelcomeData::new, SAVE_ID);
	}

	private static WelcomeData fromNbt(NbtCompound nbt) {
		WelcomeData data = new WelcomeData();
		for (String key : nbt.getKeys()) {
			try {
				data.versions.put(UUID.fromString(key), nbt.getInt(key));
			} catch (IllegalArgumentException ignored) {
			}
		}
		return data;
	}

	@Override
	public NbtCompound writeNbt(NbtCompound nbt) {
		this.versions.forEach((uuid, version) -> nbt.putInt(uuid.toString(), version));
		return nbt;
	}

	public int getVersion(UUID player) {
		return this.versions.getOrDefault(player, 0);
	}

	public void setVersion(UUID player, int version) {
		this.versions.put(player, version);
		this.markDirty();
	}
}
