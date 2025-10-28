package com.besson.endfield.blockentity;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;

public class SimpleFluidStorage {
    private Fluid fluid;
    private int amount;
    private final int capacity;

    public SimpleFluidStorage(int capacity) {
        this.capacity = capacity;
    }

    public void insertFluid(Fluid fluid, int amount, boolean simulate) {
        if (this.fluid == null || this.fluid == fluid) {
            int insertableAmount = Math.min(amount, capacity - this.amount);
            if (!simulate) {
                this.fluid = fluid;
                this.amount += insertableAmount;
            }
        }
    }

    public int extractFluid(int amount, boolean simulate) {
        int extracted = Math.min(this.amount, amount);
        if (!simulate) {
            this.amount -= extracted;
            if (this.amount <= 0) {
                this.fluid = Fluids.EMPTY;
            }
        }
        return extracted;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFluidAmount() {
        return amount;
    }

    public Fluid getFluid() {
        return fluid;
    }

    public void setFluidAmount(int value) {
        this.amount = value;
    }

    public void setFluid(Fluid fluid) {
        this.fluid = fluid;
    }
}
