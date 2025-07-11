package com.brugli.broglisplants.block.entity.client;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.block.entity.custom.SundewEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SundewModel extends GeoModel<SundewEntity> {
    @Override
    public ResourceLocation getModelResource(SundewEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "geo/sundew.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SundewEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "textures/block/sundew.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SundewEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "animations/sundew.animation.json");
    }
}
