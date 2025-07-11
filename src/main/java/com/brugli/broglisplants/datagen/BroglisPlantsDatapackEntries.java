package com.brugli.broglisplants.datagen;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.worldgen.BroglisPlantsBiomeModifiers;
import com.brugli.broglisplants.worldgen.BroglisPlantsConfiguredFeatures;
import com.brugli.broglisplants.worldgen.BroglisPlantsPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class BroglisPlantsDatapackEntries extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()

            .add(Registries.CONFIGURED_FEATURE, BroglisPlantsConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, BroglisPlantsPlacedFeatures::bootstrap)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, BroglisPlantsBiomeModifiers::bootstrap);

    public BroglisPlantsDatapackEntries(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(BroglisPlants.MODID));
    }
}