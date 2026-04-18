package com.besson.endfield.blockentity.custom.resourcing;

import com.besson.endfield.blockentity.custom.logicitis.BeltBlockEntity;
import com.besson.endfield.blockentity.custom.powering.ElectricPylonBlockEntity;
import com.besson.endfield.blockentity.custom.powering.RelayTowerBlockEntity;
import com.besson.endfield.utils.power.NodeType;
import com.besson.endfield.utils.power.PowerNetworkManager;
import com.besson.endfield.utils.power.PowerNetworkNodeManager;
import com.besson.endfield.utils.storage.GlobalStorageManager;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BaseRigBlockEntity<R extends Recipe<?>> extends BlockEntity implements ExtendedScreenHandlerFactory {
    protected final SimpleInventory outputInv;
    protected final InventoryStorage outputStorage;
    protected int tickNum = 0;
    protected boolean isPowered = false;
    protected boolean registeredToManager = false;
    protected int storedPower;
    protected static final int MAX_STORED_POWER = 10000;
    protected boolean isWorking;
    protected boolean enable = true;
    protected int progress = 0;
    protected int maxProgress;
    protected int tier;

    protected static final int SUBMIT_INTERVAL = 100;
    protected int submitTimer = 0;

    protected final PropertyDelegate propertyDelegate;
    protected boolean needsInit = true;

    public BaseRigBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int maxProgress) {
        super(type, pos, state);
        this.maxProgress = maxProgress;
        
        this.outputInv = new SimpleInventory(1) {
            @Override
            public void markDirty() {
                super.markDirty();
                BaseRigBlockEntity.this.markDirty();
            }

            @Override
            public boolean isValid(int slot, ItemStack stack) {
                return false;
            }
        };
        this.outputStorage = createOutputStorage();
        this.propertyDelegate = createPropertyDelegate();
        this.tier = getTier();
    }
    protected abstract PropertyDelegate createPropertyDelegate();
    protected abstract int getPowerCostPerTick();
    protected abstract Optional<R> getMatchRecipe(World world);
    protected abstract void craftItem(World world);
    protected abstract boolean hasCorrectRecipe(World world);
    protected abstract int getTier();
    
    public static <T extends BaseRigBlockEntity<?>> void tick(World world, BlockPos pos, BlockState state, T be) {
        if (world.isClient()) return;

        if (be.needsInit && world instanceof ServerWorld serverWorld) {
            be.needsInit = false;

            PowerNetworkManager.get(serverWorld).registerConsumer(be.getPos(), be::getRequiredPower, be::receiveElectricCharge);
            be.registeredToManager = true;
        }

        if (!be.getEnable()) {
            be.isWorking = false;
            world.updateListeners(pos, state, state, 3);
            be.markDirty();
            return;
        }

        be.tickNum++;

        if (be.tickNum % 20 == 0 && world instanceof ServerWorld serverWorld) {
            AtomicReference<BlockPos> t = new AtomicReference<>();
            PowerNetworkNodeManager manager = PowerNetworkNodeManager.get(serverWorld);
            manager.findNearest(pos, NodeType.CONSUMER, 10).ifPresent(target -> t.set(target.pos()));
            if (t.get() != null) {
                BlockEntity b = world.getBlockEntity(t.get());
                if (b instanceof ElectricPylonBlockEntity || b instanceof RelayTowerBlockEntity) {
                    be.isPowered = true;
                } else {
                    be.isPowered = false;
                    be.isWorking = false;
                }
                be.markDirty();
                world.updateListeners(pos, state, state, 3);
            }
            be.tickNum = 0;
        }
        
        if (!be.isPowered && be.storedPower < be.getPowerCostPerTick()) return;

        if (be.isPowered) {
            be.submitTimer++;
            if (be.submitTimer >= SUBMIT_INTERVAL) {
                be.submitTimer = 0;
                be.flushToGlobalStorage(world);
            }
        }

        if (be.isOutputSlotAvailable()) {
            boolean hasRecipe = be.hasCorrectRecipe(world);
            if (be.needsPower() || !hasRecipe) {
                be.isWorking = false;
            } else if (!be.needsPower() && !be.isWorking) {
                be.isWorking = true;
            }
            be.markDirty();
            world.updateListeners(pos, state, state, 3);

            if (hasRecipe && be.storedPower >= be.getPowerCostPerTick()) {
                be.incrementProgress();
                be.storedPower -= be.getPowerCostPerTick();
                if (be.hasCraftingFinished()) {
                    be.craftItem(world);
                    be.resetProgress();
                }
            } else {
                be.resetProgress();
            }
        } else {
            be.resetProgress();
        }
        be.tryPushToBelt(world, pos, state);
        be.markDirty();
    }

    public void flushToGlobalStorage(World world) {
        if (!(world instanceof ServerWorld serverWorld)) return;

        GlobalStorageManager manager = GlobalStorageManager.get(serverWorld);
        boolean changed = false;

        for (int i = 0; i < outputInv.size(); i++) {
            ItemStack stack = outputInv.getStack(i);
            if (!stack.isEmpty()) {
                long inserted = manager.insert(stack);
                if (inserted > 0) {
                    stack.decrement((int) inserted);
                    changed = true;
                }
            }
        }
        if (changed) markDirty();
    }

    protected void tryPushToBelt(World world, BlockPos pos, BlockState state) {
        Storage<ItemVariant> machine =
                ItemStorage.SIDED.find(world, pos, null);
        if (machine == null) return;
        for (Direction outputDir : Direction.values()) {
            BlockPos beltPos = pos.offset(outputDir);
            BlockEntity targetBe = world.getBlockEntity(beltPos);

            if (!(targetBe instanceof BeltBlockEntity belt)) continue;

            if (!belt.canAcceptFrom(outputDir.getOpposite())) continue;

            try (Transaction tx = Transaction.openOuter()) {

                for (StorageView<ItemVariant> view : machine) {

                    if (view.isResourceBlank()) continue;

                    ItemVariant variant = view.getResource();

                    long extracted = view.extract(variant, 1, tx);

                    if (extracted > 0) {

                        ItemStack stack = variant.toStack((int) extracted);
                        
                        tx.commit();

                        belt.acceptItem(stack, outputDir.getOpposite());

                        return;
                    }
                }
            }
        }
    }
    
    protected boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }
    protected InventoryStorage createOutputStorage() {
        return InventoryStorage.of(outputInv, null);
    }
    public SimpleInventory getOutputInv() {
        return outputInv;
    }
    public DefaultedList<ItemStack> getItems() {
        DefaultedList<ItemStack> combined = DefaultedList.ofSize(outputInv.size(), ItemStack.EMPTY);
        for (int i = 0; i < outputInv.size(); i++) {
            combined.set(i, outputInv.getStack(i));
        }
        return combined;
    }
    public void setEnable(boolean enable) {
        this.enable = enable;
        markDirty();
        if (world != null) {
            world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
        }
    }

    public boolean getEnable() {
        return this.enable;
    }

    @Override
    public void setWorld(World world) {
        super.setWorld(world);
        if (world instanceof ServerWorld) {
            needsInit = true;
        }
    }

    @Override
    public void markRemoved() {
        if (world instanceof ServerWorld serverWorld) {
            PowerNetworkManager.get(serverWorld).unregisterConsumer(this.pos);
            registeredToManager = false;
        }
        super.markRemoved();
    }

    public void receiveElectricCharge(int amount) {
        this.storedPower = Math.min(this.storedPower + amount * 20, MAX_STORED_POWER);
    }

    public boolean needsPower() {
        return this.storedPower < getPowerCostPerTick();
    }

    public int getRequiredPower() {
        if (isWorking || isPowered && storedPower < MAX_STORED_POWER) {
            return getPowerCostPerTick();
        }
        return 0;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtCompound outputTag = new NbtCompound();
        Inventories.writeNbt(outputTag, outputInv.stacks);
        nbt.put("output", outputTag);
        nbt.putInt("progress", this.progress);
        nbt.putInt("storedPower", this.storedPower);
        nbt.putBoolean("isWorking", this.isWorking);
        nbt.putBoolean("enable", this.enable);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("output")) {
            Inventories.readNbt(nbt.getCompound("output"), outputInv.stacks);
        }
        this.progress = nbt.getInt("progress");
        this.storedPower = nbt.getInt("storedPower");
        this.isWorking = nbt.getBoolean("isWorking");
        this.enable = nbt.getBoolean("enable");
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    protected void resetProgress() {
        this.progress = 0;
    }

    protected void incrementProgress() {
        this.progress++;
    }

    protected boolean canOutputAccept(ItemStack result) {
        ItemStack out = outputInv.getStack(0);
        return (out.isEmpty() || out.getItem() == result.getItem())
                && out.getCount() + result.getCount() <= out.getMaxCount();
    }

    protected boolean isOutputSlotAvailable() {
        return outputInv.getStack(0).isEmpty() || outputInv.getStack(0).getCount() < outputInv.getStack(0).getMaxCount();
    }

    @Nullable
    public Storage<ItemVariant> getStorage() {
        return outputStorage;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    public boolean isWorking() {
        return isWorking;
    }
}
