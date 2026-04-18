package com.besson.endfield.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.screen.custom.screenHandler.*;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreens {
    public static final ScreenHandlerType<PortableOriginiumRigScreenHandler> PORTABLE_ORIGINIUM_RIG_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "portable_originium_rig_screen"),
                    new ExtendedScreenHandlerType<>(PortableOriginiumRigScreenHandler::new));

    public static final ScreenHandlerType<ProtocolAnchorCoreScreenHandler> PROTOCOL_ANCHOR_CORE_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "protocol_anchor_core_screen"),
                    new ExtendedScreenHandlerType<>(ProtocolAnchorCoreScreenHandler::new));

    public static final ScreenHandlerType<ThermalBankScreenHandler> THERMAL_BANK_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "thermal_bank_screen"),
                    new ExtendedScreenHandlerType<>(ThermalBankScreenHandler::new));

    public static final ScreenHandlerType<RefiningUnitScreenHandler> REFINING_UNIT_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "refining_unit_screen"),
                    new ExtendedScreenHandlerType<>(RefiningUnitScreenHandler::new));

    public static final ScreenHandlerType<GearingUnitScreenHandler> GEARING_UNIT_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "gearing_unit_screen"),
                    new ExtendedScreenHandlerType<>(GearingUnitScreenHandler::new));

    public static final ScreenHandlerType<ElectricMiningRigScreenHandler> ELECTRIC_MINING_RIG_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "electric_mining_rig_screen"),
                    new ExtendedScreenHandlerType<>(ElectricMiningRigScreenHandler::new));

    public static final ScreenHandlerType<ElectricMiningRigMkIIScreenHandler> ELECTRIC_MINING_RIG_MK_II_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "electric_mining_rig_mk_ii"),
                    new ExtendedScreenHandlerType<>(ElectricMiningRigMkIIScreenHandler::new));

    public static final ScreenHandlerType<ShreddingUnitScreenHandler> SHREDDING_UNIT_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "shredding_unit_screen"),
                    new ExtendedScreenHandlerType<>(ShreddingUnitScreenHandler::new));

    public static final ScreenHandlerType<FittingUnitScreenHandler> FITTING_UNIT_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "fitting_unit_screen"),
                    new ExtendedScreenHandlerType<>(FittingUnitScreenHandler::new));

    public static final ScreenHandlerType<MouldingUnitScreenHandler> MOULDING_UNIT_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "moulding_unit_screen"),
                    new ExtendedScreenHandlerType<>(MouldingUnitScreenHandler::new));

    public static final ScreenHandlerType<PlantingUnitScreenHandler> PLANTING_UNIT_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "planting_unit_screen"),
                    new ExtendedScreenHandlerType<>(PlantingUnitScreenHandler::new));

    public static final ScreenHandlerType<SeedPickingUnitScreenHandler> SEED_PICKING_UNIT_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "seed_picking_unit_screen"),
                    new ExtendedScreenHandlerType<>(SeedPickingUnitScreenHandler::new));

    public static final ScreenHandlerType<FillingUnitScreenHandler> FILLING_UNIT_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "filling_unit_screen"),
                    new ExtendedScreenHandlerType<>(FillingUnitScreenHandler::new));

    public static final ScreenHandlerType<GrindingUnitScreenHandler> GRINDING_UNIT_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "grinding_unit_screen"),
                    new ExtendedScreenHandlerType<>(GrindingUnitScreenHandler::new));

    public static final ScreenHandlerType<PackagingUnitScreenHandler> PACKAGING_UNIT_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "packaging_unit_screen"),
                    new ExtendedScreenHandlerType<>(PackagingUnitScreenHandler::new));

    public static final ScreenHandlerType<CrafterScreenHandler> CRAFTER_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "crafter_screen"),
                    new ExtendedScreenHandlerType<>(CrafterScreenHandler::new));

    public static final ScreenHandlerType<FluidPumpScreenHandler> FLUID_PUMP_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "fluid_pump_screen"),
                    new ExtendedScreenHandlerType<>(FluidPumpScreenHandler::new));
    public static final ScreenHandlerType<FluidTankScreenHandler> FLUID_TANK_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "fluid_tank_screen"),
                    new ExtendedScreenHandlerType<>(FluidTankScreenHandler::new));

    public static final ScreenHandlerType<SupplyTerminalScreenHandler> SUPPLY_TERMINAL_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "supply_terminal_screen"),
                    new ExtendedScreenHandlerType<>(SupplyTerminalScreenHandler::new));

    public static final ScreenHandlerType<StorageScreenHandler> STORAGE_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "storage_screen"),
                    new ExtendedScreenHandlerType<>(StorageScreenHandler::new));

    public static final ScreenHandlerType<ProtocolAnchorCorePortScreenHandler> PROTOCOL_ANCHOR_CORE_PORT_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "protocol_anchor_core_port_screen"),
                    new ExtendedScreenHandlerType<>(ProtocolAnchorCorePortScreenHandler::new));
    public static final ScreenHandlerType<DepotUnloaderScreenHandler> DEPOT_UNLOADER_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "depot_unloader_screen"),
                    new ExtendedScreenHandlerType<>(DepotUnloaderScreenHandler::new));
    public static final ScreenHandlerType<ProtocolStashScreenHandler> PROTOCOL_STASH_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(ArknightsEndfield.MOD_ID, "protocol_stash_screen"),
                    new ExtendedScreenHandlerType<>(ProtocolStashScreenHandler::new));

    public static void register() {

    }
}
