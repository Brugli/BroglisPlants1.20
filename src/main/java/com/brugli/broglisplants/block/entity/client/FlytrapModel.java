package com.brugli.broglisplants.block.entity.client;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.block.entity.custom.FlytrapEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class FlytrapModel extends GeoModel<FlytrapEntity> {
    @Override
    public ResourceLocation getModelResource(FlytrapEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "geo/flytrap.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FlytrapEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "textures/block/flytrap.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FlytrapEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "animations/flytrap.animation.json");
    }
}
