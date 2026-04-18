package com.besson.endfield.blockentity.custom.logicitis;

import com.besson.endfield.blockentity.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class DepotBusSectionBlockEntity extends BlockEntity implements GeoBlockEntity {
    
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public DepotBusSectionBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DEPOT_BUS_SECTION, pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
    
}
