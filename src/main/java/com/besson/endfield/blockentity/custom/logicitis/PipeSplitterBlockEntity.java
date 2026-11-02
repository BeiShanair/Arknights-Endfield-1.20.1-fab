package com.besson.endfield.blockentity.custom.logicitis;

import com.besson.endfield.block.custom.logicitis.PipeSplitterBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class PipeSplitterBlockEntity extends BlockEntity {
    private int nextIndex = 0;

    public PipeSplitterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PIPE_SPLITTER, pos, state);
    }

    public boolean tryDistribute(World world, BlockPos pos, Direction incomingDir, FluidPipeBlockEntity sourcePipe) {

        BlockState state = world.getBlockState(pos);
        Direction inputDir = state.get(PipeSplitterBlock.FACING);

        if (incomingDir.getOpposite() != inputDir) return false;

        Direction[] outputs = getOutputDirections(inputDir);

        for (int i = 0; i < outputs.length; i++) {

            Direction dir = outputs[(nextIndex + i) % outputs.length];

            BlockPos outPos = pos.offset(dir);
            BlockEntity outBE = world.getBlockEntity(outPos);

            if (!(outBE instanceof FluidPipeBlockEntity targetPipe)) continue;
            if (!targetPipe.canAcceptFrom(dir.getOpposite())) continue;

            targetPipe.acceptFluid(sourcePipe.storedFluid, sourcePipe.storedAmount, dir.getOpposite());

            sourcePipe.resetFluid();
            sourcePipe.markDirty();
            world.updateListeners(sourcePipe.getPos(), sourcePipe.getCachedState(), sourcePipe.getCachedState(), 3);

            nextIndex = (nextIndex + 1) % outputs.length;

            return true;
        }

        return false;
    }

    private Direction[] getOutputDirections(Direction inputDir) {

        Direction[] dirs = new Direction[3];
        int index = 0;

        for (Direction dir : Direction.Type.HORIZONTAL) {
            if (dir != inputDir) {
                dirs[index++] = dir;
            }
        }

        return dirs;
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("NextIndex", nextIndex);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        nextIndex = nbt.getInt("NextIndex");
    }
}
