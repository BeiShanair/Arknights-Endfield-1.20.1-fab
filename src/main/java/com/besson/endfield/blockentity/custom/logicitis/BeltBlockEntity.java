package com.besson.endfield.blockentity.custom.logicitis;

import com.besson.endfield.block.custom.logicitis.BeltBlock;
import com.besson.endfield.block.custom.logicitis.BeltShape;
import com.besson.endfield.blockentity.ModBlockEntities;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BeltBlockEntity extends BlockEntity {
    public ItemStack storedItem = ItemStack.EMPTY;

    public float progress = 0f;
    public float lastProgress = 0f;
    public static final float SPEED = 0.025f;
    public Direction travelDirection = null;

    public BeltBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BELT, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, BeltBlockEntity be) {
        if (world.isClient()) return;

        if (!be.storedItem.isEmpty() && be.travelDirection != null) {

            be.lastProgress = be.progress;
            be.progress += SPEED;

            if (be.progress >= 1f) {

                int pushResult = be.tryPushToStorage(world, pos, state);
                if (pushResult == 1) {
                    be.resetItem();
                    return;
                } else if (pushResult == 0) {
                    be.lastProgress = be.progress = 1f;
                    be.markDirty();
                    world.updateListeners(pos, be.getCachedState(), be.getCachedState(), 3);
                    return;
                }

                boolean moved = be.tryPushForward(world, pos, state);
                if (moved) {
                    be.progress = 0f;
                } else {
                    be.lastProgress = be.progress = 1f;
                    be.markDirty();
                    world.updateListeners(pos, be.getCachedState(), be.getCachedState(), 3);
                    return;
                }
            }
        }

        be.markDirty();
        world.updateListeners(pos, be.getCachedState(), be.getCachedState(), 3);
    }

    public void resetItem() {
        this.progress = 0f;
        this.lastProgress = 0f;
        this.storedItem = ItemStack.EMPTY;
        this.travelDirection = null;
    }

    private int tryPushToStorage(World world, BlockPos pos, BlockState state) {
        if (this.storedItem.isEmpty()) return -1;

        BeltShape shape = state.get(BeltBlock.SHAPE);

        Direction next = BeltBlock.getNextDirection(shape, this.travelDirection);
        if (next == null) return -1;

        BlockPos outputPos = BeltBlock.getNextPos(pos, shape, next);

        Storage<ItemVariant> storage =
                ItemStorage.SIDED.find(world, outputPos, next.getOpposite());

        if (storage == null) return -1;

        ItemVariant variant = ItemVariant.of(this.storedItem);

        try (Transaction tx = Transaction.openOuter()) {
            long inserted = storage.insert(variant, 1, tx);
            if (inserted == 1) {
                this.storedItem.decrement(1);
                this.travelDirection = next;
                tx.commit();
                markDirty();
                return 1;
            } else {
                return 0;
            }
        }
    }
    public boolean canAcceptFrom(Direction from) {
        if (!this.storedItem.isEmpty()) return false;
        BeltShape shape = this.getCachedState().get(BeltBlock.SHAPE);
        return switch (from) {
            case NORTH ->
                    shape == BeltShape.NORTH_SOUTH || shape == BeltShape.NORTH_EAST || shape == BeltShape.NORTH_WEST;
            case SOUTH -> 
                    shape == BeltShape.NORTH_SOUTH || shape == BeltShape.SOUTH_EAST || shape == BeltShape.SOUTH_WEST;
            case WEST -> 
                    shape == BeltShape.EAST_WEST || shape == BeltShape.NORTH_WEST || shape == BeltShape.SOUTH_WEST;
            case EAST -> 
                    shape == BeltShape.EAST_WEST || shape == BeltShape.NORTH_EAST || shape == BeltShape.SOUTH_EAST;
            default -> false;
        };
    }
    
    public void acceptItem(ItemStack stack, Direction from) {
        if (!this.storedItem.isEmpty()) return;

        this.storedItem = stack;
        this.progress = 0f;
        this.lastProgress = 0f;
        this.travelDirection = from;

        markDirty();
    }

    private boolean tryPushForward(World world, BlockPos pos, BlockState state) {
        BeltShape shape = state.get(BeltBlock.SHAPE);

        Direction next = BeltBlock.getNextDirection(shape, this.travelDirection);
        if (next == null) {
            ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), this.storedItem);
            this.storedItem = ItemStack.EMPTY;
            return true;
        }

        BlockPos forwardPos = BeltBlock.getNextPos(pos, shape, next);

        BlockEntity forwardBE = world.getBlockEntity(forwardPos);

        if (forwardBE instanceof BeltBridgeBlockEntity bridge) {
            return bridge.tryPassThrough(world, forwardPos, next, this);
        }
        if (forwardBE instanceof SplitterBlockEntity splitter) {
            return splitter.tryDistribute(world, forwardPos, next, this);
        }
        if (forwardBE instanceof ConvergerBlockEntity converger) {
            return converger.tryMerge(world, forwardPos, next, this);
        }
        if (forwardBE instanceof BeltBlockEntity forwardBelt) {
            if (forwardBelt.storedItem.isEmpty()) {
                forwardBelt.storedItem = this.storedItem;
                forwardBelt.travelDirection = next.getOpposite();
                this.resetItem();
                return true;
            } else {
                return false;
            }
        } else if (world.getBlockEntity(forwardPos.down()) instanceof BeltBlockEntity b) {
            if (b.storedItem.isEmpty()) {
                b.storedItem = this.storedItem;
                b.travelDirection = next.getOpposite();
                this.resetItem();
                return true;
            } else {
                return false;
            }
        } else {
            ItemScatterer.spawn(world, forwardPos.getX(), forwardPos.getY(), forwardPos.getZ(), this.storedItem);
            this.storedItem = ItemStack.EMPTY;
            return true;
        }
    }
    
    public ItemStack getStoredItem() {
        return storedItem;
    }
    
    public DefaultedList<ItemStack> getItem() {
        DefaultedList<ItemStack> list = DefaultedList.of();
        list.add(this.storedItem);
        return list;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putFloat("progress", progress);
        if (!this.storedItem.isEmpty()) {
            NbtCompound itemNbt = new NbtCompound();
            this.storedItem.writeNbt(itemNbt);
            nbt.put("storedItem", itemNbt);
        }
        if (this.travelDirection != null) {
            nbt.putInt("travelDirection", this.travelDirection.getId());
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.progress = nbt.getFloat("progress");
        if (nbt.contains("storedItem")) {
            this.storedItem = ItemStack.fromNbt(nbt.getCompound("storedItem"));
        } else {
            this.storedItem = ItemStack.EMPTY;
        }
        if (nbt.contains("travelDirection")) {
            this.travelDirection = Direction.byId(nbt.getInt("travelDirection"));
        } else {
            this.travelDirection = null;
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

    public Direction getTravelDirection() {
        return this.travelDirection;
    }
}
