package com.besson.endfield.blockentity.custom.logicitis;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class PipeBridgeBlockEntity extends BlockEntity {
    public PipeBridgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PIPE_BRIDGE, pos, state);
    }

    public boolean tryPassThrough(World world, BlockPos bridgePos, Direction incomingDir, FluidPipeBlockEntity sourcePipe) {
        Direction outgoingDir = incomingDir;

        BlockPos outPos = bridgePos.offset(outgoingDir);
        BlockEntity outBE = world.getBlockEntity(outPos);

        if (!(outBE instanceof FluidPipeBlockEntity targetPipe)) return false;

        if (!targetPipe.canAcceptFrom(outgoingDir.getOpposite())) return false;

        targetPipe.acceptFluid(sourcePipe.storedFluid, sourcePipe.storedAmount, outgoingDir.getOpposite());

        sourcePipe.resetFluid();
        sourcePipe.markDirty();
        world.updateListeners(sourcePipe.getPos(), sourcePipe.getCachedState(), sourcePipe.getCachedState(), 3);

        return true;
    }
}
