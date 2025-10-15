package com.besson.endfield.power;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;

public class PowerState extends PersistentState {
    public int storedEnergy = 0;

    public PowerState() {}

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putInt("StoredEnergy", storedEnergy);
        return nbt;
    }

    public static PowerState fromNbt(NbtCompound nbt) {
        PowerState state = new PowerState();
        state.storedEnergy = nbt.getInt("StoredEnergy");
        return state;
    }
}
