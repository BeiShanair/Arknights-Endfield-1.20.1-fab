package com.besson.endfield.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldView;

import java.util.ArrayList;
import java.util.List;

public final class MultiBlockPlacementUtil {

    private MultiBlockPlacementUtil() {
    }
    
    public static boolean isReplaceable(WorldView world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (state.isAir()) {
            return true;
        }
        if (state.isReplaceable()) {
            return true;
        }
        
        return !state.hasBlockEntity() && state.getCollisionShape(world, pos).isEmpty();
    }
    
    public static boolean isAllReplaceable(WorldView world, BlockPos[] positions) {
        for (BlockPos pos : positions) {
            if (!isReplaceable(world, pos)) {
                return false;
            }
        }
        return true;
    }
    
    public static BlockPos[] betweenClosed(BlockPos from, BlockPos to) {
        return BlockPos.stream(from, to).map(BlockPos::toImmutable).toArray(BlockPos[]::new);
    }
    
    public static void pushEntitiesOut(ServerWorld world, BlockPos mainPos, BlockPos[] occupied) {
        Box structure = structureBox(mainPos, occupied);
        List<Box> cubes = collectCubes(mainPos, occupied);

        for (Entity entity : world.getOtherEntities(null, structure, e -> e.isAlive() && e instanceof LivingEntity)) {
            Vec3d center = entity.getBoundingBox().getCenter();
            
            if (cubes.stream().noneMatch(cube -> cube.contains(center))) {
                continue;
            }
            pushOut(world, entity, structure);
        }
    }

    private static Box structureBox(BlockPos mainPos, BlockPos[] occupied) {
        int minX = mainPos.getX();
        int minY = mainPos.getY();
        int minZ = mainPos.getZ();
        int maxX = minX;
        int maxY = minY;
        int maxZ = minZ;
        for (BlockPos pos : occupied) {
            minX = Math.min(minX, pos.getX());
            minY = Math.min(minY, pos.getY());
            minZ = Math.min(minZ, pos.getZ());
            maxX = Math.max(maxX, pos.getX());
            maxY = Math.max(maxY, pos.getY());
            maxZ = Math.max(maxZ, pos.getZ());
        }
        return new Box(minX, minY, minZ, maxX + 1.0D, maxY + 1.0D, maxZ + 1.0D);
    }

    private static List<Box> collectCubes(BlockPos mainPos, BlockPos[] occupied) {
        List<Box> cubes = new ArrayList<>(occupied.length + 1);
        cubes.add(cube(mainPos));
        for (BlockPos pos : occupied) {
            cubes.add(cube(pos));
        }
        return cubes;
    }

    private static Box cube(BlockPos pos) {
        return new Box(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1.0D, pos.getY() + 1.0D, pos.getZ() + 1.0D);
    }

    private static void pushOut(ServerWorld world, Entity entity, Box structure) {
        Box entityBox = entity.getBoundingBox();
        double halfWidth = entity.getWidth() / 2.0D + 1.0E-3D;
        double[][] candidates = {
                {entity.getX(), structure.maxY + 1.0E-3D, entity.getZ()},
                {entity.getX(), entity.getY(), structure.minZ - halfWidth},
                {entity.getX(), entity.getY(), structure.maxZ + halfWidth},
                {structure.minX - halfWidth, entity.getY(), entity.getZ()},
                {structure.maxX + halfWidth, entity.getY(), entity.getZ()}
        };

        double[] best = null;
        double bestDistance = Double.MAX_VALUE;
        for (double[] candidate : candidates) {
            double dx = candidate[0] - entity.getX();
            double dy = candidate[1] - entity.getY();
            double dz = candidate[2] - entity.getZ();
            double distance = Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
            if (distance >= bestDistance) {
                continue;
            }
            if (!world.isSpaceEmpty(entity, entityBox.offset(dx, dy, dz))) {
                continue;
            }
            best = candidate;
            bestDistance = distance;
        }

        if (best != null) {
            entity.teleport(best[0], best[1], best[2]);
            entity.fallDistance = 0.0F;
        }
    }
}
