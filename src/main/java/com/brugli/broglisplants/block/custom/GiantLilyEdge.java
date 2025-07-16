package com.brugli.broglisplants.block.custom;

import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import com.brugli.broglisplants.item.BroglisPlantsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class GiantLilyEdge extends HorizontalDirectionalBlock implements LiquidBlockContainer {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    protected static final VoxelShape NORTH_SHAPE = Block.box(0.0D, 14.0D, 2.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape EAST_SHAPE = Block.box(0.0D, 14.0D, 0.0D, 14.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_SHAPE = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 14.0D);
    protected static final VoxelShape WEST_SHAPE = Block.box(2.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    protected static final VoxelShape NORTH_SUPPORT_SHAPE = Block.box(0.0D, 14.0D, 2.0D, 16.0D, 15.0D, 16.0D);
    protected static final VoxelShape EAST_SUPPORT_SHAPE = Block.box(0.0D, 14.0D, 0.0D, 14.0D, 15.0D, 16.0D);
    protected static final VoxelShape SOUTH_SUPPORT_SHAPE = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 15.0D, 14.0D);
    protected static final VoxelShape WEST_SUPPORT_SHAPE = Block.box(2.0D, 14.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public GiantLilyEdge(Properties pProperties) {
        super(pProperties);
    }

    public VoxelShape getCollisionShape(BlockState pState, BlockGetter p_221562_, BlockPos p_221563_, CollisionContext p_221564_) {
        Direction direction = pState.getValue(FACING);
        switch (direction) {
            case NORTH:
                return NORTH_SUPPORT_SHAPE;
            case SOUTH:
                return SOUTH_SUPPORT_SHAPE;
            case WEST:
                return WEST_SUPPORT_SHAPE;
            default:
                return EAST_SUPPORT_SHAPE;
        }
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch (direction) {
            case NORTH:
                return NORTH_SHAPE;
            case SOUTH:
                return SOUTH_SHAPE;
            case WEST:
                return WEST_SHAPE;
            default:
                return EAST_SHAPE;
        }
    }

    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        return pDirection == pState.getValue(FACING).getOpposite() && !pState.canSurvive(pLevel, pPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.relative(pState.getValue(FACING), -1);
        return pLevel.getBlockState(blockpos).is(BroglisPlantsBlocks.GIANT_LILY.get());
    }

    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return false;
    }

    @Override
    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        return false;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8 ? super.getStateForPlacement(pContext) : null;
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pLevel.getBlockState(pPos.relative(pState.getValue(FACING), -1)).is(BroglisPlantsBlocks.GIANT_LILY.get())) {
            pLevel.destroyBlock(pPos.relative(pState.getValue(FACING), -1), true);
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    public FluidState getFluidState(BlockState pState) {
        return Fluids.WATER.getSource(false);
    }

    @Override
    public Item asItem() {
        return BroglisPlantsItems.GIANT_LILY_ITEM.get();
    }
}
