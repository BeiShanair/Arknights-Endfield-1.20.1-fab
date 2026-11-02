package com.besson.endfield.blockentity.custom.production2;

import com.besson.endfield.blockentity.custom.logicitis.BeltBlockEntity;
import com.besson.endfield.blockentity.custom.logicitis.FluidPipeBlockEntity;
import com.besson.endfield.blockentity.custom.powering.BasePylonBlockEntity;
import com.besson.endfield.blockentity.custom.powering.BaseRelayBlockEntity;
import com.besson.endfield.recipe.ReactorResource;
import com.besson.endfield.recipe.custom.ReactorCrucibleRecipe;
import com.besson.endfield.utils.power.NodeEntry;
import com.besson.endfield.utils.power.NodeType;
import com.besson.endfield.utils.power.PowerNetworkManager;
import com.besson.endfield.utils.power.PowerNetworkNodeManager;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class BaseCrucibleBlockEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory {
    public static final long MAX_ITEM_AMOUNT = 64;
    public static final long MAX_FLUID_AMOUNT = 50000;
    protected static final int MAX_PROGRESS = 40;
    protected static final int MAX_STORED_POWER = 10000;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    protected final int resourceSlotCount;
    protected final int powerCostPerTick;

    protected final Map<ReactorResource, Long> resources = new LinkedHashMap<>();
    @Nullable
    protected ReactorResource itemOutputFilter;
    @Nullable
    protected final ReactorResource[] fluidOutputFilters = new ReactorResource[2];
    protected int progress;

    protected int tickNum = 0;
    protected boolean isPowered = false;
    protected int storedPower = 0;
    protected boolean needsInit = true;

    private final Storage<ItemVariant> itemStorage = new SharedItemStorage();
    private final Storage<FluidVariant> fluidStorage = new SharedFluidStorage();
    @Nullable
    private Storage<FluidVariant>[] portFluidStorages;

    protected BaseCrucibleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state,
                                      int resourceSlotCount, int powerCostPerTick) {
        super(type, pos, state);
        this.resourceSlotCount = resourceSlotCount;
        this.powerCostPerTick = powerCostPerTick;
    }

    public static void tick(World world, BlockPos pos, BlockState state, BaseCrucibleBlockEntity be) {
        if (world.isClient()) return;

        if (be.needsInit && world instanceof ServerWorld serverWorld) {
            be.needsInit = false;
            PowerNetworkManager.get(serverWorld).registerConsumer(be.getPos(), be::getRequiredPower, be::receiveElectricCharge);
        }

        be.tickNum++;
        if (be.tickNum % 20 == 0 && world instanceof ServerWorld serverWorld) {
            PowerNetworkNodeManager manager = PowerNetworkNodeManager.get(serverWorld);
            boolean powered = false;

            Optional<NodeEntry> nearest = manager.findNearest(pos, NodeType.CONSUMER, 10);
            if (nearest.isPresent()) {
                BlockEntity b = world.getBlockEntity(nearest.get().pos());
                powered = b instanceof BasePylonBlockEntity || b instanceof BaseRelayBlockEntity;
            }

            be.isPowered = powered;
            be.tickNum = 0;
        }

        if (!be.isPowered && be.storedPower < be.powerCostPerTick) {
            if (be.progress != 0) {
                be.progress = 0;
                be.sync();
            }
            return;
        }

        List<ReactorCrucibleRecipe> plan = be.createCraftPlan(world);
        if (plan.isEmpty()) {
            be.progress = 0;
            be.sync();
            return;
        }

        be.progress++;
        be.storedPower -= be.powerCostPerTick;
        if (be.progress >= MAX_PROGRESS) {
            be.applyCraftPlan(plan);
            be.progress = 0;
        }
        be.sync();
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
        }
        super.markRemoved();
    }

    public void receiveElectricCharge(int amount) {
        this.storedPower = Math.min(this.storedPower + amount * 20, MAX_STORED_POWER);
    }

    public int getRequiredPower() {
        if (isPowered && storedPower < MAX_STORED_POWER) {
            return powerCostPerTick;
        }
        return 0;
    }

    @Nullable
    public Storage<ItemVariant> getItemStorage(@Nullable Direction side) {
        if (side == null || world == null) return null;
        BlockEntity source = world.getBlockEntity(pos.offset(side));
        if (source instanceof BeltBlockEntity) return itemStorage;
        return null;
    }

    @Nullable
    public Storage<FluidVariant> getFluidStorage(@Nullable Direction side) {
        if (side == null || world == null) return null;
        BlockEntity source = world.getBlockEntity(pos.offset(side));
        if (source instanceof FluidPipeBlockEntity) return getPortFluidStorage(-1);
        return null;
    }

    public Storage<ItemVariant> getSharedItemStorage() {
        return itemStorage;
    }

    public Storage<FluidVariant> getSharedFluidStorage() {
        return fluidStorage;
    }

    public Storage<FluidVariant> getPortFluidStorage(int portIndex) {
        if (portFluidStorages == null) {
            portFluidStorages = new Storage[]{
                    new PortFluidStorage(-1), new PortFluidStorage(0), new PortFluidStorage(1)
            };
        }
        return portFluidStorages[portIndex < 0 ? 0 : Math.min(portIndex, 1) + 1];
    }

    public List<StoredResource> getDisplayResources() {
        List<StoredResource> displayResources = new ArrayList<>();
        for (Map.Entry<ReactorResource, Long> entry : resources.entrySet()) {
            if (!entry.getKey().isBlank() && entry.getValue() > 0) {
                displayResources.add(new StoredResource(entry.getKey(), entry.getValue()));
            }
        }
        return displayResources;
    }

    public void setOutputFromDisplaySlot(int slot) {
        List<StoredResource> displayResources = getDisplayResources();
        if (slot < 0 || slot >= displayResources.size()) return;

        ReactorResource resource = displayResources.get(slot).resource();
        if (resource.isItem()) {
            this.itemOutputFilter = resource;
        } else if (resource.isFluid()) {
            int target = fluidOutputFilters[0] == null || fluidOutputFilters[0].equals(resource) ? 0 : 1;
            this.fluidOutputFilters[target] = resource;
        }
        sync();
    }

    public void clearOutputFilter(int index) {
        if (index == 0) {
            if (itemOutputFilter == null) return;
            itemOutputFilter = null;
        } else if (index >= 1 && index <= 2) {
            int target = index - 1;
            if (fluidOutputFilters[target] == null) return;
            fluidOutputFilters[target] = null;
        } else {
            return;
        }
        sync();
    }

    public ItemVariant getItemOutputFilter() {
        return itemOutputFilter != null && itemOutputFilter.isItem() ? itemOutputFilter.itemVariant() : ItemVariant.blank();
    }

    public FluidVariant getFluidOutputFilter(int index) {
        ReactorResource resource = fluidOutputFilters[index];
        return resource != null && resource.isFluid() ? resource.fluidVariant() : FluidVariant.blank();
    }

    public int getProgress() {
        return progress;
    }

    public int getMaxProgress() {
        return MAX_PROGRESS;
    }

    public int getResourceSlotCount() {
        return resourceSlotCount;
    }

    public int getPowerCostPerTick() {
        return powerCostPerTick;
    }

    private List<ReactorCrucibleRecipe> createCraftPlan(World world) {
        List<ReactorCrucibleRecipe> recipes = world.getRecipeManager().listAllOfType(ReactorCrucibleRecipe.Type.INSTANCE);
        Map<ReactorResource, Long> availableInputs = new LinkedHashMap<>(resources);
        Map<ReactorResource, Long> plannedOutputs = new LinkedHashMap<>();
        List<ReactorCrucibleRecipe> plan = new ArrayList<>();

        for (ReactorCrucibleRecipe recipe : recipes) {
            if (!recipe.canCraft(availableInputs)) continue;

            Map<ReactorResource, Long> candidateInputs = new LinkedHashMap<>(availableInputs);
            recipe.consumeInputs(candidateInputs);

            Map<ReactorResource, Long> candidateFinalState = new LinkedHashMap<>(candidateInputs);
            mergeResources(candidateFinalState, plannedOutputs);
            if (!recipe.canFitOutputs(candidateFinalState, resourceSlotCount)) continue;

            availableInputs = candidateInputs;
            recipe.addOutputs(plannedOutputs);
            plan.add(recipe);
        }
        return plan;
    }

    private void applyCraftPlan(List<ReactorCrucibleRecipe> plan) {
        Map<ReactorResource, Long> finalResources = new LinkedHashMap<>(resources);
        for (ReactorCrucibleRecipe recipe : plan) {
            if (!recipe.canCraft(finalResources)) return;
            recipe.consumeInputs(finalResources);
        }
        for (ReactorCrucibleRecipe recipe : plan) {
            if (!recipe.canFitOutputs(finalResources, resourceSlotCount)) return;
            recipe.addOutputs(finalResources);
        }
        resources.clear();
        resources.putAll(finalResources);
        cleanResources();
    }

    private void mergeResources(Map<ReactorResource, Long> target, Map<ReactorResource, Long> source) {
        for (Map.Entry<ReactorResource, Long> entry : source.entrySet()) {
            target.merge(entry.getKey(), entry.getValue(), Long::sum);
        }
    }

    private long getInsertableAmount(ReactorResource resource, long maxAmount) {
        if (resource.isBlank() || maxAmount <= 0) return 0;
        long stored = resources.getOrDefault(resource, 0L);
        if (stored <= 0 && resources.size() >= resourceSlotCount) return 0;
        return Math.min(maxAmount, resource.getMaxAmount() - stored);
    }

    private void insertResource(ReactorResource resource, long amount) {
        if (resource.isBlank() || amount <= 0) return;
        resources.merge(resource, amount, Long::sum);
        cleanResources();
    }

    private long extractItem(ItemVariant variant, long maxAmount) {
        if (variant.isBlank() || maxAmount <= 0 || itemOutputFilter == null) return 0;
        ReactorResource resource = ReactorResource.item(variant);
        if (!itemOutputFilter.equals(resource)) return 0;
        return extractResource(resource, maxAmount);
    }

    private long extractFluid(FluidVariant variant, long maxAmount) {
        if (variant.isBlank() || maxAmount <= 0) return 0;
        ReactorResource resource = ReactorResource.fluid(variant);
        if (!resource.equals(fluidOutputFilters[0]) && !resource.equals(fluidOutputFilters[1])) return 0;
        return extractResource(resource, maxAmount);
    }

    private long extractResource(ReactorResource resource, long maxAmount) {
        long stored = resources.getOrDefault(resource, 0L);
        long extracted = Math.min(stored, maxAmount);
        if (extracted > 0) {
            resources.put(resource, stored - extracted);
            cleanResources();
        }
        return extracted;
    }

    private void cleanResources() {
        resources.entrySet().removeIf(entry -> entry.getKey().isBlank() || entry.getValue() <= 0);
    }

    protected void sync() {
        markDirty();
        if (world != null) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    @Override
    public abstract Text getDisplayName();

    @Override
    public abstract @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtList resourceList = new NbtList();
        for (Map.Entry<ReactorResource, Long> entry : resources.entrySet()) {
            NbtCompound compound = new NbtCompound();
            compound.put("resource", entry.getKey().toNbt());
            compound.putLong("amount", entry.getValue());
            resourceList.add(compound);
        }
        nbt.put("resources", resourceList);

        if (itemOutputFilter != null) nbt.put("itemOutputFilter", itemOutputFilter.toNbt());
        NbtList fluidFilters = new NbtList();
        for (ReactorResource filter : fluidOutputFilters) {
            if (filter != null) fluidFilters.add(filter.toNbt());
        }
        nbt.put("fluidOutputFilters", fluidFilters);
        nbt.putInt("progress", progress);
        nbt.putInt("storedPower", storedPower);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        resources.clear();

        NbtList resourceList = nbt.getList("resources", NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < resourceList.size(); i++) {
            NbtCompound compound = resourceList.getCompound(i);
            ReactorResource resource = ReactorResource.fromNbt(compound.getCompound("resource"));
            long amount = Math.min(compound.getLong("amount"), resource.getMaxAmount());
            if (!resource.isBlank() && amount > 0 && resources.size() < resourceSlotCount) {
                resources.put(resource, amount);
            }
        }

        itemOutputFilter = nbt.contains("itemOutputFilter") ? ReactorResource.fromNbt(nbt.getCompound("itemOutputFilter")) : null;
        NbtList fluidFilters = nbt.getList("fluidOutputFilters", NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < fluidOutputFilters.length; i++) {
            fluidOutputFilters[i] = i < fluidFilters.size() ? ReactorResource.fromNbt(fluidFilters.getCompound(i)) : null;
        }
        progress = nbt.getInt("progress");
        storedPower = nbt.getInt("storedPower");
        cleanResources();
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    private class SharedItemStorage implements Storage<ItemVariant> {
        @Override
        public boolean supportsInsertion() {
            return true;
        }

        @Override
        public long insert(ItemVariant resource, long maxAmount, TransactionContext transaction) {
            ReactorResource reactorResource = ReactorResource.item(resource);
            long inserted = getInsertableAmount(reactorResource, maxAmount);
            if (inserted <= 0) return 0;
            Map<ReactorResource, Long> snapshot = new LinkedHashMap<>(resources);
            insertResource(reactorResource, inserted);
            transaction.addOuterCloseCallback(result -> {
                if (result.wasAborted()) {
                    resources.clear();
                    resources.putAll(snapshot);
                } else {
                    sync();
                }
            });
            return inserted;
        }

        @Override
        public boolean supportsExtraction() {
            return true;
        }

        @Override
        public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
            Map<ReactorResource, Long> snapshot = new LinkedHashMap<>(resources);
            long extracted = extractItem(resource, maxAmount);
            if (extracted <= 0) return 0;
            transaction.addOuterCloseCallback(result -> {
                if (result.wasAborted()) {
                    resources.clear();
                    resources.putAll(snapshot);
                } else {
                    sync();
                }
            });
            return extracted;
        }

        @Override
        public Iterator<StorageView<ItemVariant>> iterator() {
            return resources.keySet().stream()
                    .filter(reactorResource -> itemOutputFilter != null && itemOutputFilter.equals(reactorResource))
                    .<StorageView<ItemVariant>>map(ItemView::new)
                    .iterator();
        }
    }

    private class SharedFluidStorage implements Storage<FluidVariant> {
        @Override
        public boolean supportsInsertion() {
            return true;
        }

        @Override
        public long insert(FluidVariant resource, long maxAmount, TransactionContext transaction) {
            ReactorResource reactorResource = ReactorResource.fluid(resource);
            long inserted = getInsertableAmount(reactorResource, maxAmount);
            if (inserted <= 0) return 0;
            Map<ReactorResource, Long> snapshot = new LinkedHashMap<>(resources);
            insertResource(reactorResource, inserted);
            transaction.addOuterCloseCallback(result -> {
                if (result.wasAborted()) {
                    resources.clear();
                    resources.putAll(snapshot);
                } else {
                    sync();
                }
            });
            return inserted;
        }

        @Override
        public boolean supportsExtraction() {
            return true;
        }

        @Override
        public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
            Map<ReactorResource, Long> snapshot = new LinkedHashMap<>(resources);
            long extracted = extractFluid(resource, maxAmount);
            if (extracted <= 0) return 0;
            transaction.addOuterCloseCallback(result -> {
                if (result.wasAborted()) {
                    resources.clear();
                    resources.putAll(snapshot);
                } else {
                    sync();
                }
            });
            return extracted;
        }

        @Override
        public Iterator<StorageView<FluidVariant>> iterator() {
            return resources.keySet().stream()
                    .filter(reactorResource -> reactorResource.equals(fluidOutputFilters[0]) || reactorResource.equals(fluidOutputFilters[1]))
                    .<StorageView<FluidVariant>>map(FluidView::new)
                    .iterator();
        }
    }

    private class PortFluidStorage implements Storage<FluidVariant> {
        private final int portIndex;

        private PortFluidStorage(int portIndex) {
            this.portIndex = portIndex;
        }

        @Override
        public boolean supportsInsertion() {
            return true;
        }

        @Override
        public long insert(FluidVariant resource, long maxAmount, TransactionContext transaction) {
            return fluidStorage.insert(resource, maxAmount, transaction);
        }

        @Override
        public boolean supportsExtraction() {
            return portIndex >= 0;
        }

        @Override
        public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
            if (portIndex < 0 || resource.isBlank() || maxAmount <= 0) return 0;
            ReactorResource filter = fluidOutputFilters[portIndex];
            if (filter == null || !filter.isFluid() || !filter.fluidVariant().equals(resource)) return 0;
            return fluidStorage.extract(resource, maxAmount, transaction);
        }

        @Override
        public Iterator<StorageView<FluidVariant>> iterator() {
            if (portIndex < 0) return Collections.emptyIterator();
            ReactorResource filter = fluidOutputFilters[portIndex];
            if (filter == null || !filter.isFluid() || resources.getOrDefault(filter, 0L) <= 0) {
                return Collections.emptyIterator();
            }
            return List.<StorageView<FluidVariant>>of(new PortFluidView(filter)).iterator();
        }

        private class PortFluidView implements StorageView<FluidVariant> {
            private final ReactorResource resource;

            private PortFluidView(ReactorResource resource) {
                this.resource = resource;
            }

            @Override
            public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
                return PortFluidStorage.this.extract(resource, maxAmount, transaction);
            }

            @Override
            public boolean isResourceBlank() {
                return resource.isBlank() || getAmount() <= 0;
            }

            @Override
            public FluidVariant getResource() {
                return resource.fluidVariant();
            }

            @Override
            public long getAmount() {
                return resources.getOrDefault(resource, 0L);
            }

            @Override
            public long getCapacity() {
                return MAX_FLUID_AMOUNT;
            }
        }
    }

    private class ItemView implements StorageView<ItemVariant> {
        private final ReactorResource resource;

        private ItemView(ReactorResource resource) {
            this.resource = resource;
        }

        @Override
        public long extract(ItemVariant resource, long maxAmount, TransactionContext transaction) {
            return itemStorage.extract(resource, maxAmount, transaction);
        }

        @Override
        public boolean isResourceBlank() {
            return resource.isBlank() || getAmount() <= 0;
        }

        @Override
        public ItemVariant getResource() {
            return resource.itemVariant();
        }

        @Override
        public long getAmount() {
            return resources.getOrDefault(resource, 0L);
        }

        @Override
        public long getCapacity() {
            return MAX_ITEM_AMOUNT;
        }
    }

    private class FluidView implements StorageView<FluidVariant> {
        private final ReactorResource resource;

        private FluidView(ReactorResource resource) {
            this.resource = resource;
        }

        @Override
        public long extract(FluidVariant resource, long maxAmount, TransactionContext transaction) {
            return fluidStorage.extract(resource, maxAmount, transaction);
        }

        @Override
        public boolean isResourceBlank() {
            return resource.isBlank() || getAmount() <= 0;
        }

        @Override
        public FluidVariant getResource() {
            return resource.fluidVariant();
        }

        @Override
        public long getAmount() {
            return resources.getOrDefault(resource, 0L);
        }

        @Override
        public long getCapacity() {
            return MAX_FLUID_AMOUNT;
        }
    }

    public record StoredResource(ReactorResource resource, long amount) {
        public boolean item() {
            return resource.isItem();
        }

        public boolean fluid() {
            return resource.isFluid();
        }

        public ItemVariant itemVariant() {
            return resource.itemVariant();
        }

        public FluidVariant fluidVariant() {
            return resource.fluidVariant();
        }

        public ItemStack asStack() {
            return resource.asStack((int) Math.min(amount, 64));
        }
    }
}
