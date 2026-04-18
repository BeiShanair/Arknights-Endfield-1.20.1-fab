package com.besson.endfield.blockentity.custom.powering;

import com.besson.endfield.block.custom.powering.ProtocolAnchorCorePortBlock;
import com.besson.endfield.blockentity.ImplementedInventory;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.logicitis.BeltBlockEntity;
import com.besson.endfield.screen.custom.screenHandler.ProtocolAnchorCorePortScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ProtocolAnchorCorePortBlockEntity extends BlockEntity implements SidedInventory, ImplementedInventory, ExtendedScreenHandlerFactory {

    private BlockPos parentPos;
    private ItemStack filter = ItemStack.EMPTY;
    private final SimpleInventory filterInventory = new SimpleInventory(1) {
        @Override
        public int getMaxCountPerStack() {
            return 1;
        }
    };

    public ProtocolAnchorCorePortBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.PROTOCOL_ANCHOR_CORE_PORT, blockPos, blockState);
    }

    public static void tick(World world, BlockPos pos, BlockState state, ProtocolAnchorCorePortBlockEntity entity) {
        if (world.isClient()) return;
        if (entity.parentPos != null) {
            ProtocolAnchorCoreBlockEntity parent = entity.getParentBlock();
            if (parent != null) { 
                Direction facing = state.get(ProtocolAnchorCorePortBlock.FACING);
                Storage<ItemVariant> machine =
                        ItemStorage.SIDED.find(world, pos, facing.getOpposite());
                
                if (machine == null) return;
                
                BlockPos beltPos = pos.offset(facing.getOpposite());
                BlockEntity targetBe = world.getBlockEntity(beltPos);
                
                if (!(targetBe instanceof BeltBlockEntity belt)) return;
                
                if (!belt.canAcceptFrom(facing)) return;
                
                try (Transaction tx = Transaction.openOuter()) {
                    for (StorageView<ItemVariant> view : machine) {

                        if (view.isResourceBlank()) continue;
                        ItemVariant variant = view.getResource();
                        long extracted = view.extract(variant, 1, tx);

                        if (extracted > 0) {

                            ItemStack stack = variant.toStack((int) extracted);
                            tx.commit();

                            belt.acceptItem(stack, facing);
                            return;
                        }
                    }
                }
            }
            return;
        }

        for (BlockPos p : BlockPos.iterate(pos.add(4, 0, 4), pos.add(-4, 0, -4))) {
            BlockEntity checkEntity = world.getBlockEntity(p);
            if (checkEntity instanceof ProtocolAnchorCoreBlockEntity) {
                entity.setParentPos(p);
                entity.markDirty();
                break;
            }
        }
    }
    
    public @Nullable ProtocolAnchorCoreBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof ProtocolAnchorCoreBlockEntity parentBlock) {
            return parentBlock;
        }
        return null;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        ProtocolAnchorCoreBlockEntity parent = this.getParentBlock();
        if (parent != null) {
            return parent.getItems();
        }
        return DefaultedList.ofSize(0, ItemStack.EMPTY);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        ProtocolAnchorCoreBlockEntity parent = this.getParentBlock();
        if (parent == null) return new int[0];

        int[] parentSlots = parent.getAvailableSlots(side);
        if (filter == null || filter.isEmpty()) {
            return parentSlots;
        }

        DefaultedList<ItemStack> parentItems = parent.getItems();
        List<Integer> matchingSlots = new ArrayList<>();
        for (int slot : parentSlots) {
            if (slot >= 0 && slot < parentItems.size()) {
                ItemStack stackInSlot = parentItems.get(slot);
                if (!stackInSlot.isEmpty() && stackInSlot.getItem() == filter.getItem()) {
                    matchingSlots.add(slot);
                }
            }
        }
        return matchingSlots.stream().mapToInt(Integer::intValue).toArray();
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        Direction facing = this.getCachedState().get(ProtocolAnchorCorePortBlock.FACING);
        return facing == dir;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        Direction facing = this.getCachedState().get(ProtocolAnchorCorePortBlock.FACING);
        return facing != dir;
    }

    public void setParentPos(BlockPos parentPos) {
        this.parentPos = parentPos;
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
            filterInventory.setStack(0, filter.copy());
        } else {
            filterInventory.setStack(0, ItemStack.EMPTY);
        }
        
        this.markDirty();
        if (world != null) {
            world.updateListeners(this.pos, this.getCachedState(), this.getCachedState(), 3);
        }
    }

    public void clearFilter() {
        this.filter = ItemStack.EMPTY;
        filterInventory.setStack(0, ItemStack.EMPTY);
        this.markDirty();
        if (world != null) {
            world.updateListeners(this.pos, this.getCachedState(), this.getCachedState(), 3);
        }
    }
    
    public SimpleInventory getFilterInventory() {
        if (!filter.isEmpty() && filterInventory.getStack(0).isEmpty()) {
            filterInventory.setStack(0, filter.copy());
        } else if (filter.isEmpty() && !filterInventory.getStack(0).isEmpty()) {
            filterInventory.setStack(0, ItemStack.EMPTY);
        }
        return filterInventory;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (parentPos != null) {
            nbt.putLong("parentPos", parentPos.asLong());
        }
        if (filter != null && !filter.isEmpty()) {
            NbtCompound filterNbt = new NbtCompound();
            filter.writeNbt(filterNbt);
            nbt.put("filter", filterNbt);
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("parentPos")) {
            this.parentPos = BlockPos.fromLong(nbt.getLong("parentPos"));
        }
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
    
    @Override
    public Text getDisplayName() {
        return Text.translatable("block.protocol_anchor_core_port");
    }
    
    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        getFilterInventory();
        return new ProtocolAnchorCorePortScreenHandler(syncId, playerInventory, this, new PropertyDelegate() {
            @Override
            public int get(int index) {
                return 0;
            }
            
            @Override
            public void set(int index, int value) {}
            
            @Override
            public int size() {
                return 1;
            }
        });
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }
}
