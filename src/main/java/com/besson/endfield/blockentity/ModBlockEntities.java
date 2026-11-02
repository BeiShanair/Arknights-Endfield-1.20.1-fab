package com.besson.endfield.blockentity;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.block.ModBlocks;
import com.besson.endfield.blockentity.custom.*;
import com.besson.endfield.blockentity.custom.combat.*;
import com.besson.endfield.blockentity.custom.depot.*;
import com.besson.endfield.blockentity.custom.logicitis.*;
import com.besson.endfield.blockentity.custom.powering.*;
import com.besson.endfield.blockentity.custom.production1.*;
import com.besson.endfield.blockentity.custom.production2.*;
import com.besson.endfield.blockentity.custom.resourcing.*;
import com.mojang.datafixers.types.Type;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class ModBlockEntities {

    public static final BlockEntityType<ProtocolAnchorCoreBlockEntity> PROTOCOL_ANCHOR_CORE = create("protocol_anchor_core",
            BlockEntityType.Builder.create(ProtocolAnchorCoreBlockEntity::new, ModBlocks.PROTOCOL_ANCHOR_CORE));
    public static final BlockEntityType<ProtocolAnchorCorePortBlockEntity> PROTOCOL_ANCHOR_CORE_PORT = create("protocol_anchor_core_port",
            BlockEntityType.Builder.create(ProtocolAnchorCorePortBlockEntity::new, ModBlocks.PROTOCOL_ANCHOR_CORE_PORT));
    public static final BlockEntityType<RelayTowerBlockEntity> RELAY_TOWER = create("relay_tower",
            BlockEntityType.Builder.create(RelayTowerBlockEntity::new, ModBlocks.RELAY_TOWER));

    public static final BlockEntityType<SupplyTerminalBlockEntity> SUPPLY_TERMINAL = create("supply_terminal",
            BlockEntityType.Builder.create(SupplyTerminalBlockEntity::new, ModBlocks.SUPPLY_TERMINAL));
    
    public static final BlockEntityType<BeamTowerBlockEntity> BEAM_TOWER = create("beam_tower",
            BlockEntityType.Builder.create(BeamTowerBlockEntity::new, ModBlocks.BEAM_TOWER));
    public static final BlockEntityType<GrenadeTowerBlockEntity> GRENADE_TOWER = create("grenade_tower",
            BlockEntityType.Builder.create(GrenadeTowerBlockEntity::new, ModBlocks.GRENADE_TOWER));
    public static final BlockEntityType<GunTowerBlockEntity> GUN_TOWER = create("gun_tower",
            BlockEntityType.Builder.create(GunTowerBlockEntity::new, ModBlocks.GUN_TOWER));
    public static final BlockEntityType<HeavyGunTowerBlockEntity> HEAVY_GUN_TOWER = create("heavy_gun_tower",
            BlockEntityType.Builder.create(HeavyGunTowerBlockEntity::new, ModBlocks.HEAVY_GUN_TOWER));
    public static final BlockEntityType<HeGrenadeTowerBlockEntity> HE_GRENADE_TOWER = create("he_grenade_tower",
            BlockEntityType.Builder.create(HeGrenadeTowerBlockEntity::new, ModBlocks.HE_GRENADE_TOWER));
    public static final BlockEntityType<LNTowerBlockEntity> LN_TOWER = create("ln_tower",
            BlockEntityType.Builder.create(LNTowerBlockEntity::new, ModBlocks.LN_TOWER));
    public static final BlockEntityType<MedicalTowerBlockEntity> MEDICAL_TOWER = create("medical_tower",
            BlockEntityType.Builder.create(MedicalTowerBlockEntity::new, ModBlocks.MEDICAL_TOWER));
    public static final BlockEntityType<MarshGasMkIBlockEntity> MARSH_GAS_MK_I = create("marsh_gas__mk_i",
            BlockEntityType.Builder.create(MarshGasMkIBlockEntity::new, ModBlocks.MARSH_GAS_MK_I));
    public static final BlockEntityType<OmnidirectionalSonicTowerBlockEntity> OMNIDIRECTIONAL_SONIC_TOWER = create("omnidirectional_sonic_tower",
            BlockEntityType.Builder.create(OmnidirectionalSonicTowerBlockEntity::new, ModBlocks.OMNIDIRECTIONAL_SONIC_TOWER));
    public static final BlockEntityType<SentryTowerBlockEntity> SENTRY_TOWER = create("sentry_tower",
            BlockEntityType.Builder.create(SentryTowerBlockEntity::new, ModBlocks.SENTRY_TOWER));
    public static final BlockEntityType<SurgeTowerBlockEntity> SURGE_TOWER = create("surge_tower",
            BlockEntityType.Builder.create(SurgeTowerBlockEntity::new, ModBlocks.SURGE_TOWER));
    
    public static final BlockEntityType<ElectricPylonBlockEntity> ELECTRIC_PYLON = create("electric_pylon",
            BlockEntityType.Builder.create(ElectricPylonBlockEntity::new, ModBlocks.ELECTRIC_PYLON));
    public static final BlockEntityType<XiranitePylonBlockEntity> XIRANITE_PYLON = create("xiranite_pylon",
            BlockEntityType.Builder.create(XiranitePylonBlockEntity::new, ModBlocks.XIRANITE_PYLON));
    public static final BlockEntityType<XiraniteRelayBlockEntity> XIRANITE_RELAY = create("xiranite_relay",
            BlockEntityType.Builder.create(XiraniteRelayBlockEntity::new, ModBlocks.XIRANITE_RELAY));
    
    public static final BlockEntityType<ElectricMiningRigBlockEntity> ELECTRIC_MINING_RIG = create("electric_mining_rig",
            BlockEntityType.Builder.create(ElectricMiningRigBlockEntity::new, ModBlocks.ELECTRIC_MINING_RIG));

    public static final BlockEntityType<ElectricMiningRigMkIIBlockEntity> ELECTRIC_MINING_RIG_MK_II = create("electric_mining_rig_mk_ii",
            BlockEntityType.Builder.create(ElectricMiningRigMkIIBlockEntity::new, ModBlocks.ELECTRIC_MINING_RIG_MK_II));

    public static final BlockEntityType<PortableOriginiumRigBlockEntity> PORTABLE_ORIGINIUM_RIG = create("portable_originium_rig",
            BlockEntityType.Builder.create(PortableOriginiumRigBlockEntity::new, ModBlocks.PORTABLE_ORIGINIUM_RIG));

    public static final BlockEntityType<AcidResistantPumpMkIIBlockEntity> ACID_RESISTANT_PUMP_MK_II = create("acid_resistant_pump_mk_ii",
            BlockEntityType.Builder.create(AcidResistantPumpMkIIBlockEntity::new, ModBlocks.ACID_RESISTANT_PUMP_MK_II));
    public static final BlockEntityType<AcidResistantPumpMkIISideBlockEntity> ACID_RESISTANT_PUMP_MK_II_SIDE = create("acid_resistant_pump_mk_ii_side",
            BlockEntityType.Builder.create(AcidResistantPumpMkIISideBlockEntity::new, ModBlocks.ACID_RESISTANT_PUMP_MK_II_SIDE));
    
    public static final BlockEntityType<HydroMiningRigBlockEntity> HYDRO_MINING_RIG = create("hydro_mining_rig",
            BlockEntityType.Builder.create(HydroMiningRigBlockEntity::new, ModBlocks.HYDRO_MINING_RIG));
    public static final BlockEntityType<HydroMiningRigSideBlockEntity> HYDRO_MINING_RIG_SIDE = create("hydro_mining_rig_side",
            BlockEntityType.Builder.create(HydroMiningRigSideBlockEntity::new, ModBlocks.HYDRO_MINING_RIG_SIDE));
    
    public static final BlockEntityType<RefiningUnitBlockEntity> REFINING_UNIT = create("refining_unit",
            BlockEntityType.Builder.create(RefiningUnitBlockEntity::new, ModBlocks.REFINING_UNIT));
    public static final BlockEntityType<RefiningUnitSideBlockEntity> REFINING_UNIT_SIDE = create("refining_unit_side",
            BlockEntityType.Builder.create(RefiningUnitSideBlockEntity::new, ModBlocks.REFINING_UNIT_SIDE));

    public static final BlockEntityType<ShreddingUnitBlockEntity> SHREDDING_UNIT = create("shredding_unit",
            BlockEntityType.Builder.create(ShreddingUnitBlockEntity::new, ModBlocks.SHREDDING_UNIT));
    public static final BlockEntityType<ShreddingUnitSideBlockEntity> SHREDDING_UNIT_SIDE = create("shredding_unit_side",
            BlockEntityType.Builder.create(ShreddingUnitSideBlockEntity::new, ModBlocks.SHREDDING_UNIT_SIDE));

    public static final BlockEntityType<FillingUnitBlockEntity> FILLING_UNIT = create("filling_unit",
            BlockEntityType.Builder.create(FillingUnitBlockEntity::new, ModBlocks.FILLING_UNIT));
    public static final BlockEntityType<FillingUnitSideBlockEntity> FILLING_UNIT_SIDE = create("filling_unit_side",
            BlockEntityType.Builder.create(FillingUnitSideBlockEntity::new, ModBlocks.FILLING_UNIT_SIDE));

    public static final BlockEntityType<FittingUnitBlockEntity> FITTING_UNIT = create("fitting_unit",
            BlockEntityType.Builder.create(FittingUnitBlockEntity::new, ModBlocks.FITTING_UNIT));
    public static final BlockEntityType<FittingUnitSideBlockEntity> FITTING_UNIT_SIDE = create("fitting_unit_side",
            BlockEntityType.Builder.create(FittingUnitSideBlockEntity::new, ModBlocks.FITTING_UNIT_SIDE));

    public static final BlockEntityType<GearingUnitBlockEntity> GEARING_UNIT = create("gearing_unit",
            BlockEntityType.Builder.create(GearingUnitBlockEntity::new, ModBlocks.GEARING_UNIT));
    public static final BlockEntityType<GearingUnitSideBlockEntity> GEARING_UNIT_SIDE = create("gearing_unit_side",
            BlockEntityType.Builder.create(GearingUnitSideBlockEntity::new, ModBlocks.GEARING_UNIT_SIDE));

    public static final BlockEntityType<GrindingUnitBlockEntity> GRINDING_UNIT = create("grinding_unit",
            BlockEntityType.Builder.create(GrindingUnitBlockEntity::new, ModBlocks.GRINDING_UNIT));
    public static final BlockEntityType<GrindingUnitSideBlockEntity> GRINDING_UNIT_SIDE = create("grinding_unit_side",
            BlockEntityType.Builder.create(GrindingUnitSideBlockEntity::new, ModBlocks.GRINDING_UNIT_SIDE));

    public static final BlockEntityType<MouldingUnitBlockEntity> MOULDING_UNIT = create("moulding_unit",
            BlockEntityType.Builder.create(MouldingUnitBlockEntity::new, ModBlocks.MOULDING_UNIT));
    public static final BlockEntityType<MouldingUnitSideBlockEntity> MOULDING_UNIT_SIDE = create("moulding_unit_side",
            BlockEntityType.Builder.create(MouldingUnitSideBlockEntity::new, ModBlocks.MOULDING_UNIT_SIDE));

    public static final BlockEntityType<PackagingUnitBlockEntity> PACKAGING_UNIT = create("packaging_unit",
            BlockEntityType.Builder.create(PackagingUnitBlockEntity::new, ModBlocks.PACKAGING_UNIT));
    public static final BlockEntityType<PackagingUnitSideBlockEntity> PACKAGING_UNIT_SIDE = create("packaging_unit_side",
            BlockEntityType.Builder.create(PackagingUnitSideBlockEntity::new, ModBlocks.PACKAGING_UNIT_SIDE));
    public static final BlockEntityType<ReactorCrucibleBlockEntity> REACTOR_CRUCIBLE = create("reactor_crucible",
            BlockEntityType.Builder.create(ReactorCrucibleBlockEntity::new, ModBlocks.REACTOR_CRUCIBLE));
    public static final BlockEntityType<ReactorCrucibleSideBlockEntity> REACTOR_CRUCIBLE_SIDE = create("reactor_crucible_side",
            BlockEntityType.Builder.create(ReactorCrucibleSideBlockEntity::new, ModBlocks.REACTOR_CRUCIBLE_SIDE));
    public static final BlockEntityType<ExpandedCrucibleBlockEntity> EXPANDED_CRUCIBLE = create("expanded_crucible",
            BlockEntityType.Builder.create(ExpandedCrucibleBlockEntity::new, ModBlocks.EXPANDED_CRUCIBLE));
    public static final BlockEntityType<ExpandedCrucibleSideBlockEntity> EXPANDED_CRUCIBLE_SIDE = create("expanded_crucible_side",
            BlockEntityType.Builder.create(ExpandedCrucibleSideBlockEntity::new, ModBlocks.EXPANDED_CRUCIBLE_SIDE));
    public static final BlockEntityType<SeparatingUnitBlockEntity> SEPARATING_UNIT = create("separating_unit",
            BlockEntityType.Builder.create(SeparatingUnitBlockEntity::new, ModBlocks.SEPARATING_UNIT));
    public static final BlockEntityType<SeparatingUnitSideBlockEntity> SEPARATING_UNIT_SIDE = create("separating_unit_side",
            BlockEntityType.Builder.create(SeparatingUnitSideBlockEntity::new, ModBlocks.SEPARATING_UNIT_SIDE));
    public static final BlockEntityType<ForgeOfTheSkyBlockEntity> FORGE_OF_THE_SKY = create("forge_of_the_sky",
            BlockEntityType.Builder.create(ForgeOfTheSkyBlockEntity::new, ModBlocks.FORGE_OF_THE_SKY));
    public static final BlockEntityType<ForgeOfTheSkySideBlockEntity> FORGE_OF_THE_SKY_SIDE = create("forge_of_the_sky_side",
            BlockEntityType.Builder.create(ForgeOfTheSkySideBlockEntity::new, ModBlocks.FORGE_OF_THE_SKY_SIDE));
    
    public static final BlockEntityType<PlantingUnitBlockEntity> PLANTING_UNIT = create("planting_unit",
            BlockEntityType.Builder.create(PlantingUnitBlockEntity::new, ModBlocks.PLANTING_UNIT));
    public static final BlockEntityType<PlantingUnitSideBlockEntity> PLANTING_UNIT_SIDE = create("planting_unit_side",
            BlockEntityType.Builder.create(PlantingUnitSideBlockEntity::new, ModBlocks.PLANTING_UNIT_SIDE));

    public static final BlockEntityType<SeedPickingUnitBlockEntity> SEED_PICKING_UNIT = create("seed_picking_unit",
            BlockEntityType.Builder.create(SeedPickingUnitBlockEntity::new, ModBlocks.SEED_PICKING_UNIT));
    public static final BlockEntityType<SeedPickingUnitSideBlockEntity> SEED_PICKING_UNIT_SIDE = create("seed_picking_unit_side",
            BlockEntityType.Builder.create(SeedPickingUnitSideBlockEntity::new, ModBlocks.SEED_PICKING_UNIT_SIDE));

    public static final BlockEntityType<ThermalBankBlockEntity> THERMAL_BANK = create("thermal_bank",
            BlockEntityType.Builder.create(ThermalBankBlockEntity::new, ModBlocks.THERMAL_BANK));
    public static final BlockEntityType<ThermalBankSideBlockEntity> THERMAL_BANK_SIDE = create("thermal_bank_side",
            BlockEntityType.Builder.create(ThermalBankSideBlockEntity::new, ModBlocks.THERMAL_BANK_SIDE));

    public static final BlockEntityType<CrafterBlockEntity> CRAFTER = create("crafter",
            BlockEntityType.Builder.create(CrafterBlockEntity::new, ModBlocks.CRAFTER));

    public static final BlockEntityType<ProtocolStashBlockEntity> PROTOCOL_STASH = create("protocol_stash",
            BlockEntityType.Builder.create(ProtocolStashBlockEntity::new, ModBlocks.PROTOCOL_STASH));
    public static final BlockEntityType<ProtocolStashSideBlockEntity> PROTOCOL_STASH_SIDE = create("protocol_stash_side",
            BlockEntityType.Builder.create(ProtocolStashSideBlockEntity::new, ModBlocks.PROTOCOL_STASH_SIDE));
    public static final BlockEntityType<DepotBusSectionBlockEntity> DEPOT_BUS_SECTION = create("depot_bus_section", 
            BlockEntityType.Builder.create(DepotBusSectionBlockEntity::new, ModBlocks.DEPOT_BUS_SECTION));
    public static final BlockEntityType<DepotBusSectionSideBlockEntity> DEPOT_BUS_SECTION_SIDE = create("depot_bus_section_side",
            BlockEntityType.Builder.create(DepotBusSectionSideBlockEntity::new, ModBlocks.DEPOT_BUS_SECTION_SIDE));
    public static final BlockEntityType<DepotLoaderBlockEntity> DEPOT_LOADER = create("depot_loader",
            BlockEntityType.Builder.create(DepotLoaderBlockEntity::new, ModBlocks.DEPOT_LOADER));
    public static final BlockEntityType<DepotLoaderSideBlockEntity> DEPOT_LOADER_SIDE = create("depot_loader_side",
            BlockEntityType.Builder.create(DepotLoaderSideBlockEntity::new, ModBlocks.DEPOT_LOADER_SIDE));
    public static final BlockEntityType<DepotUnloaderBlockEntity> DEPOT_UNLOADER = create("depot_unloader",
            BlockEntityType.Builder.create(DepotUnloaderBlockEntity::new, ModBlocks.DEPOT_UNLOADER));
    public static final BlockEntityType<DepotUnloaderSideBlockEntity> DEPOT_UNLOADER_SIDE = create("depot_unloader_side",
            BlockEntityType.Builder.create(DepotUnloaderSideBlockEntity::new, ModBlocks.DEPOT_UNLOADER_SIDE));
    
    
    public static final BlockEntityType<FluidPumpBlockEntity> FLUID_PUMP = create("fluid_pump",
            BlockEntityType.Builder.create(FluidPumpBlockEntity::new, ModBlocks.FLUID_PUMP));
    public static final BlockEntityType<FluidPumpSideBlockEntity> FLUID_PUMP_SIDE = create("fluid_pump_side",
            BlockEntityType.Builder.create(FluidPumpSideBlockEntity::new, ModBlocks.FLUID_PUMP_SIDE));

    public static final BlockEntityType<BeltBlockEntity> BELT = create("belt",
            BlockEntityType.Builder.create(BeltBlockEntity::new, ModBlocks.BELT));
    public static final BlockEntityType<BeltBridgeBlockEntity> BELT_BRIDGE = create("belt_bridge",
            BlockEntityType.Builder.create(BeltBridgeBlockEntity::new, ModBlocks.BELT_BRIDGE));
    public static final BlockEntityType<ConvergerBlockEntity> CONVERGER = create("converger", 
            BlockEntityType.Builder.create(ConvergerBlockEntity::new, ModBlocks.CONVERGER));
    public static final BlockEntityType<SplitterBlockEntity> SPLITTER = create("splitter",
            BlockEntityType.Builder.create(SplitterBlockEntity::new, ModBlocks.SPLITTER));
    public static final BlockEntityType<OutputPortBlockEntity> OUTPUT_PORT = create("output_port",
            BlockEntityType.Builder.create(OutputPortBlockEntity::new, ModBlocks.OUTPUT_PORT_BLOCK));
    public static final BlockEntityType<FluidPipeBlockEntity> FLUID_PIPE = create("fluid_pipe.json",
            BlockEntityType.Builder.create(FluidPipeBlockEntity::new, ModBlocks.FLUID_PIPE));
    public static final BlockEntityType<PipeBridgeBlockEntity> PIPE_BRIDGE = create("pipe_bridge",
            BlockEntityType.Builder.create(PipeBridgeBlockEntity::new, ModBlocks.PIPE_BRIDGE));
    public static final BlockEntityType<PipeSplitterBlockEntity> PIPE_SPLITTER = create("pipe_splitter",
            BlockEntityType.Builder.create(PipeSplitterBlockEntity::new, ModBlocks.PIPE_SPLITTER));
    public static final BlockEntityType<PipeConvergerBlockEntity> PIPE_CONVERGER = create("pipe_converger",
            BlockEntityType.Builder.create(PipeConvergerBlockEntity::new, ModBlocks.PIPE_CONVERGER));
    
    public static final BlockEntityType<FluidTankBlockEntity> FLUID_TANK = create("fluid_tank",
            BlockEntityType.Builder.create(FluidTankBlockEntity::new, ModBlocks.FLUID_TANK));
    public static final BlockEntityType<FluidTankSideBlockEntity> FLUID_TANK_SIDE = create("fluid_tank_side",
            BlockEntityType.Builder.create(FluidTankSideBlockEntity::new, ModBlocks.FLUID_TANK_SIDE));
    public static final BlockEntityType<WaterTreatmentUnitBlockEntity> WATER_TREATMENT_UNIT = create("water_treatment_unit",
            BlockEntityType.Builder.create(WaterTreatmentUnitBlockEntity::new, ModBlocks.WATER_TREATMENT_UNIT));
    public static final BlockEntityType<WaterTreatmentUnitSideBlockEntity> WATER_TREATMENT_UNIT_SIDE = create("water_treatment_unit_side",
            BlockEntityType.Builder.create(WaterTreatmentUnitSideBlockEntity::new, ModBlocks.WATER_TREATMENT_UNIT_SIDE));
    
    private static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.Builder<T> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(ArknightsEndfield.MOD_ID, id), builder.build(type));
    }

    public static void registerModBlockEntities() {

    }
}
