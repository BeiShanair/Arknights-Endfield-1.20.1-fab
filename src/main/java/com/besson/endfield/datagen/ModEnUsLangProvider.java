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

        translationBuilder.add("itemGroup.arknights_endfield", "Arknights Endfield");
    }
}
