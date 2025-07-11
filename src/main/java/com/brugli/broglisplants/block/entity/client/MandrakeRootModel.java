package com.brugli.broglisplants.block.entity.client;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.block.entity.custom.MandrakeRootEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class MandrakeRootModel extends GeoModel<MandrakeRootEntity> {
    @Override
    public ResourceLocation getModelResource(MandrakeRootEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "geo/mandrake_root.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MandrakeRootEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "textures/block/mandrake_root_sleeping.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MandrakeRootEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "animations/mandrake_root.animation.json");
    }
}
