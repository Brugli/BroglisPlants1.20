package com.brugli.broglisplants.particle;

import com.brugli.broglisplants.BroglisPlants;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BroglisPlantsParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BroglisPlants.MODID);

    public static final RegistryObject<SimpleParticleType> STINKY_FLOWER_PARTICLES =
            PARTICLE_TYPES.register("stinky_flower_particles", () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> STINKY_FLOWER_PUFF_PARTICLES =
            PARTICLE_TYPES.register("stinky_flower_puff_particles", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
