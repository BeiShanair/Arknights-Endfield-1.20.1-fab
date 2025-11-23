package com.besson.endfield.blockentity.custom;

import com.besson.endfield.block.custom.ProtocolAnchorCoreBlock;
import com.besson.endfield.blockentity.ImplementedInventory;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.power.PowerNetworkManager;
import com.besson.endfield.screen.custom.ProtocolAnchorCoreScreenHandler;
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
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public class ProtocolAnchorCoreBlockEntity extends BlockEntity implements GeoBlockEntity, ExtendedScreenHandlerFactory, ImplementedInventory, SidedInventory {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final DefaultedList<ItemStack> inv = DefaultedList.ofSize(54, ItemStack.EMPTY);

    private boolean registeredToManager = false;

    public ProtocolAnchorCoreBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PROTOCOL_ANCHOR_CORE, pos, state);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inv;
    }

    @Override
    public void setWorld(World world) {
        super.setWorld(world);
        if (!registeredToManager && world instanceof ServerWorld serverWorld) {
            PowerNetworkManager manager = PowerNetworkManager.get(serverWorld);
            manager.registerGenerator(this.getPos(), () -> {
                try {
                    return 150;
                } catch (Throwable t) {
                    return 0;
                }
            });
            registeredToManager = true;
        }
    }

    @Override
    public void markRemoved() {
        if (world instanceof ServerWorld serverWorld) {
            PowerNetworkManager.get(serverWorld).unregisterGenerator(this.getPos());
        }
        super.markRemoved();
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
        PowerNetworkManager manager = PowerNetworkManager.get((ServerWorld) serverPlayerEntity.getWorld());
        packetByteBuf.writeDouble(manager.getLastSupplyRatio());
        packetByteBuf.writeInt(manager.getLastTotalGenerated());
        packetByteBuf.writeInt(manager.getLastTotalDemand());
        packetByteBuf.writeInt(manager.getCurrentStoredEnergy());
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.protocol_anchor_core");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ProtocolAnchorCoreScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inv);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inv);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        int[] slots = new int[inv.size()];
        for (int i = 0; i < inv.size(); i++) {
            slots[i] = i;
        }
        return slots;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        Direction facing = this.getCachedState().get(ProtocolAnchorCoreBlock.FACING);
        return dir == facing;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        Direction facing = this.getCachedState().get(ProtocolAnchorCoreBlock.FACING);
        return dir != facing;
    }
}
