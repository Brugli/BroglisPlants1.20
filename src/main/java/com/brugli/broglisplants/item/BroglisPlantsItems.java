package com.brugli.broglisplants.item;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import com.brugli.broglisplants.item.custom.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BroglisPlantsItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BroglisPlants.MODID);

    public static final RegistryObject<Item> MANDRAKE_ROOT_ITEM = ITEMS.register("mandrake_root",
            () -> new BlockItem(BroglisPlantsBlocks.MANDRAKE_ROOT.get(), new Item.Properties()));

    public static final RegistryObject<Item> FLYTRAP_ITEM = ITEMS.register("flytrap",
            () -> new BlockItem(BroglisPlantsBlocks.FLYTRAP.get(), new Item.Properties()));

    public static final RegistryObject<Item> GIANT_LILY_ITEM = ITEMS.register("giant_lily",
            () -> new BlockItem(BroglisPlantsBlocks.GIANT_LILY.get(), new Item.Properties()));

    public static final RegistryObject<Item> PITCHER_PLANT_ITEM = ITEMS.register("pitcher_plant",
            () -> new BlockItem(BroglisPlantsBlocks.PITCHER_PLANT.get(), new Item.Properties()));

    public static final RegistryObject<Item> SEED_BOMB_ITEM = ITEMS.register("seed_bomb",
            () -> new BlockItem(BroglisPlantsBlocks.SEED_BOMB.get(), new Item.Properties()));

    public static final RegistryObject<Item> SUNDEW_ITEM = ITEMS.register("sundew",
            () -> new BlockItem(BroglisPlantsBlocks.SUNDEW.get(), new Item.Properties()));

    public static final RegistryObject<Item> STINKY_FLOWER_ITEM = ITEMS.register("stinky_flower",
            () -> new BlockItem(BroglisPlantsBlocks.STINKY_FLOWER.get(), new Item.Properties()));

    public static final RegistryObject<Item> SANDBOX_SAPLING_ITEM = ITEMS.register("sandbox_sapling",
            () -> new FuelItem(BroglisPlantsBlocks.SANDBOX_SAPLING.get(), new Item.Properties(), 100));

    public static final RegistryObject<Item> SANDBOX_LOG_ITEM = ITEMS.register("sandbox_log",
            () -> new FuelItem(BroglisPlantsBlocks.SANDBOX_LOG.get(), new Item.Properties(), 300));

    public static final RegistryObject<Item> SANDBOX_WOOD_ITEM = ITEMS.register("sandbox_wood",
            () -> new FuelItem(BroglisPlantsBlocks.SANDBOX_WOOD.get(), new Item.Properties(), 300));

    public static final RegistryObject<Item> SANDBOX_LEAVES_ITEM = ITEMS.register("sandbox_leaves",
            () -> new BlockItem(BroglisPlantsBlocks.SANDBOX_LEAVES.get(), new Item.Properties()));

    public static final RegistryObject<Item> TOTEM_ITEM = ITEMS.register("totem",
            () -> new FuelItem(BroglisPlantsBlocks.TOTEM.get(), new Item.Properties(), 300));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
