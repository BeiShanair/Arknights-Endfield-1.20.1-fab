package com.besson.endfield.pipe;

import com.besson.endfield.blockentity.custom.FluidPumpBlockEntity;
import com.besson.endfield.blockentity.custom.PipeBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class PipeAPI {
    /**
     * 获取目标方块实体在指定方向的流体存储接口。
     *
     * @param world     当前世界
     * @param pos       目标方块位置
     * @param side      我们连接的那一侧（例如对于北面的方块，要传输流体到它，就取 SOUTH）
     * @return 如果目标支持 Fabric 流体传输接口则返回 Storage，否则 null
     */
    public static Storage<FluidVariant> getNeighborFluidStorage(World world, BlockPos pos, Direction side) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof PipeBlockEntity pipe) {
            return pipe.getStorage();
        }
        if (be instanceof FluidPumpBlockEntity pump) {
            return pump.getFluidStorageForSide(side);
        }
        // 未来可支持储液罐等其他方块
        return null;
    }
}
