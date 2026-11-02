package com.besson.endfield.blockentity.custom.resourcing;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.powering.BasePylonBlockEntity;
import com.besson.endfield.blockentity.custom.powering.BaseRelayBlockEntity;
import com.besson.endfield.blockentity.custom.powering.ElectricPylonBlockEntity;
import com.besson.endfield.blockentity.custom.powering.RelayTowerBlockEntity;
import com.besson.endfield.screen.custom.screenHandler.AcidResistantPumpMkIIScreenHandler;
import com.besson.endfield.utils.PumpFluidUtil;
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
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.FluidTags;
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

public class AcidResistantPumpMkIIBlockEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final long CAPACITY = 50000;
    private static final int POWER_COST = 20 * 20;
    private static final int MAX_STORED_POWER = 10000;
    private int storedPower = 0;
    private boolean isPowered = false;
    private boolean registeredToManager = false;
    private boolean needsInit = true;
    private int powerCheckTimer = 0;

    private int pumpCooldown = 0;
    protected final PropertyDelegate propertyDelegate;
    
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

    public AcidResistantPumpMkIIBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ACID_RESISTANT_PUMP_MK_II, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> (int) fluidStorage.getAmount();
                    case 1 -> (int) fluidStorage.getCapacity();
                    case 2 -> storedPower;
                    case 3 -> fluidStorage.variant.isBlank()
                            ? Registries.FLUID.getRawId(Fluids.WATER)
                            : Registries.FLUID.getRawId(fluidStorage.variant.getFluid());
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {

            }

            @Override
            public int size() {
                return 4;
            }
        };
    }

    public static void tick(World world, BlockPos pos, BlockState state, AcidResistantPumpMkIIBlockEntity be) {
        if (world.isClient()) return;

        if (be.needsInit && world instanceof ServerWorld serverWorld) {
            be.needsInit = false;
            PowerNetworkManager.get(serverWorld).registerConsumer(be.getPos(), be::getRequiredPower, be::receiveElectricCharge);
            be.registeredToManager = true;
        }

        if (world instanceof ServerWorld serverWorld) {
            be.powerCheckTimer++;
            if (be.powerCheckTimer >= 20) {
                be.powerCheckTimer = 0;
                boolean powered = false;
                Optional<NodeEntry> nearest = PowerNetworkNodeManager.get(serverWorld).findNearest(pos, NodeType.CONSUMER, 10);
                if (nearest.isPresent()) {
                    BlockEntity b = world.getBlockEntity(nearest.get().pos());
                    powered = b instanceof BasePylonBlockEntity || b instanceof BaseRelayBlockEntity;
                }
                be.isPowered = powered;
            }
        }

        if (!be.isPowered && be.storedPower < POWER_COST) return;

        Direction facing = be.getCachedFacing();

        be.pumpCooldown++;
        if (be.pumpCooldown < 20) return;
        be.pumpCooldown = 0;

        BlockPos waterPos = pos.offset(facing.getOpposite(), 2).down();
        FluidState fluidState = world.getFluidState(waterPos);
        if (!fluidState.isIn(FluidTags.WATER)) return;

        Fluid fluid = fluidState.getFluid();
        Fluid still = PumpFluidUtil.toStill(fluid);
        long space = CAPACITY - be.fluidStorage.getAmount();
        if (space > 0) {
            try (Transaction tx = Transaction.openOuter()) {
                long inserted = be.fluidStorage.insert(FluidVariant.of(still), Math.min(1000, space), tx);
                if (inserted > 0) {
                    tx.commit();
                    // 完成一次抽水，消耗缓冲电力
                    if (be.storedPower >= POWER_COST) {
                        be.storedPower -= POWER_COST;
                    }
                }
            }
        }
    }

    public int getRequiredPower() {
        if (isPowered && storedPower < MAX_STORED_POWER) {
            return POWER_COST;
        }
        return 0;
    }

    public void receiveElectricCharge(int amount) {
        this.storedPower = Math.min(this.storedPower + amount * 20, MAX_STORED_POWER);
    }

    public int getStoredPower() {
        return storedPower;
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
        if (world instanceof ServerWorld serverWorld && registeredToManager) {
            PowerNetworkManager.get(serverWorld).unregisterConsumer(this.pos);
            registeredToManager = false;
        }
        super.markRemoved();
    }
    
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.acid_resistant_pump_mk_ii");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new AcidResistantPumpMkIIScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
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
        nbt.putInt("storedPower", this.storedPower);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.storedPower = nbt.getInt("storedPower");
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
        if (side == null) return null;

        Direction facing = getCachedFacing();
        if (side == facing) {
            return fluidStorage;
        }
        return null;
    }

    private Direction getCachedFacing() {
        BlockState state = this.getWorld().getBlockState(this.getPos());
        return state.get(ModBlockEntityWithFacing.FACING);
    }

    public void tryFillBucket(PlayerEntity player) {
        ItemStack cursor = player.currentScreenHandler.getCursorStack();
        if (cursor.isEmpty() || cursor.getItem() != Items.BUCKET) return;

        FluidVariant stored = fluidStorage.getResource();
        if (stored.isBlank() || fluidStorage.getAmount() < 1000) return;

        ItemStack filled = PumpFluidUtil.getFilledBucket(stored.getFluid());
        if (filled.isEmpty()) return;

        try (Transaction tx = Transaction.openOuter()) {
            long extracted = fluidStorage.extract(stored, 1000, tx);
            if (extracted == 1000) {
                tx.commit();
                cursor.decrement(1);
                if (cursor.isEmpty()) {
                    player.currentScreenHandler.setCursorStack(filled);
                } else if (!player.getInventory().insertStack(filled)) {
                    player.dropItem(filled, false);
                }
            }
        }
    }
}
