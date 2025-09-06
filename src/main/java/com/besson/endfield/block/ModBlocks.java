package com.besson.endfield.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.ProtocolAnchorCoreBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block PROTOCOL_ANCHOR_CORE = Registry.register(Registries.BLOCK, new Identifier(ArknightsEndfield.MOD_ID, "protocol_anchor_core"),
            new ProtocolAnchorCoreBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    private static Block registerBlocks(String name, Block block) {
        registerBlockItems(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(ArknightsEndfield.MOD_ID, name), block);
    }

    private static void registerBlockItems(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(ArknightsEndfield.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {

    }
}
