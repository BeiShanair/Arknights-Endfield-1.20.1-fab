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
        translationBuilder.add(ModItems.FILLING_UNIT_ITEM, "灌装机");
        translationBuilder.add(ModItems.FITTING_UNIT_ITEM, "配件机");
        translationBuilder.add(ModItems.GEARING_UNIT_ITEM, "装备原件机");
        translationBuilder.add(ModItems.GRINDING_UNIT_ITEM, "研磨机");
        translationBuilder.add(ModItems.MOULDING_UNIT_ITEM, "塑形机");
        translationBuilder.add(ModItems.PACKAGING_UNIT_ITEM, "封装机");
        translationBuilder.add(ModItems.PLANTING_UNIT_ITEM, "种植机");
        translationBuilder.add(ModItems.SEED_PICKING_UNIT_ITEM, "采种机");
        translationBuilder.add(ModItems.THERMAL_BANK_ITEM, "热能池");

        translationBuilder.add(ModBlocks.AMETHYST_MINERAL_VEIN_BLOCK, "紫水晶矿脉");
        translationBuilder.add(ModBlocks.COAL_MINERAL_VEIN_BLOCK, "煤矿脉");
        translationBuilder.add(ModBlocks.COPPER_MINERAL_VEIN_BLOCK, "铜矿脉");
        translationBuilder.add(ModBlocks.DIAMOND_MINERAL_VEIN_BLOCK, "钻石矿脉");
        translationBuilder.add(ModBlocks.EMERALD_MINERAL_VEIN_BLOCK, "绿宝石矿脉");
        translationBuilder.add(ModBlocks.GOLD_MINERAL_VEIN_BLOCK, "金矿脉");
        translationBuilder.add(ModBlocks.IRON_MINERAL_VEIN_BLOCK, "铁矿脉");
        translationBuilder.add(ModBlocks.LAPIS_MINERAL_VEIN_BLOCK, "青金石矿脉");
        translationBuilder.add(ModBlocks.ORIGINIUM_MINERAL_VEIN_BLOCK, "源石矿脉");
        translationBuilder.add(ModBlocks.REDSTONE_MINERAL_VEIN_BLOCK, "红石矿脉");

        translationBuilder.add("blockEntity.portable_originium_rig", "便携源石矿机");
        translationBuilder.add("blockEntity.protocol_anchor_core", "协议核心");
        translationBuilder.add("blockEntity.thermal_bank", "热能池");
        translationBuilder.add("blockEntity.refining_unit", "精炼炉");

        translationBuilder.add("screen.protocol_core.buffer", "电力缓冲区: %s / %s");
        translationBuilder.add("screen.protocol_core.base_power", "基础发电量: %s");
        translationBuilder.add("screen.protocol_core.extra_power", "额外发电量: %s");
        translationBuilder.add("screen.protocol_core.load", "接入节点数: %s");

        translationBuilder.add("itemGroup.arknights_endfield", "明日方舟：终末地" );
    }
}
