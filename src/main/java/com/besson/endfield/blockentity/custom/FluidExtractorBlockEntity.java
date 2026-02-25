package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.custom.FluidExtractorBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FluidExtractorBlockEntity extends BlockEntity {
    public static final long CAPACITY = 1000;
    public static final long TRANSFER_RATE = 100;

    private final SingleVariantStorage<FluidVariant> tank =
            new SingleVariantStorage<FluidVariant>() {
                @Override
                protected FluidVariant getBlankVariant() {
                    return FluidVariant.blank();
                }

                @Override
                protected long getCapacity(FluidVariant variant) {
                    return CAPACITY;
                }

                @Override
                protected void onFinalCommit() {
                    markDirty();
                }
            };

    public FluidExtractorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLUID_EXTRACTOR, pos, state);
    }

    @Nullable
    public Storage<FluidVariant> getFluidStorage(Direction side) {
        if (side == null) return null;

        Direction facing = getCachedState().get(FluidExtractorBlock.FACING);

        if (side == facing || side == facing.getOpposite()) {
            return tank;
        }

        return null;
    }

    public static void tick(World world, BlockPos pos, BlockState state, FluidExtractorBlockEntity blockEntity) {
        if (world.isClient()) return;

        Direction facing = state.get(FluidExtractorBlock.FACING);

        if (blockEntity.tank.getAmount() < CAPACITY) {
            BlockPos sourcePos = pos.offset(facing.getOpposite());

            Storage<FluidVariant> source = FluidStorage.SIDED.find(world, sourcePos, facing);

            if (source != null) {
                try (Transaction tx = Transaction.openOuter()) {
                    for (StorageView<FluidVariant> view : source) {
                        // 取出实际的资源并检查是否为空
                        if (view.isResourceBlank()) continue;
                        FluidVariant resource = view.getResource();
                        if (resource.equals(FluidVariant.blank())) continue;

                        // 若自身已有流体，必须一致（比较资源而不是 StorageView）
                        if (blockEntity.tank.getAmount() > 0
                                && !blockEntity.tank.getResource().equals(resource)) {
                            continue;
                        }

                        long extracted = source.extract(resource, TRANSFER_RATE, tx);

                        if (extracted > 0) {
                            long inserted = blockEntity.tank.insert(resource, extracted, tx);

                            if (inserted == extracted) {
                                tx.commit();
                            }
                        }
                        break;
                    }
                }
            }
        }

        if (blockEntity.tank.getAmount() == 0) return;

        BlockPos targetPos = pos.offset(facing);
        Storage<FluidVariant> target =
                FluidStorage.SIDED.find(
                        world,
                        targetPos,
                        facing.getOpposite()
                );

        if (target == null) return;

        FluidVariant fluid = blockEntity.tank.getResource();

        try (Transaction tx = Transaction.openOuter()) {
            long extracted = blockEntity.tank.extract(fluid, TRANSFER_RATE, tx);

            if (extracted == 0) return;

            long accepted = target.insert(fluid, extracted, tx);

            if (accepted == extracted) {
                tx.commit();
            }
        }
    }
}
