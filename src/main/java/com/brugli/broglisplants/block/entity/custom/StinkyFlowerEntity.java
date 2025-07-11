package com.brugli.broglisplants.block.entity.custom;

import com.brugli.broglisplants.block.entity.BroglisPlantsBlockEntities;
import com.brugli.broglisplants.particle.BroglisPlantsParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

import java.util.List;

public class StinkyFlowerEntity extends BlockEntity implements GeoBlockEntity {

    private static final RawAnimation PUFF = RawAnimation.begin().thenPlay("puff");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public StinkyFlowerEntity(BlockPos pPos, BlockState pBlockState) {
        super(BroglisPlantsBlockEntities.STINKY_FLOWER_ENTITY.get(), pPos, pBlockState);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "idle", 40, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "puff_controller", 0, state -> PlayState.CONTINUE)
                .triggerableAnim("puff", PUFF).setParticleKeyframeHandler(state -> {
                    if (state.getKeyframeData().getEffect().matches("puff")) {
                        BlockPos pPos = this.getBlockPos();
                        RandomSource randomsource = this.getLevel().getRandom();
                        this.getLevel().addAlwaysVisibleParticle(BroglisPlantsParticles.STINKY_FLOWER_PUFF_PARTICLES.get(), true, (double)pPos.getX() + 0.5D + randomsource.nextDouble() / 3.0D * (double)(randomsource.nextBoolean() ? 0.2 : -0.2), (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D + randomsource.nextDouble() / 3.0D * (double)(randomsource.nextBoolean() ? 0.2 : -0.2), 0.0D, 0.03D, 0.0D);
                    }
                }));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }

    public void puff() {
        if (level instanceof ServerLevel){
            triggerAnim( "puff_controller", "puff");
        }
    }

    public void tick(Level serverLevel, BlockPos pPos) {
        AABB aabb = (new AABB(pPos)).inflate(2).expandTowards(0.0D, 0.0D, 0.0D);
        List<Player> list = serverLevel.getEntitiesOfClass(Player.class, aabb);
        if (!list.isEmpty()) {
            puff();
        }
        for(Player player : list) {
            player.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.CONFUSION, 201, 1, true, true)));
        }
    }
}
