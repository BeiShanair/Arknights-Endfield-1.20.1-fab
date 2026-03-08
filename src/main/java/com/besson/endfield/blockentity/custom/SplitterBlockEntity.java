package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.custom.SplitterBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class SplitterBlockEntity extends BlockEntity {
    private int nextIndex = 0;
    
    public SplitterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SPLITTER, pos, state);
    }

    public boolean tryDistribute(World world, BlockPos pos, Direction incomingDir, BeltBlockEntity sourceBelt) {

        BlockState state = world.getBlockState(pos);
        Direction inputDir = state.get(SplitterBlock.FACING);

        // 只允许从输入方向进入
        if (incomingDir.getOpposite() != inputDir) return false;

        Direction[] outputs = getOutputDirections(inputDir);

        for (int i = 0; i < outputs.length; i++) {

            Direction dir = outputs[(nextIndex + i) % outputs.length];

            BlockPos outPos = pos.offset(dir);
            BlockEntity outBE = world.getBlockEntity(outPos);

            if (!(outBE instanceof BeltBlockEntity targetBelt)) continue;
            if (!targetBelt.storedItem.isEmpty()) continue;

            // 转发
            targetBelt.storedItem = sourceBelt.storedItem;
            targetBelt.travelDirection = dir.getOpposite();

            sourceBelt.resetItem();

            // 更新轮询索引
            nextIndex = (nextIndex + 1) % outputs.length;

            return true;
        }

        return false; // 三个方向都被堵
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
