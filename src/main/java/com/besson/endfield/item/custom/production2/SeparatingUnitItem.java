package com.besson.endfield.item.custom.production2;

import com.besson.endfield.item.custom.AbstractMultiBlockItem;
import com.besson.endfield.renderer.item.production2.SeparatingUnitItemRenderer;
import net.minecraft.block.Block;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SeparatingUnitItem extends AbstractMultiBlockItem implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);
    public SeparatingUnitItem(Block block, Settings settings) {
        super(block, settings);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private final SeparatingUnitItemRenderer renderer = new SeparatingUnitItemRenderer();
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
        Direction facing = context.getHorizontalPlayerFacing().getOpposite();
        Direction left = facing.rotateYCounterclockwise();
        Direction right = facing.rotateYClockwise();
        Direction back = facing.getOpposite();
        Direction backLeft = back.rotateYClockwise();
        Direction backRight = back.rotateYCounterclockwise();

        return new BlockPos[]{
            pos.offset(facing),
            pos.offset(facing).offset(left), pos.offset(facing).offset(right),
            pos.offset(facing).offset(left, 2), pos.offset(facing).offset(right, 2), pos.offset(facing).offset(right, 3),
            pos.offset(right), pos.offset(left),
            pos.offset(right, 2), pos.offset(right, 3), pos.offset(left, 2),
            pos.offset(back), pos.offset(back, 2),
            pos.offset(back).offset(backLeft), pos.offset(back).offset(backRight),
            pos.offset(back).offset(backLeft, 2), pos.offset(back).offset(backRight, 2), pos.offset(back).offset(backRight, 3),
            pos.offset(back, 2).offset(backLeft), pos.offset(back, 2).offset(backRight),
            pos.offset(back, 2).offset(backLeft, 2), pos.offset(back, 2).offset(backRight, 2), pos.offset(back, 2).offset(backRight, 3)
        };

    }
}
