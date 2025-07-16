package com.brugli.broglisplants.worldgen;

import com.brugli.broglisplants.BroglisPlants;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class BroglisPlantsBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_FLYTRAP = registerKey("add_flytrap");

    public static final ResourceKey<BiomeModifier> ADD_MANDRAKE = registerKey("add_mandrake");

    public static final ResourceKey<BiomeModifier> ADD_PITCHER_PLANT = registerKey("add_pitcher_plant");

    public static final ResourceKey<BiomeModifier> ADD_STINKY_PLANT = registerKey("add_stinky_plant");

    public static final ResourceKey<BiomeModifier> ADD_SUNDEW = registerKey("add_sundew");

    public static final ResourceKey<BiomeModifier> ADD_SANDBOX_TREE = registerKey("add_sandbox_tree");

    public static final ResourceKey<BiomeModifier> ADD_GIANT_LILY = registerKey("add_giant_lily");

    public static final ResourceKey<BiomeModifier> ADD_TOTEM = registerKey("add_totem");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeature = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_FLYTRAP, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.MANGROVE_SWAMP)),
                HolderSet.direct(placedFeature.getOrThrow(BroglisPlantsPlacedFeatures.FLYTRAP_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_MANDRAKE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.DARK_FOREST)),
                HolderSet.direct(placedFeature.getOrThrow(BroglisPlantsPlacedFeatures.MANDRAKE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_PITCHER_PLANT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.MANGROVE_SWAMP)),
                HolderSet.direct(placedFeature.getOrThrow(BroglisPlantsPlacedFeatures.PITCHER_PLANT_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_STINKY_PLANT, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.JUNGLE), biomes.getOrThrow(Biomes.SPARSE_JUNGLE)),
                HolderSet.direct(placedFeature.getOrThrow(BroglisPlantsPlacedFeatures.STINKY_FLOWER_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_SUNDEW, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.MANGROVE_SWAMP)),
                HolderSet.direct(placedFeature.getOrThrow(BroglisPlantsPlacedFeatures.SUNDEW_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_SANDBOX_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.JUNGLE), biomes.getOrThrow(Biomes.SPARSE_JUNGLE)),
                HolderSet.direct(placedFeature.getOrThrow(BroglisPlantsPlacedFeatures.SANDBOX_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_GIANT_LILY, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.JUNGLE), biomes.getOrThrow(Biomes.SPARSE_JUNGLE), biomes.getOrThrow(Biomes.SWAMP), biomes.getOrThrow(Biomes.MANGROVE_SWAMP)),
                HolderSet.direct(placedFeature.getOrThrow(BroglisPlantsPlacedFeatures.GIANT_LILY_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_TOTEM, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.JUNGLE), biomes.getOrThrow(Biomes.SPARSE_JUNGLE)),
                HolderSet.direct(placedFeature.getOrThrow(BroglisPlantsPlacedFeatures.TOTEM_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(BroglisPlants.MODID, name));
    }
}
