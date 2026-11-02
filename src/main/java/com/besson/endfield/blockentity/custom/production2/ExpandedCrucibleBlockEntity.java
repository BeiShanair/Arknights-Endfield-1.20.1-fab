package com.besson.endfield.blockentity.custom.production2;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.screen.custom.screenHandler.ExpandedCrucibleScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ExpandedCrucibleBlockEntity extends BaseCrucibleBlockEntity {
    public static final int RESOURCE_SLOT_COUNT = 8;
    public static final int POWER_COST_PER_TICK = 100;

    public ExpandedCrucibleBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EXPANDED_CRUCIBLE, pos, state, RESOURCE_SLOT_COUNT, POWER_COST_PER_TICK);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("blockEntity.expanded_crucible");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ExpandedCrucibleScreenHandler(syncId, playerInventory, this);
    }
}
