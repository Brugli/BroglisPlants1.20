package com.brugli.broglisplants.worldgen.custom;

import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import com.brugli.broglisplants.block.custom.GiantLily;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;

public class GiantLilyFeature extends Feature<CountConfiguration> {
    public GiantLilyFeature(Codec<CountConfiguration> p_66219_) {
        super(p_66219_);
    }

    public boolean place(FeaturePlaceContext<CountConfiguration> p_159956_) {
        int i = 0;
        WorldGenLevel worldgenlevel = p_159956_.level();
        BlockPos blockpos = p_159956_.origin();
        RandomSource randomsource = p_159956_.random();
        int j = worldgenlevel.getHeight(Heightmap.Types.OCEAN_FLOOR, blockpos.getX(), blockpos.getZ());
        int k = 62;
        BlockPos blockpos1 = new BlockPos(blockpos.getX(), j, blockpos.getZ());
        if (worldgenlevel.getBlockState(blockpos1).is(Blocks.WATER) && j > 52) {
            BlockState blockstate = BroglisPlantsBlocks.GIANT_LILY.get().defaultBlockState();
            BlockState blockstate1 = BroglisPlantsBlocks.STEM.get().defaultBlockState();

            for(int l = 0; l <= k; ++l) {
                if (worldgenlevel.getBlockState(blockpos1).is(Blocks.WATER) && blockstate1.canSurvive(worldgenlevel, blockpos1)) {
                    if (l == k) {
                        worldgenlevel.setBlock(blockpos1, blockstate.setValue(GiantLily.AGE, Integer.valueOf(randomsource.nextInt(4) + 20)), 2);
                        worldgenlevel.scheduleTick(blockpos1, blockstate.getBlock(), 0);
                        ++i;
                    } else {
                        worldgenlevel.setBlock(blockpos1, blockstate1, 2);
                    }
                } else if (l > 0) {
                    BlockPos blockpos2 = blockpos1.below();
                    if (blockstate.canSurvive(worldgenlevel, blockpos2) && !worldgenlevel.getBlockState(blockpos2.below()).is(BroglisPlantsBlocks.GIANT_LILY.get())) {
                        worldgenlevel.setBlock(blockpos2, blockstate.setValue(GiantLily.AGE, Integer.valueOf(randomsource.nextInt(4) + 20)), 2);
                        worldgenlevel.scheduleTick(blockpos2, blockstate.getBlock(), 0);
                        ++i;
                    }
                    break;
                }
                blockpos1 = blockpos1.above();
            }
        }

        return i > 0;
    }
}
