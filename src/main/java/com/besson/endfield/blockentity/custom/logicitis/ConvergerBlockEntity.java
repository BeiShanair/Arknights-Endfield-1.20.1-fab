package com.besson.endfield.blockentity.custom.logicitis;

import com.besson.endfield.block.custom.logicitis.ConvergerBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ConvergerBlockEntity extends BlockEntity {
    private int nextInputIndex = 0;
    public ConvergerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CONVERGER, pos, state);
    }

    public boolean tryMerge(World world, BlockPos pos, Direction incomingDir, BeltBlockEntity sourceBelt) {

        BlockState state = world.getBlockState(pos);
        Direction outputDir = state.get(ConvergerBlock.FACING).getOpposite();

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

        if (!(outBE instanceof BeltBlockEntity targetBelt)) return false;
        if (!targetBelt.storedItem.isEmpty()) return false;

        // 成功转发
        targetBelt.storedItem = sourceBelt.storedItem;
        targetBelt.travelDirection = outputDir.getOpposite();

        sourceBelt.resetItem();

        // 轮询从当前成功方向的下一个开始
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
