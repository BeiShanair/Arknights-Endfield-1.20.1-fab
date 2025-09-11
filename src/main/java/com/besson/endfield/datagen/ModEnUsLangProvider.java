package com.besson.endfield.datagen;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModEnUsLangProvider extends FabricLanguageProvider {
    public ModEnUsLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.PROTOCOL_ANCHOR_CORE_ITEM, "Protocol Anchor Core");
        translationBuilder.add(ModItems.RELAY_TOWER_ITEM, "Relay Tower");
        translationBuilder.add(ModItems.ELECTRIC_PYLON_ITEM, "Electric Pylon");
        translationBuilder.add(ModItems.ELECTRIC_MINING_RIG_ITEM, "Electric Mining Rig");
        translationBuilder.add(ModItems.ELECTRIC_MINING_RIG_MK_II_ITEM, "Electric Mining Rig Mk II");
        translationBuilder.add(ModItems.PORTABLE_ORIGINIUM_RIG_ITEM, "Portable Originium Rig");
        translationBuilder.add(ModItems.REFINING_UNIT_ITEM, "Refining Unit");
        translationBuilder.add(ModItems.SHREDDING_UNIT_ITEM, "Shredding Unit");
        translationBuilder.add(ModItems.FILLING_UNIT_ITEM, "Filling Unit");
        translationBuilder.add(ModItems.FITTING_UNIT_ITEM, "Fitting Unit");
        translationBuilder.add(ModItems.GEARING_UNIT_ITEM, "Gearing Unit");
        translationBuilder.add(ModItems.GRINDING_UNIT_ITEM, "Grinding Unit");
        translationBuilder.add(ModItems.MOULDING_UNIT_ITEM, "Moulding Unit");
        translationBuilder.add(ModItems.PACKAGING_UNIT_ITEM, "Packaging Unit");
        translationBuilder.add(ModItems.PLANTING_UNIT_ITEM, "Planting Unit");
        translationBuilder.add(ModItems.SEED_PICKING_UNIT_ITEM, "Seed-Picking Unit");
        translationBuilder.add(ModItems.THERMAL_BANK_ITEM, "Thermal Unit");

        translationBuilder.add(ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK, "Amethyst Mineral Vein");
        translationBuilder.add(ModBlocks.COAL_MINERAL_VEIN_BLOCK, "Coal Mineral Vein");
        translationBuilder.add(ModBlocks.COPPER_MINERAL_VEIN_BLOCK, "Copper Mineral Vein");
        translationBuilder.add(ModBlocks.DIAMOND_MINERAL_VEIN_BLOCK, "Diamond Mineral Vein");
        translationBuilder.add(ModBlocks.EMERALD_MINERAL_VEIN_BLOCK, "Emerald Mineral Vein");
        translationBuilder.add(ModBlocks.GOLD_MINERAL_VEIN_BLOCK, "Gold Mineral Vein");
        translationBuilder.add(ModBlocks.IRON_MINERAL_VEIN_BLOCK, "Iron Mineral Vein");
        translationBuilder.add(ModBlocks.LAPIS_MINERAL_VEIN_BLOCK, "Lapis Mineral Vein");
        translationBuilder.add(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK, "Originium Mineral Vein");
        translationBuilder.add(ModBlocks.REDSTONE_MINERAL_VEIN_BLOCK, "Redstone Mineral Vein");

        translationBuilder.add("blockEntity.portable_originium_rig", "Portable Originium Rig");

        translationBuilder.add("itemGroup.arknights_endfield", "Arknights Endfield");
    }
}
