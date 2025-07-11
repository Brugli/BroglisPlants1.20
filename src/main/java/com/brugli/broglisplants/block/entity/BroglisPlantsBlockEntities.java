package com.brugli.broglisplants.block.entity;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import com.brugli.broglisplants.block.entity.custom.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BroglisPlantsBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BroglisPlants.MODID);

    public static final RegistryObject<BlockEntityType<MandrakeRootEntity>> MANDRAKE_ROOT_ENTITY =
            BLOCK_ENTITIES.register("mandrake_root_entity", () ->
                    BlockEntityType.Builder.of(MandrakeRootEntity::new,
                            BroglisPlantsBlocks.MANDRAKE_ROOT.get()).build(null));

    public static final RegistryObject<BlockEntityType<FlytrapEntity>> FLYTRAP_ENTITY =
            BLOCK_ENTITIES.register("flytrap_entity", () ->
                    BlockEntityType.Builder.of(FlytrapEntity::new,
                            BroglisPlantsBlocks.FLYTRAP.get()).build(null));

    public static final RegistryObject<BlockEntityType<PitcherPlantEntity>> PITCHER_PLANT_ENTITY =
            BLOCK_ENTITIES.register("pitcher_plant_entity", () ->
                    BlockEntityType.Builder.of(PitcherPlantEntity::new,
                            BroglisPlantsBlocks.PITCHER_PLANT.get()).build(null));

    public static final RegistryObject<BlockEntityType<SundewEntity>> SUNDEW_ENTITY =
            BLOCK_ENTITIES.register("sundew_entity", () ->
                    BlockEntityType.Builder.of(SundewEntity::new,
                            BroglisPlantsBlocks.SUNDEW.get()).build(null));

    public static final RegistryObject<BlockEntityType<StinkyFlowerEntity>> STINKY_FLOWER_ENTITY =
            BLOCK_ENTITIES.register("stinky_flower_entity", () ->
                    BlockEntityType.Builder.of(StinkyFlowerEntity::new,
                            BroglisPlantsBlocks.STINKY_FLOWER.get()).build(null));

    public static final RegistryObject<BlockEntityType<SeedBombEntity>> SEED_BOMB_ENTITY =
            BLOCK_ENTITIES.register("seed_bomb_entity", () ->
                    BlockEntityType.Builder.of(SeedBombEntity::new,
                            BroglisPlantsBlocks.SEED_BOMB.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
