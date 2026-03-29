package com.besson.endfield.mixin;

import com.besson.endfield.utils.IPlayerData;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin implements IPlayerData {
    @Unique
    private boolean receivedProtocol = false;
    @Unique
    private boolean receivedOriginiumOre = false;
    @Unique
    private boolean receivedAmethystOre = false;
    @Unique
    private boolean receivedFerriumOre = false;

    @Override
    public boolean hasReceivedProtocol() {
        return receivedProtocol;
    }

    @Override
    public boolean hasReceivedOriginiumOre() {
        return receivedOriginiumOre;
    }

    @Override
    public boolean hasReceivedAmethystOre() {
        return receivedAmethystOre;
    }

    @Override
    public boolean hasReceivedFerriumOre() {
        return receivedFerriumOre;
    }

    @Override
    public void setHasReceivedProtocol(boolean value) {
        receivedProtocol = value;
    }

    @Override
    public void setHasReceivedOriginiumOre(boolean value) {
        receivedOriginiumOre = value;
    }

    @Override
    public void setHasReceivedAmethystOre(boolean value) {
        receivedAmethystOre = value;
    }

    @Override
    public void setHasReceivedFerriumOre(boolean value) {
        receivedFerriumOre = value;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    private void writeCustomData(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("has_received_protocol", receivedProtocol);
        nbt.putBoolean("has_received_originium_ore", receivedOriginiumOre);
        nbt.putBoolean("has_received_amethyst_ore", receivedAmethystOre);
        nbt.putBoolean("has_received_ferrium_ore", receivedFerriumOre);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void readCustomData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("has_received_protocol")) {
            receivedProtocol = nbt.getBoolean("has_received_protocol");
        }
        if (nbt.contains("has_received_originium_ore")) {
            receivedOriginiumOre = nbt.getBoolean("has_received_originium_ore");
        }
        if (nbt.contains("has_received_amethyst_ore")) {
            receivedAmethystOre = nbt.getBoolean("has_received_amethyst_ore");
        }
        if (nbt.contains("has_received_ferrium_ore")) {
            receivedFerriumOre = nbt.getBoolean("has_received_ferrium_ore");
        }
    }
}
