package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.custom.FluidPipeBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumMap;
import java.util.Map;

public class FluidPipeBlockEntity extends BlockEntity {

    public static final long CAPACITY = 1000;
    public static final long TRANSFER_RATE = 100;

    private final SingleVariantStorage<FluidVariant> tank =
            new SingleVariantStorage<>() {

                @Override
                protected FluidVariant getBlankVariant() {
                    return FluidVariant.blank();
                }

                @Override
                protected long getCapacity(FluidVariant fluidVariant) {
                    return CAPACITY;
                }

                @Override
                protected void onFinalCommit() {
                    markDirty();
                }
            };

    private final Map<Direction, Long> lastReceivedTick = new EnumMap<>(Direction.class);

    public FluidPipeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLUID_PIPE, pos, state);
    }

    public Storage<FluidVariant> getFluidStorage(@Nullable Direction direction) {
        if (direction == null) return null;

        return tank;
    }

    private static BooleanProperty propertyFor(Direction dir) {
        return switch (dir) {
            case NORTH -> FluidPipeBlock.NORTH;
            case SOUTH -> FluidPipeBlock.SOUTH;
            case EAST -> FluidPipeBlock.EAST;
            case WEST -> FluidPipeBlock.WEST;
            case UP -> FluidPipeBlock.UP;
            case DOWN -> FluidPipeBlock.DOWN;
        };
    }

    public static void tick(World world, BlockPos pos, BlockState state, FluidPipeBlockEntity blockEntity) {
        if (world.isClient()) return;

        long availableInTank = blockEntity.tank.getAmount();
        if (availableInTank == 0) return;
        System.out.println("Pipe at " + pos + " has " + availableInTank + " fluid to transfer.");

        FluidVariant fluid = blockEntity.tank.getResource();
        if (fluid.isBlank()) return;

        for (Direction dir : Direction.values()) {
            BooleanProperty prop = propertyFor(dir);
            if (!state.get(prop)) continue; // 该方向未连接，跳过

            BlockPos targetPos = pos.offset(dir);

            // 优先判断目标是否为管道：如果是管道，比较其 tank 状态决定是否推送
            BlockEntity neighborBe = world.getBlockEntity(targetPos);
            if (neighborBe instanceof FluidPipeBlockEntity targetPipe) {
                FluidVariant targetVariant = targetPipe.tank.getResource();
                long targetAmount = targetPipe.tank.getAmount();

                // 只在目标未满且（目标为空或与当前同种流体且目标量小于当前管道量）时才推送
                boolean sameOrEmpty = targetVariant.isBlank() || targetVariant.equals(fluid);
                boolean notFull = targetAmount < CAPACITY;
                boolean targetLessThanSource = targetAmount < blockEntity.tank.getAmount();

                if (!(notFull && sameOrEmpty && targetLessThanSource)) {
                    continue; // 不满足推送条件，跳过该方向
                }
            }

            if (neighborBe instanceof FluidPumpBlockEntity) continue;

            // 找到目标的 storage（可能不是管道也能接受）
            Storage<FluidVariant> target = FluidStorage.SIDED.find(world, targetPos, dir.getOpposite());
            if (target == null) continue;

            // 计算本次最大尝试量（不超过当前管内量）
            long maxAttempt = Math.min(availableInTank, TRANSFER_RATE);
            if (maxAttempt == 0) continue;

            try (Transaction tx = Transaction.openOuter()) {
                long accepted = target.insert(fluid, maxAttempt, tx);
                if (accepted == 0) {
                    // 插入不接受，事务结束回滚
                    continue;
                }

                long extracted = blockEntity.tank.extract(fluid, accepted, tx);
                if (extracted != accepted) {
                    // 无法从 tank 中提取期望量，回滚
                    continue;
                }

                // 提取与插入量匹配，提交事务
                tx.commit();
                // 更新本地可用量，避免对后续方向重复使用已传出的量
                availableInTank -= extracted;
                if (availableInTank == 0) break;
            }
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtCompound tankNbt = new NbtCompound();
        tank.writeNbt(tankNbt);
        nbt.put("Tank", tankNbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (!nbt.contains("Tank")) return;

        NbtCompound tankNbt = nbt.getCompound("Tank");

        FluidVariant variant =
                FluidVariant.fromNbt(tankNbt.getCompound("variant"));

        long amount = tankNbt.getLong("amount");

        tank.variant = amount == 0 ? FluidVariant.blank() : variant;
        tank.amount = amount;
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}
