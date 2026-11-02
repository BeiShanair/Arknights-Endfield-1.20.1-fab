package com.besson.endfield.blockentity.custom.resourcing;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.BaseIOSideBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class AcidResistantPumpMkIISideBlockEntity extends BaseIOSideBlockEntity {
    public AcidResistantPumpMkIISideBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ACID_RESISTANT_PUMP_MK_II_SIDE, pos, state);
    }

    @Override
    public @Nullable AcidResistantPumpMkIIBlockEntity getParentBlock() {
        if (parentPos == null || world == null) return null;
        BlockEntity entity = world.getBlockEntity(parentPos);
        if (entity instanceof AcidResistantPumpMkIIBlockEntity parent) {
            return parent;
        }
        return null;
    }
}
