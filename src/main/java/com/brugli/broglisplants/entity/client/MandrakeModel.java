package com.brugli.broglisplants.entity.client;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.entity.custom.MandrakeEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MandrakeModel extends GeoModel<MandrakeEntity> {
    @Override
    public ResourceLocation getModelResource(MandrakeEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "geo/mandrake.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MandrakeEntity animatable) {
        if (animatable.getEntityData().get(MandrakeEntity.CRYING)) {
            return new ResourceLocation(BroglisPlants.MODID, "textures/block/mandrake_root_sleeping.png");
        } else return new ResourceLocation(BroglisPlants.MODID, "textures/block/mandrake_root.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MandrakeEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "animations/mandrake.animation.json");
    }
}