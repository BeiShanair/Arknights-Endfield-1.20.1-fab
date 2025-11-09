package com.besson.endfield.blockentity.custom;

import com.besson.endfield.blockentity.ImplementedInventory;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.screen.custom.SupplyTerminalScreenHandler;
import com.besson.endfield.trade.SupplyTrade;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class SupplyTerminalBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private int level = 1;
    private int tradePoints = 0;
    private final int[] thresholds = {100, 300, 600};
    public final PropertyDelegate propertyDelegate;

    public SupplyTerminalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SUPPLY_TERMINAL, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> level;
                    case 1 -> tradePoints;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> level = value;
                    case 1 -> tradePoints = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.supply_terminal");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new SupplyTerminalScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public void tryTrade(PlayerEntity player) {
        ItemStack input = inventory.get(0);
        for (int i = 1; i <= level; i++) {
            for (SupplyTrade trade : SupplyTrade.getTradesForLevel(i)) {
                if (input.getItem() == trade.input.getItem() && input.getCount() >= trade.amount) {
                    input.decrement(trade.amount);

                    player.giveItemStack(trade.reward.copy());
                    player.addExperience(trade.ex);

                    tradePoints += trade.ex / 10;
                    checkLevelUp();

                    markDirty();

                    return;
                }
            }
        }
    }

    private void checkLevelUp() {
        if (level < 3 && tradePoints >= thresholds[level - 1]) {
            level++;
        }
    }

    public int getLevel() {
        return level;
    }

    public float getProgress() {
        return level == 3 ? 1 : (float) tradePoints / thresholds[level - 1];
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("Level", level);
        nbt.putInt("TradePoints", tradePoints);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        level = nbt.getInt("Level");
        tradePoints = nbt.getInt("TradePoints");
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }
}
