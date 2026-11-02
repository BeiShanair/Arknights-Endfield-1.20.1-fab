package com.besson.endfield.blockentity.custom.production1;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.powering.BasePylonBlockEntity;
import com.besson.endfield.blockentity.custom.powering.BaseRelayBlockEntity;
import com.besson.endfield.screen.custom.screenHandler.WaterTreatmentUnitScreenHandler;
import com.besson.endfield.utils.power.NodeEntry;
import com.besson.endfield.utils.power.NodeType;
import com.besson.endfield.utils.power.PowerNetworkManager;
import com.besson.endfield.utils.power.PowerNetworkNodeManager;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.screen.PropertyDelegate;
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

import java.util.Optional;

public class WaterTreatmentUnitBlockEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final int POWER_PER_TICK = 50;
    public static final int CYCLE_TICKS = 40;
    public static final long PROCESS_AMOUNT = 1000;
    public static final long FLUID_CAPACITY = 50000;

    private static final int MAX_STORED_POWER = 10000;

    public final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            return FLUID_CAPACITY;
        }

        @Override
        protected void onFinalCommit() {
            markDirty();
            if (world != null) {
                world.updateListeners(pos, getCachedState(), getCachedState(), 3);
            }
        }
    };

    private int tickNum = 0;
    private boolean isPowered = false;
    private int storedPower = 0;
    private boolean isWorking = false;
    private boolean enable = true;
    private int progress = 0;
    private boolean needsInit = true;

    public final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> WaterTreatmentUnitBlockEntity.this.progress;
                case 1 -> CYCLE_TICKS;
                case 2 -> WaterTreatmentUnitBlockEntity.this.enable ? 1 : 0;
                case 3 -> (int) WaterTreatmentUnitBlockEntity.this.fluidStorage.amount;
                case 4 -> (int) FLUID_CAPACITY;
                case 5 -> WaterTreatmentUnitBlockEntity.this.storedPower;
                case 6 -> WaterTreatmentUnitBlockEntity.this.isWorking ? 1 : 0;
                case 7 -> WaterTreatmentUnitBlockEntity.this.fluidStorage.variant.isBlank()
                        ? Registries.FLUID.getRawId(Fluids.WATER)
                        : Registries.FLUID.getRawId(WaterTreatmentUnitBlockEntity.this.fluidStorage.variant.getFluid());
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            switch (index) {
                case 0 -> WaterTreatmentUnitBlockEntity.this.progress = value;
                case 2 -> WaterTreatmentUnitBlockEntity.this.enable = value == 1;
            }
        }

        @Override
        public int size() {
            return 8;
        }
    };

    public WaterTreatmentUnitBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WATER_TREATMENT_UNIT, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, WaterTreatmentUnitBlockEntity be) {
        if (world.isClient()) return;

        if (be.needsInit && world instanceof ServerWorld serverWorld) {
            be.needsInit = false;
            PowerNetworkManager.get(serverWorld).registerConsumer(pos, be::getRequiredPower, be::receiveElectricCharge);
        }

        if (!be.enable) {
            be.isWorking = false;
            world.updateListeners(pos, state, state, 3);
            be.markDirty();
            return;
        }

        be.tickNum++;

        if (be.tickNum % 20 == 0 && world instanceof ServerWorld serverWorld) {
            boolean powered = false;
            PowerNetworkNodeManager manager = PowerNetworkNodeManager.get(serverWorld);
            Optional<NodeEntry> nearest = manager.findNearest(pos, NodeType.CONSUMER, 10);
            if (nearest.isPresent()) {
                BlockEntity b = world.getBlockEntity(nearest.get().pos());
                powered = b instanceof BasePylonBlockEntity || b instanceof BaseRelayBlockEntity;
            }
            be.isPowered = powered;
            if (!powered) {
                be.isWorking = false;
            }
            be.tickNum = 0;
        }

        if (be.fluidStorage.amount == 0) {
            be.isWorking = false;
            be.progress = 0;
            world.updateListeners(pos, state, state, 3);
            be.markDirty();
            return;
        }

        if (!be.isPowered && be.storedPower < POWER_PER_TICK) {
            be.isWorking = false;
            return;
        }

        if (be.storedPower >= POWER_PER_TICK) {
            be.isWorking = true;
            be.storedPower -= POWER_PER_TICK;
            be.progress++;

            if (be.progress >= CYCLE_TICKS) {
                be.processFluid();
                be.progress = 0;
            }
        } else {
            be.isWorking = false;
            be.progress = 0;
        }

        be.markDirty();
        world.updateListeners(pos, state, state, 3);
    }

    private void processFluid() {
        if (fluidStorage.variant.isBlank()) return;
        try (Transaction tx = Transaction.openOuter()) {
            long extracted = fluidStorage.extract(fluidStorage.variant, PROCESS_AMOUNT, tx);
            if (extracted > 0) {
                tx.commit();
            }
        }
    }

    public void receiveElectricCharge(int amount) {
        this.storedPower = Math.min(this.storedPower + amount * 20, MAX_STORED_POWER);
    }

    public int getRequiredPower() {
        if (isPowered && storedPower < MAX_STORED_POWER) {
            return POWER_PER_TICK;
        }
        return 0;
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

    public boolean isWorking() {
        return isWorking;
    }

    @Nullable
    public Storage<FluidVariant> getFluidStorage(@Nullable Direction side) {
        if (side == null) return null;
        return fluidStorage;
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
        return Text.translatable("blockEntity.water_treatment_unit");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new WaterTreatmentUnitScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtCompound tankNbt = new NbtCompound();
        tankNbt.put("variant", fluidStorage.variant.toNbt());
        tankNbt.putLong("amount", fluidStorage.amount);
        nbt.put("FluidTank", tankNbt);
        nbt.putInt("progress", this.progress);
        nbt.putInt("storedPower", this.storedPower);
        nbt.putBoolean("isWorking", this.isWorking);
        nbt.putBoolean("enable", this.enable);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("FluidTank")) {
            NbtCompound tankNbt = nbt.getCompound("FluidTank");
            FluidVariant variant = FluidVariant.fromNbt(tankNbt.getCompound("variant"));
            long amount = tankNbt.getLong("amount");
            fluidStorage.variant = amount == 0 ? FluidVariant.blank() : variant;
            fluidStorage.amount = amount;
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
}
