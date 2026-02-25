package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.custom.WaterInjectorBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class WaterInjectorBlockEntity extends BlockEntity {
    public static final long CAPACITY = 1000;
    public static final long TRANSFER_RATE = 50;

    private final SingleVariantStorage<FluidVariant> tank =
            new SingleVariantStorage<>() {

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

    public WaterInjectorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WATER_INJECTOR, pos, state);
    }


    @Nullable
    public Storage<FluidVariant> getFluidStorage(Direction side) {
        if (side == null) return null;

        Direction facing = getCachedState().get(WaterInjectorBlock.FACING);

        if (side == facing.getOpposite()) {
            return tank;
        }

        return null;
    }


    public static void tick(World world, BlockPos pos, BlockState state, WaterInjectorBlockEntity be) {
        if (world.isClient) return;

        if (be.tank.getAmount() == 0) return;

        FluidVariant fluid = be.tank.getResource();

        try (Transaction tx = Transaction.openOuter()) {
            long consumed = be.tank.extract(fluid, TRANSFER_RATE, tx);

            if (consumed > 0) {
                System.out.println(
                        "FluidInjector at " + pos +
                                " consumed " + consumed + " mB of " +
                                Registries.FLUID.getId(fluid.getFluid())
                );
                tx.commit();
            }
        }
    }
}
