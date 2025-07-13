package com.brugli.broglisplants.worldgen.tree;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.worldgen.tree.custom.SandBoxTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BroglisPlantsTrunkPlacers {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, BroglisPlants.MODID);

    public static final RegistryObject<TrunkPlacerType<SandBoxTrunkPlacer>> SANDBOX_TRUNK_PLACER =
            TRUNK_PLACER.register("sandbox_trunk_placer", () -> new TrunkPlacerType<>(SandBoxTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {
        TRUNK_PLACER.register(eventBus);
    }
}
