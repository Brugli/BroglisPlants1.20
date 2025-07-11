package com.brugli.broglisplants.worldgen;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import com.brugli.broglisplants.block.custom.Flytrap;
import com.brugli.broglisplants.block.custom.GiantLily;
import com.brugli.broglisplants.block.custom.SeedBomb;
import com.eliotlash.mclib.math.functions.utility.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MangrovePropaguleBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;
import java.util.OptionalInt;

public class BroglisPlantsConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> FLYTRAP_KEY = registerKey("flytrap");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MANDRAKE_KEY = registerKey("mandrake");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PITCHER_PLANT_KEY = registerKey("pitcher_plant");

    public static final ResourceKey<ConfiguredFeature<?, ?>> STINKY_FLOWER_KEY = registerKey("stinky_flower");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SUNDEW_KEY = registerKey("sundew");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SANDBOX_KEY = registerKey("sandbox");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_LILY_KEY = registerKey("giant_lily");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        register(context, FLYTRAP_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                        BlockStateProvider.simple(BroglisPlantsBlocks.FLYTRAP.get().defaultBlockState())), List.of(Blocks.GRASS_BLOCK)));

        register(context, MANDRAKE_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                        BlockStateProvider.simple(BroglisPlantsBlocks.MANDRAKE_ROOT.get().defaultBlockState())), List.of(Blocks.GRASS_BLOCK)));

        register(context, PITCHER_PLANT_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                        BlockStateProvider.simple(BroglisPlantsBlocks.PITCHER_PLANT.get().defaultBlockState())), List.of(Blocks.GRASS_BLOCK)));

        register(context, STINKY_FLOWER_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                        BlockStateProvider.simple(BroglisPlantsBlocks.STINKY_FLOWER.get().defaultBlockState())), List.of(Blocks.GRASS_BLOCK)));

        register(context, SUNDEW_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                        BlockStateProvider.simple(BroglisPlantsBlocks.SUNDEW.get().defaultBlockState())), List.of(Blocks.GRASS_BLOCK)));

        register(context, SANDBOX_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(BroglisPlantsBlocks.SANDBOX_LOG.get()),
                new DarkOakTrunkPlacer(6, 2, 1),
                BlockStateProvider.simple(BroglisPlantsBlocks.SANDBOX_LEAVES.get()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))
                .decorators(List.of(new AttachedToLeavesDecorator(0.14F, 1, 0, new RandomizedIntStateProvider(BlockStateProvider.simple(BroglisPlantsBlocks.SEED_BOMB.get().defaultBlockState()), SeedBomb.AGE, UniformInt.of(0, 3)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context, GIANT_LILY_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BroglisPlantsBlocks.GIANT_LILY.get()))));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(BroglisPlants.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
