package com.besson.endfield.blockentity.custom;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.screen.custom.ProtocolAnchorCoreScreenHandler;
import com.besson.endfield.utils.ProtocolAnchorCoreStatus;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
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
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

// TODO: 全局电网管理器
public class ProtocolAnchorCoreBlockEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private int buffer = 0;
    private final int baseMaxBuffer = 100000;
    private final int basePower = 150;
    private int extraPower = 0;
    private int loadNode = 0;

    protected final PropertyDelegate propertyDelegate;

    public ProtocolAnchorCoreBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PROTOCOL_ANCHOR_CORE, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> ProtocolAnchorCoreBlockEntity.this.buffer;
                    case 1 -> ProtocolAnchorCoreBlockEntity.this.getMaxBuffer();
                    case 2 -> ProtocolAnchorCoreBlockEntity.this.basePower;
                    case 3 -> ProtocolAnchorCoreBlockEntity.this.getExtraPower();
                    case 4 -> ProtocolAnchorCoreBlockEntity.this.loadNode;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {

            }

            @Override
            public int size() {
                return 5;
            }
        };
    }

    public static void tick(World world, BlockPos pos, BlockState state, ProtocolAnchorCoreBlockEntity entity) {
        if (world.isClient()) return;
        entity.buffer = Math.min(entity.buffer + entity.getTotalPower(), entity.getMaxBuffer());
        entity.markDirty();
    }

    private int getNearbyThermalBankPower() {
        int sum = 0;
        int loadNode = 0;
        BlockPos blockPos = this.getPos();
        if (world != null) {
            for (BlockPos pos : BlockPos.iterate(blockPos.add(-30, -10, -30), blockPos.add(30, 10, 30))) {
                BlockEntity be = world.getBlockEntity(pos);
                if (be instanceof ThermalBankBlockEntity blockEntity) {
                    sum += blockEntity.getPowerOutput();
                    loadNode += 1;
                }
            }
        }
        this.loadNode = loadNode;
        return sum;
    }

    public int getMaxBuffer() {
        return baseMaxBuffer + getNearbyThermalBankPower();
    }

    private int getExtraPower() {
        this.extraPower = getNearbyThermalBankPower();
        return extraPower;
    }

    public int getTotalPower() {
        return basePower + getExtraPower();
    }

    public ProtocolAnchorCoreStatus getStatus() {
        return new ProtocolAnchorCoreStatus(buffer, getMaxBuffer(), basePower, getExtraPower(), loadNode);
    }
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0,
                state -> state.setAndContinue(RawAnimation.begin().thenLoop("idle"))));
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
        return Text.translatable("blockEntity.protocol_anchor_core");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ProtocolAnchorCoreScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.buffer = nbt.getInt("buffer");
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("buffer", this.buffer);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    public int getStoredPower() {
        return (int) this.buffer;
    }

    public boolean canSupplyPower() {
        return this.buffer >= 100;
    }

    public void consumePower(int i) {
        this.buffer = Math.max(0, this.buffer - i);
        markDirty();
    }
}
