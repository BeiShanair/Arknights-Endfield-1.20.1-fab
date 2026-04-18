package com.besson.endfield.blockentity.custom.logicitis;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.utils.storage.GlobalStorageManager;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class DepotLoaderBlockEntity extends BlockEntity implements GeoBlockEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean working = false;
    private int tickNum = 0;

    public DepotLoaderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DEPOT_LOADER, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public static void tick(World world, BlockPos pos, BlockState state, DepotLoaderBlockEntity be) {
        if (world.isClient()) return;

        be.tickNum++;

        if (be.tickNum % 20 == 0) {
            be.tickNum = 0;
            Direction facing = state.get(ModBlockEntityWithFacing.FACING);
            BlockEntity backEn = world.getBlockEntity(pos.offset(facing.getOpposite()));
            be.working = backEn instanceof DepotBusSectionBlockEntity || backEn instanceof DepotBusSectionSideBlockEntity;
            be.markDirty();
        }

    }

    public boolean sendItemToGlobalStorage(World world, ItemStack stack) {
        if (world.isClient()) return false;

        if (world instanceof ServerWorld serverWorld && this.working) {
            GlobalStorageManager manager = GlobalStorageManager.get(serverWorld);
            long in = manager.insert(stack);
            return in > 0;
        }
        return false;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putBoolean("working", this.working);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.working = nbt.getBoolean("working");
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}
