package com.besson.endfield.blockentity.custom;

import com.besson.endfield.blockentity.ImplementedInventory;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.screen.custom.BigStorageScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
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
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class BigStorageBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final Map<ItemVariant, Long> storage = new HashMap<>();
    private final long capacity = 10000L;
    public static final int MAX_SLOTS = 108;

    private final DefaultedList<ItemStack> dummyInventory = DefaultedList.ofSize(MAX_SLOTS, ItemStack.EMPTY);

    public BigStorageBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

//    public BigStorageBlockEntity(BlockPos pos, BlockState state) {
//        super(ModBlockEntities.BIG_STORAGE, pos, state);
//    }

    public Map<ItemVariant, Long> getStorage() {
        return storage;
    }

    public boolean insertItem(ItemStack stack) {
        if (stack.isEmpty()) return false;

        ItemVariant variant = ItemVariant.of(stack);
        long existing = storage.getOrDefault(variant, 0L);

        if (!storage.containsKey(variant) && storage.size() >= MAX_SLOTS) {
            return false;
        }

        long newCount = existing + stack.getCount();
        storage.put(variant, newCount);

        // 更新虚拟 Inventory 用于客户端显示
        int index = getVariantIndex(variant);
        dummyInventory.set(index, variant.toStack((int)Math.min(newCount, 10000)));

        stack.decrement(stack.getCount());
        markDirty();
        return true;
    }

    public ItemStack extractItem(ItemVariant variant, long amount) {
        long existing = storage.getOrDefault(variant, 0L);
        if (existing <= 0) return ItemStack.EMPTY;

        long toExtract = Math.min(amount, existing);
        storage.put(variant, existing - toExtract);
        if (storage.get(variant) == 0) storage.remove(variant);

        // 更新虚拟 Inventory
        int index = getVariantIndex(variant);
        if (storage.containsKey(variant)) {
            dummyInventory.set(index, variant.toStack((int)Math.min(storage.get(variant), 64)));
        } else {
            dummyInventory.set(index, ItemStack.EMPTY);
        }

        markDirty();
        return variant.toStack((int)toExtract);
    }

    private int getVariantIndex(ItemVariant variant) {
        int i = 0;
        for (ItemVariant v : storage.keySet()) {
            if (v.equals(variant)) return i;
            i++;
            if (i >= MAX_SLOTS) break;
        }
        return 0; // 默认返回0，不会越界
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);

        NbtList list = new NbtList();
        int count = 0;
        for (Map.Entry<ItemVariant, Long> entry : storage.entrySet()) {
            if (count++ >= MAX_SLOTS) break;
            NbtCompound entryTag = new NbtCompound();
            entryTag.put("Item", entry.getKey().toNbt());
            entryTag.putLong("Count", entry.getValue());
            list.add(entryTag);
        }
        nbt.put("Items", list);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        storage.clear();

        NbtList list = nbt.getList("Items", NbtElement.COMPOUND_TYPE);
        int index = 0;
        for (NbtElement e : list) {
            NbtCompound entryTag = (NbtCompound) e;
            ItemVariant variant = ItemVariant.fromNbt(entryTag.getCompound("Item"));
            long count = entryTag.getLong("Count");
            storage.put(variant, count);

            // 同步到 dummyInventory
            if (index < MAX_SLOTS) {
                dummyInventory.set(index, variant.toStack((int)Math.min(count, 64)));
                index++;
            }
        }

        // 填充剩余空槽
        for (int i = index; i < MAX_SLOTS; i++) {
            dummyInventory.set(i, ItemStack.EMPTY);
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
        return Text.translatable("blockEntity.big_storage");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BigStorageScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return dummyInventory;
    }
}
