package com.besson.endfield.blockentity.custom;

import com.besson.endfield.blockentity.ImplementedInventory;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.screen.custom.ThermalBankScreenHandler;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
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
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

// TODO: 机械动力适配
public class ThermalBankBlockEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory, ImplementedInventory {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final DefaultedList<ItemStack> inv = DefaultedList.ofSize(1, ItemStack.EMPTY);

    private int burnTime;
    private int fuelTime;
    protected final PropertyDelegate propertyDelegate;

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

        if (entity.burnTime == 0 && !entity.getStack(0).isEmpty()) {
            int fuelTime = (FuelRegistry.INSTANCE.get(entity.inv.get(0).getItem())) / 2;
            if (fuelTime > 0) {
                entity.fuelTime = fuelTime;
                entity.burnTime = fuelTime;

                entity.inv.get(0).decrement(1);
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
}
