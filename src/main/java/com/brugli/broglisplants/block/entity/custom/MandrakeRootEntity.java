package com.brugli.broglisplants.block.entity.custom;

import com.brugli.broglisplants.block.custom.MandrakeRoot;
import com.brugli.broglisplants.block.entity.BroglisPlantsBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

public class MandrakeRootEntity extends BlockEntity implements GeoBlockEntity {

    public int timer = 47;

    private static final RawAnimation SNUGGLE = RawAnimation.begin().thenPlay("snuggle");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public MandrakeRootEntity(BlockPos pPos, BlockState pBlockState) {
        super(BroglisPlantsBlockEntities.MANDRAKE_ROOT_ENTITY.get(), pPos, pBlockState);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "snuggle_controller", 0, state -> PlayState.CONTINUE)
                .triggerableAnim("snuggle", SNUGGLE)
                .setSoundKeyframeHandler(state -> {
                    if (state.getKeyframeData().getSound().matches("sound")) {
                        this.getLevel().playLocalSound(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ(), SoundEvents.GRASS_HIT, SoundSource.BLOCKS, 0.5f, 1f, false);
                    }
                }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }

    public void snuggle() {
        if (level instanceof ServerLevel){
            BlockState pState = this.getBlockState();
            level.setBlock(this.getBlockPos(), pState.setValue(MandrakeRoot.SNUGGLING, Boolean.TRUE), 2);
            this.timer = 0;
            triggerAnim( "snuggle_controller", "snuggle");
        }
    }

    public void tick(Level serverLevel, BlockPos pPos) {
        if (this.timer < 47) {
            this.timer++;
        }
        if (this.timer == 47) {
            BlockState pState = this.getBlockState();
            level.setBlock(this.getBlockPos(), pState.setValue(MandrakeRoot.SNUGGLING, Boolean.FALSE), 2);
        }
    }
}
