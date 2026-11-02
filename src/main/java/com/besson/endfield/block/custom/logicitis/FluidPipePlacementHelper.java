package com.besson.endfield.block.custom.logicitis;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FluidPipePlacementHelper {
    private final World world;
    private final BlockPos pos;
    private final FluidPipeBlock block;
    private BlockState state;
    private final boolean forbidCurves;
    private final List<BlockPos> neighbors = Lists.newArrayList();

    public FluidPipePlacementHelper(World world, BlockPos pos, BlockState state) {
        this.world = world;
        this.pos = pos;
        this.state = state;
        this.block = (FluidPipeBlock) state.getBlock();
        FluidPipeShape shape = state.get(this.block.getShapeProperty());
        this.forbidCurves = !this.block.isFlexiblePipe(state, world, pos);
        this.computeNeighbors(shape);
    }

    public List<BlockPos> getNeighbors() {
        return this.neighbors;
    }

    private void computeNeighbors(FluidPipeShape shape) {
        this.neighbors.clear();
        switch (shape) {
            case NORTH_SOUTH:
                this.neighbors.add(this.pos.north());
                this.neighbors.add(this.pos.south());
                break;
            case EAST_WEST:
                this.neighbors.add(this.pos.west());
                this.neighbors.add(this.pos.east());
                break;
            case ASCENDING_EAST:
                this.neighbors.add(this.pos.west());
                this.neighbors.add(this.pos.east().up());
                break;
            case ASCENDING_WEST:
                this.neighbors.add(this.pos.west().up());
                this.neighbors.add(this.pos.east());
                break;
            case ASCENDING_NORTH:
                this.neighbors.add(this.pos.north().up());
                this.neighbors.add(this.pos.south());
                break;
            case ASCENDING_SOUTH:
                this.neighbors.add(this.pos.north());
                this.neighbors.add(this.pos.south().up());
                break;
            case SOUTH_EAST:
                this.neighbors.add(this.pos.east());
                this.neighbors.add(this.pos.south());
                break;
            case SOUTH_WEST:
                this.neighbors.add(this.pos.west());
                this.neighbors.add(this.pos.south());
                break;
            case NORTH_WEST:
                this.neighbors.add(this.pos.west());
                this.neighbors.add(this.pos.north());
                break;
            case NORTH_EAST:
                this.neighbors.add(this.pos.east());
                this.neighbors.add(this.pos.north());
        }
    }

    private void updateNeighborPositions() {
        for (int i = 0; i < this.neighbors.size(); i++) {
            FluidPipePlacementHelper helper = this.getNeighboringPipe(this.neighbors.get(i));
            if (helper != null && helper.isNeighbor(this)) {
                this.neighbors.set(i, helper.pos);
            } else {
                this.neighbors.remove(i--);
            }
        }
    }

    private boolean isVerticallyNearPipe(BlockPos pos) {
        return FluidPipeBlock.isPipe(this.world, pos) || FluidPipeBlock.isPipe(this.world, pos.up()) || FluidPipeBlock.isPipe(this.world, pos.down());
    }

    @Nullable
    private FluidPipePlacementHelper getNeighboringPipe(BlockPos pos) {
        BlockState blockState = this.world.getBlockState(pos);
        if (FluidPipeBlock.isPipe(blockState)) {
            return new FluidPipePlacementHelper(this.world, pos, blockState);
        } else {
            BlockPos blockPos = pos.up();
            blockState = this.world.getBlockState(blockPos);
            if (FluidPipeBlock.isPipe(blockState)) {
                return new FluidPipePlacementHelper(this.world, blockPos, blockState);
            } else {
                blockPos = pos.down();
                blockState = this.world.getBlockState(blockPos);
                return FluidPipeBlock.isPipe(blockState) ? new FluidPipePlacementHelper(this.world, blockPos, blockState) : null;
            }
        }
    }

    private boolean isNeighbor(FluidPipePlacementHelper other) {
        return this.isNeighbor(other.pos);
    }

    private boolean isNeighbor(BlockPos pos) {
        for (int i = 0; i < this.neighbors.size(); i++) {
            BlockPos blockPos = this.neighbors.get(i);
            if (blockPos.getX() == pos.getX() && blockPos.getZ() == pos.getZ()) {
                return true;
            }
        }
        return false;
    }

    protected int getNeighborCount() {
        int i = 0;
        for (Direction direction : Direction.Type.HORIZONTAL) {
            if (this.isVerticallyNearPipe(this.pos.offset(direction))) {
                i++;
            }
        }
        return i;
    }

    private boolean canConnect(FluidPipePlacementHelper helper) {
        return this.isNeighbor(helper) || this.neighbors.size() != 2;
    }

    private void computePipeShape(FluidPipePlacementHelper helper) {
        this.neighbors.add(helper.pos);
        BlockPos blockPos = this.pos.north();
        BlockPos blockPos2 = this.pos.south();
        BlockPos blockPos3 = this.pos.west();
        BlockPos blockPos4 = this.pos.east();
        boolean bl = this.isNeighbor(blockPos);
        boolean bl2 = this.isNeighbor(blockPos2);
        boolean bl3 = this.isNeighbor(blockPos3);
        boolean bl4 = this.isNeighbor(blockPos4);
        FluidPipeShape pipeShape = null;
        if (bl || bl2) {
            pipeShape = FluidPipeShape.NORTH_SOUTH;
        }

        if (bl3 || bl4) {
            pipeShape = FluidPipeShape.EAST_WEST;
        }

        if (!this.forbidCurves) {
            if (bl2 && bl4 && !bl && !bl3) {
                pipeShape = FluidPipeShape.SOUTH_EAST;
            }

            if (bl2 && bl3 && !bl && !bl4) {
                pipeShape = FluidPipeShape.SOUTH_WEST;
            }

            if (bl && bl3 && !bl2 && !bl4) {
                pipeShape = FluidPipeShape.NORTH_WEST;
            }

            if (bl && bl4 && !bl2 && !bl3) {
                pipeShape = FluidPipeShape.NORTH_EAST;
            }
        }

        if (pipeShape == FluidPipeShape.NORTH_SOUTH) {
            if (FluidPipeBlock.isPipe(this.world, blockPos.up())) {
                pipeShape = FluidPipeShape.ASCENDING_NORTH;
            }

            if (FluidPipeBlock.isPipe(this.world, blockPos2.up())) {
                pipeShape = FluidPipeShape.ASCENDING_SOUTH;
            }
        }

        if (pipeShape == FluidPipeShape.EAST_WEST) {
            if (FluidPipeBlock.isPipe(this.world, blockPos4.up())) {
                pipeShape = FluidPipeShape.ASCENDING_EAST;
            }

            if (FluidPipeBlock.isPipe(this.world, blockPos3.up())) {
                pipeShape = FluidPipeShape.ASCENDING_WEST;
            }
        }

        if (pipeShape == null) {
            pipeShape = FluidPipeShape.NORTH_SOUTH;
        }

        this.state = this.state.with(this.block.getShapeProperty(), pipeShape);
        this.world.setBlockState(this.pos, this.state, Block.NOTIFY_ALL);
    }

    private boolean canConnect(BlockPos pos) {
        FluidPipePlacementHelper helper = this.getNeighboringPipe(pos);
        if (helper == null) {
            return false;
        } else {
            helper.updateNeighborPositions();
            return helper.canConnect(this);
        }
    }

    public FluidPipePlacementHelper updateBlockState(boolean powered, boolean forceUpdate, FluidPipeShape pipeShape) {
        BlockPos blockPos = this.pos.north();
        BlockPos blockPos2 = this.pos.south();
        BlockPos blockPos3 = this.pos.west();
        BlockPos blockPos4 = this.pos.east();
        boolean bl = this.canConnect(blockPos);
        boolean bl2 = this.canConnect(blockPos2);
        boolean bl3 = this.canConnect(blockPos3);
        boolean bl4 = this.canConnect(blockPos4);
        FluidPipeShape pipeShape2 = null;
        boolean bl5 = bl || bl2;
        boolean bl6 = bl3 || bl4;
        if (bl5 && !bl6) {
            pipeShape2 = FluidPipeShape.NORTH_SOUTH;
        }

        if (bl6 && !bl5) {
            pipeShape2 = FluidPipeShape.EAST_WEST;
        }

        boolean bl7 = bl2 && bl4;
        boolean bl8 = bl2 && bl3;
        boolean bl9 = bl && bl4;
        boolean bl10 = bl && bl3;
        if (!this.forbidCurves) {
            if (bl7 && !bl && !bl3) {
                pipeShape2 = FluidPipeShape.SOUTH_EAST;
            }

            if (bl8 && !bl && !bl4) {
                pipeShape2 = FluidPipeShape.SOUTH_WEST;
            }

            if (bl10 && !bl2 && !bl4) {
                pipeShape2 = FluidPipeShape.NORTH_WEST;
            }

            if (bl9 && !bl2 && !bl3) {
                pipeShape2 = FluidPipeShape.NORTH_EAST;
            }
        }

        if (pipeShape2 == null) {
            if (bl5 && bl6) {
                pipeShape2 = pipeShape;
            } else if (bl5) {
                pipeShape2 = FluidPipeShape.NORTH_SOUTH;
            } else if (bl6) {
                pipeShape2 = FluidPipeShape.EAST_WEST;
            }

            if (!this.forbidCurves) {
                if (powered) {
                    if (bl7) {
                        pipeShape2 = FluidPipeShape.SOUTH_EAST;
                    }
                    if (bl8) {
                        pipeShape2 = FluidPipeShape.SOUTH_WEST;
                    }
                    if (bl9) {
                        pipeShape2 = FluidPipeShape.NORTH_EAST;
                    }
                    if (bl10) {
                        pipeShape2 = FluidPipeShape.NORTH_WEST;
                    }
                } else {
                    if (bl10) {
                        pipeShape2 = FluidPipeShape.NORTH_WEST;
                    }
                    if (bl9) {
                        pipeShape2 = FluidPipeShape.NORTH_EAST;
                    }
                    if (bl8) {
                        pipeShape2 = FluidPipeShape.SOUTH_WEST;
                    }
                    if (bl7) {
                        pipeShape2 = FluidPipeShape.SOUTH_EAST;
                    }
                }
            }
        }

        if (pipeShape2 == FluidPipeShape.NORTH_SOUTH) {
            if (FluidPipeBlock.isPipe(this.world, blockPos.up())) {
                pipeShape2 = FluidPipeShape.ASCENDING_NORTH;
            }
            if (FluidPipeBlock.isPipe(this.world, blockPos2.up())) {
                pipeShape2 = FluidPipeShape.ASCENDING_SOUTH;
            }
        }

        if (pipeShape2 == FluidPipeShape.EAST_WEST) {
            if (FluidPipeBlock.isPipe(this.world, blockPos4.up())) {
                pipeShape2 = FluidPipeShape.ASCENDING_EAST;
            }
            if (FluidPipeBlock.isPipe(this.world, blockPos3.up())) {
                pipeShape2 = FluidPipeShape.ASCENDING_WEST;
            }
        }

        if (pipeShape2 == null) {
            pipeShape2 = pipeShape;
        }

        this.computeNeighbors(pipeShape2);
        this.state = this.state.with(this.block.getShapeProperty(), pipeShape2);
        if (forceUpdate || this.world.getBlockState(this.pos) != this.state) {
            this.world.setBlockState(this.pos, this.state, Block.NOTIFY_ALL);

            for (int i = 0; i < this.neighbors.size(); i++) {
                FluidPipePlacementHelper helper = this.getNeighboringPipe(this.neighbors.get(i));
                if (helper != null) {
                    helper.updateNeighborPositions();
                    if (helper.canConnect(this)) {
                        helper.computePipeShape(this);
                    }
                }
            }
        }

        return this;
    }

    public BlockState getBlockState() {
        return this.state;
    }
}
