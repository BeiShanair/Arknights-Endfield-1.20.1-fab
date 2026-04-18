package com.besson.endfield.blockentity.custom.logicitis;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.powering.ElectricPylonBlockEntity;
import com.besson.endfield.blockentity.custom.powering.RelayTowerBlockEntity;
import com.besson.endfield.screen.custom.screenHandler.ProtocolStashScreenHandler;
import com.besson.endfield.utils.power.NodeType;
import com.besson.endfield.utils.power.PowerNetworkManager;
import com.besson.endfield.utils.power.PowerNetworkNodeManager;
import com.besson.endfield.utils.storage.GlobalStorageManager;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.concurrent.atomic.AtomicReference;

public class ProtocolStashBlockEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory {
    private boolean isPowered = false;
    public boolean enable = true;
    private boolean registeredToManager = false;
    private int tickNum = 0;
    private boolean needsInit = true;

    private static final int SUBMIT_INTERVAL = 200;
    private int submitTimer = 0;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final PropertyDelegate propertyDelegate;

    private final SimpleInventory inventory = new SimpleInventory(27) {
        @Override
        public void markDirty() {
            super.markDirty();
            ProtocolStashBlockEntity.this.markDirty();
        }
    };
    private final InventoryStorage inventoryStorage = InventoryStorage.of(inventory, null);

    public ProtocolStashBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PROTOCOL_STASH, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return ProtocolStashBlockEntity.this.enable ? 1 : 0;
            }

            @Override
            public void set(int index, int value) {
                ProtocolStashBlockEntity.this.enable = value == 1;
            }

            @Override
            public int size() {
                return 1;
            }
        };
    }

    public static void tick(World world, BlockPos pos, BlockState state, ProtocolStashBlockEntity be) {
        if (world.isClient()) return;
        
        if (be.needsInit && world instanceof ServerWorld serverWorld) {
            be.needsInit = false;
            PowerNetworkManager.get(serverWorld).registerConsumer(
                be.getPos(), () -> 0, amount -> {});
            be.registeredToManager = true;
        }

        if (!be.getEnable()) {
            world.updateListeners(pos, state, state, 3);
            be.markDirty();
            return;
        }
        be.tickNum++;

        if (be.tickNum % 20 == 0 && world instanceof ServerWorld serverWorld) {
            AtomicReference<BlockPos> nearestPower = new AtomicReference<>();
            PowerNetworkNodeManager manager = PowerNetworkNodeManager.get(serverWorld);
            manager.findNearest(pos, NodeType.CONSUMER, 10).ifPresent(target -> nearestPower.set(target.pos()));
            
            if (nearestPower.get() != null) {
                BlockEntity nearbyBE = world.getBlockEntity(nearestPower.get());
                be.isPowered = nearbyBE instanceof ElectricPylonBlockEntity || nearbyBE instanceof RelayTowerBlockEntity;
            } else {
                be.isPowered = false;
            }
            be.tickNum = 0;
            be.markDirty();
            world.updateListeners(pos, state, state, 3);
        }

        if (be.isPowered) {
            be.submitTimer++;
            if (be.submitTimer >= SUBMIT_INTERVAL) {
                be.submitTimer = 0;
                be.flushToGlobalStorage(world);
            }
        }
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        markDirty();
        if (world != null) {
            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    public boolean getEnable() {
        return this.enable;
    }
    
    public void flushToGlobalStorage(World world) {
        if (!(world instanceof ServerWorld serverWorld)) return;
        
        GlobalStorageManager manager = GlobalStorageManager.get(serverWorld);
        boolean changed = false;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
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

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.isPowered = nbt.getBoolean("isPowered");
        this.submitTimer = nbt.getInt("submitTimer");
        this.enable = nbt.getBoolean("enable");
        Inventories.readNbt(nbt, this.inventory.stacks);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putBoolean("isPowered", this.isPowered);
        nbt.putInt("submitTimer", this.submitTimer);
        nbt.putBoolean("enable", this.enable);
        Inventories.writeNbt(nbt, this.inventory.stacks);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.protocol_stash");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ProtocolStashScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    public boolean isPowered() {
        return isPowered;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Nullable
    public Storage<ItemVariant> getStorage(BlockState state, Direction side) {
        return inventoryStorage;
    }

    public DefaultedList<ItemStack> getItems() {
        DefaultedList<ItemStack> inv = DefaultedList.ofSize(27, ItemStack.EMPTY);
        for (int i = 0; i < inventory.size(); i++) {
            inv.set(i, inventory.getStack(i));
        }
        return inv;
    }

    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(pos);
    }
}
