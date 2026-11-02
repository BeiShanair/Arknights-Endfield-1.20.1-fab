package com.besson.endfield.utils;

import com.besson.endfield.fluid.ModFluids;
import com.besson.endfield.item.ModItems;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.jetbrains.annotations.Nullable;

public final class PumpFluidUtil {
    private PumpFluidUtil() {}

    private static Fluid stillOf(Fluid fluid) {
        if (fluid instanceof FlowableFluid ff) return ff.getStill();
        return fluid;
    }

    public static boolean isAcidic(@Nullable Fluid fluid) {
        if (fluid == null) return false;
        return stillOf(fluid) == ModFluids.STILL_PRECIPITATION_ACID;
    }

    public static Fluid toStill(@Nullable Fluid fluid) {
        if (fluid == null) return Fluids.EMPTY;
        return stillOf(fluid);
    }

    @Nullable
    public static ItemStack getFilledBucket(@Nullable Fluid fluid) {
        if (fluid == null) return null;
        Fluid still = stillOf(fluid);
        if (still == Fluids.WATER) return new ItemStack(Items.WATER_BUCKET);
        if (still == Fluids.LAVA) return new ItemStack(Items.LAVA_BUCKET);
        if (still == ModFluids.STILL_SEWAGE) return new ItemStack(ModItems.SEWAGE_BUCKET);
        if (still == ModFluids.STILL_PRECIPITATION_ACID) return new ItemStack(ModItems.PRECIPITATION_ACID_BUCKET);
        if (still == ModFluids.STILL_XIRCON_EFFLUENT) return new ItemStack(ModItems.XIRCON_EFFLUENT_BUCKET);
        if (still == ModFluids.STILL_INERT_XIRCON_EFFLUENT) return new ItemStack(ModItems.INERT_XIRCON_EFFLUENT_BUCKET);
        if (still == ModFluids.STILL_YAZHEN_SOLUTION) return new ItemStack(ModItems.YAZHEN_SOLUTION_BUCKET);
        if (still == ModFluids.STILL_JINCAO_SOLUTION) return new ItemStack(ModItems.JINCAO_SOLUTION_BUCKET);
        if (still == ModFluids.STILL_LIQUID_XIRANITE) return new ItemStack(ModItems.LIQUID_XIRANITE_BUCKET);
        return null;
    }
}
