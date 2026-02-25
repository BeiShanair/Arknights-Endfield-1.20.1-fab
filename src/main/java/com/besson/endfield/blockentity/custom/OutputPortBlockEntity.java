package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.custom.OutputPortBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class OutputPortBlockEntity extends BlockEntity {
    public OutputPortBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.OUTPUT_PORT, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, OutputPortBlockEntity be) {

        if (world.isClient()) return;

        // 机器方向
        Direction facing = state.get(OutputPortBlock.FACING);

        // 获取机器 Storage
        BlockPos machinePos = pos.offset(facing.getOpposite());
        Storage<ItemVariant> machine =
                ItemStorage.SIDED.find(world, machinePos, facing);

        if (machine == null) return;

        // 传送带方向（反方向）
        BlockPos beltPos = pos.offset(facing);

        BlockEntity targetBe = world.getBlockEntity(beltPos);

        if (!(targetBe instanceof BeltBlockEntity belt)) return;

        if (!belt.canAcceptFrom(facing.getOpposite())) return;
        
        try (Transaction tx = Transaction.openOuter()) {

            for (StorageView<ItemVariant> view : machine) {

                if (view.isResourceBlank()) continue;

                ItemVariant variant = view.getResource();

                long extracted = view.extract(variant, 1, tx);

                if (extracted > 0) {

                    ItemStack stack = variant.toStack((int) extracted);

                    // 提交事务（真正扣除机器物品）
                    tx.commit();

                    // 传给传送带
                    belt.acceptItem(stack, facing.getOpposite());

                    return;
                }
            }
        }
    }
}
