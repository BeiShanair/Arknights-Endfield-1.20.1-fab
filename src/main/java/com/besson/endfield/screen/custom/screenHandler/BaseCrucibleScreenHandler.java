package com.besson.endfield.screen.custom.screenHandler;

import com.besson.endfield.blockentity.custom.production2.BaseCrucibleBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public abstract class BaseCrucibleScreenHandler extends ScreenHandler {
    private final BaseCrucibleBlockEntity blockEntity;

    protected BaseCrucibleScreenHandler(ScreenHandlerType<?> type, int syncId,
                                        PlayerInventory playerInventory, BaseCrucibleBlockEntity blockEntity) {
        super(type, syncId);
        this.blockEntity = blockEntity;
    }

    protected static @Nullable BaseCrucibleBlockEntity readCrucibleBlockEntity(PlayerInventory playerInventory, PacketByteBuf buf) {
        BlockPos pos = buf.readBlockPos();
        BlockEntity be = playerInventory.player.getWorld().getBlockEntity(pos);
        return be instanceof BaseCrucibleBlockEntity crucible ? crucible : null;
    }

    public BaseCrucibleBlockEntity getBlockEntity() {
        return blockEntity;
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return blockEntity.getWorld() == player.getWorld()
                && player.squaredDistanceTo(blockEntity.getPos().getX() + 0.5, blockEntity.getPos().getY() + 0.5, blockEntity.getPos().getZ() + 0.5) <= 64.0;
    }
}
