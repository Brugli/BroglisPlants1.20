package com.brugli.broglisplants.block.entity.client;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.block.entity.custom.StinkyFlowerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StinkyFlowerModel extends GeoModel<StinkyFlowerEntity> {
    @Override
    public ResourceLocation getModelResource(StinkyFlowerEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "geo/stinky_flower.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StinkyFlowerEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "textures/block/stinky_flower.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StinkyFlowerEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "animations/stinky_flower.animation.json");
    }
}
