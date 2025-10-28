package com.besson.endfield.block.custom;

import com.besson.endfield.blockentity.ModBlockEntities;
import com.besson.endfield.blockentity.custom.FluidPumpBlockEntity;
import com.besson.endfield.blockentity.custom.PipeBlockEntity;
import com.besson.endfield.pipe.PipeNetworkManager;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class PipeBlock extends BlockWithEntity {
    public static final BooleanProperty NORTH = BooleanProperty.of("north");
    public static final BooleanProperty SOUTH = BooleanProperty.of("south");
    public static final BooleanProperty EAST = BooleanProperty.of("east");
    public static final BooleanProperty WEST = BooleanProperty.of("west");
    public static final BooleanProperty UP = BooleanProperty.of("up");
    public static final BooleanProperty DOWN = BooleanProperty.of("down");
    public static final BooleanProperty HAS_NODE = BooleanProperty.of("has_node");

    public static final VoxelShape SHAPE_NODE = Block.createCuboidShape(5, 5, 5, 11, 11, 11);
    public static final VoxelShape SHAPE_NODE_N = Block.createCuboidShape(5, 5, 0, 11, 11, 5);
    public static final VoxelShape SHAPE_NODE_S = Block.createCuboidShape(5, 5, 11, 11, 11, 16);
    public static final VoxelShape SHAPE_NODE_E = Block.createCuboidShape(11, 5, 5, 16, 11, 11);
    public static final VoxelShape SHAPE_NODE_W = Block.createCuboidShape(0, 5, 5, 5, 11, 11);
    public static final VoxelShape SHAPE_NODE_U = Block.createCuboidShape(5, 11, 5, 11, 16, 11);
    public static final VoxelShape SHAPE_NODE_D = Block.createCuboidShape(5, 0, 5, 11, 5, 11);

    public PipeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(NORTH, false)
                .with(SOUTH, false)
                .with(EAST, false)
                .with(WEST, false)
                .with(UP, false)
                .with(DOWN, false)
                .with(HAS_NODE, true));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = SHAPE_NODE;

        if (state.get(NORTH)) {
            shape = VoxelShapes.union(shape, SHAPE_NODE_N);
        }
        if (state.get(SOUTH)) {
            shape = VoxelShapes.union(shape, SHAPE_NODE_S);
        }
        if (state.get(EAST)) {
            shape = VoxelShapes.union(shape, SHAPE_NODE_E);
        }
        if (state.get(WEST)) {
            shape = VoxelShapes.union(shape, SHAPE_NODE_W);
        }
        if (state.get(UP)) {
            shape = VoxelShapes.union(shape, SHAPE_NODE_U);
        }
        if (state.get(DOWN)) {
            shape = VoxelShapes.union(shape, SHAPE_NODE_D);
        }

        return shape;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, SOUTH, EAST, WEST, UP, DOWN, HAS_NODE);
    }

    private boolean canConnect(WorldAccess world, BlockPos pos) {
        BlockState neighborState = world.getBlockState(pos);
        return neighborState.getBlock() instanceof PipeBlock ||
                neighborState.getBlock() instanceof FluidPumpBlock;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        int connected = 0;
        Direction firstDir = null;
        Direction secondDir = null;
        for (Direction dir : Direction.values()) {
            if (canConnect(world, pos.offset(dir))) {
                connected++;
                if (firstDir == null) {
                    firstDir = dir;
                } else {
                    secondDir = dir;
                }
            }
        }

        if (connected == 2 && firstDir != null && secondDir != null) {
            // 只有两端且互为相反方向时为直线，否则为转角
            if (firstDir.getOpposite() == secondDir) {
                state = state.with(HAS_NODE, false);
            } else {
                state = state.with(HAS_NODE, true);
            }
        } else {
            state = state.with(HAS_NODE, true);
        }

        if (canConnect(world, neighborPos)) {
            state = state.with(getPropertyForDirection(direction), true);
        } else {
            state = state.with(getPropertyForDirection(direction), false);
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldAccess world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState state = this.getDefaultState();

        int connected = 0;
        Direction firstDir = null;
        Direction secondDir = null;
        for (Direction dir : Direction.values()) {
            if (canConnect(world, pos.offset(dir))) {
                state = state.with(getPropertyForDirection(dir), true);
                connected++;
                if (firstDir == null) {
                    firstDir = dir;
                } else if (secondDir == null) {
                    secondDir = dir;
                }
            }
        }

        if (connected == 2 && firstDir != null && secondDir != null) {
            if (firstDir.getOpposite() == secondDir) {
                state = state.with(HAS_NODE, false); // 直线段不显示节点
            } else {
                state = state.with(HAS_NODE, true); // 转角显示节点
            }
        } else {
            state = state.with(HAS_NODE, true); // 其他情况都显示节点
        }

        return state;
    }

    private Property<Boolean> getPropertyForDirection(Direction dir) {
        return switch (dir) {
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case EAST -> EAST;
            case WEST -> WEST;
            case UP -> UP;
            case DOWN -> DOWN;
        };
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PipeBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntities.PIPE, (world1, pos, state1, blockEntity) ->
                PipeBlockEntity.tick(world1, pos, state1, (PipeBlockEntity) blockEntity));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient()) {
            PipeNetworkManager manager = PipeNetworkManager.getInstance((ServerWorld) world);
            manager.onPipeAdded(world, pos);
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!world.isClient() && state.getBlock() != newState.getBlock()) {
            PipeNetworkManager manager = PipeNetworkManager.getInstance((ServerWorld) world);
            manager.onPipeRemoved(world, pos);
        }

        super.onStateReplaced(state, world, pos, newState, moved);
    }
}
