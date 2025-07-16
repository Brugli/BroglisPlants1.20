package com.brugli.broglisplants.entity.client;

import com.brugli.broglisplants.entity.custom.MandrakeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MandrakeRenderer extends GeoEntityRenderer<MandrakeEntity> {
    public MandrakeRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MandrakeModel());
    }
}