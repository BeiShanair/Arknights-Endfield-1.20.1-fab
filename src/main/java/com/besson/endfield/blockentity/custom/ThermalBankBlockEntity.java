package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.custom.ThermalBankBlock;
import com.besson.endfield.blockentity.ImplementedInventory;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.screen.custom.ThermalBankScreenHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
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
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ThermalBankBlockEntity extends BlockEntity implements SidedInventory, GeoBlockEntity, ExtendedScreenHandlerFactory, ImplementedInventory {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final DefaultedList<ItemStack> inv = DefaultedList.ofSize(1, ItemStack.EMPTY);

    private int burnTime;
    private int fuelTime;
    protected final PropertyDelegate propertyDelegate;

    public static final int INPUT_SLOT = 0;
    public static final int[] INPUT_SLOTS = {0};

    public ThermalBankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.THERMAL_BANK, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> ThermalBankBlockEntity.this.burnTime;
                    case 1 -> ThermalBankBlockEntity.this.fuelTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ThermalBankBlockEntity.this.burnTime = value;
                    case 1 -> ThermalBankBlockEntity.this.fuelTime = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inv;
    }

    public static void tick(World world, BlockPos pos, BlockState state, ThermalBankBlockEntity entity) {
        if (entity.burnTime > 0) {
            entity.burnTime--;
        }

        if (entity.burnTime == 0 && !entity.getStack(INPUT_SLOT).isEmpty()) {
            Integer fuelValue = FuelRegistry.INSTANCE.get(entity.inv.get(INPUT_SLOT).getItem());

            if (fuelValue != null && fuelValue > 0) {
                int fuelTime = fuelValue / 2;
                entity.fuelTime = fuelTime;
                entity.burnTime = fuelTime;

                entity.inv.get(INPUT_SLOT).decrement(1);
                entity.markDirty();
            }
        }
    }

    public boolean isBurning() {
        return this.burnTime > 0;
    }

    public int getPowerOutput() {
        return isBurning() ? 150 : 0;
    }

    public float getFuelProgress() {
        if (fuelTime == 0) return 0;
        return (float) burnTime / (float) fuelTime;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inv);
        nbt.putInt("burnTime", burnTime);
        nbt.putInt("fuelTime", fuelTime);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inv);
        burnTime = nbt.getInt("burnTime");
        fuelTime = nbt.getInt("fuelTime");
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
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.thermal_bank");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ThermalBankScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        BlockState state = world != null && pos != null ? world.getBlockState(pos) : null;
        if (state != null && state.contains(ThermalBankBlock.FACING)) {
            Direction facing = state.get(ThermalBankBlock.FACING);
            if (side == facing) {
                return INPUT_SLOTS;
            }
        }
        return new int[0];
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return slot == INPUT_SLOT && FuelRegistry.INSTANCE.get(stack.getItem()) != null
                && FuelRegistry.INSTANCE.get(stack.getItem()) > 0;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return false;
    }
}
