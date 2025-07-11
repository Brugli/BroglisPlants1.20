package com.brugli.broglisplants.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

import javax.annotation.Nullable;

public class StinkyFlowerParticle extends TextureSheetParticle {
    protected StinkyFlowerParticle(ClientLevel pLevel, double pX, double pY, double pZ,
                                   SpriteSet spriteSet, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);

        this.friction = 0.96F;
        this.scale(1.0F);
        this.hasPhysics = false;
        this.lifetime = 30;

        this.setSpriteFromAge(spriteSet);

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ,
                                       double pXSpeed, double pYSpeed, double pZSpeed) {
            StinkyFlowerParticle stinkyFlowerParticles = new StinkyFlowerParticle(pLevel, pX, pY, pZ, this.spriteSet, pXSpeed, pYSpeed, pZSpeed);
            stinkyFlowerParticles.setAlpha(1.0F);
            stinkyFlowerParticles.setParticleSpeed(pXSpeed, pYSpeed, pZSpeed);
            stinkyFlowerParticles.setLifetime(pLevel.random.nextInt(34) + 6);
            return stinkyFlowerParticles;
        }
    }
}