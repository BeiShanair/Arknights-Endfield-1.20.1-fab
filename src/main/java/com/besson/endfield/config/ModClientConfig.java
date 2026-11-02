package com.besson.endfield.config;

import com.besson.endfield.ArknightsEndfield;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.RenderLayer;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ModClientConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("arknights_endfield-client.json");
    private static final int DEFAULT_RENDER_DISTANCE = 256;

    private static ModClientConfig INSTANCE;

    public BeltRenderLayer beltRenderLayer = BeltRenderLayer.TRANSLUCENT;
    public int equipmentRenderDistance = DEFAULT_RENDER_DISTANCE;

    private ModClientConfig() {
    }

    public static ModClientConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = load();
        }
        return INSTANCE;
    }

    public RenderLayer resolveBeltRenderLayer() {
        return this.beltRenderLayer == BeltRenderLayer.CUTOUT ? RenderLayer.getCutout() : RenderLayer.getTranslucent();
    }

    public int getEquipmentRenderDistance() {
        return Math.max(0, this.equipmentRenderDistance);
    }

    private static ModClientConfig load() {
        if (!Files.exists(CONFIG_PATH)) {
            ModClientConfig config = new ModClientConfig();
            config.save();
            return config;
        }

        try (Reader reader = Files.newBufferedReader(CONFIG_PATH, StandardCharsets.UTF_8)) {
            JsonElement element = JsonParser.parseReader(reader);
            if (!element.isJsonObject()) {
                throw new JsonParseException("Expected JSON object");
            }

            JsonObject json = element.getAsJsonObject();
            ModClientConfig config = new ModClientConfig();
            if (json.has("beltRenderLayer")) {
                config.beltRenderLayer = BeltRenderLayer.fromName(json.get("beltRenderLayer").getAsString());
            }
            if (json.has("equipmentRenderDistance")) {
                config.equipmentRenderDistance = json.get("equipmentRenderDistance").getAsInt();
            }
            return config;
        } catch (IOException | JsonParseException | IllegalArgumentException e) {
            ArknightsEndfield.LOGGER.warn("Failed to load client config, using defaults.", e);
            ModClientConfig config = new ModClientConfig();
            config.save();
            return config;
        }
    }

    private void save() {
        JsonObject json = new JsonObject();
        json.addProperty("beltRenderLayer", this.beltRenderLayer.getSerializedName());
        json.addProperty("equipmentRenderDistance", this.getEquipmentRenderDistance());

        try (Writer writer = Files.newBufferedWriter(CONFIG_PATH, StandardCharsets.UTF_8)) {
            GSON.toJson(json, writer);
        } catch (IOException e) {
            ArknightsEndfield.LOGGER.warn("Failed to save client config.", e);
        }
    }

    public enum BeltRenderLayer {
        TRANSLUCENT("translucent"),
        CUTOUT("cutout");

        private final String serializedName;

        BeltRenderLayer(String serializedName) {
            this.serializedName = serializedName;
        }

        public String getSerializedName() {
            return this.serializedName;
        }

        public static BeltRenderLayer fromName(String name) {
            for (BeltRenderLayer value : values()) {
                if (value.serializedName.equalsIgnoreCase(name)) {
                    return value;
                }
            }
            throw new IllegalArgumentException("Unknown belt render layer: " + name);
        }
    }
}
