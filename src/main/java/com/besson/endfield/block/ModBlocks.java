package com.besson.endfield.block;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.custom.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.effect.StatusEffects;
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

    public static final Block AKETINE_BLOCK = registerBlocksWithoutItem("aketine_block",
            new FlowerBlock(StatusEffects.JUMP_BOOST, 100, AbstractBlock.Settings.create().strength(0.5f).nonOpaque().breakInstantly().noCollision()));
    public static final Block POTTED_AKETINE_BLOCK = registerBlocksWithoutItem("potted_aketine_block",
            new FlowerPotBlock(ModBlocks.AKETINE_BLOCK, AbstractBlock.Settings.create().strength(0.5f).nonOpaque()));
    public static final Block AMBER_RICE_BLOCK = registerBlocksWithoutItem("amber_rice_block",
            new FlowerBlock(StatusEffects.SPEED, 100, AbstractBlock.Settings.create().strength(0.5f).nonOpaque().breakInstantly().noCollision()));
    public static final Block POTTED_AMBER_RICE_BLOCK = registerBlocksWithoutItem("potted_amber_rice_block",
            new FlowerPotBlock(ModBlocks.AMBER_RICE_BLOCK, AbstractBlock.Settings.create().strength(0.5f).nonOpaque()));
    public static final Block BUCKFLOWER_BLOCK = registerBlocksWithoutItem("buckflower_block",
            new FlowerBlock(StatusEffects.REGENERATION, 100, AbstractBlock.Settings.create().strength(0.5f).nonOpaque().breakInstantly().noCollision()));
    public static final Block POTTED_BUCKFLOWER_BLOCK = registerBlocksWithoutItem("potted_buckflower_block",
            new FlowerPotBlock(ModBlocks.BUCKFLOWER_BLOCK, AbstractBlock.Settings.create().strength(0.5f).nonOpaque()));
    public static final Block CITROME_BLOCK = registerBlocksWithoutItem("citrome_block",
            new FlowerBlock(StatusEffects.SATURATION, 100, AbstractBlock.Settings.create().strength(0.5f).nonOpaque().breakInstantly().noCollision()));
    public static final Block POTTED_CITROME_BLOCK = registerBlocksWithoutItem("potted_citrome_block",
            new FlowerPotBlock(ModBlocks.CITROME_BLOCK, AbstractBlock.Settings.create().strength(0.5f).nonOpaque()));
    public static final Block FIREBUCKLE_BLOCK = registerBlocksWithoutItem("firebuckle_block",
            new FlowerBlock(StatusEffects.FIRE_RESISTANCE, 100, AbstractBlock.Settings.create().strength(0.5f).nonOpaque().breakInstantly().noCollision()));
    public static final Block POTTED_FIREBUCKLE_BLOCK = registerBlocksWithoutItem("potted_firebuckle_block",
            new FlowerPotBlock(ModBlocks.FIREBUCKLE_BLOCK, AbstractBlock.Settings.create().strength(0.5f).nonOpaque()));
    public static final Block FLUFFED_JINCAO_BLOCK = registerBlocksWithoutItem("fluffed_jincao_block",
            new FlowerBlock(StatusEffects.SLOWNESS, 100, AbstractBlock.Settings.create().strength(0.5f).nonOpaque().breakInstantly().noCollision()));
    public static final Block POTTED_FLUFFED_JINCAO_BLOCK = registerBlocksWithoutItem("potted_fluffed_jincao_block",
            new FlowerPotBlock(ModBlocks.FLUFFED_JINCAO_BLOCK, AbstractBlock.Settings.create().strength(0.5f).nonOpaque()));
    public static final Block JINCAO_BLOCK = registerBlocksWithoutItem("jincao_block",
            new FlowerBlock(StatusEffects.INSTANT_HEALTH, 100, AbstractBlock.Settings.create().strength(0.5f).nonOpaque().breakInstantly().noCollision()));
    public static final Block POTTED_JINCAO_BLOCK = registerBlocksWithoutItem("potted_jincao_block",
            new FlowerPotBlock(ModBlocks.JINCAO_BLOCK, AbstractBlock.Settings.create().strength(0.5f).nonOpaque()));
    public static final Block REDJADE_GINSENG_BLOCK = registerBlocksWithoutItem("redjade_ginseng_block",
            new FlowerBlock(StatusEffects.STRENGTH, 100, AbstractBlock.Settings.create().strength(0.5f).nonOpaque().breakInstantly().noCollision()));
    public static final Block POTTED_REDJADE_GINSENG_BLOCK = registerBlocksWithoutItem("potted_redjade_ginseng_block",
            new FlowerPotBlock(ModBlocks.REDJADE_GINSENG_BLOCK, AbstractBlock.Settings.create().strength(0.5f).nonOpaque()));
    public static final Block REED_RYE_BLOCK = registerBlocksWithoutItem("reed_rye_block",
            new FlowerBlock(StatusEffects.HASTE, 100, AbstractBlock.Settings.create().strength(0.5f).nonOpaque().breakInstantly().noCollision()));
    public static final Block POTTED_REED_RYE_BLOCK = registerBlocksWithoutItem("potted_reed_rye_block",
            new FlowerPotBlock(ModBlocks.REED_RYE_BLOCK, AbstractBlock.Settings.create().strength(0.5f).nonOpaque()));
    public static final Block SANDLEAF_BLOCK = registerBlocksWithoutItem("sandleaf_block",
            new FlowerBlock(StatusEffects.WATER_BREATHING, 100, AbstractBlock.Settings.create().strength(0.5f).nonOpaque().breakInstantly().noCollision()));
    public static final Block POTTED_SANDLEAF_BLOCK = registerBlocksWithoutItem("potted_sandleaf_block",
            new FlowerPotBlock(ModBlocks.SANDLEAF_BLOCK, AbstractBlock.Settings.create().strength(0.5f).nonOpaque()));
    public static final Block TARTPEPPER_BLOCK = registerBlocksWithoutItem("tartpepper_block",
            new FlowerBlock(StatusEffects.NAUSEA, 100, AbstractBlock.Settings.create().strength(0.5f).nonOpaque().breakInstantly().noCollision()));
    public static final Block POTTED_TARTPEPPER_BLOCK = registerBlocksWithoutItem("potted_tartpepper_block",
            new FlowerPotBlock(ModBlocks.TARTPEPPER_BLOCK, AbstractBlock.Settings.create().strength(0.5f).nonOpaque()));
    public static final Block THORNY_YAZHEN_BLOCK = registerBlocksWithoutItem("thorny_yazhen_block",
            new FlowerBlock(StatusEffects.POISON, 100, AbstractBlock.Settings.create().strength(0.5f).nonOpaque().breakInstantly().noCollision()));
    public static final Block POTTED_THORNY_YAZHEN_BLOCK = registerBlocksWithoutItem("potted_thorny_yazhen_block",
            new FlowerPotBlock(ModBlocks.THORNY_YAZHEN_BLOCK, AbstractBlock.Settings.create().strength(0.5f).nonOpaque()));
    public static final Block UMBRALINE_BLOCK = registerBlocksWithoutItem("umbraline_block",
            new FlowerBlock(StatusEffects.INVISIBILITY, 100, AbstractBlock.Settings.create().strength(0.5f).nonOpaque().breakInstantly().noCollision()));
    public static final Block POTTED_UMBRALINE_BLOCK = registerBlocksWithoutItem("potted_umbraline_block",
            new FlowerPotBlock(ModBlocks.UMBRALINE_BLOCK, AbstractBlock.Settings.create().strength(0.5f).nonOpaque()));
    public static final Block YAZHEN_BLOCK = registerBlocksWithoutItem("yazhen_block",
            new FlowerBlock(StatusEffects.REGENERATION, 100, AbstractBlock.Settings.create().strength(0.5f).nonOpaque().breakInstantly().noCollision()));
    public static final Block POTTED_YAZHEN_BLOCK = registerBlocksWithoutItem("potted_yazhen_block",
            new FlowerPotBlock(ModBlocks.YAZHEN_BLOCK, AbstractBlock.Settings.create().strength(0.5f).nonOpaque()));

    public static final Block CRAFTER = registerBlocks("crafter",
            new CrafterBlock(AbstractBlock.Settings.create().strength(3f).nonOpaque()));

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
