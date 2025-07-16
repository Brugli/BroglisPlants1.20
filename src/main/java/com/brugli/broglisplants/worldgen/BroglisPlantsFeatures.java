package com.brugli.broglisplants.worldgen;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.worldgen.custom.GiantLilyFeature;
import com.brugli.broglisplants.worldgen.custom.TotemFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.CountConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BroglisPlantsFeatures {

    public static final DeferredRegister<Feature<?>> FEATURE_TYPE =
            DeferredRegister.create(Registries.FEATURE, BroglisPlants.MODID);

    public static final RegistryObject<GiantLilyFeature> GIANT_LILY_FEATURE =
            FEATURE_TYPE.register("giant_lily_feature", () -> new GiantLilyFeature(CountConfiguration.CODEC));

    public static final RegistryObject<TotemFeature> TOTEM_FEATURE =
            FEATURE_TYPE.register("totem_feature", () -> new TotemFeature(NoneFeatureConfiguration.CODEC));

    public static void register(IEventBus eventBus) {
        FEATURE_TYPE.register(eventBus);
    }
}
