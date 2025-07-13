package com.brugli.broglisplants.worldgen.tree;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.worldgen.tree.custom.SandboxFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class BroglisPlantsFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, BroglisPlants.MODID);

//    public static final RegistryObject<FoliagePlacerType<SandboxFoliagePlacer>> SANDBOX_FOLIAGE_PLACER =
//            FOLIAGE_PLACERS.register("sandbox_foliage_placer", () -> new FoliagePlacerType<>(SandboxFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACERS.register(eventBus);
    }
}
