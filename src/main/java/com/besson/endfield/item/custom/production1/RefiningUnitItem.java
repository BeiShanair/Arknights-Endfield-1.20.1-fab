package com.besson.endfield.item.custom.production1;

import com.besson.endfield.item.custom.AbstractMultiBlockItem;
import com.besson.endfield.renderer.item.production1.RefiningUnitItemRenderer;
import com.besson.endfield.util.MultiBlockPlacementUtil;
import net.minecraft.block.Block;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class RefiningUnitItem extends AbstractMultiBlockItem implements GeoItem {

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public RefiningUnitItem(Block block, Settings settings) {
        super(block, settings);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private final RefiningUnitItemRenderer renderer = new RefiningUnitItemRenderer();
            @Override
            public BuiltinModelItemRenderer getCustomRenderer() {
                return renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    protected BlockPos[] getOccupiedPositions(ItemPlacementContext context) {
        BlockPos pos = context.getBlockPos();
        return MultiBlockPlacementUtil.betweenClosed(pos.add(1, 0, 1), pos.add(-1, 0, -1));
    }
}
