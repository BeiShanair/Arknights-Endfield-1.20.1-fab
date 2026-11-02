package com.besson.endfield.utils;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.depot.FluidTankBlockEntity;
import com.besson.endfield.blockentity.custom.powering.ThermalBankBlockEntity;
import com.besson.endfield.blockentity.custom.production1.*;
import com.besson.endfield.blockentity.custom.production2.*;
import com.besson.endfield.blockentity.custom.resourcing.AcidResistantPumpMkIIBlockEntity;
import com.besson.endfield.blockentity.custom.resourcing.FluidPumpBlockEntity;
import com.besson.endfield.blockentity.custom.resourcing.HydroMiningRigBlockEntity;
import com.besson.endfield.blockentity.custom.depot.ProtocolStashBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;

public class ModStorageRegister {
    public static void register() {
        FluidStorage.SIDED.registerForBlockEntity(FluidPumpBlockEntity::getFluidStorage, ModBlockEntities.FLUID_PUMP);
        FluidStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    FluidPumpBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getFluidStorage(side);
                }, ModBlockEntities.FLUID_PUMP_SIDE);
        FluidStorage.SIDED.registerForBlockEntity(AcidResistantPumpMkIIBlockEntity::getFluidStorage, ModBlockEntities.ACID_RESISTANT_PUMP_MK_II);
        FluidStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    AcidResistantPumpMkIIBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getFluidStorage(side);
                }, ModBlockEntities.ACID_RESISTANT_PUMP_MK_II_SIDE);
        FluidStorage.SIDED.registerForBlockEntity(FluidTankBlockEntity::getFluidStorage, ModBlockEntities.FLUID_TANK);
        FluidStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    FluidTankBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getFluidStorage(side);
                }, ModBlockEntities.FLUID_TANK_SIDE);
        FluidStorage.SIDED.registerForBlockEntity(HydroMiningRigBlockEntity::getFluidStorage, ModBlockEntities.HYDRO_MINING_RIG);
        FluidStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    HydroMiningRigBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getFluidStorage(side);
                }, ModBlockEntities.HYDRO_MINING_RIG_SIDE);
        FluidStorage.SIDED.registerForBlockEntity(RefiningUnitBlockEntity::getFluidStorage, ModBlockEntities.REFINING_UNIT);
        FluidStorage.SIDED.registerForBlockEntity(WaterTreatmentUnitBlockEntity::getFluidStorage, ModBlockEntities.WATER_TREATMENT_UNIT);
        FluidStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    WaterTreatmentUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getFluidStorage(side);
                }, ModBlockEntities.WATER_TREATMENT_UNIT_SIDE);
        FluidStorage.SIDED.registerForBlockEntity(ReactorCrucibleBlockEntity::getFluidStorage, ModBlockEntities.REACTOR_CRUCIBLE);
        FluidStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    ReactorCrucibleBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getPortFluidStorage(sideBe.getFluidPortIndex());
                }, ModBlockEntities.REACTOR_CRUCIBLE_SIDE);
        FluidStorage.SIDED.registerForBlockEntity(ExpandedCrucibleBlockEntity::getFluidStorage, ModBlockEntities.EXPANDED_CRUCIBLE);
        FluidStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    ExpandedCrucibleBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getPortFluidStorage(sideBe.getFluidPortIndex());
                }, ModBlockEntities.EXPANDED_CRUCIBLE_SIDE);
        FluidStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    RefiningUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getFluidStorage(side);
                }, ModBlockEntities.REFINING_UNIT_SIDE);
        FluidStorage.SIDED.registerForBlockEntity(SeparatingUnitBlockEntity::getFluidStorage, ModBlockEntities.SEPARATING_UNIT);
        FluidStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    SeparatingUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getFluidStorage(side);
                }, ModBlockEntities.SEPARATING_UNIT_SIDE);
        FluidStorage.SIDED.registerForBlockEntity(FillingUnitBlockEntity::getFluidStorage, ModBlockEntities.FILLING_UNIT);
        FluidStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    FillingUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getFluidStorage(side);
                }, ModBlockEntities.FILLING_UNIT_SIDE);
        FluidStorage.SIDED.registerForBlockEntity(PlantingUnitBlockEntity::getFluidStorage, ModBlockEntities.PLANTING_UNIT);
        FluidStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    PlantingUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getFluidStorage(side);
                }, ModBlockEntities.PLANTING_UNIT_SIDE);
        FluidStorage.SIDED.registerForBlockEntity(ForgeOfTheSkyBlockEntity::getFluidStorage, ModBlockEntities.FORGE_OF_THE_SKY);
        FluidStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    ForgeOfTheSkyBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getFluidStorage(side);
                }, ModBlockEntities.FORGE_OF_THE_SKY_SIDE);

        ItemStorage.SIDED.registerForBlockEntity((be, dir) -> be.getStorage(), ModBlockEntities.ELECTRIC_MINING_RIG);
        ItemStorage.SIDED.registerForBlockEntity((be, dir) -> be.getStorage(), ModBlockEntities.ELECTRIC_MINING_RIG_MK_II);
        
        ItemStorage.SIDED.registerForBlockEntity((be, dir) -> be.getStorage(be.getCachedState(), dir), ModBlockEntities.THERMAL_BANK);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    ThermalBankBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getStorage(sideBe.getCachedState(), side);
                }, ModBlockEntities.THERMAL_BANK_SIDE);
        
        ItemStorage.SIDED.registerForBlockEntity((be, side) -> be.getStorage(be.getCachedState(), side), ModBlockEntities.FILLING_UNIT);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    FillingUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getStorage(sideBe.getCachedState(), side);
                }, ModBlockEntities.FILLING_UNIT_SIDE);
        ItemStorage.SIDED.registerForBlockEntity((be, side) -> be.getStorage(be.getCachedState(), side), ModBlockEntities.FITTING_UNIT);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    FittingUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getStorage(sideBe.getCachedState(), side);
                }, ModBlockEntities.FITTING_UNIT_SIDE);
        ItemStorage.SIDED.registerForBlockEntity((be, side) -> be.getStorage(be.getCachedState(), side), ModBlockEntities.GEARING_UNIT);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    GearingUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getStorage(sideBe.getCachedState(), side);
                }, ModBlockEntities.GEARING_UNIT_SIDE);
        ItemStorage.SIDED.registerForBlockEntity((be, side) -> be.getStorage(be.getCachedState(), side), ModBlockEntities.GRINDING_UNIT);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    GrindingUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getStorage(sideBe.getCachedState(), side);
                }, ModBlockEntities.GRINDING_UNIT_SIDE);
        ItemStorage.SIDED.registerForBlockEntity((be, side) -> be.getStorage(be.getCachedState(), side), ModBlockEntities.MOULDING_UNIT);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    MouldingUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getStorage(sideBe.getCachedState(), side);
                }, ModBlockEntities.MOULDING_UNIT_SIDE);
        ItemStorage.SIDED.registerForBlockEntity((be, side) -> be.getStorage(be.getCachedState(), side), ModBlockEntities.PACKAGING_UNIT);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    PackagingUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getStorage(sideBe.getCachedState(), side);
                }, ModBlockEntities.PACKAGING_UNIT_SIDE);
        ItemStorage.SIDED.registerForBlockEntity(ReactorCrucibleBlockEntity::getItemStorage, ModBlockEntities.REACTOR_CRUCIBLE);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    ReactorCrucibleBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getSharedItemStorage();
                }, ModBlockEntities.REACTOR_CRUCIBLE_SIDE);
        ItemStorage.SIDED.registerForBlockEntity(ExpandedCrucibleBlockEntity::getItemStorage, ModBlockEntities.EXPANDED_CRUCIBLE);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    ExpandedCrucibleBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getSharedItemStorage();
                }, ModBlockEntities.EXPANDED_CRUCIBLE_SIDE);
        ItemStorage.SIDED.registerForBlockEntity((be, side) -> be.getStorage(be.getCachedState(), side), ModBlockEntities.PLANTING_UNIT);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    PlantingUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getStorage(sideBe.getCachedState(), side);
                }, ModBlockEntities.PLANTING_UNIT_SIDE);
        ItemStorage.SIDED.registerForBlockEntity((be, side) -> be.getStorage(be.getCachedState(), side), ModBlockEntities.REFINING_UNIT);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    RefiningUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getStorage(sideBe.getCachedState(), side);
                }, ModBlockEntities.REFINING_UNIT_SIDE);
        ItemStorage.SIDED.registerForBlockEntity((be, side) -> be.getStorage(be.getCachedState(), side), ModBlockEntities.SEPARATING_UNIT);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    SeparatingUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getStorage(sideBe.getCachedState(), side);
                }, ModBlockEntities.SEPARATING_UNIT_SIDE);
        ItemStorage.SIDED.registerForBlockEntity((be, side) -> be.getStorage(be.getCachedState(), side), ModBlockEntities.FORGE_OF_THE_SKY);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    ForgeOfTheSkyBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getStorage(sideBe.getCachedState(), side);
                }, ModBlockEntities.FORGE_OF_THE_SKY_SIDE);
        ItemStorage.SIDED.registerForBlockEntity((be, side) -> be.getStorage(be.getCachedState(), side), ModBlockEntities.SEED_PICKING_UNIT);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    SeedPickingUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getStorage(sideBe.getCachedState(), side);
                }, ModBlockEntities.SEED_PICKING_UNIT_SIDE);
        ItemStorage.SIDED.registerForBlockEntity((be, side) -> be.getStorage(be.getCachedState(), side), ModBlockEntities.SHREDDING_UNIT);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    ShreddingUnitBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getStorage(sideBe.getCachedState(), side);
                }, ModBlockEntities.SHREDDING_UNIT_SIDE);
        ItemStorage.SIDED.registerForBlockEntity((be, side) -> be.getStorage(be.getCachedState(), side), ModBlockEntities.PROTOCOL_STASH);
        ItemStorage.SIDED.registerForBlockEntity(
                (sideBe, side) -> {
                    ProtocolStashBlockEntity parent = sideBe.getParentBlock();
                    if (parent == null) return null;
                    return parent.getStorage(sideBe.getCachedState(), side);
                }, ModBlockEntities.PROTOCOL_STASH_SIDE);
    }
}
