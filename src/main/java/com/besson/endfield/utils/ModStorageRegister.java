package com.besson.endfield.utils;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.*;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;

public class ModStorageRegister {
    public static void register() {
        FluidStorage.SIDED.registerForBlockEntity(FluidPipeBlockEntity::getFluidStorage, ModBlockEntities.FLUID_PIPE);
        FluidStorage.SIDED.registerForBlockEntity(FluidPumpBlockEntity::getFluidStorage, ModBlockEntities.FLUID_PUMP);
        FluidStorage.SIDED.registerForBlockEntity(WaterInjectorBlockEntity::getFluidStorage, ModBlockEntities.WATER_INJECTOR);

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
    }
}
