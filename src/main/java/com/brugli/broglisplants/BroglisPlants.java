package com.brugli.broglisplants;

import com.brugli.broglisplants.block.BroglisPlantsBlocks;
import com.brugli.broglisplants.block.custom.Thorn;
import com.brugli.broglisplants.block.entity.BroglisPlantsBlockEntities;
import com.brugli.broglisplants.block.entity.client.*;
import com.brugli.broglisplants.entity.BroglisPlantsEntities;
import com.brugli.broglisplants.entity.client.MandrakeRenderer;
import com.brugli.broglisplants.entity.client.PrimedSeedBombRenderer;
import com.brugli.broglisplants.entity.custom.MandrakeEntity;
import com.brugli.broglisplants.item.BroglisPlantsItems;
import com.brugli.broglisplants.particle.BroglisPlantsParticles;
import com.brugli.broglisplants.particle.custom.StinkyFlowerParticle;
import com.brugli.broglisplants.particle.custom.StinkyFlowerPuffParticle;
import com.brugli.broglisplants.sound.BroglisPlantsSounds;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.GenericEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BroglisPlants.MODID)
public class BroglisPlants
{
    public static final String MODID = "broglisplants";

    public BroglisPlants()
    {

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        GeckoLib.initialize();

        BroglisPlantsBlocks.register(bus);
        BroglisPlantsBlockEntities.register(bus);
        BroglisPlantsEntities.register(bus);
        BroglisPlantsItems.register(bus);
        BroglisPlantsParticles.register(bus);
        BroglisPlantsSounds.register(bus);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public class ModEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(BroglisPlantsEntities.MANDRAKE_ENTITY.get(), MandrakeEntity.setAttributes());
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(BroglisPlantsEntities.MANDRAKE_ENTITY.get(), MandrakeRenderer::new);
            EntityRenderers.register(BroglisPlantsEntities.PRIMED_SEED_BOMB.get(), PrimedSeedBombRenderer::new);

            BlockEntityRenderers.register(BroglisPlantsBlockEntities.MANDRAKE_ROOT_ENTITY.get(), MandrakeRootRenderer::new);
            BlockEntityRenderers.register(BroglisPlantsBlockEntities.FLYTRAP_ENTITY.get(), FlytrapRenderer::new);
            BlockEntityRenderers.register(BroglisPlantsBlockEntities.PITCHER_PLANT_ENTITY.get(), PitcherPlantRenderer::new);
            BlockEntityRenderers.register(BroglisPlantsBlockEntities.SUNDEW_ENTITY.get(), SundewRenderer::new);
            BlockEntityRenderers.register(BroglisPlantsBlockEntities.STINKY_FLOWER_ENTITY.get(), StinkyFlowerRenderer::new);
        }

        @SubscribeEvent
        public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(BroglisPlantsParticles.STINKY_FLOWER_PARTICLES.get(), StinkyFlowerParticle.Provider::new);
            event.registerSpriteSet(BroglisPlantsParticles.STINKY_FLOWER_PUFF_PARTICLES.get(), StinkyFlowerPuffParticle.Provider::new);
        }

        @SubscribeEvent
        public static void buildCreativeTabs(BuildCreativeModeTabContentsEvent event) {
            if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
                event.accept(BroglisPlantsItems.FLYTRAP_ITEM);
                event.accept(BroglisPlantsItems.MANDRAKE_ROOT_ITEM);
                event.accept(BroglisPlantsItems.PITCHER_PLANT_ITEM);
                event.accept(BroglisPlantsItems.STINKY_FLOWER_ITEM);
                event.accept(BroglisPlantsItems.SUNDEW_ITEM);
                event.accept(BroglisPlantsItems.GIANT_LILY_ITEM);
                event.accept(BroglisPlantsItems.SEED_BOMB_ITEM);
                event.accept(BroglisPlantsItems.SANDBOX_SAPLING_ITEM);
                event.accept(BroglisPlantsItems.SANDBOX_LOG_ITEM);
                event.accept(BroglisPlantsItems.SANDBOX_WOOD_ITEM);
                event.accept(BroglisPlantsItems.TOTEM_ITEM);
            }
            if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
                event.accept(BroglisPlantsItems.SANDBOX_LOG_ITEM);
                event.accept(BroglisPlantsItems.SANDBOX_WOOD_ITEM);
                event.accept(BroglisPlantsItems.TOTEM_ITEM);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ModForgeEvents {
        @SubscribeEvent
        public static void blockBreakEvent(final BlockEvent.BreakEvent event) {
            BlockPos pPos = event.getPos();
            ServerLevel pLevel = (ServerLevel) event.getLevel();
            BlockState pState = event.getLevel().getBlockState(pPos);

            if ((pState.is(BroglisPlantsBlocks.MANDRAKE_ROOT.get()))) {
                MandrakeEntity mandrakeEntity = BroglisPlantsEntities.MANDRAKE_ENTITY.get().create(pLevel);
                if (mandrakeEntity != null) {
                    mandrakeEntity.moveTo((double)pPos.getX() + 0.5D, (double)pPos.getY(), (double)pPos.getZ() + 0.5D, 0.0F, 0.0F);
                    pLevel.addFreshEntity(mandrakeEntity);
                }
            }
        }
    }
}