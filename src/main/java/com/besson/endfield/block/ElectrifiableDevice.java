package com.besson.endfield.block;

public interface ElectrifiableDevice {
    void receiveElectricCharge(int amount);
    boolean needsPower();
    int getRequiredPower();
}
