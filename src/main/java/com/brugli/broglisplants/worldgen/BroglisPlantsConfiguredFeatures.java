package com.brugli.broglisplants.worldgen;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import com.brugli.broglisplants.block.custom.SeedBomb;
import com.brugli.broglisplants.worldgen.tree.custom.SandBoxTrunkPlacer;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;

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

    public static final ResourceKey<ConfiguredFeature<?, ?>> TOTEM_KEY = registerKey("totem");


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
                new SandBoxTrunkPlacer(6, 2, 1),
                BlockStateProvider.simple(BroglisPlantsBlocks.SANDBOX_LEAVES.get()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))
                .decorators(List.of(new AttachedToLeavesDecorator(0.14F, 1, 0, new RandomizedIntStateProvider(BlockStateProvider.simple(BroglisPlantsBlocks.SEED_BOMB.get().defaultBlockState()), SeedBomb.AGE, UniformInt.of(0, 3)), 2, List.of(Direction.DOWN)))).ignoreVines().build());

        register(context, GIANT_LILY_KEY, Feature.RANDOM_PATCH,
                FeatureUtils.simplePatchConfiguration(BroglisPlantsFeatures.GIANT_LILY_FEATURE.get(), new CountConfiguration(10)));

        register(context, TOTEM_KEY, BroglisPlantsFeatures.TOTEM_FEATURE.get());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(BroglisPlants.MODID, name));
    }

    public static void register(BootstapContext<ConfiguredFeature<?, ?>> pContext, ResourceKey<ConfiguredFeature<?, ?>> pKey, Feature<NoneFeatureConfiguration> pFeature) {
        register(pContext, pKey, pFeature, FeatureConfiguration.NONE);
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> pContext, ResourceKey<ConfiguredFeature<?, ?>> pKey, F pFeature, FC pConfig) {
        pContext.register(pKey, new ConfiguredFeature<>(pFeature, pConfig));
    }
}
