package com.besson.endfield.utils;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TurretFakePlayer {
    private static final GameProfile PROFILE = 
            new GameProfile(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee"), "[Turret]");
    private static final Map<ServerWorld, ServerPlayerEntity> CACHE = new HashMap<>();
    
    public static ServerPlayerEntity get(ServerWorld world) {
        return CACHE.computeIfAbsent(world, TurretFakePlayer::createFakePlayer);
    }
    
    private static ServerPlayerEntity createFakePlayer(ServerWorld world) {
        MinecraftServer server = world.getServer();
        ServerPlayerEntity fakePlayer = new ServerPlayerEntity(
                server, world, PROFILE);
        
        fakePlayer.setSilent(true);
        fakePlayer.setInvisible(true);
        
        return fakePlayer;
    }
}
