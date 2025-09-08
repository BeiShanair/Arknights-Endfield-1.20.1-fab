package com.besson.endfield.datagen;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class ModZhCnLangProvider extends FabricLanguageProvider {
    public ModZhCnLangProvider(FabricDataOutput dataOutput) {
        super(dataOutput, "zh_cn");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.PROTOCOL_ANCHOR_CORE_ITEM, "协议核心");
        translationBuilder.add(ModItems.RELAY_TOWER_ITEM, "中继器");
        translationBuilder.add(ModItems.ELECTRIC_PYLON_ITEM, "供电桩");
        translationBuilder.add(ModItems.ELECTRIC_MINING_RIG_ITEM, "电驱矿机");
        translationBuilder.add(ModItems.ELECTRIC_MINING_RIG_MK_II_ITEM, "二型电驱矿机");
        translationBuilder.add(ModItems.PORTABLE_ORIGINIUM_RIG_ITEM, "便携源石矿机");
        translationBuilder.add(ModItems.REFINING_UNIT_ITEM, "精炼炉");
        translationBuilder.add(ModItems.SHREDDING_UNIT_ITEM, "粉碎机");

        translationBuilder.add("itemGroup.arknights_endfield", "明日方舟：终末地" );
    }
}
