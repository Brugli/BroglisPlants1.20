package com.brugli.broglisplants.worldgen;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

public class BroglisPlantsPlacedFeatures {

    public static final ResourceKey<PlacedFeature> FLYTRAP_PLACED_KEY = registerKey("flytrap_placed");

    public static final ResourceKey<PlacedFeature> MANDRAKE_PLACED_KEY = registerKey("mandrake_placed");

    public static final ResourceKey<PlacedFeature> PITCHER_PLANT_PLACED_KEY = registerKey("pitcher_plant_placed");

    public static final ResourceKey<PlacedFeature> STINKY_FLOWER_PLACED_KEY = registerKey("stinky_flower_placed");

    public static final ResourceKey<PlacedFeature> SUNDEW_PLACED_KEY = registerKey("sundew_placed");

    public static final ResourceKey<PlacedFeature> SANDBOX_PLACED_KEY = registerKey("sandbox_placed");

    public static final ResourceKey<PlacedFeature> GIANT_LILY_PLACED_KEY = registerKey("giant_lily_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, FLYTRAP_PLACED_KEY, configuredFeatures.getOrThrow(BroglisPlantsConfiguredFeatures.FLYTRAP_KEY),
                List.of(RarityFilter.onAverageOnceEvery(2), CountPlacement.of(3), InSmallSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome()));

        register(context, MANDRAKE_PLACED_KEY, configuredFeatures.getOrThrow(BroglisPlantsConfiguredFeatures.MANDRAKE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(4), CountPlacement.of(2), InSmallSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome()));

        register(context, PITCHER_PLANT_PLACED_KEY, configuredFeatures.getOrThrow(BroglisPlantsConfiguredFeatures.PITCHER_PLANT_KEY),
                List.of(RarityFilter.onAverageOnceEvery(2), CountPlacement.of(3), InSmallSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome()));

        register(context, STINKY_FLOWER_PLACED_KEY, configuredFeatures.getOrThrow(BroglisPlantsConfiguredFeatures.STINKY_FLOWER_KEY),
                List.of(RarityFilter.onAverageOnceEvery(4), CountPlacement.of(2), InSmallSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome()));

        register(context, SUNDEW_PLACED_KEY, configuredFeatures.getOrThrow(BroglisPlantsConfiguredFeatures.SUNDEW_KEY),
                List.of(RarityFilter.onAverageOnceEvery(2), CountPlacement.of(3), InSmallSquarePlacement.spread(), PlacementUtils.RANGE_4_4, BiomeFilter.biome()));

        register(context, SANDBOX_PLACED_KEY, configuredFeatures.getOrThrow(BroglisPlantsConfiguredFeatures.SANDBOX_KEY),
                treePlacement(PlacementUtils.countExtra(0, 0.1F, 1), BroglisPlantsBlocks.SANDBOX_SAPLING.get()));

        register(context, GIANT_LILY_PLACED_KEY, configuredFeatures.getOrThrow(BroglisPlantsConfiguredFeatures.GIANT_LILY_KEY),
                List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(BroglisPlants.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
