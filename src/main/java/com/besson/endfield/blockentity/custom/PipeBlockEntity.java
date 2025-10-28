package com.besson.endfield.blockentity.custom;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class PipeBlockEntity extends BlockEntity {
    private final SingleVariantStorage<FluidVariant> storage = new SingleVariantStorage<FluidVariant>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant fluidVariant) {
            return 10000;
        }
    };

    public PipeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PIPE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, PipeBlockEntity blockEntity) {
        if (world.isClient()) return;

        for (Direction direction : Direction.values()) {
            BlockEntity be = world.getBlockEntity(pos.offset(direction));
            if (be == null) continue;

            Storage<FluidVariant> target = FluidStorage.SIDED.find(world, pos.offset(direction), direction.getOpposite());
            if (target == null) continue;

            try (Transaction transaction = Transaction.openOuter()){
                long moved = StorageUtil.move(blockEntity.storage, target, f -> true, 1000, transaction);
                if (moved > 0) {
                    transaction.commit();
                }
            }
        }
    }

    public SingleVariantStorage<FluidVariant> getStorage() {
        return storage;
    }

    public long getCapacity() {
        return storage.getCapacity();
    }
}
