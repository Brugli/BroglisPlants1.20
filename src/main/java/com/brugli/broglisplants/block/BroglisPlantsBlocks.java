package com.brugli.broglisplants.block;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.block.custom.*;
import com.brugli.broglisplants.worldgen.tree.custom.SandboxTreeGrower;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BroglisPlantsBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, BroglisPlants.MODID);

    public static final RegistryObject<Block> MANDRAKE_ROOT = BLOCKS.register("mandrake_root",
            () -> new MandrakeRoot(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> FLYTRAP = BLOCKS.register("flytrap",
            () -> new Flytrap(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> GIANT_LILY = BLOCKS.register("giant_lily",
            () -> new GiantLily(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .sound(SoundType.MUDDY_MANGROVE_ROOTS)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> GIANT_LILY_CORNER = BLOCKS.register("giant_lily_corner",
            () -> new GiantLilyCorner(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .sound(SoundType.MUDDY_MANGROVE_ROOTS)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> GIANT_LILY_EDGE = BLOCKS.register("giant_lily_edge",
            () -> new GiantLilyEdge(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .instabreak()
                    .sound(SoundType.MUDDY_MANGROVE_ROOTS)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> PITCHER_PLANT = BLOCKS.register("pitcher_plant",
            () -> new PitcherPlant(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> SUNDEW = BLOCKS.register("sundew",
            () -> new Sundew(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .speedFactor(0.5f)
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> SEED_BOMB = BLOCKS.register("seed_bomb",
            () -> new SeedBomb(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.LODESTONE)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> STINKY_FLOWER = BLOCKS.register("stinky_flower",
            () -> new StinkyFlower(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> STEM = BLOCKS.register("stem",
            () -> new Stem(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.MUDDY_MANGROVE_ROOTS)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> THORN = BLOCKS.register("thorn",
            () -> new Thorn(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .replaceable()
                    .sound(SoundType.LODESTONE)
                    .pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<Block> SANDBOX_SAPLING = BLOCKS.register("sandbox_sapling",
            () -> new SaplingBlock(new SandboxTreeGrower(), BlockBehaviour.Properties.copy(Blocks.DARK_OAK_SAPLING)));

    public static final RegistryObject<Block> SANDBOX_LOG = BLOCKS.register("sandbox_log",
            () -> new SandboxLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> SANDBOX_WOOD = BLOCKS.register("sandbox_wood",
            () -> new SandboxLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static final RegistryObject<Block> SANDBOX_LEAVES = BLOCKS.register("sandbox_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    public static final RegistryObject<Block> TOTEM = BLOCKS.register("totem",
            () -> new Totem(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
