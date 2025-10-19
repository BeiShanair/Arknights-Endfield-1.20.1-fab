package com.besson.endfield.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent SAVORY_TANGBAO = new FoodComponent.Builder().hunger(5).saturationModifier(2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 10800), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10800), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 10800), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10800), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent CITROME_PUDDING = new FoodComponent.Builder().hunger(3).saturationModifier(0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 6000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent VALLEY_GRAYBREAD = new FoodComponent.Builder().hunger(4).saturationModifier(1.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 3000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent CITROME_JELLY = new FoodComponent.Builder().hunger(2).saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent SAVORY_TANGMIAN = new FoodComponent.Builder().hunger(4).saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10800), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent CITROME_JAM = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10800), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent SAVORY_FILLET = new FoodComponent.Builder().hunger(4).saturationModifier(1.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 3000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent FILLET_CONFIT = new FoodComponent.Builder().hunger(6).saturationModifier(2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10800), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 10800), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent BUCKPILL_S = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent BUCKPILL_L = new FoodComponent.Builder().hunger(2).saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 2000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent BUCKPILL_RF = new FoodComponent.Builder().hunger(2).saturationModifier(0.4f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 4000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent CITROMIX_S = new FoodComponent.Builder().hunger(3).saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent CITROMIX_L = new FoodComponent.Builder().hunger(5).saturationModifier(0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent CITROMIX_RF = new FoodComponent.Builder().hunger(8).saturationModifier(0.7f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 4000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent CANNED_CITROME_C = new FoodComponent.Builder().hunger(6).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent CANNED_CITROME_B = new FoodComponent.Builder().hunger(10).saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent CANNED_CITROME_A = new FoodComponent.Builder().hunger(14).saturationModifier(1.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 4000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent JINCAO_TISANE = new FoodComponent.Builder().hunger(4).saturationModifier(0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 2000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent JINCAO_INFUSION = new FoodComponent.Builder().hunger(4).saturationModifier(0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 4000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent ART_VIAL = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent ART_TUBE = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 4000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent KUNST_VIAL = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 5400), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent KUBST_TUBE = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10800), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent PERPLEXING_MEDICATION = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10800), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10800), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent BIZARRO_CHILI = new FoodComponent.Builder().hunger(4).saturationModifier(0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 4000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent CITROBUCKY_MIX = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 4000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent BUCK_CAPSULE_C = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent BUCK_CAPSULE_B = new FoodComponent.Builder().hunger(2).saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 2000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent BUCK_CAPSULE_A = new FoodComponent.Builder().hunger(2).saturationModifier(0.4f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 4000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent YAZHEN_SPRAY_S = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 3000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent YAZHEN_SPRAY_L = new FoodComponent.Builder().hunger(2).saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent MOSSFIELD_PIE = new FoodComponent.Builder().hunger(5).saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 2000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent COARSE_FLATBREAD = new FoodComponent.Builder().hunger(3).saturationModifier(0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent MEAT_STIR_FRY = new FoodComponent.Builder().hunger(6).saturationModifier(1.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 2000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent TARTPEPPER_SALAD = new FoodComponent.Builder().hunger(4).saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 2000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent GINSENG_MEAT_STEW = new FoodComponent.Builder().hunger(7).saturationModifier(2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10800), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 10800), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent FORTIFYING_INFUSION = new FoodComponent.Builder().hunger(5).saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10800), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent WULING_FRIED_RICE = new FoodComponent.Builder().hunger(6).saturationModifier(1.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 10800), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent GARDEN_FRIED_RICE = new FoodComponent.Builder().hunger(5).saturationModifier(1.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 3000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent HOT_CRUNCHY_RIBS = new FoodComponent.Builder().hunger(7).saturationModifier(2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 600), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 600), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent CARTILAGE_TACK = new FoodComponent.Builder().hunger(4).saturationModifier(1f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent FIRETACK = new FoodComponent.Builder().hunger(6).saturationModifier(1.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10800), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent BUGTACK = new FoodComponent.Builder().hunger(5).saturationModifier(1.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 5400), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent TARTPEPPER_PICKLE = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 10800), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent BIZARROTACK = new FoodComponent.Builder().hunger(7).saturationModifier(2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 5400), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent CORRECTIVE_REMEDY = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent BLANCHED_REMEDY = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 6000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent SENSORY_REMEDY = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 6000), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent ASHPIN_REMEDY = new FoodComponent.Builder().hunger(2).saturationModifier(0.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 2000), 1.0f)
            .alwaysEdible().build();
    public static  final FoodComponent SMOKED_RICEBALL = new FoodComponent.Builder().hunger(3).saturationModifier(0.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10800), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 10800), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent GARDEN_STIR_FRY = new FoodComponent.Builder().hunger(5).saturationModifier(1.2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 5400), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent FIRESTOVE_RICE = new FoodComponent.Builder().hunger(7).saturationModifier(2f)
            .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 10800), 1.0f)
            .alwaysEdible().build();
    public static final FoodComponent PRESERVE_STEW = new FoodComponent.Builder().hunger(6).saturationModifier(1.5f)
            .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10800), 1.0f)
            .alwaysEdible().build();
}
