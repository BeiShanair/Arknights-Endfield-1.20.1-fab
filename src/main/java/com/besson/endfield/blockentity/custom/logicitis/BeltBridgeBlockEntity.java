package com.besson.endfield.blockentity.custom.logicitis;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class BeltBridgeBlockEntity extends BlockEntity {
    public BeltBridgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BELT_BRIDGE, pos, state);
    }

    public boolean tryPassThrough(World world, BlockPos bridgePos, Direction incomingDir, BeltBlockEntity sourceBelt) {
        Direction outgoingDir = incomingDir;

        BlockPos outPos = bridgePos.offset(outgoingDir);
        BlockEntity outBE = world.getBlockEntity(outPos);

        if (!(outBE instanceof BeltBlockEntity targetBelt)) return false;

        if (!targetBelt.storedItem.isEmpty()) return false;

        // 直接转移
        targetBelt.storedItem = sourceBelt.storedItem;
        targetBelt.travelDirection = outgoingDir.getOpposite();

        sourceBelt.resetItem();

        return true;
    }
}
