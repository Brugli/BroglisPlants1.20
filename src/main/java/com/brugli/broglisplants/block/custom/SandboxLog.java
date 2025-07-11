package com.brugli.broglisplants.block.custom;

import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraftforge.common.ToolAction;

import javax.annotation.Nullable;
import java.util.function.BiConsumer;

public class SandboxLog extends FlammableRotatedPillarBlock {

    public SandboxLog(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
        if (pLevel.getBlockState(pPos.north(-1)).is(Blocks.AIR) || pLevel.getBlockState(pPos.north(-1)).canBeReplaced()) {
            pLevel.setBlock(pPos.north(-1), BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.SOUTH), 2);
        }
        if (pLevel.getBlockState(pPos.north(1)).is(Blocks.AIR) || pLevel.getBlockState(pPos.north(1)).canBeReplaced()) {
            pLevel.setBlock(pPos.north(1), BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.NORTH), 2);
        }
        if (pLevel.getBlockState(pPos.east(-1)).is(Blocks.AIR) || pLevel.getBlockState(pPos.east(-1)).canBeReplaced()) {
            pLevel.setBlock(pPos.east(-1), BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.WEST), 2);
        }
        if (pLevel.getBlockState(pPos.east(1)).is(Blocks.AIR) || pLevel.getBlockState(pPos.east(1)).canBeReplaced()) {
            pLevel.setBlock(pPos.east(1), BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.EAST), 2);
        }
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if(context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(BroglisPlantsBlocks.SANDBOX_LOG.get()) || state.is(BroglisPlantsBlocks.SANDBOX_WOOD.get())) {
                return BroglisPlantsBlocks.TOTEM.get().defaultBlockState().setValue(Totem.STYLE, RandomSource.create().nextInt(1,4)).setValue(AXIS, state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
