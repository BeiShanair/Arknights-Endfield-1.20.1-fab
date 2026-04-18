package com.besson.endfield.blockentity.custom.logicitis;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.screen.custom.screenHandler.DepotUnloaderScreenHandler;
import com.besson.endfield.utils.storage.GlobalStorageManager;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
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

public class DepotUnloaderBlockEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private ItemStack filter = ItemStack.EMPTY;
    private boolean working = false;
    private int tickNum = 0;
    private final SimpleInventory filterInv = new SimpleInventory(1) {
        @Override
        public int getMaxCountPerStack() {
            return 1;
        }
    };

    public DepotUnloaderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DEPOT_UNLOADER, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, DepotUnloaderBlockEntity be) {
        if (world.isClient()) return;

        be.tickNum++;
        if (be.tickNum % 20 == 0) {
            be.tickNum = 0;
            Direction facing = state.get(ModBlockEntityWithFacing.FACING);
            BlockEntity backEn = world.getBlockEntity(pos.offset(facing.getOpposite()));
            be.working = backEn instanceof DepotBusSectionBlockEntity || backEn instanceof DepotBusSectionSideBlockEntity;
            be.markDirty();
        }

        if (world instanceof ServerWorld serverWorld && be.working && !be.filter.isEmpty()) {
            GlobalStorageManager manager = GlobalStorageManager.get(serverWorld);
            Direction fac = state.get(ModBlockEntityWithFacing.FACING);
            BlockEntity forwardBe = world.getBlockEntity(pos.offset(fac));

            if (!(forwardBe instanceof BeltBlockEntity belt)) return;

            if (!belt.canAcceptFrom(fac)) return;

            belt.acceptItem(manager.extract(be.getFilter().getItem(), 1), fac.getOpposite());
            be.markDirty();
        }
    }

    public ItemStack getFilter() {
        return filter;
    }

    public void setFilter(ItemStack filter) {
        if (filter == null) {
            this.filter = ItemStack.EMPTY;
        }
        this.filter = filter.copy();

        if (!filter.isEmpty()) {
            filterInv.setStack(0, filter.copy());
        } else {
            filterInv.setStack(0, ItemStack.EMPTY);
        }

        this.markDirty();
        if (world != null) {
            world.updateListeners(this.pos, this.getCachedState(), this.getCachedState(), 3);
        }
    }

    public void clearFilter() {
        this.filter = ItemStack.EMPTY;
        filterInv.setStack(0, ItemStack.EMPTY);
        this.markDirty();
        if (world != null) {
            world.updateListeners(this.pos, this.getCachedState(), this.getCachedState(), 3);
        }
    }

    public SimpleInventory getFilterInventory() {
        if (!filter.isEmpty() && filterInv.getStack(0).isEmpty()) {
            filterInv.setStack(0, filter.copy());
        } else if (filter.isEmpty() && !filterInv.getStack(0).isEmpty()) {
            filterInv.setStack(0, ItemStack.EMPTY);
        }
        return filterInv;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (filter != null && !filter.isEmpty()) {
            NbtCompound filterNbt = new NbtCompound();
            filter.writeNbt(filterNbt);
            nbt.put("filter", filterNbt);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("filter")) {
            NbtCompound filterNbt = nbt.getCompound("filter");
            this.filter = ItemStack.fromNbt(filterNbt);
        } else {
            this.filter = ItemStack.EMPTY;
        }
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    public DefaultedList<ItemStack> getItems() {
        return DefaultedList.copyOf(ItemStack.EMPTY, this.filter);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.depot_unloader");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        getFilterInventory();
        return new DepotUnloaderScreenHandler(syncId, playerInventory, this, new PropertyDelegate() {
            @Override
            public int get(int index) {
                return 0;
            }

            @Override
            public void set(int index, int value) {

            }

            @Override
            public int size() {
                return 1;
            }
        });
    }
}
