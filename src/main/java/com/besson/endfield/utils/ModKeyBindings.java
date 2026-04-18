package com.besson.endfield.utils;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeyBindings {
    public static KeyBinding OPEN_STORAGE;
    public static void register() {
        OPEN_STORAGE = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.endfield.open_storage",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "category.endfield"
        ));
    }
}
