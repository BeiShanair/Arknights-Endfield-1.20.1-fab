package com.besson.endfield.blockentity.custom.logicitis;

import com.besson.endfield.block.custom.logicitis.FluidPipeBlock;
import com.besson.endfield.block.custom.logicitis.FluidPipeShape;
import com.besson.endfield.blockentity.ModBlockEntities;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FluidPipeBlockEntity extends BlockEntity {

    public static final long CAPACITY = 2000;
    public FluidVariant storedFluid = FluidVariant.blank();
    public long storedAmount = 0;
    public Direction travelDirection = null;
    public PipeMode mode = PipeMode.TRANSFER;

    private int tickCounter = 0;

    public FluidPipeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLUID_PIPE, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, FluidPipeBlockEntity be) {
        if (world.isClient()) return;

        be.tickCounter++;
        if (be.tickCounter < 20) return;
        be.tickCounter = 0;

        BlockState currentState = world.getBlockState(pos);

        if (be.mode == PipeMode.EXTRACT && be.storedFluid.isBlank() && be.storedAmount == 0) {
            be.tryExtractFromNeighbor(world, pos, currentState);
        }

        if (be.storedFluid.isBlank() || be.storedAmount == 0) return;
        if (be.travelDirection == null) return;

        int pushResult = be.tryPushToStorage(world, pos, currentState);
        if (pushResult == 1) {
            be.resetFluid();
            return;
        } else if (pushResult == 0) {
            be.markDirty();
            world.updateListeners(pos, currentState, currentState, 3);
            return;
        }

        boolean moved = be.tryPushForward(world, pos, currentState);
        if (!moved) {
            be.markDirty();
            world.updateListeners(pos, currentState, currentState, 3);
        } else {
            be.resetFluid();
        }
    }

    public void resetFluid() {
        this.storedFluid = FluidVariant.blank();
        this.storedAmount = 0;
        this.travelDirection = null;
    }
    
    private void tryExtractFromNeighbor(World world, BlockPos pos, BlockState state) {
        FluidPipeShape shape = state.get(FluidPipeBlock.SHAPE);
        for (Direction side : FluidPipeBlock.getConnections(shape)) {
            BlockPos neighborPos = pos.offset(side);
            if (world.getBlockEntity(neighborPos) instanceof FluidPipeBlockEntity) continue;

            Storage<FluidVariant> storage = FluidStorage.SIDED.find(world, neighborPos, side.getOpposite());
            if (storage == null) continue;

            try (Transaction tx = Transaction.openOuter()) {
                for (StorageView<FluidVariant> view : storage) {
                    FluidVariant variant = view.getResource();
                    if (variant.isBlank() || view.getAmount() <= 0) continue;

                    long extracted = storage.extract(variant, Math.min(CAPACITY, view.getAmount()), tx);
                    if (extracted > 0) {
                        this.storedFluid = variant;
                        this.storedAmount = extracted;
                        this.travelDirection = side;
                        tx.commit();
                        markDirty();
                        world.updateListeners(pos, state, state, 3);
                        return;
                    }
                }
            }
        }
    }

    private int tryPushToStorage(World world, BlockPos pos, BlockState state) {
        if (this.mode != PipeMode.INSERT) return -1;
        if (this.storedFluid.isBlank()) return -1;

        FluidPipeShape shape = state.get(FluidPipeBlock.SHAPE);
        Direction next = FluidPipeBlock.getNextDirection(shape, this.travelDirection);
        if (next == null) return -1;

        BlockPos outputPos = FluidPipeBlock.getNextPos(pos, shape, next);
        BlockEntity targetBe = world.getBlockEntity(outputPos);

        if (targetBe instanceof FluidPipeBlockEntity) return -1;

        Storage<FluidVariant> storage = FluidStorage.SIDED.find(world, outputPos, next.getOpposite());
        if (storage == null) return -1;

        try (Transaction tx = Transaction.openOuter()) {
            long inserted = storage.insert(this.storedFluid, this.storedAmount, tx);
            if (inserted > 0) {
                this.storedAmount -= inserted;
                if (this.storedAmount <= 0) {
                    this.storedFluid = FluidVariant.blank();
                    this.storedAmount = 0;
                }
                tx.commit();
                markDirty();
                return 1;
            } else {
                return 0;
            }
        }
    }

    private boolean tryPushForward(World world, BlockPos pos, BlockState state) {
        FluidPipeShape shape = state.get(FluidPipeBlock.SHAPE);

        Direction next = FluidPipeBlock.getNextDirection(shape, this.travelDirection);
        if (next == null) return false;

        BlockPos forwardPos = FluidPipeBlock.getNextPos(pos, shape, next);
        BlockEntity forwardBe = world.getBlockEntity(forwardPos);

        if (forwardBe instanceof PipeBridgeBlockEntity bridge) {
            return bridge.tryPassThrough(world, forwardPos, next, this);
        }
        if (forwardBe instanceof PipeSplitterBlockEntity splitter) {
            return splitter.tryDistribute(world, forwardPos, next, this);
        }
        if (forwardBe instanceof PipeConvergerBlockEntity converger) {
            return converger.tryMerge(world, forwardPos, next, this);
        }

        if (forwardBe instanceof FluidPipeBlockEntity forwardPipe) {
            if (forwardPipe.canAcceptFrom(next.getOpposite())) {
                forwardPipe.acceptFluid(this.storedFluid, this.storedAmount, next.getOpposite());
                return true;
            }
            return false;
        } else if (world.getBlockEntity(forwardPos.down()) instanceof FluidPipeBlockEntity b) {
            if (b.canAcceptFrom(next.getOpposite())) {
                b.acceptFluid(this.storedFluid, this.storedAmount, next.getOpposite());
                return true;
            }
            return false;
        }

        if (this.mode != PipeMode.INSERT) return false;

        Storage<FluidVariant> storage = FluidStorage.SIDED.find(world, forwardPos, next.getOpposite());
        if (storage != null) {
            try (Transaction tx = Transaction.openOuter()) {
                long inserted = storage.insert(this.storedFluid, this.storedAmount, tx);
                if (inserted > 0) {
                    this.storedAmount -= inserted;
                    if (this.storedAmount <= 0) {
                        this.storedFluid = FluidVariant.blank();
                        this.storedAmount = 0;
                    }
                    tx.commit();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canAcceptFrom(Direction from) {
        if (!this.storedFluid.isBlank() || this.storedAmount > 0) return false;

        BlockState state = world.getBlockState(pos);
        FluidPipeShape shape = state.get(FluidPipeBlock.SHAPE);
        Direction[] connections = FluidPipeBlock.getConnections(shape);
        return from == connections[0] || from == connections[1];
    }

    public void acceptFluid(FluidVariant fluid, long amount, Direction from) {
        if (!this.storedFluid.isBlank() || this.storedAmount > 0) return;

        this.storedFluid = fluid;
        this.storedAmount = amount;
        this.travelDirection = from;

        markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putLong("storedAmount", storedAmount);
        if (!this.storedFluid.isBlank()) {
            nbt.put("storedFluid", this.storedFluid.toNbt());
        }
        if (this.travelDirection != null) {
            nbt.putInt("travelDirection", this.travelDirection.getId());
        }
        nbt.putInt("mode", this.mode.ordinal());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.storedAmount = nbt.getLong("storedAmount");
        if (nbt.contains("storedFluid")) {
            this.storedFluid = FluidVariant.fromNbt(nbt.getCompound("storedFluid"));
        } else {
            this.storedFluid = FluidVariant.blank();
        }
        if (nbt.contains("travelDirection")) {
            this.travelDirection = Direction.byId(nbt.getInt("travelDirection"));
        } else {
            this.travelDirection = null;
        }
        if (nbt.contains("mode")) {
            int id = nbt.getInt("mode");
            this.mode = (id >= 0 && id < PipeMode.values().length) ? PipeMode.values()[id] : PipeMode.TRANSFER;
        } else {
            this.mode = PipeMode.TRANSFER;
        }
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    public enum PipeMode {
        TRANSFER,
        EXTRACT,
        INSERT;

        public PipeMode next() {
            return switch (this) {
                case TRANSFER -> EXTRACT;
                case EXTRACT -> INSERT;
                case INSERT -> TRANSFER;
            };
        }
    }
}
