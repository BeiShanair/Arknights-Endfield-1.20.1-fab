package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.custom.resourcing.FluidPumpBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.screen.custom.screenHandler.FluidTankScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class FluidTankBlockEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final long CAPACITY = 50000;
    private final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            return CAPACITY;
        }

        @Override
        protected void onFinalCommit() {
            markDirty();
            if (world != null) {
                world.updateListeners(pos, getCachedState(), getCachedState(), 3);
            }
        }
    };

    protected final PropertyDelegate propertyDelegate;

    public FluidTankBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLUID_TANK, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> (int) fluidStorage.getAmount();
                    case 1 -> (int) fluidStorage.getCapacity();
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {

            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.fluid_tank");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FluidTankScreenHandler(syncId, playerInventory, this, propertyDelegate);
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
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtCompound tankNbt = new NbtCompound();
        fluidStorage.writeNbt(tankNbt);
        nbt.put("Tank", tankNbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (!nbt.contains("Tank")) return;

        NbtCompound tankNbt = nbt.getCompound("Tank");

        FluidVariant variant =
                FluidVariant.fromNbt(tankNbt.getCompound("variant"));
        long amount = tankNbt.getLong("amount");

        fluidStorage.variant =
                amount == 0 ? FluidVariant.blank() : variant;
        fluidStorage.amount = amount;
    }

    @Nullable
    public Storage<FluidVariant> getFluidStorage(Direction side) {
        return fluidStorage;
    }

    private Direction getCachedFacing() {
        BlockState state = this.getWorld().getBlockState(this.getPos());
        return state.get(FluidPumpBlock.FACING);
    }
}
