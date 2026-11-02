package com.besson.endfield.blockentity.custom.logicitis;

import com.besson.endfield.block.custom.logicitis.PipeConvergerBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class PipeConvergerBlockEntity extends BlockEntity {
    private int nextInputIndex = 0;

    public PipeConvergerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PIPE_CONVERGER, pos, state);
    }

    public boolean tryMerge(World world, BlockPos pos, Direction incomingDir, FluidPipeBlockEntity sourcePipe) {

        BlockState state = world.getBlockState(pos);
        Direction outputDir = state.get(PipeConvergerBlock.FACING).getOpposite();

        if (incomingDir.getOpposite() == outputDir) return false;

        Direction[] inputs = getInputDirections(outputDir);

        int incomingIndex = -1;

        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] == incomingDir.getOpposite()) {
                incomingIndex = i;
                break;
            }
        }

        if (incomingIndex == -1) return false;

        BlockPos outPos = pos.offset(outputDir);
        BlockEntity outBE = world.getBlockEntity(outPos);

        if (!(outBE instanceof FluidPipeBlockEntity targetPipe)) return false;
        if (!targetPipe.canAcceptFrom(outputDir.getOpposite())) return false;

        targetPipe.acceptFluid(sourcePipe.storedFluid, sourcePipe.storedAmount, outputDir.getOpposite());

        sourcePipe.resetFluid();
        sourcePipe.markDirty();
        world.updateListeners(sourcePipe.getPos(), sourcePipe.getCachedState(), sourcePipe.getCachedState(), 3);

        nextInputIndex = (incomingIndex + 1) % inputs.length;

        return true;
    }

    private Direction[] getInputDirections(Direction outputDir) {
        Direction[] dirs = new Direction[3];
        int index = 0;
        for (Direction dir : Direction.Type.HORIZONTAL) {
            if (dir != outputDir) {
                dirs[index++] = dir;
            }
        }
        return dirs;
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("NextIndex", nextInputIndex);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        nextInputIndex = nbt.getInt("NextIndex");
    }
}
