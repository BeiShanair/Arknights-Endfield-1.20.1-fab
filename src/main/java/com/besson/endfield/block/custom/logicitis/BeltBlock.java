package com.besson.endfield.block.custom.logicitis;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.logicitis.BeltBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Formatting;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BeltBlock extends BlockWithEntity {
    public static final EnumProperty<BeltShape> SHAPE = EnumProperty.of("belt_shape", BeltShape.class);
    protected static final VoxelShape STRAIGHT_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
    protected static final VoxelShape ASCENDING_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    private boolean isStraight = false;
    public BeltBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(SHAPE, BeltShape.NORTH_SOUTH));
    }
    
    public static boolean isBelt(World world, BlockPos pos) {
        return isBelt(world.getBlockState(pos));
    }

    public static boolean isBelt(BlockState state) {
        return state.getBlock() instanceof BeltBlock;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        BeltShape beltShape = state.isOf(this) ? state.get(this.getShapeProperty()) : null;
        return beltShape != null && beltShape.isAscending() ? ASCENDING_SHAPE : STRAIGHT_SHAPE;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BeltBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.BELT, BeltBlockEntity::tick);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public Property<BeltShape> getShapeProperty() {
        return SHAPE;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        switch (rotation) {
            case CLOCKWISE_180:
                switch ((BeltShape)state.get(SHAPE)) {
                    case ASCENDING_EAST:
                        return state.with(SHAPE, BeltShape.ASCENDING_WEST);
                    case ASCENDING_WEST:
                        return state.with(SHAPE, BeltShape.ASCENDING_EAST);
                    case ASCENDING_NORTH:
                        return state.with(SHAPE, BeltShape.ASCENDING_SOUTH);
                    case ASCENDING_SOUTH:
                        return state.with(SHAPE, BeltShape.ASCENDING_NORTH);
                    case SOUTH_EAST:
                        return state.with(SHAPE, BeltShape.NORTH_WEST);
                    case SOUTH_WEST:
                        return state.with(SHAPE, BeltShape.NORTH_EAST);
                    case NORTH_WEST:
                        return state.with(SHAPE, BeltShape.SOUTH_EAST);
                    case NORTH_EAST:
                        return state.with(SHAPE, BeltShape.SOUTH_WEST);
                }
            case COUNTERCLOCKWISE_90:
                switch ((BeltShape)state.get(SHAPE)) {
                    case ASCENDING_EAST:
                        return state.with(SHAPE, BeltShape.ASCENDING_NORTH);
                    case ASCENDING_WEST:
                        return state.with(SHAPE, BeltShape.ASCENDING_SOUTH);
                    case ASCENDING_NORTH:
                        return state.with(SHAPE, BeltShape.ASCENDING_WEST);
                    case ASCENDING_SOUTH:
                        return state.with(SHAPE, BeltShape.ASCENDING_EAST);
                    case SOUTH_EAST:
                        return state.with(SHAPE, BeltShape.NORTH_EAST);
                    case SOUTH_WEST:
                        return state.with(SHAPE, BeltShape.SOUTH_EAST);
                    case NORTH_WEST:
                        return state.with(SHAPE, BeltShape.SOUTH_WEST);
                    case NORTH_EAST:
                        return state.with(SHAPE, BeltShape.NORTH_WEST);
                    case NORTH_SOUTH:
                        return state.with(SHAPE, BeltShape.EAST_WEST);
                    case EAST_WEST:
                        return state.with(SHAPE, BeltShape.NORTH_SOUTH);
                }
            case CLOCKWISE_90:
                switch ((BeltShape)state.get(SHAPE)) {
                    case ASCENDING_EAST:
                        return state.with(SHAPE, BeltShape.ASCENDING_SOUTH);
                    case ASCENDING_WEST:
                        return state.with(SHAPE, BeltShape.ASCENDING_NORTH);
                    case ASCENDING_NORTH:
                        return state.with(SHAPE, BeltShape.ASCENDING_EAST);
                    case ASCENDING_SOUTH:
                        return state.with(SHAPE, BeltShape.ASCENDING_WEST);
                    case SOUTH_EAST:
                        return state.with(SHAPE, BeltShape.SOUTH_WEST);
                    case SOUTH_WEST:
                        return state.with(SHAPE, BeltShape.NORTH_WEST);
                    case NORTH_WEST:
                        return state.with(SHAPE, BeltShape.NORTH_EAST);
                    case NORTH_EAST:
                        return state.with(SHAPE, BeltShape.SOUTH_EAST);
                    case NORTH_SOUTH:
                        return state.with(SHAPE, BeltShape.EAST_WEST);
                    case EAST_WEST:
                        return state.with(SHAPE, BeltShape.NORTH_SOUTH);
                }
            default:
                return state;
        }
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        BeltShape beltShape = state.get(SHAPE);
        switch (mirror) {
            case LEFT_RIGHT:
                switch (beltShape) {
                    case ASCENDING_NORTH:
                        return state.with(SHAPE, BeltShape.ASCENDING_SOUTH);
                    case ASCENDING_SOUTH:
                        return state.with(SHAPE, BeltShape.ASCENDING_NORTH);
                    case SOUTH_EAST:
                        return state.with(SHAPE, BeltShape.NORTH_EAST);
                    case SOUTH_WEST:
                        return state.with(SHAPE, BeltShape.NORTH_WEST);
                    case NORTH_WEST:
                        return state.with(SHAPE, BeltShape.SOUTH_WEST);
                    case NORTH_EAST:
                        return state.with(SHAPE, BeltShape.SOUTH_EAST);
                    default:
                        return super.mirror(state, mirror);
                }
            case FRONT_BACK:
                switch (beltShape) {
                    case ASCENDING_EAST:
                        return state.with(SHAPE, BeltShape.ASCENDING_WEST);
                    case ASCENDING_WEST:
                        return state.with(SHAPE, BeltShape.ASCENDING_EAST);
                    case ASCENDING_NORTH:
                    case ASCENDING_SOUTH:
                    default:
                        break;
                    case SOUTH_EAST:
                        return state.with(SHAPE, BeltShape.SOUTH_WEST);
                    case SOUTH_WEST:
                        return state.with(SHAPE, BeltShape.SOUTH_EAST);
                    case NORTH_WEST:
                        return state.with(SHAPE, BeltShape.NORTH_EAST);
                    case NORTH_EAST:
                        return state.with(SHAPE, BeltShape.NORTH_WEST);
                }
        }

        return super.mirror(state, mirror);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SHAPE);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (!oldState.isOf(state.getBlock())) {
            this.updateCurves(state, world, pos, notify);
        }
        this.isStraight = false;
    }
    
    protected BlockState updateCurves(BlockState state, World world, BlockPos pos, boolean notify) {
        state = this.updateBlockState(world, pos, state, true);
        if (this.isStraight) {
            world.updateNeighbor(state, pos, this, pos, notify);
        }
        return state;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient && world.getBlockState(pos).isOf(this)) {
            this.updateBlockState(state, world, pos, sourceBlock);
        }
    }
    
    protected void updateBlockState(BlockState state, World world, BlockPos pos, Block neighbor) {
        if (neighbor.getDefaultState().emitsRedstonePower() && new BeltPlacementHelper(world, pos, state).getNeighborCount() == 3) {
            this.updateBlockState(world, pos, state, false);
        }
    }

    protected BlockState updateBlockState(World world, BlockPos pos, BlockState state, boolean forceUpdate) {
        if (world.isClient) {
            return state;
        } else {
            BeltShape beltShape = state.get(this.getShapeProperty());
            return new BeltPlacementHelper(world, pos, state).updateBlockState(world.isReceivingRedstonePower(pos), forceUpdate, beltShape).getBlockState();
        }
    }
    
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity b = world.getBlockEntity(pos);
            if (b instanceof BeltBlockEntity belt) {
                ItemScatterer.spawn(world, pos, belt.getItem());
                world.updateComparators(pos, this);
            }
        }
        if (!moved) {
            if (state.get(getShapeProperty()).isAscending()) {
                world.updateComparators(pos.up(), this);
            }

            if (this.isStraight) {
                world.updateComparators(pos, this);
                world.updateComparators(pos.down(), this);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }
    
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        this.isStraight = ctx.getPlayer() != null && ctx.getPlayer().isSneaking();
        BlockState blockState = super.getDefaultState();
        Direction direction = ctx.getHorizontalPlayerFacing();
        boolean bl2 = direction == Direction.EAST || direction == Direction.WEST;
        return blockState.with(this.getShapeProperty(), bl2 ? BeltShape.EAST_WEST : BeltShape.NORTH_SOUTH);
    }

    public static Direction[] getConnections(BeltShape shape) {
        return switch (shape) {

            case NORTH_SOUTH -> new Direction[]{Direction.NORTH, Direction.SOUTH};
            case EAST_WEST -> new Direction[]{Direction.EAST, Direction.WEST};

            case SOUTH_EAST -> new Direction[]{Direction.SOUTH, Direction.EAST};
            case SOUTH_WEST -> new Direction[]{Direction.SOUTH, Direction.WEST};
            case NORTH_WEST -> new Direction[]{Direction.NORTH, Direction.WEST};
            case NORTH_EAST -> new Direction[]{Direction.NORTH, Direction.EAST};

            case ASCENDING_EAST -> new Direction[]{Direction.WEST, Direction.EAST};
            case ASCENDING_WEST -> new Direction[]{Direction.EAST, Direction.WEST};
            case ASCENDING_NORTH -> new Direction[]{Direction.SOUTH, Direction.NORTH};
            case ASCENDING_SOUTH -> new Direction[]{Direction.NORTH, Direction.SOUTH};
        };
    }
    public static Direction getNextDirection(BeltShape shape, Direction incoming) {
        Direction[] connections = getConnections(shape);

        if (incoming == connections[0]) {
            return connections[1];
        }

        if (incoming == connections[1]) {
            return connections[0];
        }

        return null; // 不匹配说明不是合法输入
    }
    public static boolean isAscendingTowards(BeltShape shape, Direction direction) {
        return switch (shape) {
            case ASCENDING_EAST -> direction == Direction.EAST;
            case ASCENDING_WEST -> direction == Direction.WEST;
            case ASCENDING_NORTH -> direction == Direction.NORTH;
            case ASCENDING_SOUTH -> direction == Direction.SOUTH;
            default -> false;
        };
    }
    public static BlockPos getNextPos(BlockPos pos, BeltShape shape, Direction direction) {

        BlockPos next = pos.offset(direction);

        if (isAscendingTowards(shape, direction)) {
            next = next.up();
        } else if (isAscendingTowards(shape, direction.getOpposite())) {
            return next;
        }

        return next;
    }

    public boolean isFlexibleRail(BlockState state, World world, BlockPos pos) {
        return !this.isStraight;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("belt.tooltip").formatted(Formatting.GRAY));
    }
}
