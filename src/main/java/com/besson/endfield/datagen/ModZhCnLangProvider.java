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
        // region Powering
        translationBuilder.add(ModItems.PROTOCOL_ANCHOR_CORE_ITEM, "协议核心");
        translationBuilder.add(ModItems.RELAY_TOWER_ITEM, "中继器");
        translationBuilder.add(ModItems.ELECTRIC_PYLON_ITEM, "供电桩");
        translationBuilder.add(ModItems.XIRANITE_PYLON_ITEM, "息壤供电桩");
        translationBuilder.add(ModItems.XIRANITE_RELAY_ITEM, "息壤中继器");
        translationBuilder.add(ModItems.THERMAL_BANK_ITEM, "热能池");
        // endregion
        // region Resourcing
        translationBuilder.add(ModItems.ELECTRIC_MINING_RIG_ITEM, "电驱矿机");
        translationBuilder.add(ModItems.ELECTRIC_MINING_RIG_MK_II_ITEM, "二型电驱矿机");
        translationBuilder.add(ModItems.PORTABLE_ORIGINIUM_RIG_ITEM, "便携源石矿机");
        translationBuilder.add(ModItems.HYDRO_MINING_RIG_ITEM, "水驱矿机");
        translationBuilder.add(ModBlocks.FLUID_PUMP, "水泵");
        translationBuilder.add(ModBlocks.ACID_RESISTANT_PUMP_MK_II, "二型耐酸水泵");
        // endregion
        // region Production1
        translationBuilder.add(ModItems.REFINING_UNIT_ITEM, "精炼炉");
        translationBuilder.add(ModItems.SHREDDING_UNIT_ITEM, "粉碎机");
        translationBuilder.add(ModItems.PLANTING_UNIT_ITEM, "种植机");
        translationBuilder.add(ModItems.SEED_PICKING_UNIT_ITEM, "采种机");
        translationBuilder.add(ModItems.MOULDING_UNIT_ITEM, "塑形机");
        translationBuilder.add(ModItems.FITTING_UNIT_ITEM, "配件机");
        translationBuilder.add(ModItems.WATER_TREATMENT_UNIT_ITEM, "废水处理机");
        // endregion
        // region Production2
        translationBuilder.add(ModItems.FILLING_UNIT_ITEM, "灌装机");
        translationBuilder.add(ModItems.GEARING_UNIT_ITEM, "装备原件机");
        translationBuilder.add(ModItems.GRINDING_UNIT_ITEM, "研磨机");
        translationBuilder.add(ModItems.PACKAGING_UNIT_ITEM, "封装机");
        translationBuilder.add(ModItems.FORGE_OF_THE_SKY, "天有烘炉");
        translationBuilder.add(ModItems.SEPARATING_UNIT_ITEM, "拆解机");
        translationBuilder.add(ModItems.REACTOR_CRUCIBLE, "反应池");
        translationBuilder.add(ModItems.EXPANDED_CRUCIBLE, "扩容反应池");
        // endregion
        // region Logistics
        translationBuilder.add(ModBlocks.BELT, "传送带");
        translationBuilder.add(ModBlocks.BELT_BRIDGE, "物流桥");
        translationBuilder.add(ModBlocks.CONVERGER, "汇流器");
        translationBuilder.add(ModBlocks.SPLITTER, "分流器");
        translationBuilder.add(ModBlocks.OUTPUT_PORT_BLOCK, "输出端口");
        translationBuilder.add(ModBlocks.FLUID_PIPE, "管道");
        translationBuilder.add(ModBlocks.PIPE_BRIDGE, "管道桥");
        translationBuilder.add(ModBlocks.PIPE_CONVERGER, "管道汇流器");
        translationBuilder.add(ModBlocks.PIPE_SPLITTER, "管道分流器");
        translationBuilder.add(ModBlocks.PROTOCOL_ANCHOR_CORE_PORT, "协议核心端口");
        translationBuilder.add(ModBlocks.PROTOCOL_ANCHOR_CORE_SIDE, "协议核心侧面");
        translationBuilder.add(ModItems.PROTOCOL_STASH_ITEM, "协议储存箱");
        translationBuilder.add(ModItems.DEPOT_BUS_SECTION_ITEM, "仓库存取线基段");
        translationBuilder.add(ModItems.DEPOT_LOADER_ITEM, "协议存货口");
        translationBuilder.add(ModItems.DEPOT_UNLOADER_ITEM, "协议取货口");
        translationBuilder.add(ModBlocks.FLUID_TANK, "储液罐");
        // endregion
        // region Ores
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
        translationBuilder.add(ModBlocks.AMETHYST_ORE_BLOCK, "紫水晶矿");
        translationBuilder.add(ModBlocks.CUPRIUM_MINERAL_VEIN_BLOCK, "赤铜矿脉");
        translationBuilder.add(ModBlocks.CUPRIUM_ORE_BLOCK, "赤铜矿");
        translationBuilder.add(ModBlocks.DEEPSLATE_AMETHYST_ORE, "深板岩紫水晶矿");
        translationBuilder.add(ModBlocks.DEEPSLATE_CUPRIUM_ORE, "深板岩赤铜矿");
        translationBuilder.add(ModBlocks.DEEPSLATE_FERRIUM_ORE, "深板岩蓝铁矿");
        translationBuilder.add(ModBlocks.DEEPSLATE_ORIGINIUM_ORE, "深板岩源石矿");
        translationBuilder.add(ModBlocks.FERRIUM_MINERAL_VEIN_BLOCK, "蓝铁矿脉");
        translationBuilder.add(ModBlocks.FERRIUM_ORE_BLOCK, "蓝铁矿");
        translationBuilder.add(ModBlocks.ORIGINIUM_ORE_BLOCK, "源石矿");
        // endregion
        // region Combat
        translationBuilder.add(ModBlocks.BEAM_TOWER, "射线塔");
        translationBuilder.add(ModBlocks.GUN_TOWER, "铳械塔");
        translationBuilder.add(ModBlocks.HEAVY_GUN_TOWER, "扩装铳械塔");
        translationBuilder.add(ModBlocks.GRENADE_TOWER, "榴弹塔");
        translationBuilder.add(ModBlocks.HE_GRENADE_TOWER, "高爆榴弹塔");
        translationBuilder.add(ModBlocks.SENTRY_TOWER, "哨戒塔");
        translationBuilder.add(ModBlocks.MEDICAL_TOWER, "医疗塔");
        translationBuilder.add(ModBlocks.LN_TOWER, "液氮塔");
        translationBuilder.add(ModBlocks.MARSH_GAS_MK_I, "“毒沼”MK-I");
        translationBuilder.add(ModBlocks.OMNIDIRECTIONAL_SONIC_TOWER, "全向声波塔");
        translationBuilder.add(ModBlocks.SURGE_TOWER, "电涌塔");
        // endregion
        // region Core Tooltip
        translationBuilder.add("endfield.range", "攻击范围: %s m");
        translationBuilder.add("endfield.attack", "攻击力: %s");
        translationBuilder.add("endfield.cooldown", "攻击间隔: %s s");
        translationBuilder.add("endfield.powerCost", "能耗: %s EFU");

        translationBuilder.add("endfield.protocolAnchorCoreTooltip", "不能合成");
        translationBuilder.add("endfield.protocolAnchorCoreTooltip2", "建议将放置的区块设置为常加载");

        translationBuilder.add("protocol_anchor_core_port.tooltip1", "破坏协议核心周围方块后放置此方块，可输入/输出物品");
        translationBuilder.add("protocol_anchor_core_port.tooltip2", "使用某物品右键——设置为只能输出该物品");
        translationBuilder.add("protocol_anchor_core_port.tooltip3", "空手右键——打开GUI");
        translationBuilder.add("protocol_anchor_core_port.tooltip4", "Shift + 空手右键——取消输出筛选限制");
        // endregion
        // region Fluid Buckets
        translationBuilder.add(ModItems.SEWAGE_BUCKET, "污水桶");
        translationBuilder.add(ModItems.PRECIPITATION_ACID_BUCKET, "沉积酸桶");
        translationBuilder.add(ModItems.XIRCON_EFFLUENT_BUCKET, "壤晶废液桶");
        translationBuilder.add(ModItems.INERT_XIRCON_EFFLUENT_BUCKET, "惰性壤晶废液桶");
        translationBuilder.add(ModItems.YAZHEN_SOLUTION_BUCKET, "芽针溶液桶");
        translationBuilder.add(ModItems.JINCAO_SOLUTION_BUCKET, "锦草溶液桶");
        translationBuilder.add(ModItems.LIQUID_XIRANITE_BUCKET, "液化息壤桶");
        // endregion
        // region Fluid Bottles
        String[][] bottledMaterials = {
                {"amethyst", "紫晶质瓶"}, {"ferrium", "蓝铁瓶"}, {"cryston", "高晶质瓶"},
                {"steel", "钢质瓶"}, {"hetonite", "赫铜瓶"},{"cuprium", "赤铜瓶"}};
        String[][] bottledFluids = {
                {"water", "水"}, {"sewage", "污水"}, {"precipitation_acid", "沉积酸"},
                {"xircon_effluent", "壤晶废液"}, {"inert_xircon_effluent", "惰性壤晶废液"},
                {"yazhen_solution", "芽针溶液"}, {"jincao_solution", "锦草溶液"},
                {"liquid_xiranite", "液化息壤"}};
        for (String[] material : bottledMaterials) {
            for (String[] fluid : bottledFluids) {
                translationBuilder.add("item.arknights_endfield." + material[0] + "_bottled_" + fluid[0],
                        material[1] + "（" + fluid[1] + "）");
            }
        }
        // endregion

        translationBuilder.add(ModBlocks.CRAFTER, "制造台");

        translationBuilder.add(ModBlocks.AMETHYST_DRUSE, "紫晶晶簇");
        translationBuilder.add(ModBlocks.FERRIUM_DRUSE, "蓝铁晶簇");
        translationBuilder.add(ModBlocks.ORIGINIUM_DRUSE, "源石晶簇");
        
        translationBuilder.add(ModItems.AGGAGRIT, "异香石");
        translationBuilder.add(ModItems.AGGAGRIT_BLOCK, "异香石块");
        translationBuilder.add(ModItems.AGGAGRIT_CLUSTER, "异香石簇");
        translationBuilder.add(ModItems.AKETINE, "酮化灌木");
        translationBuilder.add(ModItems.AKETINE_POWDER, "酮化灌木粉末");
        translationBuilder.add(ModItems.AKETINE_SEED, "酮化树种");
        translationBuilder.add(ModItems.AMBER_RICE, "金石稻");
        translationBuilder.add(ModItems.AMBER_RICE_SEED, "金石稻种子");
        translationBuilder.add(ModItems.AMETHYST_BOTTLE, "紫晶质瓶");
        translationBuilder.add(ModItems.AMETHYST_COMPONENT, "紫晶装备原件");
        translationBuilder.add(ModItems.AMETHYST_FIBER, "紫晶纤维");
        translationBuilder.add(ModItems.AMETHYST_ORE, "紫晶矿");
        translationBuilder.add(ModItems.AMETHYST_PART, "紫晶零件");
        translationBuilder.add(ModItems.AMETHYST_POWDER, "紫晶粉末");
        translationBuilder.add(ModItems.ARTS_TUBE, "Arts Tube");
        translationBuilder.add(ModItems.ARTS_VIAL, "刻术瓶");
        translationBuilder.add(ModItems.ASHPIN_REMEDY, "灰针秘剂");
        translationBuilder.add(ModItems.BIZARROTACK, "怪味饼干");
        translationBuilder.add(ModItems.BIZARRO_CHILI, "Bizarro Chili");
        translationBuilder.add(ModItems.BLANCHED_REMEDY, "苍白秘剂");
        translationBuilder.add(ModItems.BUCKFLOWER, "荞花");
        translationBuilder.add(ModItems.BUCKFLOWER_POWDER, "荞花粉末");
        translationBuilder.add(ModItems.BUCKFLOWER_SEED, "荞花种子");
        translationBuilder.add(ModItems.BUCKPILL_L, "大瓶荞复锭剂");
        translationBuilder.add(ModItems.BUCKPILL_RF, "精选荞复锭剂");
        translationBuilder.add(ModItems.BUCKPILL_S, "小瓶荞复锭剂");
        translationBuilder.add(ModItems.BUCK_CAPSULE_A, "精选荞愈胶囊");
        translationBuilder.add(ModItems.BUCK_CAPSULE_B, "优质荞愈胶囊");
        translationBuilder.add(ModItems.BUCK_CAPSULE_C, "荞愈胶囊");
        translationBuilder.add(ModItems.BUGTACK, "虫粉饼干");
        translationBuilder.add(ModItems.CANNED_CITROME_A, "精选柑实罐头");
        translationBuilder.add(ModItems.CANNED_CITROME_B, "优质柑实罐头");
        translationBuilder.add(ModItems.CANNED_CITROME_C, "柑实罐头");
        translationBuilder.add(ModItems.CARBON, "碳块");
        translationBuilder.add(ModItems.CARBON_POWDER, "碳粉末");
        translationBuilder.add(ModItems.CARTILAGE_BIT, "软骨碎屑");
        translationBuilder.add(ModItems.CARTILAGE_TACK, "软骨饼干");
        translationBuilder.add(ModItems.CHITIN_BIT, "虫壳碎屑");
        translationBuilder.add(ModItems.CITROBUCKY_MIX, "荞柑混剂");
        translationBuilder.add(ModItems.CITROME, "柑实");
        translationBuilder.add(ModItems.CITROME_JAM, "柑橘风味果酱");
        translationBuilder.add(ModItems.CITROME_JELLY, "柑实风味果冻");
        translationBuilder.add(ModItems.CITROME_POWDER, "柑实粉末");
        translationBuilder.add(ModItems.CITROME_PUDDING, "瓶装柑橘布丁");
        translationBuilder.add(ModItems.CITROME_SEED, "柑实种子");
        translationBuilder.add(ModItems.CITROMIX, "柑实冲剂");
        translationBuilder.add(ModItems.CITROMIX_L, "Citromix L");
        translationBuilder.add(ModItems.CITROMIX_RF, "Citromix Rf");
        translationBuilder.add(ModItems.CITROMIX_S, "Citromix S");
        translationBuilder.add(ModItems.COARSE_FLATBREAD, "粗制麦饼");
        translationBuilder.add(ModItems.CORRECTIVE_REMEDY, "Corrective Remedy");
        translationBuilder.add(ModItems.CRYSTON_BOTTLE, "高晶质瓶");
        translationBuilder.add(ModItems.CRYSTON_COMPONENT, "高晶装备原件");
        translationBuilder.add(ModItems.CRYSTON_FIBER, "高晶纤维");
        translationBuilder.add(ModItems.CRYSTON_PART, "高晶零件");
        translationBuilder.add(ModItems.CRYSTON_POWDER, "高晶粉末");
        translationBuilder.add(ModItems.CUPRIUM, "赤铜块");
        translationBuilder.add(ModItems.CUPRIUM_COMPONENT, "赤铜装备原件");
        translationBuilder.add(ModItems.CUPRIUM_JAR, "赤铜罐");
        translationBuilder.add(ModItems.CUPRIUM_ORE, "赤铜矿");
        translationBuilder.add(ModItems.CUPRIUM_PART, "铜制零件");
        translationBuilder.add(ModItems.CUPRIUM_POWDER, "赤铜粉末");
        translationBuilder.add(ModItems.CUPRIUM_BOTTLE, "赤铜瓶");
        translationBuilder.add(ModItems.DENSE_CARBON_POWDER, "致密碳粉末");
        translationBuilder.add(ModItems.DENSE_FERRIUM_POWDER, "致密蓝铁粉末");
        translationBuilder.add(ModItems.DENSE_ORIGINIUM_POWDER, "致密源石粉末");
        translationBuilder.add(ModItems.DENSE_ORIGOCRUST_POWDER, "致密晶体粉末");
        translationBuilder.add(ModItems.FERRIUM, "蓝铁块");
        translationBuilder.add(ModItems.FERRIUM_BOTTLE, "蓝铁瓶");
        translationBuilder.add(ModItems.FERRIUM_COMPONENT, "蓝铁装备原件");
        translationBuilder.add(ModItems.FERRIUM_ORE, "蓝铁矿");
        translationBuilder.add(ModItems.FERRIUM_PART, "铁质零件");
        translationBuilder.add(ModItems.FERRIUM_POWDER, "蓝铁粉末");
        translationBuilder.add(ModItems.FILLET, "兽肉");
        translationBuilder.add(ModItems.FILLET_CONFIT, "油封赤盐焗兽排");
        translationBuilder.add(ModItems.FIREBUCKLE, "映火荞花");
        translationBuilder.add(ModItems.FIREBUCKLE_POWDER, "荞愈粉末");
        translationBuilder.add(ModItems.FIRESTOVE_RICE, "Firestove Rice");
        translationBuilder.add(ModItems.FIRETACK, "Firetack");
        translationBuilder.add(ModItems.FLUFFED_JINCAO, "蓬茸锦草");
        translationBuilder.add(ModItems.FLUFFED_JINCAO_POWDER, "蓬茸锦草粉末");
        translationBuilder.add(ModItems.FORTIFYING_INFUSION, "正本补元汤剂");
        translationBuilder.add(ModItems.GARDEN_FRIED_RICE, "Garden Fried Rice");
        translationBuilder.add(ModItems.GARDEN_STIR_FRY, "炝炒时蔬");
        translationBuilder.add(ModItems.GINSENG_MEAT_STEW, "Ginseng Meat Stew");
        translationBuilder.add(ModItems.GLOWBUG, "萤壳虫");
        translationBuilder.add(ModItems.GRASS_CHAFF, "草籽碎屑");
        translationBuilder.add(ModItems.GROUND_BUCKFLOWER_POWDER, "细磨荞花粉末");
        translationBuilder.add(ModItems.GROUND_CITROME_POWDER, "细末柑实粉末");
        translationBuilder.add(ModItems.HC_BATTERY, "高容电池");
        translationBuilder.add(ModItems.HOLLOW_BONE, "中空骨块");
        translationBuilder.add(ModItems.HOLLOW_BONECHIP, "中空骨片");
        translationBuilder.add(ModItems.HOT_CRUNCHY_RIBS, "重辣烤脆骨");
        translationBuilder.add(ModItems.INDUSTRIAL_EXPLOSIVE, "工业爆炸物");
        translationBuilder.add(ModItems.JINCAO, "锦草");
        translationBuilder.add(ModItems.JINCAO_DRINK, "锦草软饮");
        translationBuilder.add(ModItems.JINCAO_INFUSION, "Jincao Infusion");
        translationBuilder.add(ModItems.JINCAO_POWDER, "锦草粉末");
        translationBuilder.add(ModItems.JINCAO_SEED, "锦草种子");
        translationBuilder.add(ModItems.JINCAO_TEA, "优质锦草软饮");
        translationBuilder.add(ModItems.JINCAO_TISANE, "锦草 Tisane");
        translationBuilder.add(ModItems.KUNST_TUBE, "附术铁瓶");
        translationBuilder.add(ModItems.KUNST_VIAL, "附术瓶");
        translationBuilder.add(ModItems.LC_BATTERY, "低容电池");
        translationBuilder.add(ModItems.MEAT_STIR_FRY, "Meat Stir Fry");
        translationBuilder.add(ModItems.MOSSFIELD_PIE, "Mossfield Pie");
        translationBuilder.add(ModItems.ORIGINIUM_ORE, "源矿");
        translationBuilder.add(ModItems.ORIGINIUM_POWDER, "源石粉末");
        translationBuilder.add(ModItems.ORIGOCRUST, "晶体外壳");
        translationBuilder.add(ModItems.ORIGOCRUST_POWDER, "晶体外壳粉末");
        translationBuilder.add(ModItems.PACKED_ORIGOCRUST, "密制晶体");
        translationBuilder.add(ModItems.PERPLEXING_MEDICATION, "意乱药剂");
        translationBuilder.add(ModItems.PRESERVE_STEW, "干菜羹");
        translationBuilder.add(ModItems.REDJADE_GINSENG, "琼叶参");
        translationBuilder.add(ModItems.REDJADE_GINSENG_SEED, "琼叶参种子");
        translationBuilder.add(ModItems.REED_RYE, "灰芦麦");
        translationBuilder.add(ModItems.REED_RYE_SEED, "灰芦麦种子");
        translationBuilder.add(ModItems.SANDLEAF, "砂叶");
        translationBuilder.add(ModItems.SANDLEAF_POWDER, "砂叶粉末");
        translationBuilder.add(ModItems.SANDLEAF_SEED, "砂叶种子");
        translationBuilder.add(ModItems.SAVORY_FILLET, "异香兽排");
        translationBuilder.add(ModItems.SAVORY_TANGBAO, "异香汤包");
        translationBuilder.add(ModItems.SAVORY_TANGMIAN, "异香汤面");
        translationBuilder.add(ModItems.SCORCHBUG, "灼壳虫");
        translationBuilder.add(ModItems.SC_BATTERY, "中容电池");
        translationBuilder.add(ModItems.SENSORY_REMEDY, "Sensory Remedy");
        translationBuilder.add(ModItems.SMOKED_RICEBALL, "Smoked Riceball");
        translationBuilder.add(ModItems.SOAKED_WOOD, "浸水木块");
        translationBuilder.add(ModItems.SOAKED_WOODCHIP, "浸水木片");
        translationBuilder.add(ModItems.STABILIZED_CARBON, "稳定碳块");
        translationBuilder.add(ModItems.STEEL, "钢块");
        translationBuilder.add(ModItems.STEEL_BOTTLE, "钢质瓶");
        translationBuilder.add(ModItems.STEEL_JAR, "钢质罐");
        translationBuilder.add(ModItems.STEEL_PART, "钢质零件");
        translationBuilder.add(ModItems.TARTPEPPER, "苦叶椒");
        translationBuilder.add(ModItems.TARTPEPPER_PICKLE, "苦叶椒腌菜");
        translationBuilder.add(ModItems.TARTPEPPER_SALAD, "苦叶沙拉");
        translationBuilder.add(ModItems.TARTPEPPER_SEED, "苦叶椒种子");
        translationBuilder.add(ModItems.THORNY_YAZHEN, "荆刺芽针");
        translationBuilder.add(ModItems.THORNY_YAZHEN_POWDER, "荆刺芽针粉末");
        translationBuilder.add(ModItems.UMBRALINE, "Umbraline");
        translationBuilder.add(ModItems.VALLEY_GRAYBREAD, "Valley Graybread");
        translationBuilder.add(ModItems.WOOD, "原木");
        translationBuilder.add(ModItems.WULING_FRIED_RICE, "Wuling Fried Rice");
        translationBuilder.add(ModItems.YAZHEN, "芽针");
        translationBuilder.add(ModItems.YAZHEN_POWDER, "芽针粉末");
        translationBuilder.add(ModItems.YAZHEN_SEED, "芽针种子");
        translationBuilder.add(ModItems.YAZHEN_SPRAY_L, "Yazhen Spray L");
        translationBuilder.add(ModItems.YAZHEN_SPRAY_S, "Yazhen Spray S");
        translationBuilder.add(ModItems.YAZHEN_SYRINGE_A, "优质芽针针剂");
        translationBuilder.add(ModItems.YAZHEN_SYRINGE_C, "芽针针剂");
        
        translationBuilder.add(ModItems.XIRANITE, "息壤");
        translationBuilder.add(ModItems.HEAVY_XIRANITE, "重息壤");
        translationBuilder.add(ModItems.HETONITE, "赫铜块");
        translationBuilder.add(ModItems.HETONITE_BOTTLE, "赫铜瓶");
        translationBuilder.add(ModItems.HETONITE_PART, "赫铜零件");
        translationBuilder.add(ModItems.HETONITE_COMPONENT, "赫铜装备原件");
        translationBuilder.add(ModItems.LC_WULING_BATTERY, "低容武陵电池");
        translationBuilder.add(ModItems.SC_WULING_BATTERY, "中容武陵电池");
        translationBuilder.add(ModItems.XIRANITE_COMPONENT, "息壤装备原件");
        translationBuilder.add(ModItems.XIRCON, "壤晶");
        
        // region BlockEntity Names
        translationBuilder.add("blockEntity.portable_originium_rig", "便携源石矿机");
        translationBuilder.add("blockEntity.hydro_mining_rig", "水驱矿机");
        translationBuilder.add("blockEntity.protocol_anchor_core", "协议核心");
        translationBuilder.add("blockEntity.thermal_bank", "热能池");
        translationBuilder.add("blockEntity.refining_unit", "精炼炉");
        translationBuilder.add("blockEntity.water_treatment_unit", "废水处理机");
        translationBuilder.add("blockEntity.gearing_unit", "装备原件机");
        translationBuilder.add("blockEntity.electric_mining_rig", "电驱矿机");
        translationBuilder.add("blockEntity.electric_mining_rig_mk_ii", "二型电驱矿机");
        translationBuilder.add("blockEntity.shredding_unit", "粉碎机");
        translationBuilder.add("blockEntity.fitting_unit", "配件机");
        translationBuilder.add("blockEntity.moulding_unit", "塑形机");
        translationBuilder.add("blockEntity.planting_unit", "种植机");
        translationBuilder.add("blockEntity.seed_picking_unit", "采种机");
        translationBuilder.add("blockEntity.filling_unit", "灌装机");
        translationBuilder.add("blockEntity.grinding_unit", "研磨机");
        translationBuilder.add("blockEntity.packaging_unit", "封装机");
        translationBuilder.add("blockEntity.crafter", "制造台");
        translationBuilder.add("blockEntity.fluid_pump", "抽水泵");
        translationBuilder.add("blockEntity.supply_terminal", "供货终端");
        translationBuilder.add("blockEntity.protocol_stash", "协议储存箱");
        translationBuilder.add("block.protocol_anchor_core_port", "协议核心端口");
        translationBuilder.add("screen.supply_terminal.level", "Level: %s");
        translationBuilder.add("blockEntity.depot_unloader", "协议取货口");
        translationBuilder.add("blockEntity.fluid_tank", "储液罐");
        translationBuilder.add("blockEntity.reactor_crucible", "反应池");
        translationBuilder.add("blockEntity.expanded_crucible", "扩容反应池");
        translationBuilder.add("blockEntity.separating_unit", "拆解机");
        translationBuilder.add("blockEntity.forge_of_the_sky", "天有烘炉");
        translationBuilder.add("blockEntity.acid_resistant_pump_mk_ii", "二型耐酸水泵");
        // endregion
        // region Screen Translations
        translationBuilder.add("screen.protocol_core.buffer", "电池储能:");
        translationBuilder.add("screen.protocol_core.storedEnergy", " %s /");
        translationBuilder.add("screen.protocol_core.max", "%s");
        translationBuilder.add("screen.protocol_core.generated", "发电功率 %s");
        translationBuilder.add("screen.protocol_core.consumer", "%s 用电功率");
        translationBuilder.add("screen.global_storage", "全局仓储");
        // endregion
        // region Tooltip Translations
        translationBuilder.add("tooltip.water_pump.fluid", "水量: %s / %s B");
        translationBuilder.add("tooltip.water_pump.fluid_type", "%s: %s / %s B");
        translationBuilder.add("tooltip.water_pump.empty", "空");
        translationBuilder.add("tooltip.fluid_pump.stored_power", "储电: %s EFU");

        translationBuilder.add("tooltip.refining_unit.empty", "空");
        translationBuilder.add("tooltip.refining_unit.sewage", "污水: %s / %s B");
        translationBuilder.add("tooltip.refining_unit.sewage_empty", "无污水");
        translationBuilder.add("tooltip.filling_unit.tank", "流体: %s / %s B");
        translationBuilder.add("tooltip.filling_unit.tank_empty", "空");
        translationBuilder.add("tooltip.planting_unit.tank", "流体: %s / %s B");
        translationBuilder.add("tooltip.planting_unit.tank_empty", "空");

        translationBuilder.add("tooltip.water_treatment_unit.empty", "空");
        translationBuilder.add("tooltip.water_treatment_unit.fluid", "%s: %s / %s B");
        translationBuilder.add("tooltip.water_treatment_unit.stored_power", "储电: %s EFU");

        translationBuilder.add("tooltip.separating_unit.fluid", "%s: %s / %s B");
        translationBuilder.add("tooltip.separating_unit.fluid_empty", "空");
        translationBuilder.add("tooltip.separating_unit.stored_power", "储电: %s EFU");

        translationBuilder.add("tooltip.crucible.item_output", "物品输出");
        translationBuilder.add("tooltip.crucible.fluid_output", "流体输出");
        translationBuilder.add("tooltip.crucible.unset", "未设置");
        translationBuilder.add("tooltip.crucible.click_clear", "点击取消设置");
        // endregion
        // region Fluid Translations
        translationBuilder.add("fluid.arknights_endfield.sewage", "污水");
        translationBuilder.add("block.arknights_endfield.sewage", "污水");
        translationBuilder.add("fluid.arknights_endfield.xircon_effluent", "壤晶废液");
        translationBuilder.add("block.arknights_endfield.xircon_effluent", "壤晶废液");
        translationBuilder.add("fluid.arknights_endfield.inert_xircon_effluent", "惰性壤晶废液");
        translationBuilder.add("block.arknights_endfield.inert_xircon_effluent", "惰性壤晶废液");
        translationBuilder.add("fluid.arknights_endfield.yazhen_solution", "芽针溶液");
        translationBuilder.add("block.arknights_endfield.yazhen_solution", "芽针溶液");
        translationBuilder.add("fluid.arknights_endfield.jincao_solution", "锦草溶液");
        translationBuilder.add("block.arknights_endfield.jincao_solution", "锦草溶液");
        translationBuilder.add("fluid.arknights_endfield.liquid_xiranite", "液化息壤");
        translationBuilder.add("block.arknights_endfield.liquid_xiranite", "液化息壤");
        // endregion
        
        // region Command Translations
        translationBuilder.add("commands.endfield.deposit", "已存入物品 %s 个");
        translationBuilder.add("commands.endfield.withdraw", "已取出物品 %s 个");
        translationBuilder.add("commands.endfield.cap", "已将存储上限设置为 %s");
        translationBuilder.add("commands.endfield.deposit.warn", "§c物品不足！你只有 %s §c个，但需要 %s §c个");
        translationBuilder.add("commands.endfield.cap.negative", "§c输入值为负值！");
        // endregion
        
        // region Advancement Translations
        translationBuilder.add("advancement.endfield.root.title", "明日方舟：终末地");
        translationBuilder.add("advancement.endfield.root.description", "欢迎来到明日‘方’舟：终末地！");
        translationBuilder.add("advancement.endfield.originium_ore.title", "源石矿");
        translationBuilder.add("advancement.endfield.originium_ore.description", "终末地工业的基础！你可以在地下发现它们");
        translationBuilder.add("advancement.endfield.amethyst_ore.title", "紫晶矿");
        translationBuilder.add("advancement.endfield.amethyst_ore.description", "更高级的矿石。用它来制作更好的装备吧！");
        translationBuilder.add("advancement.endfield.find_all_ores.title", "矿石收集者");
        translationBuilder.add("advancement.endfield.find_all_ores.description", "收集所有四种终末地矿石：源石矿、赤铜矿、蓝铁矿和紫晶矿");
        translationBuilder.add("advancement.endfield.originium_rig.title", "便携源石矿机");
        translationBuilder.add("advancement.endfield.originium_rig.description", "使用便携源石矿机来开采矿脉方块吧！");
        translationBuilder.add("advancement.endfield.electric_mining_rig.title", "电驱矿机");
        translationBuilder.add("advancement.endfield.electric_mining_rig.description", "使用电驱矿机来开采矿脉方块吧！");
        translationBuilder.add("advancement.endfield.electric_mining_rig_mk_ii.title", "二型电驱矿机");
        translationBuilder.add("advancement.endfield.electric_mining_rig_mk_ii.description", "使用二型电驱矿机来更高效地开采矿脉方块吧！");
        translationBuilder.add("advancement.endfield.all_mineral_vein_block.title", "矿脉收集者");
        translationBuilder.add("advancement.endfield.all_mineral_vein_block.description", "收集所有矿脉方块，包括终末地和原有矿石的矿脉方块");
        translationBuilder.add("advancement.endfield.relay_tower.title", "中继器");
        translationBuilder.add("advancement.endfield.relay_tower.description", "使用中继器来扩展你的电力网络");
        translationBuilder.add("advancement.endfield.electric_pylon.title", "供电桩");
        translationBuilder.add("advancement.endfield.electric_pylon.description", "使用供电桩来为你的机器供电");
        translationBuilder.add("advancement.endfield.crafting.title", "制造台");
        translationBuilder.add("advancement.endfield.crafting.description", "使用制造台来制作终末地的工业设备");
        translationBuilder.add("advancement.endfield.refining_unit.title", "精炼炉");
        translationBuilder.add("advancement.endfield.refining_unit.description", "熔炼材料");
        translationBuilder.add("advancement.endfield.shredding_unit.title", "粉碎机");
        translationBuilder.add("advancement.endfield.shredding_unit.description", "粉碎物品");
        translationBuilder.add("advancement.endfield.fitting_unit.title", "配件机");
        translationBuilder.add("advancement.endfield.fitting_unit.description", "制造零件");
        translationBuilder.add("advancement.endfield.moulding_unit.title", "塑形机");
        translationBuilder.add("advancement.endfield.moulding_unit.description", "塑形瓶子");
        translationBuilder.add("advancement.endfield.planting_unit.title", "种植机");
        translationBuilder.add("advancement.endfield.planting_unit.description", "种植作物");
        translationBuilder.add("advancement.endfield.seed_picking_unit.title", "采种机");
        translationBuilder.add("advancement.endfield.seed_picking_unit.description", "采集种子");
        translationBuilder.add("advancement.endfield.gearing_unit.title", "装备原件机");
        translationBuilder.add("advancement.endfield.gearing_unit.description", "制造装备原件");
        translationBuilder.add("advancement.endfield.filling_unit.title", "灌装机");
        translationBuilder.add("advancement.endfield.filling_unit.description", "灌装容器");
        translationBuilder.add("advancement.endfield.grinding_unit.title", "研磨机");
        translationBuilder.add("advancement.endfield.grinding_unit.description", "制造更高级的粉末");
        translationBuilder.add("advancement.endfield.packaging_unit.title", "封装机");
        translationBuilder.add("advancement.endfield.packaging_unit.description", "封装物品");
        // endregion
        
        translationBuilder.add(ModItems.ORIGINIUM_HAMMER, "源石锤");
        
        // region Control Item
        translationBuilder.add(ModItems.CONTROL_ITEM, "控制终端");
        translationBuilder.add("message.control_item.pipe_mode.transfer", "管道已切换为默认模式（纯传输）");
        translationBuilder.add("message.control_item.pipe_mode.extract", "管道已切换为抽取模式");
        translationBuilder.add("message.control_item.pipe_mode.insert", "管道已切换为注入模式");
        translationBuilder.add("message.control_item.crucible_port.bound", "该侧面已绑定流体输出口 %s：抽取管道在此只会抽到输出槽 %s 的流体");
        translationBuilder.add("message.control_item.crucible_port.unbound", "该侧面已解绑流体输出口：仅可注入，不可抽取");
        translationBuilder.add("message.control_item.start_recorded", "开始电力运输，起点已记录：%s, %s, %s");
        translationBuilder.add("message.control_item.completed", "电力运输完成！起点：%s,%s,%s -> 终点：%s,%s,%s");
        translationBuilder.add("message.control_item.no_start", "未记录起点，请先蹲下并右键中继器、供电桩或协议核心开始电力运输！");
        // endregion
        
        // region Other Translations
        translationBuilder.add("itemGroup.arknights_endfield", "明日方舟：终末地" );

        translationBuilder.add("endfield.fluid_mode.open", "流体模式: 开启");
        translationBuilder.add("endfield.fluid_mode.close", "流体模式: 关闭");
        translationBuilder.add("endfield.fluid_mode.blocked", "上方空间不足，无法开启流体模式");
        translationBuilder.add("endfield.switch_button.enable", "工作");
        translationBuilder.add("endfield.switch_button.disable", "停止");

        translationBuilder.add("message.no_enough_area", "没有足够的空间放置该方块");
        translationBuilder.add("endfield.require_area", "需要空间: %s");

        translationBuilder.add("protocol_stash.tooltip", "在通电状态下，每隔10秒提交一次存储的物品");
        translationBuilder.add("depot_unloader.tooltip", "需要紧贴仓库存取线基段放置才能工作");
        translationBuilder.add("belt.tooltip", "并列放置时，按住 SHIFT 可获得更好的效果");

        translationBuilder.add("key.endfield.open_storage", "打开全局仓库");
        translationBuilder.add("category.endfield", "终末地工业");

        translationBuilder.add("electric_pylon.title", "当前电网状态：");
        translationBuilder.add("electric_pylon.total_generated", "总发电量：%s");
        translationBuilder.add("electric_pylon.total_demand", "总耗电量：%s");
        translationBuilder.add("electric_pylon.stored_power", "电池储能：%s");

        // region Welcome package
        translationBuilder.add("message.arknights_endfield.welcome_package", "终末地工业：更新礼包已发放到背包。");
        translationBuilder.add("message.arknights_endfield.welcome_package_dropped", "背包已满，%s 件礼包物品掉落在脚下，请尽快拾取。");
        // endregion
        // endregion
    }
}
