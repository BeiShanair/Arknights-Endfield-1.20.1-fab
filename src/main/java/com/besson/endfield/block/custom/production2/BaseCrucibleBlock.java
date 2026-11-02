package com.besson.endfield.block.custom.production2;

import com.besson.endfield.block.ModBlockEntityWithFacing;
import com.besson.endfield.blockentity.custom.production2.BaseCrucibleBlockEntity;
import com.besson.endfield.blockentity.custom.production2.BaseCrucibleSideBlockEntity;
import com.besson.endfield.util.MultiBlockPlacementUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class BaseCrucibleBlock extends ModBlockEntityWithFacing {
    private final int powerCost;
    private final String areaLabel;

    protected BaseCrucibleBlock(Settings settings, int powerCost, String areaLabel) {
        super(settings);
        this.powerCost = powerCost;
        this.areaLabel = areaLabel;
    }

    protected abstract Block getSideBlock();
    protected abstract BlockEntityType<? extends BaseCrucibleBlockEntity> crucibleType();

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            BlockEntity entity = world.getBlockEntity(pos);
            if (entity instanceof NamedScreenHandlerFactory screenHandlerFactory) {
                player.openHandledScreen(screenHandlerFactory);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.CONSUME;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, crucibleType(), BaseCrucibleBlockEntity::tick);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!world.isClient()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BaseCrucibleBlockEntity be) {
                DefaultedList<ItemStack> drops = DefaultedList.of();
                for (BaseCrucibleBlockEntity.StoredResource resource : be.getDisplayResources()) {
                    if (resource.item()) {
                        drops.add(resource.asStack());
                    }
                }
                if (!drops.isEmpty()) {
                    ItemScatterer.spawn(world, pos, drops);
                }
                world.updateComparators(pos, this);
            }

            BlockPos[] adjacentPositions = getAdjacentPositions(state, pos);
            for (BlockPos p : adjacentPositions) {
                if (world.getBlockState(p).getBlock() == getSideBlock()) {
                    world.breakBlock(p, false);
                }
            }

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient()) {
            BlockPos[] sidePositions = getAdjacentPositions(state, pos);

            for (BlockPos p : sidePositions) {
                world.setBlockState(p, getSideBlock().getDefaultState().with(FACING, state.get(FACING)));
                BlockEntity blockEntity = world.getBlockEntity(p);
                if (blockEntity instanceof BaseCrucibleSideBlockEntity sideBlockEntity) {
                    sideBlockEntity.setParentPos(pos);
                }
            }
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos[] sidePositions = getAdjacentPositions(state, pos);

        for (BlockPos p : sidePositions) {
            if (!MultiBlockPlacementUtil.isReplaceable(world, p)) {
                return false;
            }
        }
        return true;
    }

    protected BlockPos[] getAdjacentPositions(BlockState state, BlockPos pos) {
        Direction facing = state.get(FACING);
        Direction left = facing.rotateYCounterclockwise();
        Direction right = facing.rotateYClockwise();
        Direction back = facing.getOpposite();
        Direction backLeft = back.rotateYClockwise();
        Direction backRight = back.rotateYCounterclockwise();

        return new BlockPos[]{
                pos.offset(facing),
                pos.offset(facing).offset(left), pos.offset(facing).offset(right),
                pos.offset(facing).offset(left, 2), pos.offset(facing).offset(right, 2),
                pos.offset(facing).offset(right, 3),
                pos.offset(facing, 2),
                pos.offset(facing, 2).offset(left), pos.offset(facing, 2).offset(right),
                pos.offset(facing, 2).offset(left, 2), pos.offset(facing, 2).offset(right, 2), pos.offset(facing, 2).offset(right, 3),
                pos.offset(right), pos.offset(left),
                pos.offset(right, 2), pos.offset(left, 2),
                pos.offset(back), pos.offset(back, 2),
                pos.offset(back).offset(backLeft), pos.offset(back).offset(backRight),
                pos.offset(back).offset(backLeft, 2), pos.offset(back).offset(backRight, 2), pos.offset(back).offset(backRight, 3),
                pos.offset(back, 2).offset(backLeft), pos.offset(back, 2).offset(backRight), pos.offset(back, 2).offset(backRight, 3),
                pos.offset(back, 2).offset(backLeft, 2), pos.offset(back, 2).offset(backRight, 2), pos.offset(back, 2).offset(backRight, 3),
                pos.offset(facing).offset(right, 3).up(2),
                pos.offset(facing).offset(left, 2).up(2),
                pos.offset(back).offset(right, 3).up(2),
                pos.offset(back).offset(left, 2).up(2)
        };
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("endfield.powerCost", powerCost).formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("endfield.require_area", areaLabel).formatted(Formatting.GRAY));
    }
}
