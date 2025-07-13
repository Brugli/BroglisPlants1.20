package com.brugli.broglisplants.worldgen.tree.custom;

import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import com.brugli.broglisplants.block.custom.Thorn;
import com.brugli.broglisplants.worldgen.tree.BroglisPlantsTrunkPlacers;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class SandBoxTrunkPlacer extends TrunkPlacer {
    public static final Codec<SandBoxTrunkPlacer> CODEC = RecordCodecBuilder.create((p_70161_) -> {
        return trunkPlacerParts(p_70161_).apply(p_70161_, SandBoxTrunkPlacer::new);
    });

    public SandBoxTrunkPlacer(int p_70148_, int p_70149_, int p_70150_) {
        super(p_70148_, p_70149_, p_70150_);
    }

    protected TrunkPlacerType<?> type() {
        return BroglisPlantsTrunkPlacers.SANDBOX_TRUNK_PLACER.get();
    }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, int p_226119_, BlockPos pPos, TreeConfiguration pConfig) {
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(pRandom);
        int i = p_226119_ - pRandom.nextInt(4) - 1;
        int j = 3 - pRandom.nextInt(3);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int k = pPos.getX();
        int l = pPos.getZ();
        OptionalInt optionalint = OptionalInt.empty();

        for(int i1 = 0; i1 < p_226119_; ++i1) {
            int j1 = pPos.getY() + i1;
            if (i1 >= i && j > 0) {
                k += direction.getStepX();
                l += direction.getStepZ();
                --j;
            }

            if (pLevel.isStateAtPosition(blockpos$mutableblockpos.set(k, j1, l), BlockBehaviour.BlockStateBase::canBeReplaced)) {
                pBlockSetter.accept(blockpos$mutableblockpos.set(k, j1, l),
                        ((BlockState) Function.identity().apply(BroglisPlantsBlocks.SANDBOX_LOG.get().defaultBlockState())));
                if (i1 < p_226119_ - 1) {
                    if (pLevel.isStateAtPosition(blockpos$mutableblockpos.set(k, j1, l).relative(Direction.NORTH, 1), BlockBehaviour.BlockStateBase::isAir)) {
                        pBlockSetter.accept(blockpos$mutableblockpos.set(k, j1, l).relative(Direction.NORTH, 1), ((BlockState)
                                Function.identity().apply(BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.NORTH))));
                    }
                    if (pLevel.isStateAtPosition(blockpos$mutableblockpos.set(k, j1, l).relative(Direction.NORTH, -1), BlockBehaviour.BlockStateBase::isAir)) {
                        pBlockSetter.accept(blockpos$mutableblockpos.set(k, j1, l).relative(Direction.NORTH, -1), ((BlockState)
                                Function.identity().apply(BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.SOUTH))));
                    }
                    if (pLevel.isStateAtPosition(blockpos$mutableblockpos.set(k, j1, l).relative(Direction.EAST, 1), BlockBehaviour.BlockStateBase::isAir)) {
                        pBlockSetter.accept(blockpos$mutableblockpos.set(k, j1, l).relative(Direction.EAST, 1), ((BlockState)
                                Function.identity().apply(BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.EAST))));
                    }
                    if (pLevel.isStateAtPosition(blockpos$mutableblockpos.set(k, j1, l).relative(Direction.EAST, -1), BlockBehaviour.BlockStateBase::isAir)) {
                        pBlockSetter.accept(blockpos$mutableblockpos.set(k, j1, l).relative(Direction.EAST, -1), ((BlockState)
                                Function.identity().apply(BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.WEST))));
                    }
                }
                optionalint = OptionalInt.of(j1 + 1);
            }
        }

        if (optionalint.isPresent()) {
            list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(k, optionalint.getAsInt(), l), 1, false));
        }

        k = pPos.getX();
        l = pPos.getZ();
        Direction direction1 = Direction.Plane.HORIZONTAL.getRandomDirection(pRandom);
        if (direction1 != direction) {
            int j2 = i - pRandom.nextInt(2) - 1;
            int k1 = 1 + pRandom.nextInt(3);
            optionalint = OptionalInt.empty();

            for(int l1 = j2; l1 < p_226119_ && k1 > 0; --k1) {
                if (l1 >= 1) {
                    int i2 = pPos.getY() + l1;
                    k += direction1.getStepX();
                    l += direction1.getStepZ();

                    if (pLevel.isStateAtPosition(blockpos$mutableblockpos.set(k, i2, l), BlockBehaviour.BlockStateBase::canBeReplaced)) {
                        pBlockSetter.accept(blockpos$mutableblockpos.set(k, i2, l),
                                ((BlockState) Function.identity().apply(BroglisPlantsBlocks.SANDBOX_LOG.get().defaultBlockState())));
                        if (l1 < p_226119_ - 1) {
                            if (pLevel.isStateAtPosition(blockpos$mutableblockpos.set(k, i2, l).relative(Direction.NORTH, 1), BlockBehaviour.BlockStateBase::isAir)) {
                                pBlockSetter.accept(blockpos$mutableblockpos.set(k, i2, l).relative(Direction.NORTH, 1), ((BlockState)
                                        Function.identity().apply(BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.NORTH))));
                            }
                            if (pLevel.isStateAtPosition(blockpos$mutableblockpos.set(k, i2, l).relative(Direction.NORTH, -1), BlockBehaviour.BlockStateBase::isAir)) {
                                pBlockSetter.accept(blockpos$mutableblockpos.set(k, i2, l).relative(Direction.NORTH, -1), ((BlockState)
                                        Function.identity().apply(BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.SOUTH))));
                            }
                            if (pLevel.isStateAtPosition(blockpos$mutableblockpos.set(k, i2, l).relative(Direction.EAST, 1), BlockBehaviour.BlockStateBase::isAir)) {
                                pBlockSetter.accept(blockpos$mutableblockpos.set(k, i2, l).relative(Direction.EAST, 1), ((BlockState)
                                        Function.identity().apply(BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.EAST))));
                            }
                            if (pLevel.isStateAtPosition(blockpos$mutableblockpos.set(k, i2, l).relative(Direction.EAST, -1), BlockBehaviour.BlockStateBase::isAir)) {
                                pBlockSetter.accept(blockpos$mutableblockpos.set(k, i2, l).relative(Direction.EAST, -1), ((BlockState)
                                        Function.identity().apply(BroglisPlantsBlocks.THORN.get().defaultBlockState().setValue(Thorn.FACING, Direction.WEST))));
                            }
                        }
                        optionalint = OptionalInt.of(i2 + 1);
                    }
                }

                ++l1;
            }

            if (optionalint.isPresent()) {
                list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(k, optionalint.getAsInt(), l), 0, false));
            }
        }

        return list;
    }
}