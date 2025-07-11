package com.brugli.broglisplants.block.entity.custom;

import com.brugli.broglisplants.block.entity.BroglisPlantsBlockEntities;
import com.brugli.broglisplants.sound.BroglisPlantsSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

public class PitcherPlantEntity extends BlockEntity implements GeoBlockEntity {

    public int timer = 57;

    private static final RawAnimation EAT = RawAnimation.begin().thenPlay("eat");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public PitcherPlantEntity(BlockPos pPos, BlockState pBlockState) {
        super(BroglisPlantsBlockEntities.PITCHER_PLANT_ENTITY.get(), pPos, pBlockState);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "idle", 10, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "eat_controller", 0, state -> PlayState.CONTINUE)
                .triggerableAnim("spit_bone", EAT)
                .setSoundKeyframeHandler(state -> {
                    if (state.getKeyframeData().getSound().matches("pitcher_plant_burps")) {
                        this.getLevel().playLocalSound(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ(), BroglisPlantsSounds.PITCHER_PLANT_BURPS.get(), SoundSource.BLOCKS, 0.8f, 1f, false);
                    }
                })
                .setCustomInstructionKeyframeHandler(state -> {
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


    public void spitBone() {
        if (level instanceof ServerLevel){
            triggerAnim( "eat_controller", "spit_bone");
            this.timer = 0;
//          this.getLevel().playLocalSound(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ(), BroglisPlantsSounds.PITCHER_PLANT_BURPS.get(), SoundSource.BLOCKS, 1f, 1f, false);
        }
    }

    public void tick(Level serverLevel, BlockPos pPos) {
        if (timer < 57) {
            timer++;
        }
        if (timer == 56) {
            Direction direction = Direction.UP;
            double d0 = (pPos.getX() + 0.35) + 0.7D * (double) direction.getStepX();
            double d1 = (pPos.getY() + 0.35) * (double) direction.getStepY();
            double d2 = (pPos.getZ() + 0.35) + 0.7D * (double) direction.getStepZ();
            ItemEntity item = new ItemEntity(serverLevel, d0, d1, d2, new ItemStack(Items.BONE));
            serverLevel.addFreshEntity(item);
        }
    }
}
