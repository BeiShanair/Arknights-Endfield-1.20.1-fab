package com.besson.endfield.screen;

import com.besson.endfield.ArknightsEndfield;
import com.besson.endfield.screen.custom.*;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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

    public static void register() {

    }
}
