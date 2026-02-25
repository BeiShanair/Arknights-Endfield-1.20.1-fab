package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.custom.FluidPumpBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.screen.custom.FluidPumpScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class FluidPumpBlockEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final long CAPACITY = 10000;
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

    private int pumpCooldown = 0;
    protected final PropertyDelegate propertyDelegate;

    public FluidPumpBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLUID_PUMP, pos, state);
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

    public static void tick(World world, BlockPos pos, BlockState state, FluidPumpBlockEntity be) {
        if (world.isClient()) return;

        Direction facing = be.getCachedFacing();

        // ===== 1. 抽水（每秒）=====
        be.pumpCooldown++;
        if (be.pumpCooldown >= 20) {
            be.pumpCooldown = 0;

            BlockPos waterPos = pos.offset(facing.getOpposite()).down();
            FluidState fluidState = world.getFluidState(waterPos);

            if (fluidState.isIn(FluidTags.WATER)) {
                long space = CAPACITY - be.fluidStorage.getAmount();
                if (space > 0) {
                    try (Transaction tx = Transaction.openOuter()) {
                        long inserted = be.fluidStorage.insert(FluidVariant.of(Fluids.WATER), Math.min(1000, space), tx);

                        if (inserted > 0) tx.commit();
                    }
                }
            }
        }

        // ===== 2. 向前输出（每 tick）=====
        if (be.fluidStorage.getAmount() == 0) return;

        BlockPos targetPos = pos.offset(facing);
        Storage<FluidVariant> target = FluidStorage.SIDED.find(world, targetPos, facing);

        if (target == null) return;
        System.out.println("Pump at " + pos + " pushing fluid to " + targetPos);

        FluidVariant fluid = be.fluidStorage.getResource();

        try (Transaction tx = Transaction.openOuter()) {
            long extracted = be.fluidStorage.extract(fluid, 100, tx);

            long accepted = target.insert(fluid, extracted, tx);
            if (accepted == extracted) {
                tx.commit();
            }
        }
    }
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.fluid_pump");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FluidPumpScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
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
        if (side == null) return null;

        Direction facing = getCachedFacing();

        // 只允许正面被抽
        if (side == facing) {
            return fluidStorage;
        }
        return null;
    }

    private Direction getCachedFacing() {
        BlockState state = this.getWorld().getBlockState(this.getPos());
        return state.get(FluidPumpBlock.FACING);
    }
}
