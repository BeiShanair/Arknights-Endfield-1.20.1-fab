package com.besson.endfield.blockentity.custom.production1;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOBlockEntity;
import com.besson.endfield.blockentity.custom.BaseIOSideBlockEntity;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RefiningUnitSideBlockEntity extends BaseIOSideBlockEntity {

    public RefiningUnitSideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.REFINING_UNIT_SIDE, pos, state);
    }

    @Override
    public @Nullable RefiningUnitBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof RefiningUnitBlockEntity e) {
            return e;
        }
        return null;
    }

    public static <T extends BaseIOSideBlockEntity> void tick(World world, BlockPos pos, BlockState state, T be) {
        BaseIOSideBlockEntity.tick(world, pos, state, be);

        if (world.isClient()) return;
        RefiningUnitBlockEntity parent = (RefiningUnitBlockEntity) be.getParentBlock();
        if (parent == null) return;
        if (parent.sewageStorage.getAmount() <= 0) return;

        Direction facing = parent.getFacing(state);
        BlockPos targetPos = pos.offset(facing.rotateYCounterclockwise());

        Storage<FluidVariant> target = FluidStorage.SIDED.find(world, targetPos, facing.rotateYCounterclockwise());
        if (target == null) return;

        try (Transaction tx = Transaction.openOuter()) {
            FluidVariant variant = parent.sewageStorage.getResource();
            long toExtract = Math.min(2000, parent.sewageStorage.getAmount());
            long extracted = parent.sewageStorage.extract(variant, toExtract, tx);
            if (extracted > 0) {
                long inserted = target.insert(variant, extracted, tx);
                if (inserted > 0) {
                    if (inserted < extracted) {
                        parent.sewageStorage.insert(variant, extracted - inserted, tx);
                    }
                    tx.commit();
                }
            }
        }
    }
}
