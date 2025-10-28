package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.custom.FluidPumpBlock;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.screen.custom.FluidPumpScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageUtil;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
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
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
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

    private static final int CAPACITY = 10000;
    private final SingleVariantStorage<FluidVariant> fluidStorage = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            return CAPACITY;
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

    public static void tick(World world, BlockPos pos, BlockState state, FluidPumpBlockEntity blockEntity) {
        if (world.isClient()) return;

        blockEntity.pumpCooldown++;
        if (blockEntity.pumpCooldown % 20 != 0) return;

        BlockPos waterPos = pos.offset(blockEntity.getCachedFacing().getOpposite()).down();
        FluidState waterState = world.getFluidState(waterPos);
        if (waterState.isIn(FluidTags.WATER)) {
            try (Transaction tx = Transaction.openOuter()) {
                long inserted = blockEntity.fluidStorage.insert(FluidVariant.of(Fluids.WATER), 1000, tx);
                if (inserted > 0) tx.commit();
            }
        }
        blockEntity.pumpCooldown = 0;
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
        NbtCompound fluidNbt = new NbtCompound();
        FluidVariant variant = fluidStorage.getResource();
        fluidNbt.putString("fluid", Registries.FLUID.getId(variant.getFluid()).toString());
        fluidNbt.putLong("amount", fluidStorage.getAmount());
        nbt.put("fluidStorage", fluidNbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("fluidStorage")) {
            NbtCompound fluidNbt = nbt.getCompound("fluidStorage");
            String fluidIdStr = fluidNbt.getString("fluid");
            Fluid fluid = null;
            if (fluidIdStr != null && !fluidIdStr.isEmpty()) {
                try {
                    fluid = Registries.FLUID.get(new Identifier(fluidIdStr));
                } catch (Exception e) {
                    fluid = null;
                }
            }
            long amount = fluidNbt.getLong("amount");

            // 如果没有有效流体或数量为 0，则清空存储并返回，避免插入 blank variant
            if (fluid == null || fluid == Fluids.EMPTY || amount <= 0) {
                try (Transaction tx = Transaction.openOuter()) {
                    StorageUtil.move(fluidStorage, new SingleVariantStorage<>() {
                        @Override
                        protected FluidVariant getBlankVariant() { return FluidVariant.blank(); }
                        @Override
                        protected long getCapacity(FluidVariant variant) { return Long.MAX_VALUE; }
                    }, v -> true, Long.MAX_VALUE, tx);
                    tx.commit();
                }
                return;
            }

            try (Transaction tx = Transaction.openOuter()) {
                // 提取当前所有流体，等效于清空
                StorageUtil.move(fluidStorage, new SingleVariantStorage<>() {
                    @Override
                    protected FluidVariant getBlankVariant() { return FluidVariant.blank(); }
                    @Override
                    protected long getCapacity(FluidVariant variant) { return Long.MAX_VALUE; }
                }, v -> true, Long.MAX_VALUE, tx);

                // 仅在 fluid 非空且 amount > 0 时插入
                fluidStorage.insert(FluidVariant.of(fluid), amount, tx);
                tx.commit();
            }
        }
    }

    public Storage<FluidVariant> getFluidStorageForSide(Direction side) {
        // 输出端：Pump 朝向的正面
        Direction outputSide = this.getCachedFacing(); // 自己缓存的朝向
        if (side == outputSide) {
            return fluidStorage;
        }
        return null;
    }

    private Direction getCachedFacing() {
        BlockState state = this.getWorld().getBlockState(this.getPos());
        return state.get(FluidPumpBlock.FACING);
    }
}
