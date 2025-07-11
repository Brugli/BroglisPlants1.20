package com.brugli.broglisplants.entity.custom;

import com.brugli.broglisplants.item.BroglisPlantsItems;
import com.brugli.broglisplants.particle.BroglisPlantsParticles;
import com.brugli.broglisplants.sound.BroglisPlantsSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.ClientUtils;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class MandrakeEntity extends PathfinderMob implements GeoEntity {

    public int cooldown = 0;

    public static final EntityDataAccessor<Boolean> CRYING = SynchedEntityData.defineId(MandrakeEntity.class, EntityDataSerializers.BOOLEAN);

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public MandrakeEntity(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 6.0F, 0.75D, 1.28D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return false;
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return 0.4F;
    }

    @Override
    public boolean isAlwaysTicking() {
        return Boolean.TRUE;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        AABB aabb = (new AABB(this.blockPosition())).inflate(3).expandTowards(0.0D, 0.0D, 0.0D);
        List<Player> playerList = level().getEntitiesOfClass(Player.class, aabb);
        List<Monster> monsterList = level().getEntitiesOfClass(Monster.class, aabb);
        if (!playerList.isEmpty() || !monsterList.isEmpty()) {
            this.entityData.set(CRYING, Boolean.TRUE);
            for(Player player : playerList) {
                player.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.CONFUSION, 101, 0, true, true)));
                player.addEffect(new MobEffectInstance(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 101, 1, true, true)));
            }
            cooldown = 0;
        }
        else if (cooldown < 60 && entityData.get(CRYING)) {
            cooldown++;
        }
        if (cooldown > 59) {
            this.entityData.set(CRYING, Boolean.FALSE);
        }
     }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(CRYING, false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        if (this.entityData.get(CRYING)) {
            pCompound.putBoolean("crying", true);
        }
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.entityData.set(CRYING, pCompound.getBoolean("crying"));
    }

    @Override
    public @Nullable ItemStack getPickResult() {
        return BroglisPlantsItems.MANDRAKE_ROOT_ITEM.get().getDefaultInstance();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "move_and_idle", 0, this::movePredicate));
        controllerRegistrar.add(new AnimationController<>(this, "cry", 0, this::predicate)
                .setParticleKeyframeHandler(state -> {
                    if (state.getKeyframeData().getEffect().matches("tears")) {
                        this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.WATER.defaultBlockState()),
                                this.getX(), this.getY() + 0.4D, this.getZ(), 0.0D, 0.0D, 0.0D);
                    }})
                .setSoundKeyframeHandler(state -> {
                    MandrakeEntity mandrakeEntity = state.getAnimatable();
                    if (level().getRandom().nextFloat() < 0.2) {
                        mandrakeEntity.level().playLocalSound(mandrakeEntity.getX(), mandrakeEntity.getY(), mandrakeEntity.getZ(), BroglisPlantsSounds.MANDRAKE_CRY_1.get(), mandrakeEntity.getSoundSource(), 0.1F, 1.0F, false);
                    }
                    else if (level().getRandom().nextFloat() < 0.4) {
                        mandrakeEntity.level().playLocalSound(mandrakeEntity.getX(), mandrakeEntity.getY(), mandrakeEntity.getZ(), BroglisPlantsSounds.MANDRAKE_CRY_2.get(), mandrakeEntity.getSoundSource(), 0.1F, 1.0F, false);
                    }
                    else if (level().getRandom().nextFloat() < 0.6) {
                        mandrakeEntity.level().playLocalSound(mandrakeEntity.getX(), mandrakeEntity.getY(), mandrakeEntity.getZ(), BroglisPlantsSounds.MANDRAKE_CRY_3.get(), mandrakeEntity.getSoundSource(), 0.1F, 1.0F, false);
                    }
                    else if (level().getRandom().nextFloat() < 0.8) {
                        mandrakeEntity.level().playLocalSound(mandrakeEntity.getX(), mandrakeEntity.getY(), mandrakeEntity.getZ(), BroglisPlantsSounds.MANDRAKE_CRY_4.get(), mandrakeEntity.getSoundSource(), 0.1F, 1.0F, false);
                    }
                    else if (level().getRandom().nextFloat() < 1.0) {
                        mandrakeEntity.level().playLocalSound(mandrakeEntity.getX(), mandrakeEntity.getY(), mandrakeEntity.getZ(), BroglisPlantsSounds.MANDRAKE_CRY_5.get(), mandrakeEntity.getSoundSource(), 0.1F, 1.0F, false);
                    }
                    }));
    }

    private <T extends GeoAnimatable> PlayState movePredicate(AnimationState<T> tAnimationState) {
        if (tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("walking", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if (this.getEntityData().get(CRYING)) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("crying", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
