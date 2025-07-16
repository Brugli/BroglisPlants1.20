package com.brugli.broglisplants.block.custom;

import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import com.brugli.broglisplants.item.BroglisPlantsItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.LiquidBlockContainer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class GiantLily extends GrowingPlantHeadBlock implements LiquidBlockContainer {

    public static final BooleanProperty IS_LARGE = BlockStateProperties.CONDITIONAL;

    protected static final VoxelShape SMALL_TOP = Block.box(2.0D, 14.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape TOP = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape STEM = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 14.0D, 11.0D);

    protected static final VoxelShape SMALL_SHAPE = Shapes.or(SMALL_TOP, STEM);
    protected static final VoxelShape SHAPE = Shapes.or(TOP, STEM);

    protected static final VoxelShape SMALL_TOP_SUPPORT = Block.box(2.0D, 14.0D, 2.0D, 14.0D, 15.0D, 14.0D);
    protected static final VoxelShape TOP_SUPPORT = Block.box(0.0D, 14.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    public GiantLily(Properties pProperties) {
        super(pProperties, Direction.UP, SHAPE, true, 0.14D);
        this.registerDefaultState(this.stateDefinition.any().setValue(IS_LARGE, Boolean.FALSE).setValue(AGE, 0));
    }

    public boolean isRandomlyTicking(BlockState pState) {
        return !pState.getValue(IS_LARGE);
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        pLevel.scheduleTick(pPos, this, 0);
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pLevel.getBlockState(pPos.above()).is(Blocks.AIR)) {
            if ((pLevel.getBlockState(pPos.north(-1)).is(Blocks.WATER) || pLevel.getBlockState(pPos.north(-1)).canBeReplaced())
                    && (pLevel.getBlockState(pPos.north(1)).is(Blocks.WATER)  || pLevel.getBlockState(pPos.north(1)).canBeReplaced())
                    && (pLevel.getBlockState(pPos.east(-1)).is(Blocks.WATER) || pLevel.getBlockState(pPos.east(-1)).canBeReplaced())
                    && (pLevel.getBlockState(pPos.east(1)).is(Blocks.WATER) || pLevel.getBlockState(pPos.east(1)).canBeReplaced())
                    && (pLevel.getBlockState(pPos.offset(1,0,1)).is(Blocks.WATER) || pLevel.getBlockState(pPos.offset(1,0,1)).canBeReplaced())
                    && (pLevel.getBlockState(pPos.offset(-1,0,-1)).is(Blocks.WATER) || pLevel.getBlockState(pPos.offset(-1,0,-1)).canBeReplaced())
                    && (pLevel.getBlockState(pPos.offset(-1,0,1)).is(Blocks.WATER) || pLevel.getBlockState(pPos.offset(-1,0,1)).canBeReplaced())
                    && (pLevel.getBlockState(pPos.offset(1,0,-1)).is(Blocks.WATER) || pLevel.getBlockState(pPos.offset(1,0,-1)).canBeReplaced())) {
                pLevel.setBlock(pPos, BroglisPlantsBlocks.GIANT_LILY.get().defaultBlockState().setValue(IS_LARGE, Boolean.TRUE), 2);
            }
        }
        if (!pState.canSurvive(pLevel, pPos)) {
            pLevel.destroyBlock(pPos, false);
        }
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.relative(this.growthDirection.getOpposite());
        BlockState blockstate = pLevel.getBlockState(blockpos);
        if (!this.canAttachTo(blockstate)) {
            return false;
        } else {
            return blockstate.is(this.getHeadBlock()) || blockstate.is(this.getBodyBlock()) || blockstate.isFaceSturdy(pLevel, blockpos, this.growthDirection);
        }
    }

    public VoxelShape getCollisionShape(BlockState pState, BlockGetter p_221562_, BlockPos p_221563_, CollisionContext p_221564_) {
        if (pState.getValue(IS_LARGE)) {
            return TOP_SUPPORT;
        }
        else return SMALL_TOP_SUPPORT;
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if (pState.getValue(IS_LARGE)) {
            return SHAPE;
        }
        else return SMALL_SHAPE;
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
        pLevel.scheduleTick(pPos, this, 0);
        if (pState.getValue(IS_LARGE)){
            pLevel.setBlock(pPos.north(-1), BroglisPlantsBlocks.GIANT_LILY_EDGE.get().defaultBlockState().setValue(GiantLilyCorner.FACING, Direction.SOUTH), 2);
            pLevel.setBlock(pPos.north(1), BroglisPlantsBlocks.GIANT_LILY_EDGE.get().defaultBlockState().setValue(GiantLilyCorner.FACING, Direction.NORTH), 2);
            pLevel.setBlock(pPos.east(-1), BroglisPlantsBlocks.GIANT_LILY_EDGE.get().defaultBlockState().setValue(GiantLilyCorner.FACING, Direction.WEST), 2);
            pLevel.setBlock(pPos.east(1), BroglisPlantsBlocks.GIANT_LILY_EDGE.get().defaultBlockState().setValue(GiantLilyCorner.FACING, Direction.EAST), 2);
            pLevel.setBlock(pPos.offset(1, 0, 1), BroglisPlantsBlocks.GIANT_LILY_CORNER.get().defaultBlockState().setValue(GiantLilyCorner.FACING, Direction.SOUTH), 2);
            pLevel.setBlock(pPos.offset(-1, 0, -1), BroglisPlantsBlocks.GIANT_LILY_CORNER.get().defaultBlockState().setValue(GiantLilyCorner.FACING, Direction.NORTH), 2);
            pLevel.setBlock(pPos.offset(-1, 0, 1), BroglisPlantsBlocks.GIANT_LILY_CORNER.get().defaultBlockState().setValue(GiantLilyCorner.FACING, Direction.WEST), 2);
            pLevel.setBlock(pPos.offset(1, 0, -1), BroglisPlantsBlocks.GIANT_LILY_CORNER.get().defaultBlockState().setValue(GiantLilyCorner.FACING, Direction.EAST), 2);
        }
    }

    protected boolean canGrowInto(BlockState pState) {
        return pState.is(Blocks.WATER);
    }

    protected Block getBodyBlock() {
        return BroglisPlantsBlocks.STEM.get();
    }

    public boolean canAttachTo(BlockState pState) {
        return !pState.is(Blocks.MAGMA_BLOCK);
    }

    public boolean canPlaceLiquid(BlockGetter pLevel, BlockPos pPos, BlockState pState, Fluid pFluid) {
        return false;
    }

    public boolean placeLiquid(LevelAccessor pLevel, BlockPos pPos, BlockState pState, FluidState pFluidState) {
        return false;
    }

    protected int getBlocksToGrowWhenBonemealed(RandomSource pRandom) {
        return 1;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        return fluidstate.is(FluidTags.WATER) && fluidstate.getAmount() == 8 ? super.getStateForPlacement(pContext) : null;
    }

    public FluidState getFluidState(BlockState pState) {
        return Fluids.WATER.getSource(false);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(IS_LARGE, AGE);
    }

    @Override
    public Item asItem() {
        return BroglisPlantsItems.GIANT_LILY_ITEM.get();
    }
}
