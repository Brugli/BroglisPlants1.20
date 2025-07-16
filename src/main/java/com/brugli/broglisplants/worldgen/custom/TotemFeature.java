package com.brugli.broglisplants.worldgen.custom;

import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import com.brugli.broglisplants.block.custom.Thorn;
import com.brugli.broglisplants.block.custom.Totem;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class TotemFeature extends Feature<NoneFeatureConfiguration> {
    public TotemFeature(Codec<NoneFeatureConfiguration> p_66219_) {
        super(p_66219_);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> p_159956_) {
        BlockPos blockpos = p_159956_.origin();
        BlockPos blockPos1 = blockpos.above();
        BlockState blockState = p_159956_.level().getBlockState(blockPos1);
        RandomSource randomsource = p_159956_.random();

        WorldGenLevel worldgenlevel;
        for(worldgenlevel = p_159956_.level(); worldgenlevel.isEmptyBlock(blockpos) && blockpos.getY() > worldgenlevel.getMinBuildHeight() + 2; blockpos = blockpos.below()) {
        }

        if (!worldgenlevel.getBlockState(blockpos).is(Blocks.GRASS_BLOCK)) {
            return false;
        } else {
            int i = randomsource.nextInt(3, 4);

            for(int k = 0; k <= i; ++k) {
                if (blockState.isAir() || blockState.canBeReplaced()) {
                    if (k == i) {
                        worldgenlevel.setBlock(blockpos, BroglisPlantsBlocks.TOTEM.get().defaultBlockState().setValue(Totem.STYLE, Integer.valueOf(randomsource.nextInt(1, 5))), 2);
                    } else {
                        worldgenlevel.setBlock(blockpos, BroglisPlantsBlocks.SANDBOX_LOG.get().defaultBlockState(), 2);
                        if (worldgenlevel.isStateAtPosition(blockpos.relative(Direction.NORTH, 1), BlockBehaviour.BlockStateBase::isAir)) {
                            worldgenlevel.setBlock(blockpos.relative(Direction.NORTH, 1), BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.NORTH), 2);
                        }
                        if (worldgenlevel.isStateAtPosition(blockpos.relative(Direction.NORTH, -1), BlockBehaviour.BlockStateBase::isAir)) {
                            worldgenlevel.setBlock(blockpos.relative(Direction.NORTH, -1), BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.SOUTH), 2);
                        }
                        if (worldgenlevel.isStateAtPosition(blockpos.relative(Direction.EAST, 1), BlockBehaviour.BlockStateBase::isAir)) {
                            worldgenlevel.setBlock(blockpos.relative(Direction.EAST, 1), BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.EAST), 2);
                        }
                        if (worldgenlevel.isStateAtPosition(blockpos.relative(Direction.EAST, -1), BlockBehaviour.BlockStateBase::isAir)) {
                            worldgenlevel.setBlock(blockpos.relative(Direction.EAST, -1), BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.WEST), 2);
                        }
                    }
                }
                blockpos = blockpos.above();
            }
            return true;
        }
    }
}
