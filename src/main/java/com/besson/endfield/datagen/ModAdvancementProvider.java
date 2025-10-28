package com.besson.endfield.datagen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement root = Advancement.Builder.create()
                .display(ModItems.PROTOCOL_ANCHOR_CORE_ITEM,
                        Text.translatable("advancement.endfield.root.title"),
                        Text.translatable("advancement.endfield.root.description"),
                        new Identifier("textures/gui/advancements/backgrounds/stone.png"),
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_protocol_anchor_core", InventoryChangedCriterion.Conditions.items(ModItems.PROTOCOL_ANCHOR_CORE_ITEM))
                .build(consumer, "arknights_endfield:root");

        Advancement originium_ore = Advancement.Builder.create()
                .parent(root)
                .display(ModItems.ORIGINIUM_ORE,
                        Text.translatable("advancement.endfield.originium_ore.title"),
                        Text.translatable("advancement.endfield.originium_ore.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_originium_ore", InventoryChangedCriterion.Conditions.items(ModItems.ORIGINIUM_ORE))
                .build(consumer, "arknights_endfield:originium_ore");

        Advancement amethyst_ore = Advancement.Builder.create()
                .parent(originium_ore)
                .display(ModItems.AMETHYST_ORE,
                        Text.translatable("advancement.endfield.amethyst_ore.title"),
                        Text.translatable("advancement.endfield.amethyst_ore.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_amethyst_ore", InventoryChangedCriterion.Conditions.items(ModItems.AMETHYST_ORE))
                .build(consumer, "arknights_endfield:amethyst_ore");

        Advancement find_all_ores = Advancement.Builder.create()
                .parent(amethyst_ore)
                .display(ModItems.CUPRIUM_ORE,
                        Text.translatable("advancement.endfield.find_all_ores.title"),
                        Text.translatable("advancement.endfield.find_all_ores.description"),
                        null,
                        AdvancementFrame.GOAL, true, true, false)
                .criterion("find_all_ores", InventoryChangedCriterion.Conditions.items(
                        ModItems.AMETHYST_ORE,
                        ModItems.CUPRIUM_ORE,
                        ModItems.FERRIUM_ORE,
                        ModItems.ORIGINIUM_ORE
                )).build(consumer, "arknights_endfield:find_all_ores");

        Advancement orginium_rig = Advancement.Builder.create()
                .parent(originium_ore)
                .display(ModItems.PORTABLE_ORIGINIUM_RIG_ITEM,
                        Text.translatable("advancement.endfield.originium_rig.title"),
                        Text.translatable("advancement.endfield.originium_rig.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_originium_rig", InventoryChangedCriterion.Conditions.items(ModItems.PORTABLE_ORIGINIUM_RIG_ITEM))
                .build(consumer, "arknights_endfield:originium_rig");

        Advancement electric_mining_rig = Advancement.Builder.create()
                .parent(orginium_rig)
                .display(ModItems.ELECTRIC_MINING_RIG_ITEM,
                        Text.translatable("advancement.endfield.electric_mining_rig.title"),
                        Text.translatable("advancement.endfield.electric_mining_rig.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_electric_mining_rig", InventoryChangedCriterion.Conditions.items(ModItems.ELECTRIC_MINING_RIG_ITEM))
                .build(consumer, "arknights_endfield:electric_mining_rig");

        Advancement electric_mining_rig_mk_ii = Advancement.Builder.create()
                .parent(electric_mining_rig)
                .display(ModItems.ELECTRIC_MINING_RIG_MK_II_ITEM,
                        Text.translatable("advancement.endfield.electric_mining_rig_mk_ii.title"),
                        Text.translatable("advancement.endfield.electric_mining_rig_mk_ii.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_electric_mining_rig_mk_ii", InventoryChangedCriterion.Conditions.items(ModItems.ELECTRIC_MINING_RIG_MK_II_ITEM))
                .build(consumer, "arknights_endfield:electric_mining_rig_mk_ii");

        Advancement all_mineral_vein_block = Advancement.Builder.create()
                .parent(electric_mining_rig_mk_ii)
                .display(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK,
                        Text.translatable("advancement.endfield.all_mineral_vein_block.title"),
                        Text.translatable("advancement.endfield.all_mineral_vein_block.description"),
                        null,
                        AdvancementFrame.GOAL, true, true, false)
                .criterion("all_mineral_vein_block", InventoryChangedCriterion.Conditions.items(
                        ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK,
                        ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK,
                        ModBlocks.FERRIUM_MINERAL_VEIN_BLOCK,
                        ModBlocks.CUPRIUM_MINERAL_VEIN_BLOCK,
                        ModBlocks.COAL_MINERAL_VEIN_BLOCK,
                        ModBlocks.IRON_MINERAL_VEIN_BLOCK,
                        ModBlocks.GOLD_MINERAL_VEIN_BLOCK,
                        ModBlocks.DIAMOND_MINERAL_VEIN_BLOCK,
                        ModBlocks.EMERALD_MINERAL_VEIN_BLOCK,
                        ModBlocks.LAPIS_MINERAL_VEIN_BLOCK,
                        ModBlocks.REDSTONE_MINERAL_VEIN_BLOCK,
                        ModBlocks.COPPER_MINERAL_VEIN_BLOCK
                )).build(consumer, "arknights_endfield:all_mineral_vein_block");

        Advancement relay_tower = Advancement.Builder.create()
                .parent(root)
                .display(ModItems.RELAY_TOWER_ITEM,
                        Text.translatable("advancement.endfield.relay_tower.title"),
                        Text.translatable("advancement.endfield.relay_tower.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_relay_tower", InventoryChangedCriterion.Conditions.items(ModItems.RELAY_TOWER_ITEM))
                .build(consumer, "arknights_endfield:relay_tower");

        Advancement electric_pylon = Advancement.Builder.create()
                .parent(relay_tower)
                .display(ModItems.ELECTRIC_PYLON_ITEM,
                        Text.translatable("advancement.endfield.electric_pylon.title"),
                        Text.translatable("advancement.endfield.electric_pylon.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_electric_pylon", InventoryChangedCriterion.Conditions.items(ModItems.ELECTRIC_PYLON_ITEM))
                .build(consumer, "arknights_endfield:electric_pylon");

        Advancement crafting = Advancement.Builder.create()
                .parent(root)
                .display(ModBlocks.CRAFTER,
                        Text.translatable("advancement.endfield.crafting.title"),
                        Text.translatable("advancement.endfield.crafting.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_crafter", InventoryChangedCriterion.Conditions.items(ModBlocks.CRAFTER))
                .build(consumer, "arknights_endfield:crafting");

        Advancement refining_unit = Advancement.Builder.create()
                .parent(root)
                .display(ModItems.REFINING_UNIT_ITEM,
                        Text.translatable("advancement.endfield.refining_unit.title"),
                        Text.translatable("advancement.endfield.refining_unit.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_refining_unit", InventoryChangedCriterion.Conditions.items(ModItems.REFINING_UNIT_ITEM))
                .build(consumer, "arknights_endfield:refining_unit");

        Advancement shredding_unit = Advancement.Builder.create()
                .parent(refining_unit)
                .display(ModItems.SHREDDING_UNIT_ITEM,
                        Text.translatable("advancement.endfield.shredding_unit.title"),
                        Text.translatable("advancement.endfield.shredding_unit.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_shredding_unit", InventoryChangedCriterion.Conditions.items(ModItems.SHREDDING_UNIT_ITEM))
                .build(consumer, "arknights_endfield:shredding_unit");

        Advancement fitting_unit = Advancement.Builder.create()
                .parent(shredding_unit)
                .display(ModItems.FITTING_UNIT_ITEM,
                        Text.translatable("advancement.endfield.fitting_unit.title"),
                        Text.translatable("advancement.endfield.fitting_unit.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_fitting_unit", InventoryChangedCriterion.Conditions.items(ModItems.FITTING_UNIT_ITEM))
                .build(consumer, "arknights_endfield:fitting_unit");

        Advancement moulding_unit = Advancement.Builder.create()
                .parent(fitting_unit)
                .display(ModItems.MOULDING_UNIT_ITEM,
                        Text.translatable("advancement.endfield.moulding_unit.title"),
                        Text.translatable("advancement.endfield.moulding_unit.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_moulding_unit", InventoryChangedCriterion.Conditions.items(ModItems.MOULDING_UNIT_ITEM))
                .build(consumer, "arknights_endfield:moulding_unit");

        Advancement planting_unit = Advancement.Builder.create()
                .parent(moulding_unit)
                .display(ModItems.PLANTING_UNIT_ITEM,
                        Text.translatable("advancement.endfield.planting_unit.title"),
                        Text.translatable("advancement.endfield.planting_unit.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_planting_unit", InventoryChangedCriterion.Conditions.items(ModItems.PLANTING_UNIT_ITEM))
                .build(consumer, "arknights_endfield:planting_unit");

        Advancement seed_picking_unit = Advancement.Builder.create()
                .parent(planting_unit)
                .display(ModItems.SEED_PICKING_UNIT_ITEM,
                        Text.translatable("advancement.endfield.seed_picking_unit.title"),
                        Text.translatable("advancement.endfield.seed_picking_unit.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_seed_picking_unit", InventoryChangedCriterion.Conditions.items(ModItems.SEED_PICKING_UNIT_ITEM))
                .build(consumer, "arknights_endfield:seed_picking_unit");

        Advancement gearing_unit = Advancement.Builder.create()
                .parent(root)
                .display(ModItems.GEARING_UNIT_ITEM,
                        Text.translatable("advancement.endfield.gearing_unit.title"),
                        Text.translatable("advancement.endfield.gearing_unit.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_gearing_unit", InventoryChangedCriterion.Conditions.items(ModItems.GEARING_UNIT_ITEM))
                .build(consumer, "arknights_endfield:gearing_unit");

        Advancement filling_unit = Advancement.Builder.create()
                .parent(gearing_unit)
                .display(ModItems.FILLING_UNIT_ITEM,
                        Text.translatable("advancement.endfield.filling_unit.title"),
                        Text.translatable("advancement.endfield.filling_unit.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_filling_unit", InventoryChangedCriterion.Conditions.items(ModItems.FILLING_UNIT_ITEM))
                .build(consumer, "arknights_endfield:filling_unit");

        Advancement packaging_unit = Advancement.Builder.create()
                .parent(filling_unit)
                .display(ModItems.PACKAGING_UNIT_ITEM,
                        Text.translatable("advancement.endfield.packaging_unit.title"),
                        Text.translatable("advancement.endfield.packaging_unit.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_packaging_unit", InventoryChangedCriterion.Conditions.items(ModItems.PACKAGING_UNIT_ITEM))
                .build(consumer, "arknights_endfield:packaging_unit");

        Advancement grinding_unit = Advancement.Builder.create()
                .parent(packaging_unit)
                .display(ModItems.GRINDING_UNIT_ITEM,
                        Text.translatable("advancement.endfield.grinding_unit.title"),
                        Text.translatable("advancement.endfield.grinding_unit.description"),
                        null,
                        AdvancementFrame.TASK, true, true, false)
                .criterion("got_grinding_unit", InventoryChangedCriterion.Conditions.items(ModItems.GRINDING_UNIT_ITEM))
                .build(consumer, "arknights_endfield:grinding_unit");
    }
}
