package com.besson.endfield.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.item.custom.ProtocolAnchorCoreItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item PROTOCOL_ANCHOR_CORE_ITEM = registerItems("protocol_anchor_core",
            new ProtocolAnchorCoreItem(ModBlocks.PROTOCOL_ANCHOR_CORE, new Item.Settings()));

    private static Item registerItems(String name, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
        return Registry.register(Registries.ITEM, new Identifier(ArknightsEndfield.MOD_ID, name), item);
    }

    public static void registerModItems() {

    }
}
