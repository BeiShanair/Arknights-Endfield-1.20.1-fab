package com.besson.endfield.compat;

import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.compat.category.*;
import com.besson.endfield.compat.display.*;
import com.besson.endfield.recipe.custom.*;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;

public class ModREIClientPlugins implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new PortableOriginiumRigCategory());
        registry.add(new ElectricMiningRigCategory());
        registry.add(new ElectricMiningRigMkIICategory());
        registry.add(new CrafterCategory());
        registry.add(new FillingUnitCategory());
        registry.add(new GearingUnitCategory());
        registry.add(new RefiningUnitCategory());
        registry.add(new FittingUnitCategory());
        registry.add(new GrindingUnitCategory());
        registry.add(new MouldingUnitCategory());
        registry.add(new PackagingUnitCategory());
        registry.add(new PlantingUnitCategory());
        registry.add(new SeedPickingUnitCategory());
        registry.add(new ShreddingUnitCategory());

        registry.addWorkstations(PortableOriginiumRigCategory.PORTABLE_ORIGINIUM_RIG, EntryStacks.of(ModBlocks.PORTABLE_ORIGINIUM_RIG));
        registry.addWorkstations(ElectricMiningRigCategory.ELECTRIC_MINING_RIG, EntryStacks.of(ModBlocks.ELECTRIC_MINING_RIG));
        registry.addWorkstations(ElectricMiningRigMkIICategory.ELECTRIC_MINING_RIG_MK_II, EntryStacks.of(ModBlocks.ELECTRIC_MINING_RIG_MK_II));
        registry.addWorkstations(CrafterCategory.CRAFTER, EntryStacks.of(ModBlocks.CRAFTER));
        registry.addWorkstations(FillingUnitCategory.FILLING_UNIT, EntryStacks.of(ModBlocks.FILLING_UNIT));
        registry.addWorkstations(GearingUnitCategory.GEARING_UNIT, EntryStacks.of(ModBlocks.GEARING_UNIT));
        registry.addWorkstations(RefiningUnitCategory.REFINING_UNIT, EntryStacks.of(ModBlocks.REFINING_UNIT));
        registry.addWorkstations(FittingUnitCategory.FITTING_UNIT, EntryStacks.of(ModBlocks.FITTING_UNIT));
        registry.addWorkstations(GrindingUnitCategory.GRINDING_UNIT, EntryStacks.of(ModBlocks.GRINDING_UNIT));
        registry.addWorkstations(MouldingUnitCategory.MOULDING_UNIT, EntryStacks.of(ModBlocks.MOULDING_UNIT));
        registry.addWorkstations(PackagingUnitCategory.PACKAGING_UNIT, EntryStacks.of(ModBlocks.PACKAGING_UNIT));
        registry.addWorkstations(PlantingUnitCategory.PLANTING_UNIT, EntryStacks.of(ModBlocks.PLANTING_UNIT));
        registry.addWorkstations(SeedPickingUnitCategory.SEED_PICKING_UNIT, EntryStacks.of(ModBlocks.SEED_PICKING_UNIT));
        registry.addWorkstations(ShreddingUnitCategory.SHREDDING_UNIT, EntryStacks.of(ModBlocks.SHREDDING_UNIT));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {

        registry.registerRecipeFiller(OreRigRecipe.class, OreRigRecipe.Type.INSTANCE, PortableOriginiumRigDisplay::new);
        registry.registerRecipeFiller(OreRigRecipe.class, OreRigRecipe.Type.INSTANCE, ElectricMiningRigDisplay::new);
        registry.registerRecipeFiller(OreRigRecipe.class, OreRigRecipe.Type.INSTANCE, ElectricMiningRigMkIIDisplay::new);
        registry.registerRecipeFiller(CrafterRecipe.class, CrafterRecipe.Type.INSTANCE, CrafterDisplay::new);
        registry.registerRecipeFiller(FillingUnitRecipe.class, FillingUnitRecipe.Type.INSTANCE, FillingUnitDisplay::new);
        registry.registerRecipeFiller(GearingUnitRecipe.class, GearingUnitRecipe.Type.INSTANCE, GearingUnitDisplay::new);
        registry.registerRecipeFiller(RefiningUnitRecipe.class, RefiningUnitRecipe.Type.INSTANCE, RefiningUnitDisplay::new);
        registry.registerRecipeFiller(FittingUnitRecipe.class, FittingUnitRecipe.Type.INSTANCE, FittingUnitDisplay::new);
        registry.registerRecipeFiller(GrindingUnitRecipe.class, GrindingUnitRecipe.Type.INSTANCE, GrindingUnitDisplay::new);
        registry.registerRecipeFiller(MouldingUnitRecipe.class, MouldingUnitRecipe.Type.INSTANCE, MouldingUnitDisplay::new);
        registry.registerRecipeFiller(PackagingUnitRecipe.class, PackagingUnitRecipe.Type.INSTANCE, PackagingUnitDisplay::new);
        registry.registerRecipeFiller(PlantingUnitRecipe.class, PlantingUnitRecipe.Type.INSTANCE, PlantingUnitDisplay::new);
        registry.registerRecipeFiller(SeedPickingUnitRecipe.class, SeedPickingUnitRecipe.Type.INSTANCE, SeedPickingUnitDisplay::new);
        registry.registerRecipeFiller(ShreddingUnitRecipe.class, ShreddingUnitRecipe.Type.INSTANCE, ShreddingUnitDisplay::new);
    }
}
