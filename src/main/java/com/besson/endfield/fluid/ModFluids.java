package com.besson.endfield.fluid;

import com.besson.endfield.ArknightsEndfield;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModFluids {
    public static final FlowableFluid STILL_SEWAGE = Registry.register(Registries.FLUID,
            new Identifier(ArknightsEndfield.MOD_ID, "sewage"),
            new SewageFluid.Source());
    public static final FlowableFluid FLOWING_SEWAGE = Registry.register(Registries.FLUID,
            new Identifier(ArknightsEndfield.MOD_ID, "flowing_sewage"),
            new SewageFluid.Flowing());
    
    public static final FlowableFluid STILL_PRECIPITATION_ACID = Registry.register(Registries.FLUID,
            new Identifier(ArknightsEndfield.MOD_ID, "precipitation_acid"),
            new PrecipitationAcidFluid.Source());
    public static final FlowableFluid FLOWING_PRECIPITATION_ACID = Registry.register(Registries.FLUID,
            new Identifier(ArknightsEndfield.MOD_ID, "flowing_precipitation_acid"),
            new PrecipitationAcidFluid.Flowing());

    public static final FlowableFluid STILL_XIRCON_EFFLUENT = Registry.register(Registries.FLUID,
            new Identifier(ArknightsEndfield.MOD_ID, "xircon_effluent"),
            new XirconEffluentFluid.Source());
    public static final FlowableFluid FLOWING_XIRCON_EFFLUENT = Registry.register(Registries.FLUID,
            new Identifier(ArknightsEndfield.MOD_ID, "flowing_xircon_effluent"),
            new XirconEffluentFluid.Flowing());

    public static final FlowableFluid STILL_INERT_XIRCON_EFFLUENT = Registry.register(Registries.FLUID,
            new Identifier(ArknightsEndfield.MOD_ID, "inert_xircon_effluent"),
            new InertXirconEffluentFluid.Source());
    public static final FlowableFluid FLOWING_INERT_XIRCON_EFFLUENT = Registry.register(Registries.FLUID,
            new Identifier(ArknightsEndfield.MOD_ID, "flowing_inert_xircon_effluent"),
            new InertXirconEffluentFluid.Flowing());

    public static final FlowableFluid STILL_YAZHEN_SOLUTION = Registry.register(Registries.FLUID,
            new Identifier(ArknightsEndfield.MOD_ID, "yazhen_solution"),
            new YazhenSolutionFluid.Source());
    public static final FlowableFluid FLOWING_YAZHEN_SOLUTION = Registry.register(Registries.FLUID,
            new Identifier(ArknightsEndfield.MOD_ID, "flowing_yazhen_solution"),
            new YazhenSolutionFluid.Flowing());

    public static final FlowableFluid STILL_JINCAO_SOLUTION = Registry.register(Registries.FLUID,
            new Identifier(ArknightsEndfield.MOD_ID, "jincao_solution"),
            new JincaoSolutionFluid.Source());
    public static final FlowableFluid FLOWING_JINCAO_SOLUTION = Registry.register(Registries.FLUID,
            new Identifier(ArknightsEndfield.MOD_ID, "flowing_jincao_solution"),
            new JincaoSolutionFluid.Flowing());

    public static final FlowableFluid STILL_LIQUID_XIRANITE = Registry.register(Registries.FLUID,
            new Identifier(ArknightsEndfield.MOD_ID, "liquid_xiranite"),
            new LiquidXiraniteFluid.Source());
    public static final FlowableFluid FLOWING_LIQUID_XIRANITE = Registry.register(Registries.FLUID,
            new Identifier(ArknightsEndfield.MOD_ID, "flowing_liquid_xiranite"),
            new LiquidXiraniteFluid.Flowing());

    public static void register() {
    }
}
