package com.brugli.broglisplants.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.Stream;

public class InSmallSquarePlacement extends PlacementModifier {
    private static final net.minecraft.world.level.levelgen.placement.InSquarePlacement INSTANCE = new net.minecraft.world.level.levelgen.placement.InSquarePlacement();
    public static final Codec<net.minecraft.world.level.levelgen.placement.InSquarePlacement> CODEC = Codec.unit(() -> {
        return INSTANCE;
    });

    public static net.minecraft.world.level.levelgen.placement.InSquarePlacement spread() {
        return INSTANCE;
    }

    public Stream<BlockPos> getPositions(PlacementContext p_226348_, RandomSource p_226349_, BlockPos p_226350_) {
        int i = p_226349_.nextInt(4) + p_226350_.getX();
        int j = p_226349_.nextInt(4) + p_226350_.getZ();
        return Stream.of(new BlockPos(i, p_226350_.getY(), j));
    }

    public PlacementModifierType<?> type() {
        return PlacementModifierType.IN_SQUARE;
    }
}