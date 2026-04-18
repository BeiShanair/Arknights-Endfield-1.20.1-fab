package com.besson.endfield.blockentity.custom;

import com.besson.endfield.blockentity.custom.powering.ElectricPylonBlockEntity;
import com.besson.endfield.blockentity.custom.powering.RelayTowerBlockEntity;
import com.besson.endfield.utils.power.NodeType;
import com.besson.endfield.utils.power.PowerNetworkManager;
import com.besson.endfield.utils.power.PowerNetworkNodeManager;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
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

public abstract class BaseIOBlockEntity<R extends Recipe<?>> extends BlockEntity implements ExtendedScreenHandlerFactory {
    protected final SimpleInventory inputInv;
    protected final SimpleInventory outputInv;
    protected final InventoryStorage inputStorage;
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

    protected final PropertyDelegate propertyDelegate;
    protected boolean needsInit = true;

    public BaseIOBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int maxProgress) {
        super(type, pos, state);

        this.maxProgress = maxProgress;

        this.inputInv = new SimpleInventory(getInputSize()){
            @Override
            public void markDirty() {
                super.markDirty();
                BaseIOBlockEntity.this.markDirty();
            }

            @Override
            public boolean isValid(int slot, ItemStack stack) {
                if (stack == null || stack.isEmpty()) return false;

                for (int i = 0; i < this.size(); i++) {
                    if (i == slot) continue;
                    ItemStack s = this.getStack(i);
                    if (!s.isEmpty() && ItemStack.canCombine(s, stack)) {
                        return false;
                    }
                }

                ItemStack current = this.getStack(slot);
                if (current.isEmpty()) {
                    return true;
                }
                
                return ItemStack.canCombine(current, stack) && current.getCount() < current.getMaxCount();
            }
        };
        this.outputInv = new SimpleInventory(getOutputSize()) {
            @Override
            public void markDirty() {
                super.markDirty();
                BaseIOBlockEntity.this.markDirty();
            }

            @Override
            public boolean isValid(int slot, ItemStack stack) {
                return false;
            }
        };
        this.inputStorage = createInputStorage();
        this.outputStorage = createOutputStorage();
        this.propertyDelegate = createPropertyDelegate();
    }

    protected abstract int getInputSize();
    protected abstract int getOutputSize();
    protected abstract PropertyDelegate createPropertyDelegate();
    protected abstract int getPowerCostPerTick();
    protected abstract Direction getFacing(BlockState state);
    protected abstract Optional<R> getMatchRecipe(World world);
    protected abstract void craftItem(World world);
    protected abstract boolean hasCorrectRecipe(World world);

    public static <T extends BaseIOBlockEntity<?>> void tick(World world, BlockPos pos, BlockState state, T be) {
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
                    be.markDirty();
                    world.updateListeners(pos, state, state, 3);
                }
            }
            be.tickNum = 0;
        }
        
        if (!be.isPowered && be.storedPower < be.getPowerCostPerTick()) return;

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
        be.markDirty();
    }
    
    protected boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    protected InventoryStorage createInputStorage() {
        return InventoryStorage.of(inputInv, null);
    }

    protected InventoryStorage createOutputStorage() {
        return InventoryStorage.of(outputInv, null);
    }

    public SimpleInventory getInputInv() {
        return inputInv;
    }

    public SimpleInventory getOutputInv() {
        return outputInv;
    }

    public DefaultedList<ItemStack> getItems() {
        DefaultedList<ItemStack> combined = DefaultedList.ofSize(inputInv.size() + outputInv.size(), ItemStack.EMPTY);
        for (int i = 0; i < inputInv.size(); i++) {
            combined.set(i, this.inputInv.getStack(i));
        }
        for (int i = 0; i < outputInv.size(); i++) {
            combined.set(i + inputInv.size(), this.outputInv.getStack(i));
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

    // region 数据持久化 & 同步
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtCompound inputTag = new NbtCompound();
        Inventories.writeNbt(inputTag, inputInv.stacks);
        nbt.put("input", inputTag);

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
        if (nbt.contains("input")) {
            Inventories.readNbt(nbt.getCompound("input"), inputInv.stacks);
        }
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
    // endregion

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
    public Storage<ItemVariant> getStorage(BlockState state, Direction side) {
        Direction facing = getFacing(state);

        if (side == facing) {
            return inputStorage;
        }
        if (side == facing.getOpposite()) {
            return outputStorage;
        }
        return null;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }
}
