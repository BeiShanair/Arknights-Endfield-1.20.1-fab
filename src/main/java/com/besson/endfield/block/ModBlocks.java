package com.besson.endfield.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.*;
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

    public static final Block RELAY_TOWER = Registry.register(Registries.BLOCK, new Identifier(ArknightsEndfield.MOD_ID, "relay_tower"),
            new RelayTowerBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block ELECTRIC_PYLON = Registry.register(Registries.BLOCK, new Identifier(ArknightsEndfield.MOD_ID, "electric_pylon"),
            new ElectricPylonBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block ELECTRIC_MINING_RIG = Registry.register(Registries.BLOCK, new Identifier(ArknightsEndfield.MOD_ID, "electric_mining_rig"),
            new ElectricMiningRigBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block ELECTRIC_MINING_RIG_MK_II = Registry.register(Registries.BLOCK, new Identifier(ArknightsEndfield.MOD_ID, "electric_mining_rig_mk_ii"),
            new ElectricMiningRigMkIIBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block PORTABLE_ORIGINIUM_RIG = Registry.register(Registries.BLOCK, new Identifier(ArknightsEndfield.MOD_ID, "portable_originium_rig"),
            new PortableOriginiumRigBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block REFINING_UNIT = Registry.register(Registries.BLOCK, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit"),
            new RefiningUnitBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block SHREDDING_UNIT = Registry.register(Registries.BLOCK, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit"),
            new ShreddingUnitBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

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
