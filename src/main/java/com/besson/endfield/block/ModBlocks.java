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
    public static final Block PROTOCOL_ANCHOR_CORE = registerBlocksWithoutItem("protocol_anchor_core",
            new ProtocolAnchorCoreBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block RELAY_TOWER = registerBlocksWithoutItem("relay_tower",
            new RelayTowerBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block ELECTRIC_PYLON = registerBlocksWithoutItem("electric_pylon",
            new ElectricPylonBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block ELECTRIC_MINING_RIG = registerBlocksWithoutItem("electric_mining_rig",
            new ElectricMiningRigBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block ELECTRIC_MINING_RIG_MK_II = registerBlocksWithoutItem("electric_mining_rig_mk_ii",
            new ElectricMiningRigMkIIBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block PORTABLE_ORIGINIUM_RIG = registerBlocksWithoutItem("portable_originium_rig",
            new PortableOriginiumRigBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block REFINING_UNIT = registerBlocksWithoutItem("refining_unit",
            new RefiningUnitBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block REFINING_UNIT_SIDE = registerBlocks("refining_unit_side",
            new RefiningUnitSideBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block SHREDDING_UNIT = registerBlocksWithoutItem("shredding_unit",
            new ShreddingUnitBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block SHREDDING_UNIT_SIDE = registerBlocks("shredding_unit_side",
            new ShreddingUnitSideBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block FILLING_UNIT = registerBlocksWithoutItem("filling_unit",
            new FillingUnitBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block FILLING_UNIT_SIDE = registerBlocks("filling_unit_side",
            new FillingUnitSideBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block FITTING_UNIT = registerBlocksWithoutItem("fitting_unit",
            new FittingUnitBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block FITTING_UNIT_SIDE = registerBlocks("fitting_unit_side",
            new FittingUnitSideBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block GEARING_UNIT = registerBlocksWithoutItem("gearing_unit",
            new GearingUnitBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block GEARING_UNIT_SIDE = registerBlocks("gearing_unit_side",
            new GearingUnitSideBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block GRINDING_UNIT = registerBlocksWithoutItem("grinding_unit",
            new GrindingUnitBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block GRINDING_UNIT_SIDE = registerBlocks("grinding_unit_side",
            new GrindingUnitSideBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block MOULDING_UNIT = registerBlocksWithoutItem("moulding_unit",
            new MouldingUnitBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block MOULDING_UNIT_SIDE = registerBlocksWithoutItem("moulding_unit_side",
            new MouldingUnitSideBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block PACKAGING_UNIT = registerBlocksWithoutItem("packaging_unit",
            new PackagingUnitBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block PACKAGING_UNIT_SIDE = registerBlocks("packaging_unit_side",
            new PackagingUnitSideBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    
    public static final Block PLANTING_UNIT = registerBlocksWithoutItem("planting_unit",
            new PlantingUnitBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block PLANTING_UNIT_SIDE = registerBlocks("planting_unit_side",
            new PlantingUnitSideBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block SEED_PICKING_UNIT = registerBlocksWithoutItem("seed_picking_unit",
            new SeedPickingUnitBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block SEED_PICKING_UNIT_SIDE = registerBlocks("seed_picking_unit_side",
            new SeedPickingUnitSideBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block THERMAL_BANK = registerBlocksWithoutItem("thermal_bank",
            new ThermalBankBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));
    public static final Block THERMAL_BANK_SIDE = registerBlocks("thermal_bank_side",
            new ThermalBankSideBlock(AbstractBlock.Settings.create().strength(3f, 5f).nonOpaque()));

    public static final Block AMETHYST_MINERAL_VEIN_BLOCK = registerBlocks("amethyst_mineral_vein_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block COAL_MINERAL_VEIN_BLOCK = registerBlocks("coal_mineral_vein_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block COPPER_MINERAL_VEIN_BLOCK = registerBlocks("copper_mineral_vein_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block DIAMOND_MINERAL_VEIN_BLOCK = registerBlocks("diamond_mineral_vein_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block GOLD_MINERAL_VEIN_BLOCK = registerBlocks("gold_mineral_vein_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block IRON_MINERAL_VEIN_BLOCK = registerBlocks("iron_mineral_vein_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block LAPIS_MINERAL_VEIN_BLOCK = registerBlocks("lapis_mineral_vein_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block REDSTONE_MINERAL_VEIN_BLOCK = registerBlocks("redstone_mineral_vein_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block EMERALD_MINERAL_VEIN_BLOCK = registerBlocks("emerald_mineral_vein_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block ORIGINIUM_MINERAL_VEIN_BLOCK = registerBlocks("originium_mineral_vein_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block AMETHYST_ORE_BLOCK = registerBlocks("amethyst_ore_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block CUPRIUM_MINERAL_VEIN_BLOCK = registerBlocks("cuprium_mineral_vein_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block CUPRIUM_ORE_BLOCK = registerBlocks("cuprium_ore_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block DEEPSLATE_AMETHYST_ORE = registerBlocks("deepslate_amethyst_ore",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block DEEPSLATE_CUPRIUM_ORE = registerBlocks("deepslate_cuprium_ore",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block DEEPSLATE_FERRIUM_ORE = registerBlocks("deepslate_ferrium_ore",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block DEEPSLATE_ORIGINIUM_ORE = registerBlocks("deepslate_originium_ore",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block FERRIUM_MINERAL_VEIN_BLOCK = registerBlocks("ferrium_mineral_vein_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block FERRIUM_ORE_BLOCK = registerBlocks("ferrium_ore_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));
    public static final Block ORIGINIUM_ORE_BLOCK = registerBlocks("originium_ore_block",
            new Block(AbstractBlock.Settings.create().strength(5f, 5f).requiresTool()));

    private static Block registerBlocksWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(ArknightsEndfield.MOD_ID, name), block);
    }

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
