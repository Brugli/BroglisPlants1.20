package com.brugli.broglisplants.block.entity.custom;

import com.brugli.broglisplants.block.custom.Flytrap;
import com.brugli.broglisplants.block.entity.BroglisPlantsBlockEntities;
import com.brugli.broglisplants.sound.BroglisPlantsSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.client.event.sound.SoundEvent;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

import java.util.List;

public class FlytrapEntity extends BlockEntity implements GeoBlockEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public FlytrapEntity(BlockPos pPos, BlockState pBlockState) {
        super(BroglisPlantsBlockEntities.FLYTRAP_ENTITY.get(), pPos, pBlockState);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate)
                .setSoundKeyframeHandler(state -> {
                    if (state.getKeyframeData().getSound().matches("snap")) {
                        this.getLevel().playLocalSound(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ(), SoundEvents.GENERIC_EAT, SoundSource.BLOCKS, 0.8f, 0.5f + getLevel().getRandom().nextFloat(), false);
                    }
                }));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if (this.getBlockState().getBlock() instanceof Flytrap && this.getBlockState().getValue(Flytrap.SNAPPING)) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("snap", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
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

    public void tick(Level serverLevel, BlockPos pPos) {
        AABB aabb = (new AABB(pPos));
        List<Player> playerList = serverLevel.getEntitiesOfClass(Player.class, aabb);
        List<Monster> monsterList = serverLevel.getEntitiesOfClass(Monster.class, aabb);
        if (playerList.isEmpty() && monsterList.isEmpty()) {
            serverLevel.setBlock(pPos, serverLevel.getBlockEntity(pPos).getBlockState().setValue(Flytrap.SNAPPING, Boolean.FALSE), 2);
        }
    }
}
