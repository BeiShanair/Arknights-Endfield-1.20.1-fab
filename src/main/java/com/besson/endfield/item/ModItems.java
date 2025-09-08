package com.besson.endfield.item;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.item.custom.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item PROTOCOL_ANCHOR_CORE_ITEM = registerItems("protocol_anchor_core",
            new ProtocolAnchorCoreItem(ModBlocks.PROTOCOL_ANCHOR_CORE, new Item.Settings()));

    public static final Item RELAY_TOWER_ITEM = registerItems("relay_tower",
            new RelayTowerItem(ModBlocks.RELAY_TOWER, new Item.Settings()));

    public static final Item ELECTRIC_PYLON_ITEM = registerItems("electric_pylon",
            new ElectricPylonItem(ModBlocks.ELECTRIC_PYLON, new Item.Settings()));

    public static final Item ELECTRIC_MINING_RIG_ITEM = registerItems("electric_mining_rig",
            new ElectricMiningRigItem(ModBlocks.ELECTRIC_MINING_RIG, new Item.Settings()));

    public static final Item ELECTRIC_MINING_RIG_MK_II_ITEM = registerItems("electric_mining_rig_mk_ii",
            new ElectricMiningRigItem(ModBlocks.ELECTRIC_MINING_RIG_MK_II, new Item.Settings()));

    public static final Item PORTABLE_ORIGINIUM_RIG_ITEM = registerItems("portable_originium_rig",
            new PortableOriginiumRigItem(ModBlocks.PORTABLE_ORIGINIUM_RIG, new Item.Settings()));

    public static final Item REFINING_UNIT_ITEM = registerItems("refining_unit",
            new RefiningUnitItem(ModBlocks.REFINING_UNIT, new Item.Settings()));

    public static final Item SHREDDING_UNIT_ITEM = registerItems("shredding_unit",
            new ShreddingUnitItem(ModBlocks.SHREDDING_UNIT, new Item.Settings()));

    private static Item registerItems(String name, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }
        return Registry.register(Registries.ITEM, new Identifier(ArknightsEndfield.MOD_ID, name), item);
    }

    public static void registerModItems() {

    }
}
