package com.brugli.broglisplants.block.entity.client;

import com.brugli.broglisplants.BroglisPlants;
import com.brugli.broglisplants.block.entity.custom.PitcherPlantEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

import static com.brugli.broglisplants.block.custom.PitcherPlant.HUNGRY;

public class PitcherPlantModel extends GeoModel<PitcherPlantEntity> {
    @Override
    public ResourceLocation getModelResource(PitcherPlantEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "geo/pitcher_plant.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PitcherPlantEntity animatable) {
        if (animatable.getBlockState().getValue(HUNGRY)) {
            return new ResourceLocation(BroglisPlants.MODID, "textures/block/pitcher_plant.png");
        }
        else return new ResourceLocation(BroglisPlants.MODID, "textures/block/pitcher_plant_empty.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PitcherPlantEntity animatable) {
        return new ResourceLocation(BroglisPlants.MODID, "animations/pitcher_plant.animation.json");
    }
}
