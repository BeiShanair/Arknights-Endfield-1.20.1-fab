package com.besson.endfield.mixin;

import com.besson.endfield.utils.IPlayerData;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin implements IPlayerData {
    private boolean receivedProtocol = false;

    @Override
    public boolean hasReceivedProtocol() {
        return receivedProtocol;
    }

    @Override
    public void setHasReceivedProtocol(boolean value) {
        receivedProtocol = value;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    private void writeCustomData(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("has_received_protocol", receivedProtocol);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void readCustomData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("has_received_protocol")) {
            receivedProtocol = nbt.getBoolean("has_received_protocol");
        }
    }
}
