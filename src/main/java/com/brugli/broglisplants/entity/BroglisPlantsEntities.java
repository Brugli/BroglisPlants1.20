package com.brugli.broglisplants.entity;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.entity.custom.MandrakeEntity;
import com.brugli.broglisplants.entity.custom.PrimedSeedBomb;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BroglisPlantsEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BroglisPlants.MODID);

    public static final RegistryObject<EntityType<MandrakeEntity>> MANDRAKE_ENTITY =
            ENTITY_TYPES.register("mandrake_entity",
                    () -> EntityType.Builder.of(MandrakeEntity::new, MobCategory.CREATURE)
                            .sized(0.6f, 0.6f)
                            .build(new ResourceLocation(BroglisPlants.MODID, "mandrake_entity").toString()));

    public static final RegistryObject<EntityType<PrimedSeedBomb>> PRIMED_SEED_BOMB =
            ENTITY_TYPES.register("primed_seed_bomb",
                    () -> EntityType.Builder.<PrimedSeedBomb>of(PrimedSeedBomb::new, MobCategory.MISC)
                            .sized(0.5f, 0.3f)
                            .build(new ResourceLocation(BroglisPlants.MODID, "primed_seed_bomb").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
